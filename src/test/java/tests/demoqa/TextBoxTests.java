package tests.demoqa;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class TextBoxTests extends TestBase {

    private final String headerText = "Text Box";
    private final String userName = "Melany Dempsey";
    private final String email = "zinnia_rickeysak@sizes.bxa";
    private final String currentAddress = "Nato Road 5890, West Prairie, Runion, 590677";
    private final String permanentAddress = "Clean St 3411, Boynton Beach, Kenya, 969352";

    @Test
    @Tags({@Tag("UI"), @Tag("demoQa")})
    @Owner("ncr0s")
    @Feature("Demo QA")
    @Story("Text Box")
    @DisplayName("Positive: Fill in the text boxes")
    @Severity(SeverityLevel.MINOR)
    @Link(value = "Lesson", url = "https://qa.guru/pl/teach/control/lesson/view?id=257901252")
    public void fillInFormTest() {

        step("Open text boxes page", () -> {
            open("/text-box");
            executeJavaScript("$('footer').remove()");
            executeJavaScript("$('#fixedban').remove()");
        });
        step("Pages header should has text " + headerText, () -> {
            $(".main-header").shouldHave(text(headerText));
        });
        step("Fill in user name with the value " + userName, () -> {
            $("#userName").setValue(userName);
        });
        step("Fill in email with the value " + email, () -> {
            $("#userEmail").setValue(email);
        });
        step("Fill in current address with the value " + currentAddress, () -> {
            $("#currentAddress").setValue(currentAddress);
        });
        step("Fill in permanent with the value " + email, () -> {
            $("#permanentAddress").setValue(permanentAddress);
        });
        step("Press submit button", () -> {
            $("#submit").click();
        });
        step("Check visibility of output window", () -> {
            $("#output").shouldBe(visible);
        });
        step("Check values in output window", () -> {
            $("#output #name").shouldHave((text(userName)));
            $("#output #email").shouldHave(text(email));
            $("#output #currentAddress").shouldHave(text(currentAddress));
            $("#output #permanentAddress").shouldHave(text(permanentAddress));
        });
    }
}
