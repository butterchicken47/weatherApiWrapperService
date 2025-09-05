package au.com.vanguard.demo.weatherapi.client;

import au.com.vanguard.demo.weatherapi.client.key.RoundRobinClientAPIKeyStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class RoundRobinClientAPIKeyStrategyTest {

    @InjectMocks
    private RoundRobinClientAPIKeyStrategy underTest;

    @Spy
    private List<String> apiKeys = Arrays.asList("key1", "key2");

    @Test
    void shouldGetNextKey() {

        // when
        var result = underTest.getNext();

        // then
        assertNotNull(result, "Key must never be null");
        assertEquals("key1", result);
    }

    @Test
    void shouldCircleToInitialKey_whenGetNextKey() {

        // when
        var initial = underTest.getNext();
        var second = underTest.getNext();
        var third = underTest.getNext();

        // then
        assertEquals("key1", initial);
        assertEquals("key1", third);
    }

}