package config;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Configuration.remote;

public class WebDriverProvider {

    public static WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    public static void setupConfig() {

        Configuration.browser = config.getBrowserName().toLowerCase();
        Configuration.baseUrl = config.getBaseUrl();
        Configuration.browserVersion = config.getBrowserVersion();
        remote = config.getRemoteUrl();
        Configuration.browserSize = config.getBrowserSize();

        if (remote != null) {
            Configuration.remote = remote;
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
            "enableVNC", true,
            "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }
}
