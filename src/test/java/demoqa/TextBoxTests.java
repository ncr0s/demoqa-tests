package demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTests {

    private final String userName = "Melany Dempsey";
    private final String email = "zinnia_rickeysak@sizes.bxa";
    private final String currentAddress = "Nato Road 5890, West Prairie, Runion, 590677";
    private final String permanentAddress = "Clean St 3411, Boynton Beach, Kenya, 969352";

    private final String gender = "Female";
    private final String phone = "0912019847";
    private final String dob = "13 April 1997";
    private final String subject = "physics";
    private final String hobby = "Reading";
    private final String state = "NCR";
    private final String city = "Gurgaon";

    @BeforeAll
    public static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }
    @Test
    public void fillInFormTest() {

        open("/text-box");
        $(".main-header").shouldHave(text("Text Box"));
        $("#userName").setValue(userName);
        $("#userEmail").setValue(email);
        $("#currentAddress").setValue(currentAddress);
        $("#permanentAddress").setValue(permanentAddress);
        $("#submit").click();

        $("#output").shouldBe(visible);
        $("#output #name").shouldHave((text(userName)));
        $("#output #email").shouldHave(text(email));
        $("#output #currentAddress").shouldHave(text(currentAddress));
        $("#output #permanentAddress").shouldHave(text(permanentAddress));
    }

    /*
    Разработайте один автотест на проверку формы https://demoqa.com/automation-practice-form

    Запушьте код в свой репозиторий и дайте на него ссылку в качестве ответа на домашнее задание
    */
    @Test
    public void fillInAutomationPracticeForm() {
        open("/automation-practice-form");
        $(".main-header").shouldHave(text("Practice Form"));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        String[] nameParts = userName.split(" ");
        $("#firstName").setValue(nameParts[0]);
        $("#lastName").setValue(nameParts[1]);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(phone);
        $("#dateOfBirthInput").click();
        String[] dobParts = dob.split(" ");
        $(".react-datepicker__month-select").selectOption(dobParts[1]);
        $(".react-datepicker__year-select").selectOption(dobParts[2]);
        $$(".react-datepicker__day").findBy(text(dobParts[0])).click();
        $("#subjectsInput").setValue(subject).pressEnter();
        $("#hobbiesWrapper").$(byText(hobby)).click();
        $("#uploadPicture").uploadFromClasspath("pic.jpg");
        $("#currentAddress").setValue(currentAddress);
        $("#state").click();
        $(byText(state)).click();
        $("#city").click();
        $(byText(city)).click();
        $("#submit").click();

        $(".modal-dialog").shouldBe(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                text(userName),
                text(email),
                text(gender),
                text(phone),
                text(String.format("%s %s,%s",dobParts[0], dobParts[1], dobParts[2])),
                text(subject),
                text(hobby),
                text("pic.jpg"),
                text(currentAddress),
                text(String.format("%s %s", state, city))
        );
    }
}
