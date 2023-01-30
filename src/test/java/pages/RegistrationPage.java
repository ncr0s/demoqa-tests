package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;
import data.Genders;
import pages.components.RegistrationResultModal;
import data.Subjects;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationPage {
    CalendarComponent calendarComponent = new CalendarComponent();
    RegistrationResultModal registrationResultModal = new RegistrationResultModal();
    private final String TITLE_TEXT = "Practice Form";
    private final SelenideElement
        firstName = $("#firstName"),
        lastName = $("#lastName"),
        email = $("#userEmail"),
        gender = $("#genterWrapper"),
        phone = $("#userNumber"),
        dobInput = $("#dateOfBirthInput"),
        subjectsInput = $("#subjectsInput"),
        hobbiesWrapper = $("#hobbiesWrapper"),
        uploadButton = $("#uploadPicture"),
        currentAddress = $("#currentAddress");

    @Step("Open registration form and remove adds")
    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".main-header").shouldHave(text(TITLE_TEXT));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        return this;
    }

    @Step("Set full name: {value}")
    public RegistrationPage setFullName(String value) {
        String[] nameParts = value.split(" ");
        firstName.setValue(nameParts[0]);
        lastName.setValue(nameParts[1]);
        return this;
    }

    @Step("Set email: {value}")
    public RegistrationPage setEmail(String value) {
        email.setValue(value);
        return this;
    }

    @Step("Set gender: {value}")
    public RegistrationPage setGender(Genders value) {
        gender.$(byText(value.toString())).click();
        return this;
    }

    @Step("Set phone: {value}")
    public RegistrationPage setPhone(String value) {
        phone.setValue(value);
        return this;
    }

    @Step("Set birthdate: {date}/{month}/{year}")
    public RegistrationPage setBirthDate(String date, String month, String year) {
        dobInput.click();
        calendarComponent.setDate(date, month, year);
        return this;
    }

    @Step("Set subject: {value}")
    public RegistrationPage setSubject(Subjects value) {
        subjectsInput.setValue(value.toString()).pressEnter();
        return this;
    }

    @Step("Set hobby: {value}")
    public RegistrationPage setHobby(String value) {
        hobbiesWrapper.$(byText(value)).click();
        return this;
    }

    @Step("Upload photo {fileName}")
    public RegistrationPage uploadFile(String fileName) {
        uploadButton.uploadFromClasspath(fileName);
        return this;
    }

    @Step("Set full address: {value}")
    public RegistrationPage setAddress(String value) {
        currentAddress.setValue(value);
        return this;
    }

    @Step("Add {state} and {city}")
    public RegistrationPage setStateAndCity(String state, String city) {
        $("#state").click();
        $(byText(state)).click();
        $("#city").click();
        $(byText(city)).click();
        return this;
    }

    @Step("Press submit button")
    public RegistrationPage submit() {
        $("#submit").click();
        return this;
    }

    @Step("Modal window with completed form should be shown")
    public RegistrationPage verifyModalAppears() {
        registrationResultModal.verifyModalAppears();
        return this;
    }

    @Step("In the modal window {value} should be shown for {key}")
    public RegistrationPage verifyResult(String key, String value) {
        registrationResultModal.verifyResult(key, value);
        return this;
    }
}
