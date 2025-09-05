package au.com.vanguard.demo.weatherapi.controller;

import au.com.vanguard.demo.weatherapi.exception.*;
import au.com.vanguard.demo.weatherapi.model.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

/**
 * Exception handlers to map exceptions to the appropriate REST response.
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {InvalidAPIKeyException.class})
    protected ResponseEntity<Object> handleInvalidAPIKey(InvalidAPIKeyException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), "Invalid API Key", Instant.now()), new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(value = {TooManyRequestsException.class})
    protected ResponseEntity<Object> handleTooManyRequests(TooManyRequestsException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorResponse(HttpStatus.TOO_MANY_REQUESTS.value(), "Too many requests. Please try again later.", Instant.now()), new HttpHeaders(), HttpStatus.TOO_MANY_REQUESTS, request);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<Object> handleValidationFailure(ConstraintViolationException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Invalid request: + " + ex.getMessage(), Instant.now()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {InvalidRequestException.class})
    protected ResponseEntity<Object> handleBadRequest(InvalidRequestException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Invalid request", Instant.now()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {CityNotFoundException.class})
    protected ResponseEntity<Object> handleCityNotFoundException(CityNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorResponse(HttpStatus.NOT_FOUND.value(), "City could not be found", Instant.now()), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {ClientException.class})
    protected ResponseEntity<Object> handleClientException(ClientException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to call Open Weather API", Instant.now()), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}