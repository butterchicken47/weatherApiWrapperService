package au.com.vanguard.demo.weatherapi.controller;

import au.com.vanguard.demo.weatherapi.service.key.APIKeyValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Interceptor that validates the API key, which is expected as a HTTP header.
 */
@Component
public class APIKeyInterceptor implements HandlerInterceptor {

    private final APIKeyValidator apiKeyValidator;

    public APIKeyInterceptor(APIKeyValidator apiKeyValidator) {
        this.apiKeyValidator = apiKeyValidator;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // Validate the API key from the request header
        var apiKey = request.getHeader(APIKeyValidator.HEADER_NAME);
        apiKeyValidator.validate(apiKey);

        return true;
    }
}
