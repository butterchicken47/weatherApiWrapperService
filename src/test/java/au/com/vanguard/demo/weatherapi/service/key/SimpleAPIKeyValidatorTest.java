package au.com.vanguard.demo.weatherapi.service.key;

import au.com.vanguard.demo.weatherapi.exception.InvalidAPIKeyException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class SimpleAPIKeyValidatorTest {

    @Test
    void givenValidKey_shouldValidateSuccessfully() {
        // given
        var underTest = new SimpleAPIKeyValidator("test-key");

        // when
        underTest.validate("test-key");

        // then no exception is thrown
    }

    @Test
    void givenNullKey_shouldValidateSuccessfully() {
        // given
        var underTest = new SimpleAPIKeyValidator("test-key");

        // when / then exception is thrown
        assertThrows(InvalidAPIKeyException.class, () -> underTest.validate(null));
    }

    @Test
    void givenInvalidKey_shouldValidateSuccessfully() {
        // given
        var underTest = new SimpleAPIKeyValidator("test-key");

        // when / then exception is thrown
        assertThrows(InvalidAPIKeyException.class, () -> underTest.validate("invalid-key"));
    }
}