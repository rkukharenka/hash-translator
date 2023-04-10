package tt.hashtranslator.service;

import tt.hashtranslator.entity.Application;

public interface ApplicationService {

    Application getApplicationById(String id);

    Application postApplication(Application application);

    void decryptHashForApplication(Application application);

}
