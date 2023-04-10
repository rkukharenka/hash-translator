package tt.authorization.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tt.authorization.entity.UserEntity;
import tt.authorization.enums.Role;
import tt.authorization.exception.UserAlreadyExistsException;
import tt.authorization.exception.UserNotFoundException;
import tt.authorization.repository.UserRepository;
import tt.authorization.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> getAllUsers() {
        return userRepository.findAllByUserRole();
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    @Transactional
    public UserEntity saveUser(UserEntity userEntity) {
        checkUniqueUser(userEntity);

        return userRepository.save(userEntity.setPassword(encodePassword(userEntity.getPassword())));
    }

    private void checkUniqueUser(UserEntity userEntity) {
        if (userRepository.existsByEmail(userEntity.getEmail())) {
            throw new UserAlreadyExistsException(userEntity.getEmail());
        }
    }

    private String encodePassword(String passord) {
        return passwordEncoder.encode(passord);
    }

    @Override
    @Transactional
    public void removeUser(Long id) {
        checkUserBeforeDelete(id);

        userRepository.deleteById(id);
    }

    private void checkUserBeforeDelete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
    }

    @Override
    @Transactional
    public void createAdminUserForApplication(String email, String password) {

        Optional<UserEntity> adminUser = userRepository.findByAdminRole();

        if (adminUser.isPresent()) {
            UserEntity admin = adminUser.get();
            if (isNewEmailOrPasswordForAdmin(email, password, admin)) {
                updateEmailAndPasswordForAdmin(email, password, admin);
            }
        } else {
            createAdminUser(email, password);
        }

    }

    private void createAdminUser(String email, String password) {
        UserEntity admin = new UserEntity()
                .setEmail(email)
                .setPassword(encodePassword(password))
                .setRole(Role.ADMIN);
        userRepository.save(admin);
    }

    private void updateEmailAndPasswordForAdmin(String email, String password, UserEntity admin) {
        admin.setEmail(email).setPassword(encodePassword(password));
        userRepository.save(admin);
    }

    private boolean isNewEmailOrPasswordForAdmin(String email, String password, UserEntity admin) {
        boolean isNewEmail = !StringUtils.equals(admin.getEmail(), email);
        boolean isNewPassword = !passwordEncoder.matches(password, admin.getPassword());
        return isNewEmail || isNewPassword;
    }

}
