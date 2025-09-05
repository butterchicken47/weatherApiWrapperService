package au.com.vanguard.demo.weatherapi.exception;

public class ClientException extends RuntimeException {
    public ClientException(String message) {
        super(message);
    }
}
