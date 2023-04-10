package tt.authorization.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tt.authorization.converter.UserEntityToUserResponseDtoConverter;
import tt.authorization.converter.UserRequestDtoToUserEntityConverter;
import tt.authorization.dto.UserRequestDto;
import tt.authorization.dto.UserResponseDto;
import tt.authorization.entity.UserEntity;
import tt.authorization.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserRequestDtoToUserEntityConverter userDtoToEntity;
    private final UserEntityToUserResponseDtoConverter userEntityToDto;

    @GetMapping("/{id}")
    public UserResponseDto getUserId(@PathVariable Long id) {
        UserEntity userById = userService.getUserById(id);

        return userEntityToDto.convert(userById);
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(userEntityToDto::convert)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto) {
        UserEntity newUser = userDtoToEntity.convert(userRequestDto);

        return userEntityToDto.convert(userService.saveUser(newUser));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Long id) {
        userService.removeUser(id);
    }

}
