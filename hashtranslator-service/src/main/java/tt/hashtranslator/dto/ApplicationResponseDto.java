package tt.hashtranslator.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import tt.hashtranslator.entity.HashResult;

import java.util.Set;

@Data
@Accessors(chain = true)
public class ApplicationResponseDto {

    private String id;
    private String dateTime;
    private String applicantEmail;
    private Set<HashResult> hashes;

}
