package smoke;

import methods.testMethods;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.*;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class testMethodsAssertionsTask1 {

    @BeforeEach
    void startTest() {
        System.out.println("========================Test method start");
    }

    @AfterEach
    void endTest() {
        System.out.println("Test method end");
        System.out.println("========================");
    }

//    Задача 1: написать 4 автотеста, использующих в работе ассерты.
//    Методы, которые будут проверяться автотестами, можно написать самостоятельно или взять из предыдущих заданий.
//    Требования к методам — хотя бы один метод:
//
//    возвращает булево значение;
//    должен возвращать список;
//    должен вернуть неверное значение (ассерт должен падать).
//    При падении ассерты должны дать информацию, что ожидалось и что было получено в результате падения.

    @RepeatedTest(10)
    @DisplayName("boolean isEven(int n) — запустить метод один раз со случайным числом от 1 до 100 и проверить его на чётность")
    void isEvenTest() {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 101);

        boolean checkResult = (randomNum  % 2 == 0 );
        boolean isEven = testMethods.isEven(randomNum);
        System.out.println("isEven(" + randomNum + ") -> " + isEven);
        Assertions.assertThat(isEven) // сюда я подставляю то, что должен возвращать мой метод в тесте
                .as("Число должно быть чётным, проверяется делениям на 2 без остатка. На вход получили число - %d", randomNum)
                .isEqualTo(checkResult); // Здесь я подставляю проверку
    }

    @RepeatedTest(10)
    @DisplayName("boolean isEven(int n) — запустить метод один раз со случайным числом от 1 до 100 и проверить его на чётность")
    void isEvenCustomTest() {
        int customNum = 1;
        boolean isEven = testMethods.isEven(customNum);
        System.out.println("isEven(" + customNum + ") -> " + isEven);
        Assertions.assertThat(isEven) // сюда я подставляю то, что должен возвращать мой метод в тесте
                .as("Число должно быть чётным, проверяется делениям на 2 без остатка. На вход получили число - %d", customNum)
                .isTrue();
    }

//    @RepeatedTest(10)
//    @DisplayName("boolean isEven(int n) — запустить метод один раз со случайным числом от 1 до 100 и проверить его на чётность")
//    void isEvenTestIfOff() {
//        int randomNum = ThreadLocalRandom.current().nextInt(1, 101);
//        boolean isEven = testMethods.isEven(randomNum);
//        System.out.println("isEven(" + randomNum + ") -> " + isEven);
//
//        boolean checkResult = (randomNum  % 2 == 0 );
//
////        Какой смысл от этой записи, если в каждой переменной будет одно и тоже значени?
////        Здесь я никогда не попаду на вывод сообщения.
//        assertEquals(checkResult, isEven,
//                () -> String.format("Тест проверяет метод проверки чётности числа. На вход пришло %s число - %d. Метод возвращает - %b",
//                        (checkResult ? "чётное" : "нечётное"), randomNum, isEven));
//    }

    @RepeatedTest(10)
    @DisplayName("boolean isEven(int n) — запустить метод один раз со случайным числом от 1 до 100 и проверить его на чётность")
    void isEvenTestTryCatch() {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 101);
        boolean isEven = testMethods.isEven(randomNum);
        System.out.println("isEven(" + randomNum + ") -> " + isEven);

        boolean checkResult = (randomNum  % 2 == 0 );
//        Что я могу проверить в этой обработке?
//        Напримемер, что я ожидаю чётное число, а может прийти нёчетное
        try {
            assertTrue(checkResult, String.format("Тест проверяет метод проверки чётности числа. На вход пришло нечётное число - %d. Метод возвращает - %s", randomNum, isEven));
        } catch (AssertionError e) {
            throw e;
        }
    }

}
