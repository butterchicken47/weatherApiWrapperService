package au.com.vanguard.demo.weatherapi.service;

import au.com.vanguard.demo.weatherapi.model.WeatherData;
import au.com.vanguard.demo.weatherapi.model.WeatherRequest;
import au.com.vanguard.demo.weatherapi.service.strategy.CRUDStrategy;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class WeatherDataService {

    private final CRUDStrategy crudStrategy;

    public WeatherDataService(CRUDStrategy crudStrategy) {
        this.crudStrategy = crudStrategy;
    }

    public WeatherData getWeatherData(@NotNull @Valid WeatherRequest request) {
        return crudStrategy.getWeatherData(request);
    }
}
