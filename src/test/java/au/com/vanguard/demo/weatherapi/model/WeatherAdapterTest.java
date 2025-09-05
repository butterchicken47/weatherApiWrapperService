package au.com.vanguard.demo.weatherapi.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class WeatherAdapterTest {

    @InjectMocks
    private WeatherAdapter underTest;

    @Test
    void shouldBuildWeatherRequest() {
        // given
        var city = "Paris";
        var country = "FR";

        // when
        var result = underTest.toRequest(city, country);

        // then
        assertNotNull(result);
        assertEquals(city, result.city());
        assertEquals(country, result.country());
    }

}