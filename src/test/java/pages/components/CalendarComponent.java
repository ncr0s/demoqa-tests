package pages.components;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    public void setDate(String value) {
        String[] dobParts = value.split(" ");
        $(".react-datepicker__month-select").selectOption(dobParts[1]);
        $(".react-datepicker__year-select").selectOption(dobParts[2]);
        $(".react-datepicker__day--0" + dobParts[0] +
            ":not(.react-datepicker__day--outside-month)").click();
    }
}
