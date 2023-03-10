package tests.demoqa;

import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import data.Genders;
import data.Subjects;

import static utils.RandomUtils.birthDayGenerator;
import static utils.RandomUtils.cityGenerator;

public class RegistrationPageWithPageObjectTests extends TestBase {

    @Test
    @Tags({@Tag("UI"), @Tag("demoQa")})
    @Owner("ncr0s")
    @Feature("Demo QA")
    @Story("Registration form")
    @DisplayName("Positive: Fill in the registration form")
    @Severity(SeverityLevel.CRITICAL)
    @Link(value = "Lesson", url = "https://qa.guru/pl/teach/control/lesson/view?id=257901252")
    public void fillInRegistrationForm() {
        Faker faker = new Faker();

        String userName = String.format("%s %s", faker.name().firstName(), faker.name().lastName());
        String email = faker.internet().emailAddress();
        Genders gender = faker.options().option(Genders.values());
        String phone = faker.phoneNumber().subscriberNumber(10);
        String[] dob = birthDayGenerator(3, 96);
        Subjects subject = faker.options().option(Subjects.values());
        String hobby = faker.options().option("Sports", "Reading", "Music");
        String fileName = faker.options().option("pic.jpg", "padme.jpg");
        String currentAddress = faker.address().fullAddress();
        String state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
        String city = cityGenerator(state);

        registrationPage.openPage()
            .setFullName(userName)
            .setEmail(email)
            .setGender(gender)
            .setPhone(phone)
            .setBirthDate(dob[0], dob[1], dob[2])
            .setSubject(subject)
            .setHobby(hobby)
            .uploadFile(fileName)
            .setAddress(currentAddress)
            .setStateAndCity(state, city)
            .submit();

        registrationPage.verifyModalAppears();
        registrationPage
            .verifyResult("Student Name", userName)
            .verifyResult("Student Email", email)
            .verifyResult("Gender", gender.toString())
            .verifyResult("Mobile", phone)
            .verifyResult("Date of Birth", String.format("%s %s,%s", dob[0], dob[1], dob[2]))
            .verifyResult("Subjects", subject.toString())
            .verifyResult("Hobbies", hobby)
            .verifyResult("Picture", fileName)
            .verifyResult("Address", currentAddress)
            .verifyResult("State and City", String.format("%s %s", state, city));
    }
}
