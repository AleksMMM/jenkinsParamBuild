package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.Calendar;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private Calendar calendarComponent = new Calendar();

   SelenideElement subjectsInput = $x("//div[@id = 'subjectsContainer']");

    @Step("Открываем страницу с регистрацией")
    public RegistrationPage openPage() {
        open("https://demoqa.com/automation-practice-form");
        $x("//div[@class = 'practice-form-wrapper']")
                .shouldHave(text("Student Registration Form"));

        return this;
    }

    @Step("Вводим Имя")
    public RegistrationPage setFirstName(String firstName) {
        $x("//input[@placeholder = 'First Name']")
                .setValue(firstName);
        return this;
    }

    @Step("Вводим Фамилию")
    public RegistrationPage setLastName(String lastName) {
        $x("//input[@placeholder = 'Last Name']")
                .setValue(lastName);

        return this;
    }

    @Step("Вводим Email")
    public RegistrationPage setEmail(String userEmail) {
        $x("//input[@placeholder = 'name@example.com']")
                .setValue(userEmail);

        return this;
    }

    @Step("Выбираем пол")
    public RegistrationPage setGender(String gender) {
        $x(String.format("//label[text() = '%s']", gender)).click();
        return this;
    }

    @Step("Выбираем дату рождения")
    public RegistrationPage setBirthDate(String day, String month, String year) {
        calendarComponent.setDate(day, month, year);
        return this;
    }

    @Step("Вводим номер телефона")
    public RegistrationPage setUserNumber(String userNumber) {
        $x("//input[@placeholder = 'Mobile Number']").setValue(userNumber);
        return this;
    }

    @Step("Вводим специализацию (полное название)")
    public RegistrationPage setSubjectsFull(String inputText) {
        subjectsInput.click();
        subjectsInput.setValue(inputText).pressEnter();
        return this;
    }

    @Step("Вводим специализацию (часть названия)")
    public RegistrationPage setSubjectsShort(String inputText, String listText) {
        subjectsInput.click();
        subjectsInput.setValue(inputText);
        $(byText(listText)).click();
        return this;
    }

    @Step("Выбираем хобби")
    public RegistrationPage setHobbiesUser(List<String> hobbiesUser) {
        hobbiesUser.forEach(user -> $x(String.format("//label[contains (text(), %s)]", user)).click());
        return this;
    }

    @Step("Вводим адрес")
    public RegistrationPage setCurrentAddress(String Address) {
        $x("//input[@placeholder = 'Current Address']").setValue(Address);
        return this;
    }

    @Step("Выбираем штат")
    public RegistrationPage selectState(String state) {
        $x("//div[ text() = \"Select State\"]").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        return this;
    }

    @Step("Выбираем город")
    public RegistrationPage selectCity(String city) {
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();
        return this;
    }

    @Step("Проверяем название формы")
    public RegistrationPage checkForm(String contentLable, String contentValues) {
        $(".table-responsive").$(byText(contentLable)).parent().shouldHave(text(contentValues));
        return this;
    }

    @Step("Проверяем содержимое полей таблицы с результатами")
    public RegistrationPage checkFormIteration(String contentLablel, List<String> contentValues) {
        for (String value : contentValues) {
            $(".table-responsive").$(byText(contentLablel)).parent().shouldHave(text(value));
        }
        return this;
    }

    @Step("Проверяем, что таблица с результатами открылась")
    public RegistrationPage openResultDataForm(String textModal) {
        $("#submit").click();
        $("#example-modal-sizes-title-lg").shouldHave(text(textModal));
        return this;
    }

    @Step("Закрываем таблицу с результатами")
    public RegistrationPage closeResultDataForm() {
        $("#closeLargeModal").click();
        $("#closeLargeModal").shouldNotBe(visible);
        return this;
    }
}
