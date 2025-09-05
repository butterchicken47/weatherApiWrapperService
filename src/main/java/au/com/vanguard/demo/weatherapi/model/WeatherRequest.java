package au.com.vanguard.demo.weatherapi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.util.StringUtils;

public record WeatherRequest(@NotNull @NotBlank String city, String country) {

    public String toArguments() {
        var buffer = new StringBuilder();

        buffer.append(city);

        if (StringUtils.hasText(country)) {
            buffer.append(",");
            buffer.append(country);
        }

        return buffer.toString();
    }
}
