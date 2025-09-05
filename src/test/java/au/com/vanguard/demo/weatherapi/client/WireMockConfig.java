package au.com.vanguard.demo.weatherapi.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import feign.codec.ErrorDecoder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

@TestConfiguration
@ActiveProfiles("test")
public class WireMockConfig {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public WireMockServer mockOpenWeatherService() {
        return new WireMockServer(81);
    }
}