package tt.hashtranslator.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tt.hashtranslator.dto.ApplicationResponseDto;
import tt.hashtranslator.entity.Application;

import java.time.format.DateTimeFormatter;

@Component
public class ApplicationToApplicationResponseDtoConverter implements Converter<Application, ApplicationResponseDto> {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Override
    public ApplicationResponseDto convert(Application source) {
        return new ApplicationResponseDto()
                .setId(source.getId())
                .setDateTime(source.getDateTime().format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)))
                .setApplicantEmail(source.getApplicantEmail())
                .setHashes(source.getHashes());
    }

}
