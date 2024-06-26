import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DemoqaFormTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl="https://demoqa.com";
        Configuration.browserSize="3840x2160";
    }

    @Test
    void fillPracticeForm() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");

        $("#firstName").setValue("Jensen");
        $("#lastName").setValue("Huang");
        $("#userEmail").setValue("test@mail.com");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("1111111111");
        $("#dateOfBirthInput").clear();
        $(".react-datepicker__month-select").selectOption("January");
        $(".react-datepicker__year-select").selectOption("1970");
        $(".react-datepicker__day--001").click();

        $("#subjectsInput").setValue("Physics").pressEnter();
        $("#subjectsInput").setValue("Computer Science").pressEnter();

        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#hobbiesWrapper").$(byText("Music")).click();

        $("#uploadPicture").uploadFromClasspath("selenide-logo-big.png");

        $("#currentAddress").setValue("Groove street 1");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Panipat")).click();

        $("#submit").click();

        $x("//td[contains(text(),'Student Name')]/../td[2]").shouldHave(text("Jensen Huang"));
        $x("//td[contains(text(),'Student Email')]/../td[2]").shouldHave(text("test@mail.com"));
        $x("//td[contains(text(),'Gender')]/../td[2]").shouldHave(text("Male"));
        $x("//td[contains(text(),'Mobile')]/../td[2]").shouldHave(text("1111111111"));
        $x("//td[contains(text(),'Date of Birth')]/../td[2]").shouldHave(text("01 January,1970"));
        $x("//td[contains(text(),'Subjects')]/../td[2]").shouldHave(text("Physics, Computer Science"));
        $x("//td[contains(text(),'Hobbies')]/../td[2]").shouldHave(text("Reading, Music"));
        $x("//td[contains(text(),'Picture')]/../td[2]").shouldHave(text("selenide-logo-big.png"));
        $x("//td[contains(text(),'Address')]/../td[2]").shouldHave(text("Groove street 1"));
        $x("//td[contains(text(),'State and City')]/../td[2]").shouldHave(text("Haryana Panipat"));
    }
}
