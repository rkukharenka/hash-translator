package tt.hashtranslator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:md5decrypt-api-errors.properties")
public class Md5DecryptService {

    private static final String MD_5 = "md5";

    private final RestTemplate restTemplate;

    @Value("${3rd.api.url}")
    private String api;
    @Value("${3rd.party.email}")
    private String email;
    @Value("${3rd.party.code}")
    private String code;

    public String getDecryptMd5Hash(String hash) {
        URI uri = createUri(hash);

        return restTemplate.getForObject(uri, String.class);
    }

    private URI createUri(String hash) {
        return UriComponentsBuilder.fromUriString(api)
                .queryParam("hash", hash)
                .queryParam("hash_type", MD_5)
                .queryParam("email", email)
                .queryParam("code", code)
                .build()
                .toUri();
    }

}
