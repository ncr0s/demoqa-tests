package tests.exitorg;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
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
        Configuration.browserSize = System.getProperty("resolution", "1920x1080");
        Configuration.baseUrl = "https://www.exitfest.org";
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
