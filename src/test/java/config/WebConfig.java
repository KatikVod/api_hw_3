package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:remote.properties"
})
public interface WebConfig extends Config {
    String browser();
    String browserVersion();
    String browserSize();
    String baseUrl();
    String remoteUrl();
}