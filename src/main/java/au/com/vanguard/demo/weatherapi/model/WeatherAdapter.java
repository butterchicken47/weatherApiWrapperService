package au.com.vanguard.demo.weatherapi.model;

import org.springframework.stereotype.Component;

@Component
public class WeatherAdapter {
    public WeatherRequest toRequest(String city, String country) {
        return new WeatherRequest(city, country);
    }

    public WeatherResponse toResponse(WeatherData weatherData) {
        return new WeatherResponse(weatherData.getDescription());
    }
}

