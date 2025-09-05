package au.com.vanguard.demo.weatherapi.controller;

import au.com.vanguard.demo.weatherapi.service.key.APIKeyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web configuration allows us to register Interceptors.
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private APIKeyValidator apiKeyValidator;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new APIKeyInterceptor(apiKeyValidator));
    }
}
