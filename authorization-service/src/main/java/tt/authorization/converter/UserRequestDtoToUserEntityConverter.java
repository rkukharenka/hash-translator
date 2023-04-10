package tt.authorization.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tt.authorization.dto.UserRequestDto;
import tt.authorization.entity.UserEntity;

@Component
public class UserRequestDtoToUserEntityConverter implements Converter<UserRequestDto, UserEntity> {

    @Override
    public UserEntity convert(UserRequestDto source) {
        return new UserEntity(source.getEmail(), source.getPassword());
    }

}
