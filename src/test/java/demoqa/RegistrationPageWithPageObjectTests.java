package demoqa;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import data.Genders;
import data.Subjects;

import static utils.RandomUtils.birthDayGenerator;
import static utils.RandomUtils.cityGenerator;

public class RegistrationPageWithPageObjectTests extends TestBase {

    @Test
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
