package tt.hashtranslator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tt.hashtranslator.converter.ApplicationRequestDtoToApplicationConverter;
import tt.hashtranslator.converter.ApplicationToApplicationResponseDtoConverter;
import tt.hashtranslator.dto.ApplicationRequestDto;
import tt.hashtranslator.dto.ApplicationResponseDto;
import tt.hashtranslator.entity.Application;
import tt.hashtranslator.service.ApplicationService;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final ApplicationRequestDtoToApplicationConverter dtoToApplicationConverter;
    private final ApplicationToApplicationResponseDtoConverter applicationResponseDtoConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String postApplication(@Valid @RequestBody ApplicationRequestDto requestDto) {
        Application newApplication = dtoToApplicationConverter.convert(requestDto);
        return applicationService.postApplication(newApplication).getId();
    }

    @GetMapping("/{id}")
    public ApplicationResponseDto getApplicationById(@PathVariable String id) {
        return applicationResponseDtoConverter.convert(applicationService.getApplicationById(id));
    }

}
