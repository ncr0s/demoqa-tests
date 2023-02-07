package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import data.Locale;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExitMainPage {
    SelenideElement buyTicketsButton = $("#tickets .vc_btn3");

    @Step("Open EXIT fest main page")
    public ExitMainPage openPage() {
        open("/");
        return this;
    }

    @Step("Switch the page language to {language}")
    public ExitMainPage switchLanguage(String language) {
        if($(".lang .wpml-ls-native").getText().equalsIgnoreCase(language)) {
            return this;
        } else {
            $(".js-wpml-ls-item-toggle").click();
            $$(".wpml-ls-slot-shortcode_actions .wpml-ls-item")
                .find(text(language)).click();
            return this;
        }
    }

    @Step("Page header should has text {text}")
    public void checkHeaderTextPresence(String text) {
        $("#header").shouldHave(text(text));
    }

    @Step("Buy tickets button should has text {text}")
    public void checkBuyTicketsButtonWithText(String text) {
        buyTicketsButton.shouldBe(Condition.enabled);
        buyTicketsButton.shouldHave(Condition.text(text));
    }

    @Step("Switching language of the main page should redirect on expected url")
    public void mainPageSwitchLanguageCheck(Locale locale) {
        String expectedUrl = Configuration.baseUrl + '/';
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        if (locale != Locale.SR) {
            expectedUrl = expectedUrl + locale.name().toLowerCase();
        }
        assertEquals(expectedUrl, currentUrl);
    }
}
