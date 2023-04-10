package tt.authorization.config;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tt.authorization.exception.EnvironmentVariablesNotProvidedException;
import tt.authorization.service.UserService;

@Configuration
@RequiredArgsConstructor
public class AdminInitializer {

    private static final String ERROR_MSG = "Admin or Password not set in environment variables";

    private final UserService userService;

    @Value("${app.admin.email}")
    private String adminEmail;
    @Value("${app.admin.password:}")
    private String adminPassword;

    @Bean
    public CommandLineRunner initAdmin() {
        checkAdminCredentials();
        return args -> userService.createAdminUserForApplication(adminEmail, adminPassword);
    }

    private void checkAdminCredentials() {
        if (StringUtils.isAnyBlank(adminEmail, adminPassword)) {
            throw new EnvironmentVariablesNotProvidedException(ERROR_MSG);
        }
    }

}
