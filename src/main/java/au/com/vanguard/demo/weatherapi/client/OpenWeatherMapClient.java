package au.com.vanguard.demo.weatherapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign client for Open Weather client requests.
 *
 * @see <a href="http://samples.openweathermap.org/">
 * @see <a href="https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/">
 */
@FeignClient(name = "openWeatherClient", url = "${client.open-weather.url}")
public interface OpenWeatherMapClient {

    @RequestMapping("/data/2.5/weather")
    OpenWeatherResponse findWeatherData(@RequestParam("appid") String appId, @RequestParam("q") String queryParameters);
}
