package au.com.vanguard.demo.weatherapi.service;

import au.com.vanguard.demo.weatherapi.model.WeatherDataBuilder;
import au.com.vanguard.demo.weatherapi.model.WeatherRequest;
import au.com.vanguard.demo.weatherapi.service.strategy.CRUDStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class WeatherDataServiceTest {

    @InjectMocks
    private WeatherDataService underTest;

    @Mock
    private CRUDStrategy mockCRUDStrategy;

    @Test
    void shouldGetWeatherData() {
        var request = new WeatherRequest("London", "UK");
        var weatherData = new WeatherDataBuilder().city(request.city()).country(request.country()).build();
        given(mockCRUDStrategy.getWeatherData(request)).willReturn(weatherData);

        // when
        var result = underTest.getWeatherData(request);

        // then
        assertNotNull(result);
        verify(mockCRUDStrategy).getWeatherData(request);
    }
}