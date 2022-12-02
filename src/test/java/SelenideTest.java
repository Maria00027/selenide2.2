import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {
    @Test
    void test() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").setValue("Санкт-Петербург");
        String currentDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $x("//input[@placeholder=\"Дата встречи\"]").setValue(currentDate);
        $("[data-test-id ='name'] input").val("Мария Владиславовна");
        $("[data-test-id ='phone'] input").val("+79992181111");
        $("[data-test-id ='agreement']").click();
        $(".button_view_extra ").click();
        $("[data-test-id=\"notification\"] [class=\"notification__title\"]").shouldBe(text("Успешно!"), Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").should(exactText("Встреча успешно забронирована на" + currentDate));
    }
}
