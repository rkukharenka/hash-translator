package tt.hashtranslator.converter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import tt.hashtranslator.dto.ApplicationRequestDto;
import tt.hashtranslator.entity.Application;
import tt.hashtranslator.entity.HashResult;
import tt.hashtranslator.enums.Status;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ApplicationRequestDtoToApplicationConverter implements Converter<ApplicationRequestDto, Application> {

    @Override
    public Application convert(ApplicationRequestDto source) {
        return new Application()
                .setDateTime(LocalDateTime.now())
                .setApplicantEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .setHashes(mapHashToHashResult(source));
    }

    private static Set<HashResult> mapHashToHashResult(ApplicationRequestDto source) {
        return source.getHashes().stream()
                .map(hash -> new HashResult(hash, StringUtils.EMPTY, Status.IN_PROGRESS))
                .collect(Collectors.toSet());
    }

}
