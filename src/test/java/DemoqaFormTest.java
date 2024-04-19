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
        $("#firstName").setValue("Jensen");
        $("#lastName").setValue("Huang");
        $("#userEmail").setValue("test@mail.com");
        $(byText("Male")).click();
        $("#userNumber").setValue("1111111111");
        $("#dateOfBirthInput").clear();
        $(".react-datepicker__month-select").selectOption("January");
        $(".react-datepicker__year-select").selectOption("1970");
        $(".react-datepicker__day--001").click();
        $("#subjectsInput").setValue("Physics").pressEnter();
        $("#subjectsInput").setValue("Computer Science").pressEnter();
        $(byText("Reading")).click();
        $(byText("Music")).click();
        $("#uploadPicture").uploadFromClasspath("selenide-logo-big.png");
        $("#currentAddress").setValue("Groove street 1");
        $("#react-select-3-input").setValue("Haryana").pressEnter();
        $("#react-select-4-input").setValue("Panipat").pressEnter();
        $("#submit").click();

        $x("//table/tbody/tr[1]/td[2]").shouldHave(text("Jensen Huang"));
        $x("//table/tbody/tr[2]/td[2]").shouldHave(text("test@mail.com"));
        $x("//table/tbody/tr[3]/td[2]").shouldHave(text("Male"));
        $x("//table/tbody/tr[4]/td[2]").shouldHave(text("1111111111"));
        $x("//table/tbody/tr[5]/td[2]").shouldHave(text("01 January,1970"));
        $x("//table/tbody/tr[6]/td[2]").shouldHave(text("Physics, Computer Science"));
        $x("//table/tbody/tr[7]/td[2]").shouldHave(text("Reading, Music"));
        $x("//table/tbody/tr[8]/td[2]").shouldHave(text("selenide-logo-big.png"));
        $x("//table/tbody/tr[9]/td[2]").shouldHave(text("Groove street 1"));
        $x("//table/tbody/tr[10]/td[2]").shouldHave(text("Haryana Panipat"));

    }
}
