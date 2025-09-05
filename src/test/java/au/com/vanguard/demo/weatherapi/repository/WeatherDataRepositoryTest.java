package au.com.vanguard.demo.weatherapi.repository;

import au.com.vanguard.demo.weatherapi.model.WeatherDataBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class WeatherDataRepositoryTest {

    @Autowired
    private WeatherDataRepository underTest;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void shouldPersistWeatherData() {

        // given
        var weatherData = new WeatherDataBuilder().city("London").country("UK").description("Light, grey drizzle, feeling a bit uptight").build();

        // when
        var persisted = testEntityManager.persist(weatherData);

        // then
        assertNotNull(persisted.getId());
        assertEquals(weatherData, persisted);
    }

    @Test
    void shouldFindByCity() {

        // given
        var city = "London";
        var weatherData = new WeatherDataBuilder().city(city).country("UK").description("Light, grey drizzle, feeling a bit under the weather").build();
        testEntityManager.persist(weatherData);

        // when
        var maybeResult = underTest.findByCity(city);

        // then
        assertTrue(maybeResult.isPresent());

        var persisted = maybeResult.get();
        assertEquals(weatherData, persisted);
    }

    @Test
    void shouldFindByCountry() {

        // given
        var country = "UK";
        var weatherData = new WeatherDataBuilder().city("Birmingham").country(country).description("Rain, rain, rain").build();
        testEntityManager.persist(weatherData);

        // when
        var maybeResult = underTest.findByCountry(country);

        // then
        assertTrue(maybeResult.isPresent());

        var persisted = maybeResult.get();
        assertEquals(weatherData, persisted);
    }

    @Test
    void shouldFindByCityAndCountry() {

        // given
        var city = "Melbourne";
        var country = "AUS";
        var weatherData = new WeatherDataBuilder().city(city).country(country).description("Cold, Hot, Rain and Sunny").build();
        testEntityManager.persist(weatherData);

        // when
        var maybeResult = underTest.findByCityAndCountry(city, country);

        // then
        assertTrue(maybeResult.isPresent());

        var persisted = maybeResult.get();
        assertEquals(weatherData, persisted);
    }
}