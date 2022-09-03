package pages.components;

import static com.codeborne.selenide.Selenide.$x;

public class Calendar {

    public Calendar setDate (String day, String month, String year) {
        $x("//input[@id = 'dateOfBirthInput']").click();
        $x("//select[@class = 'react-datepicker__month-select']").selectOption(month);
        $x("//select[@class = 'react-datepicker__year-select']").selectOption(year);
        $x(String.format("//div[contains (@class, 'react-datepicker__day--0%s')]", day)).click();

        return this;
    }
}
