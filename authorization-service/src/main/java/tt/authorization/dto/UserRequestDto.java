package tt.authorization.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserRequestDto {

    @Email
    private String email;
    @NotBlank
    private String password;

}
