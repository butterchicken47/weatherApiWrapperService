package au.com.vanguard.demo.weatherapi.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OpenWeatherResponseSerDesTest {

    private final ObjectMapper mapper = new ObjectMapper();


    @Test
    void shouldSerialize() throws JsonProcessingException, JSONException {
        var underTest = new OpenWeatherResponse();


        var weather = new Weather();
        underTest.getWeather().add(weather);
        weather.setId("1");
        weather.setIcon("09D");
        weather.setMain("Drizzle");
        weather.setDescription("Light intensity drizzle");

        var json = mapper.writeValueAsString(underTest);
        assertNotNull(json);
    }

    @Test
    void shouldDeserialize() throws Exception {

        var result = mapper.readValue(Paths.get("target/test-classes/open-weather-valid-response.json").toFile(), OpenWeatherResponse.class);
        assertNotNull(result);

        var expected = new OpenWeatherResponse();
        var weather = new Weather();
        expected.getWeather().add(weather);
        weather.setId("300");
        weather.setIcon("09d");
        weather.setMain("Drizzle");
        weather.setDescription("light intensity drizzle");

        assertNotNull(result.getWeather());
        assertEquals(1, result.getWeather().size());
        Weather actual = result.getWeather().get(0);
        assertEquals(weather.getId(), actual.getId());
        assertEquals(weather.getMain(), actual.getMain());
        assertEquals(weather.getDescription(), actual.getDescription());
        assertEquals(weather.getIcon(), actual.getIcon());


    }

}