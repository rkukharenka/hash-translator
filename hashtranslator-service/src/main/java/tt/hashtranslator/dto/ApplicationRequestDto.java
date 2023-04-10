package tt.hashtranslator.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class ApplicationRequestDto {

    @NotEmpty
    Set<String> hashes;

}
