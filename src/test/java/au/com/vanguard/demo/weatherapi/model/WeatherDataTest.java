package au.com.vanguard.demo.weatherapi.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class WeatherDataTest {

    @Test
    void shouldEquals() {
        var weatherData = new WeatherDataBuilder().city("city").country("country").description("Hazy sunshine").build();
        var weatherData2 = new WeatherDataBuilder().city("city").country("country").description("Hazy sunshine").build();

        assertEquals(weatherData, weatherData2);
    }

    @Test
    void shouldNotEquals() {
        var weatherData = new WeatherDataBuilder().city("city").country("country").description("Hazy sunshine").build();
        var weatherData2 = new WeatherDataBuilder().city("city2").country("country2").description("Rain").build();

        assertNotEquals(weatherData, weatherData2);
    }

    @Test
    void shouldHashEqual() {
        var weatherData = new WeatherDataBuilder().city("city").country("country").description("Hazy sunshine").build();
        var weatherData2 = new WeatherDataBuilder().city("city").country("country").description("Hazy sunshine").build();

        assertEquals(weatherData.hashCode(), weatherData2.hashCode());
    }

    @Test
    void shouldNotHashEqual() {
        var weatherData = new WeatherDataBuilder().city("city").country("country").description("Hazy sunshine").build();
        var weatherData2 = new WeatherDataBuilder().city("city2").country("country2").description("Rain").build();

        assertNotEquals(weatherData.hashCode(), weatherData2.hashCode());
    }

}