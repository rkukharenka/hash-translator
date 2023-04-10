package tt.authorization.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tt.authorization.dto.UserResponseDto;
import tt.authorization.entity.UserEntity;
import tt.authorization.enums.Role;

@Component
public class UserEntityToUserResponseDtoConverter implements Converter<UserEntity, UserResponseDto> {

    @Override
    public UserResponseDto convert(UserEntity source) {
        return new UserResponseDto(source.getId(), source.getEmail(), Role.USER.getName());
    }
}
