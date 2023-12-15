package ru.netology.delivery.test;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldDeliveryCard() {
        DataGenerator.UserInfo validUser = DataGenerator.Registration.generateUser();
        int daysToAddFirstDate = 4;
        String FirstDate = DataGenerator.generateDate(daysToAddFirstDate);
        int daysToAddSecondDate = 6;
        String SecondDate = DataGenerator.generateDate(daysToAddSecondDate);
        $("[placeholder='Город']").setValue(validUser.getCity());
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[placeholder='Дата встречи']").setValue(FirstDate);
        $("[name='name']").setValue(validUser.getName());
        $("[name='phone']").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content").shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(exactText("Встреча успешно запланирована на " + FirstDate));
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[placeholder='Дата встречи']").setValue(SecondDate);
        $(byText("Запланировать")).click();
        $("[data-test-id='replan-notification'] .notification__content") .shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?")) .shouldBe(visible);
        $("[data-test-id='replan-notification'] button").click();
        $("[data-test-id='success-notification'] .notification__content") .shouldHave(exactText("Встреча успешно запланирована на " + SecondDate)) .shouldBe(visible);
    }
}

