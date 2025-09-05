package au.com.vanguard.demo.weatherapi.service.key;

import au.com.vanguard.demo.weatherapi.exception.InvalidAPIKeyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class SimpleAPIKeyValidator implements APIKeyValidator {

    private final String apiKey;

    public SimpleAPIKeyValidator(@Value("${server.api.key}") String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public void validate(String apiKey) {
        if (isNull(apiKey) || !this.apiKey.equals(apiKey)) {
            throw new InvalidAPIKeyException();
        }
    }
}
