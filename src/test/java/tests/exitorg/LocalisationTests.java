package tests.exitorg;

import data.Locale;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class LocalisationTests extends TestBaseExit {

    static Stream<Arguments> exitLocaleDataProvider() {
        return Stream.of(
            Arguments.of(Locale.EN, List.of("Lineup", "Tickets", "Tourist Info", "Giveaway", "News", "Explore", "EXIT Story", "Activism", "Info")),
            Arguments.of(Locale.SR, List.of("Program", "Ulaznice", "Turistički info", "Giveaway", "Vesti", "Istraži", "O Exitu", "Aktivizam", "Info")),
            Arguments.of(Locale.NL, List.of("Line-up", "Tickets", "Reis info", "Nieuws", "Foto", "Video", "EXIT Foundation", "Over EXIT", "Info")),
            Arguments.of(Locale.RU, List.of("Лайнап", "Билеты", "Путешествие", "Новости", "Инфо", "Активизм", "Инфо")),
            Arguments.of(Locale.FR, List.of("Programmation", "Tickets", "Voyage", "News", "Explorer", "EXIT Story", "Activisme", "Info")),
            Arguments.of(Locale.ES, List.of("Cartel", "Entradas", "Info Turística", "Noticias", "Explora", "Historia EXIT", "Activismo", "Info")),
            Arguments.of(Locale.CZ, List.of("Lineup", "Vstupenky", "Tourist Info", "Foto", "Video", "Probádat", "EXIT Story", "Info")),
            Arguments.of(Locale.DE, List.of("Line up", "Tickets", "Tourist Info", "Explore", "EXIT Story", "Info"))
        );
    }

    @Tag("UI")
    @Owner("ncr0s")
    @Feature("Demo QA")
    @Story("Registration form")
    @DisplayName("Positive: Header menu localisation test")
    @Severity(SeverityLevel.CRITICAL)
    @MethodSource("exitLocaleDataProvider")
    @ParameterizedTest(name = "For locale {0} the menu items {1} are displayed")
    void menuLocalisationTest(Locale locale, List<String> items) {
        mainPage
            .openPage()
            .switchLanguage(locale.name());

        for (String item : items) {
            mainPage.checkHeaderTextPresence(item);
        }
    }
}
