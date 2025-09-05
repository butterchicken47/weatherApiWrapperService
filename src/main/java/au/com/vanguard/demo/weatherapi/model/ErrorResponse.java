package au.com.vanguard.demo.weatherapi.model;

import java.time.Instant;

public record ErrorResponse(int code, String message, Instant timestamp) {

}
