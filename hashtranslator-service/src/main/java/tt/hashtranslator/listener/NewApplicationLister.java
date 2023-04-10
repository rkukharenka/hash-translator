package tt.hashtranslator.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import tt.hashtranslator.event.NewApplicationEvent;
import tt.hashtranslator.service.ApplicationService;

@Slf4j
@Component
@RequiredArgsConstructor
public class NewApplicationLister {

    private final ApplicationService applicationService;

    @Async("threadPoolTaskExecutor")
    @EventListener
    public void handle(NewApplicationEvent event) {
        log.info(Thread.currentThread().getName() + " " + this.getClass().getName());
        applicationService.decryptHashForApplication(event.getApplication());
    }
}
