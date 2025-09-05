package au.com.vanguard.demo.weatherapi.controller;

import au.com.vanguard.demo.weatherapi.model.WeatherAdapter;
import au.com.vanguard.demo.weatherapi.model.WeatherResponse;
import au.com.vanguard.demo.weatherapi.service.WeatherDataService;
import org.springframework.web.bind.annotation.*;

/**
 * Our single entry point into the service.
 * Provides a single GET endpoint for obtaining weather data by city, or city and country.
 */
@RestController
@RequestMapping("/api/1.0")
public class WeatherController {

    private final WeatherDataService weatherDataService;
    private final WeatherAdapter weatherAdapter;

    public WeatherController(WeatherDataService weatherDataService, WeatherAdapter weatherAdapter) {
        this.weatherDataService = weatherDataService;
        this.weatherAdapter = weatherAdapter;
    }

    @GetMapping(value = {"/weather/{city}", "/weather/{city}/{country}"}, produces = "application/json")
    public WeatherResponse getWeather(@PathVariable(name = "city") String city,
                                      @PathVariable(name = "country", required = false) String country) {

        var request = weatherAdapter.toRequest(city, country);
        var weatherData = weatherDataService.getWeatherData(request);
        return weatherAdapter.toResponse(weatherData);
    }


}
