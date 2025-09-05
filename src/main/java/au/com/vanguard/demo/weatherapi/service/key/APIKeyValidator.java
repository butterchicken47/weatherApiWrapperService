package au.com.vanguard.demo.weatherapi.service.key;

public interface APIKeyValidator {

    String HEADER_NAME = "api-key";

    void validate(String apiKey);
}
