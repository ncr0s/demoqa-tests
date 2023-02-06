package tests.exitorg;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attachments;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.ExitMainPage;

import java.util.Map;

public class TestBaseExit {
    ExitMainPage mainPage = new ExitMainPage();

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = System.getProperty("resolution", "1920x1080");
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion =  System.getProperty("browser_version", "99.0");
        Configuration.baseUrl = "https://www.exitfest.org";
        Configuration.pageLoadTimeout = 45000;
        Configuration.remote = String.format("https://user1:1234@%s/wd/hub", System.getProperty("browser_address"));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
            "enableVNC", true,
            "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attachments.screenshotAs("Last screen");
        Attachments.pageSource();
        Attachments.browserConsoleLogs();
        Attachments.addVideo();
    }
}
