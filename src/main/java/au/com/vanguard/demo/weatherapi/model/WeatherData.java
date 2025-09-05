package au.com.vanguard.demo.weatherapi.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "WEATHER_DATA", uniqueConstraints = {@UniqueConstraint(name = "UniqueCityAndCountry", columnNames = {"CITY", "COUNTRY"})})
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class WeatherData {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "DESCRIPTION")
    private String description;

    @CreatedDate
    private Instant createdDate;

    /**
     * For JPA
     */
    WeatherData() {

    }

    public WeatherData(String city, String country, String description) {
        this.city = city;
        this.country = country;
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherData that = (WeatherData) o;
        return Objects.equals(city, that.city) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, country);
    }
}
