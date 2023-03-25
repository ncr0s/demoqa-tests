package tests.exitorg;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.WebDriverProvider;
import helpers.Attachments;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.ExitMainPage;

public class TestBaseExit {
    ExitMainPage mainPage = new ExitMainPage();

    @BeforeAll
    static void setUp() {
        WebDriverProvider.setupConfig();
        Configuration.baseUrl = "https://www.exitfest.org";
        Configuration.pageLoadTimeout = 45000;
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
