package demoqa;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

public class RegistrationPageWithPageObjectTests extends TestBase {

    @Test
    public void fillInRegistrationForm() {
        Faker faker = new Faker();
        String userName = String.format("%s %s", faker.name().firstName(), faker.name().lastName());
        String email = faker.internet().emailAddress();
        String gender = "Female";
        String phone = "0893709419";
        String dob = "13 April 1997";
        String[] dobParts = dob.split(" ");
        String subject = "physics";
        String hobby = "Reading";
        String currentAddress = faker.address().fullAddress();
        String state = "NCR";
        String city = "Gurgaon";

        registrationPage.openPage()
            .setFullName(userName)
            .setEmail(email)
            .setGender(gender)
            .setPhone(phone)
            .setBirthDate(dob)
            .setSubject(subject)
            .setHobby(hobby)
            .uploadFile("pic.jpg")
            .setAddress(currentAddress)
            .setStateAndCity(state, city)
            .submit();

        registrationPage.verifyModalAppears();
        registrationPage
            .verifyResult("Student Name", userName)
            .verifyResult("Student Email", email)
            .verifyResult("Gender", gender)
            .verifyResult("Mobile", phone)
            .verifyResult("Date of Birth", String.format("%s %s,%s",dobParts[0], dobParts[1], dobParts[2]))
            .verifyResult("Subjects", subject)
            .verifyResult("Hobbies", hobby)
            .verifyResult("Picture", "pic.jpg")
            .verifyResult("Address", currentAddress)
            .verifyResult("State and City", String.format("%s %s", state, city));
    }
}
