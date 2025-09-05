package au.com.vanguard.demo.weatherapi.repository;

import au.com.vanguard.demo.weatherapi.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data repository for {@link WeatherData} entities.
 *
 * @see <a href="https://spring.io/guides/gs/accessing-data-jpa/">
 */
@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {

    Optional<WeatherData> findByCity(String city);

    Optional<WeatherData> findByCountry(String country);

    Optional<WeatherData> findByCityAndCountry(String city, String country);
}
