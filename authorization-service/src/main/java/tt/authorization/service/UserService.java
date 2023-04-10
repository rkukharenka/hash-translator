package tt.authorization.service;

import tt.authorization.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity getUserById(Long id);

    List<UserEntity> getAllUsers();

    UserEntity getUserByEmail(String email);

    UserEntity saveUser(UserEntity userEntity);

    void removeUser(Long id);

    void createAdminUserForApplication(String email, String password);

}
