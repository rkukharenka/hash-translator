package tt.hashtranslator.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import tt.hashtranslator.entity.Application;
import tt.hashtranslator.entity.HashResult;
import tt.hashtranslator.enums.Status;
import tt.hashtranslator.event.NewApplicationEvent;
import tt.hashtranslator.exception.ApplicationNotFoundException;
import tt.hashtranslator.repository.ApplicationRepository;
import tt.hashtranslator.service.ApplicationService;
import tt.hashtranslator.service.Md5DecryptService;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final Md5DecryptService md5DecryptService;
    private final ApplicationEventPublisher eventPublisher;
    private final ApplicationRepository applicationRepository;

    @Override
    public Application getApplicationById(String id) {
        return applicationRepository.findById(id).orElseThrow(() -> new ApplicationNotFoundException(id));
    }

    @Override
    public Application postApplication(Application application) {
        Application newApplication = applicationRepository.save(application);
        log.info(Thread.currentThread().getName() + " " + this.getClass().getName());
        eventPublisher.publishEvent(new NewApplicationEvent(this, application));
        return newApplication;
    }

    @Override
    public void decryptHashForApplication(Application application) {
        Set<HashResult> decryptHashes = application.getHashes().parallelStream()
                .map(this::updateHashResult)
                .collect(Collectors.toSet());

        applicationRepository.save(application.setHashes(decryptHashes));
    }

    private HashResult updateHashResult(HashResult hashResult) {
        String decryptHash = md5DecryptService.getDecryptMd5Hash(hashResult.getHash());

        if (StringUtils.isBlank(decryptHash) || decryptHash.contains("ERROR CODE")) {
            log.info(decryptHash);
            return hashResult.setStatus(Status.PROCESSED);
        }

        return hashResult
                .setResult(decryptHash)
                .setStatus(Status.DECRYPTED);
    }

}
