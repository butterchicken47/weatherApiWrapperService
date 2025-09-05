package au.com.vanguard.demo.weatherapi.service;

import au.com.vanguard.demo.weatherapi.model.WeatherRequest;
import au.com.vanguard.demo.weatherapi.service.strategy.CRUDStrategy;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verifyNoInteractions;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class WeatherDataServiceIntegrationTest {

    @Autowired
    private WeatherDataService underTest;

    @MockBean
    private CRUDStrategy mockCRUDStrategy;

    @Test
    void given_nullCityInRequest_when_getWeatherData_should_raiseConstraintException() {

        // given
        var request = new WeatherRequest(null, null);

        // when / then
        assertThrows(ConstraintViolationException.class, () -> underTest.getWeatherData(request));
        verifyNoInteractions(mockCRUDStrategy);
    }

    @Test
    void given_blankCityInRequest_when_getWeatherData_should_raiseConstraintException() {

        // given
        var request = new WeatherRequest(" ", null);

        // when / then
        assertThrows(ConstraintViolationException.class, () -> underTest.getWeatherData(request));
        verifyNoInteractions(mockCRUDStrategy);
    }
}