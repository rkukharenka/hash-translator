package tt.hashtranslator.event;


import org.springframework.context.ApplicationEvent;
import tt.hashtranslator.entity.Application;

public class NewApplicationEvent extends ApplicationEvent {

    private final Application application;

    public NewApplicationEvent(Object source, Application application) {
        super(source);
        this.application = application;
    }

    public Application getApplication() {
        return application;
    }

}
