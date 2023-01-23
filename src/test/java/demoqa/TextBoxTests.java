package demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
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
}
