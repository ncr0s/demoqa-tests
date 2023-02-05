package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class ExitMainPage {

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
}
