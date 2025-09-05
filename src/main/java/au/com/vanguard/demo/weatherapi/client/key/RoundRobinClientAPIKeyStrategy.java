package au.com.vanguard.demo.weatherapi.client.key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Simple round-robin client key strategy implementation that cycles through pre configured keys.
 * @see application.properties
 */
@Component
public class RoundRobinClientAPIKeyStrategy implements ClientAPIKeyStrategy {

    @Value("${client.open-weather.key}")
    private List<String> apiKeys = new ArrayList<>();

    private final AtomicInteger index = new AtomicInteger(-1);

    @Override
    public String getNext() {
        int currentIndex = index.incrementAndGet();

        // Circle back to 0 if we have reached the end
        if (currentIndex == apiKeys.size()) {
            currentIndex = 0;
            index.set(currentIndex);
        }

        return apiKeys.get(currentIndex);
    }
}
