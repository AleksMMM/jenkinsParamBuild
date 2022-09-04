package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import pages.RegistrationPage;

import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentRegistrationTest extends TestBase {

    //import
    private RegistrationPage registrationPage = new RegistrationPage();

ClassLoader classLoader = RegistrationPage.class.getClassLoader();


    //configuration parameters
    static String firstName = "StudentFirstName";
    static String lastName = "StudentLastName";
    static String userEmail = "email@email.com";
    static String userNumber = "8800123456";  // number of characters = 10
    static String subjectsFullText = "Maths";
    static String subjectsShortText = "Hi";
    static String subjectsShortTextSelect = "History";
    static String calendarYear = "1987"; // "yyyy" format
    static String calendarMonth = "November"; // number of characters = 3
    static String calendarDay = "14";  // "dd" format
    static String genderRoleName = "Male"; //"Male", "Female", "Other"
    static String currentAddress = "Novosibirsk";
    static String textModalWindow = "Thanks for submitting the form";
    List<String> hobbiesUser = new ArrayList<>(List.of("Reading", "Sports", "Music"));
    static String stateName = "NCR";
    static String cityName = "Delhi";
     String path = classLoader.getResource("WhatsApp.jpeg").getPath();


    @Test
    @Tag("web")
    void successTest() {


        //input data in form
        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(genderRoleName)
                .setEmail(userEmail)
                .setUserNumber(userNumber)
                .setCurrentAddress(currentAddress)
                .setHobbiesUser(hobbiesUser)
                .uploadFile(path)
                .setSubjectsFull(subjectsFullText)
                .setSubjectsShort(subjectsShortText, subjectsShortTextSelect)
                .selectState(stateName)
                .selectCity(cityName)
                .setBirthDate(calendarDay, calendarMonth, calendarYear)
                .openResultDataForm(textModalWindow);

        //checking the results in the registration form
        registrationPage
                .checkForm("Student Name", firstName + " " + lastName)
                .checkForm("Student Email", userEmail)
                .checkForm("Gender", genderRoleName)
                .checkForm("Mobile", userNumber)
                .checkForm("Date of Birth", calendarDay + " " + calendarMonth + "," + calendarYear)
                .checkForm("Subjects", subjectsFullText)
                .checkForm("Subjects", subjectsShortTextSelect)
                .checkFormIteration("Hobbies", hobbiesUser)
                .checkForm("Address", currentAddress)
                .checkForm("State and City", stateName + " " + cityName)
                .closeResultDataForm();

    }

    @Test
    @Tag("web")
    void demoWebTest() {
        System.out.println("this demo web test");
    }

    @Test
    @Tag("api")
    void demoApiTest() {
        System.out.println("this demo api test");
    }

    @Test
    @Tag("api")
    void demoApiTwoTest() {
        System.out.println("this second api test");
    }
}