package au.com.vanguard.demo.weatherapi.client.key;

/** Strategy interface for obtaining client API Keys */
public interface ClientAPIKeyStrategy {

    /**
     * Get the next API Key for calling the Open Weather API.
     */
    String getNext();
}
