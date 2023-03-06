import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class PracticeFormTests {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillFormTest() {

        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue("Anna");
        $("#lastName").setValue("Petrova");
        $("#userEmail").setValue("anna@petrova.com");
        $("label[for=gender-radio-2]").click();
        $("#userNumber").setValue("1234567890");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("December");
        $(".react-datepicker__year-select").selectOption("2000");
        $(".react-datepicker__day--001:not(.react-datepicker__day--outside-month)").click();
        $("#subjectsInput").setValue("Maths").pressEnter();
        $("label[for=hobbies-checkbox-1]").click();
        $("label[for=hobbies-checkbox-2]").click();
        $("label[for=hobbies-checkbox-3]").click();
        $("#uploadPicture").uploadFromClasspath("img/1.png");
        $("#currentAddress").setValue("Apt 4B 2311 North Los Robles Avenue");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Panipat")).click();
        $("#submit").click();


        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Anna"), text("Petrova"), text("anna@petrova.com"),
                text("Female"), text("1234567890"), text("01 December,2000"), text("Maths"), text("Sports, Reading, Music"),
                text("Apt 4B 2311 North Los Robles Avenue"), text("Haryana Panipat"));
        $("#closeLargeModal").click();
    }
}
