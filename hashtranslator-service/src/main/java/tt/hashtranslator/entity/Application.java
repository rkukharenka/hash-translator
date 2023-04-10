package tt.hashtranslator.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@Document(collection = "applications")
public class Application {

    @Id
    private String id;

    private String applicantEmail;

    private LocalDateTime dateTime;

    private Set<HashResult> hashes;

}
