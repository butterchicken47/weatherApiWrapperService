package au.com.vanguard.demo.weatherapi.model;

import au.com.vanguard.demo.weatherapi.repository.WeatherDataRepository;

/**
 * Builder for {@link WeatherData} instances.
 */
public class WeatherDataBuilder {

    private String city;
    private String country;
    private String description;

    public WeatherDataBuilder city(String city) {
        this.city = city;
        return this;
    }

    public WeatherDataBuilder country(String country) {
        this.country = country;
        return this;
    }

    public WeatherDataBuilder description(String description) {
        this.description = description;
        return this;
    }

    public WeatherData build() {
        return new WeatherData(this.city, this.country, this.description);
    }
}
