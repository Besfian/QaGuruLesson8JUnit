package ru.mail.besfian;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;


import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class Tests {
    static Stream<Arguments> textBoxMethodSourceTest() {
        return Stream.of(
                Arguments.of("Kazakov1 Vitaliy", "Kazakov@yandex.ru", "Holzunova", "Volgograd"),
                Arguments.of("Korobov1 Mikhail", "Korobov@mail.ru", "Titova", "Moskva"),
                Arguments.of("Kosterin1 Mikhail", "Kosterin@gmail.com", "Kuznecova", "Ufa")
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Проверка работы text-Box с MethodSource")
    @DisplayName("Проверка работы text-Box с MethodSource")
    void textBoxMethodSourceTest(String fullName, String mail, String currentAddress
            , String permanentAddress
    ) {

        {
            open("https://demoqa.com/text-box");
            $("[id=userName]").setValue(fullName);
            $("#userEmail").setValue(mail);
            $("#currentAddress").setValue(currentAddress);
            $("#permanentAddress").setValue(permanentAddress);
            $("#submit").scrollTo().click();

            $("#output #name").shouldHave(text(fullName));
            $("#output").$("#email").shouldHave(text(mail));
            $("#output").$("#currentAddress").shouldHave(text(currentAddress));
            $("#output").$("#permanentAddress").shouldHave(text(permanentAddress));
        }
    }

    @Disabled
    @ParameterizedTest(name = "Проверка работы text-Box с CsvSource")
    @Tag("Blocker")
    @DisplayName("Проверка работы text-Box с CsvSource")
    @CsvSource(value = {
            "Kazakov Vitaliy | Kazakov@yandex.ru | Holzunova | Volgograd",
            "Korobov Mikhail | Korobov@mail.ru | Titova | Moskva",
            "Kosterin Mikhail | Kosterin@gmail.com | Kuznecova | Ufa"
    }, delimiter = '|')
    public void textBoxCsvSourceTest(String fullName, String mail, String currentAddress
            , String permanentAddress) {
        open("https://demoqa.com/text-box");
        $("[id=userName]").setValue(fullName);
        $("#userEmail").setValue(mail);
        $("#currentAddress").setValue(currentAddress);
        $("#permanentAddress").setValue(permanentAddress);
        $("#submit").scrollTo().click();

        $("#output #name").shouldHave(text(fullName));
        $("#output").$("#email").shouldHave(text(mail));
        $("#output").$("#currentAddress").shouldHave(text(currentAddress));
        $("#output").$("#permanentAddress").shouldHave(text(permanentAddress));
    }

    @ParameterizedTest(name = "Проверка работы text-Box с ValueSource")
    @Tag("Blocker")
    @DisplayName("Проверка работы text-Box с ValueSource")
    @ValueSource(strings = {"TEST", "TEST1"})
    public void textBoxValueSourceTest(String input) {
        open("https://demoqa.com/text-box");
        $("[id=userName]").setValue(input);
        $("#userEmail").setValue("test@mail.ru");
        $("#currentAddress").setValue(input);
        $("#permanentAddress").setValue(input);
        $("#submit").scrollTo().click();

        $("#output #name").shouldHave(text(input));
        $("#output").$("#email").shouldHave(text("test@mail.ru"));
        $("#output").$("#currentAddress").shouldHave(text(input));
        $("#output").$("#permanentAddress").shouldHave(text(input));
    }


}
