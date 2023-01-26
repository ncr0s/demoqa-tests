package demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RegistrationPage;

import java.util.Map;

public class TestBaseExtended {
    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeAll
    public static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";

        // for run tests in selenoid
        Configuration.browser = "chrome";
        Configuration.browserVersion = "100.0";;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
            "enableVNC", true,
            "enableVideo", true // for record video, video will not be recorded if headless=true
        ));

        Configuration.browserCapabilities = capabilities;
    }
}
