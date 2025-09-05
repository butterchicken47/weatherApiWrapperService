package au.com.vanguard.demo.weatherapi.client;

import au.com.vanguard.demo.weatherapi.exception.*;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

/**
 * Maps Open Weather Service response codes to Exceptions.
 */
@Component
public class ClientErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {

        return switch (response.status()) {
            case 400 -> new InvalidRequestException();
            case 404 -> new CityNotFoundException();
            case 401 -> new InvalidAPIKeyException();
            case 429 -> new TooManyRequestsException();
            default ->
                    new ClientException("Failed to call open weather API " + response.status() + " " + response.reason());
        };
    }
}
