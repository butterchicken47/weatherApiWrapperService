package au.com.vanguard.demo.weatherapi.service.strategy;

import au.com.vanguard.demo.weatherapi.client.OpenWeatherMapClient;
import au.com.vanguard.demo.weatherapi.client.OpenWeatherResponse;
import au.com.vanguard.demo.weatherapi.client.Weather;
import au.com.vanguard.demo.weatherapi.client.key.ClientAPIKeyStrategy;
import au.com.vanguard.demo.weatherapi.model.WeatherData;
import au.com.vanguard.demo.weatherapi.model.WeatherDataBuilder;
import au.com.vanguard.demo.weatherapi.model.WeatherRequest;
import au.com.vanguard.demo.weatherapi.repository.WeatherDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class CachedCRUDStrategyTest {

    private CachedCRUDStrategy underTest;

    private int cacheSeconds = 600;

    @Mock
    private WeatherDataRepository mockWeatherDataRepository;

    @Mock
    private ClientAPIKeyStrategy mockClientAPIKeyStrategy;

    @Mock
    private OpenWeatherMapClient mockOpenWeatherClient;

    @Captor
    private ArgumentCaptor<WeatherData> weatherDataCaptor;

    @BeforeEach
    void beforeEach() {
        underTest = new CachedCRUDStrategy(cacheSeconds, mockWeatherDataRepository, mockClientAPIKeyStrategy, mockOpenWeatherClient);
    }

    @Test
    void given_persistedDataHasNotExpired_when_getWeatherData_then_theDataIsReturned() {

        // given
        var city = "Melbourne";
        var country = "AUS";
        var request = new WeatherRequest(city, country);
        var persistedData = new WeatherDataBuilder().city(city).country(country).build();
        persistedData.setCreatedDate(Instant.now().minusSeconds(200)); // within cache range
        given(mockWeatherDataRepository.findByCityAndCountry(city, country)).willReturn(Optional.of(persistedData));

        // when
        var result = underTest.getWeatherData(request);

        // then
        assertNotNull(result);
        assertEquals(persistedData, result);
        verify(mockWeatherDataRepository).findByCityAndCountry(city, country);
        verifyNoInteractions(mockOpenWeatherClient);
    }

    @Test
    void given_persistedDataHasExpired_when_getWeatherData_then_theDataIsRequestedAndStored() {

        // given
        var city = "Melbourne";
        var country = "AUS";
        var request = new WeatherRequest(city, country);
        var persistedData = new WeatherDataBuilder().city(city).country(country).build();
        var apiKey = "api-key";
        persistedData.setCreatedDate(Instant.now().plusSeconds(10)); // expired cache range
        given(mockWeatherDataRepository.findByCityAndCountry(city, country)).willReturn(Optional.of(persistedData));
        given(mockClientAPIKeyStrategy.getNext()).willReturn(apiKey);

        var response = new OpenWeatherResponse();
        var weather = new Weather();
        weather.setDescription("weather description");
        response.getWeather().add(weather);
        given(mockOpenWeatherClient.findWeatherData(apiKey, request.toArguments())).willReturn(response);

        // when
        var result = underTest.getWeatherData(request);

        // then
        assertNotNull(result);
        assertEquals(persistedData, result);
        verify(mockClientAPIKeyStrategy).getNext();
        verify(mockWeatherDataRepository).findByCityAndCountry(city, country);
        verify(mockOpenWeatherClient).findWeatherData(apiKey, request.toArguments());
        verify(mockWeatherDataRepository).save(weatherDataCaptor.capture());

        var capturedEntity = weatherDataCaptor.getValue();
        assertEquals(request.city(), capturedEntity.getCity());
        assertEquals(request.country(), capturedEntity.getCountry());
        assertEquals(response.getDescription(), capturedEntity.getDescription());
    }

}