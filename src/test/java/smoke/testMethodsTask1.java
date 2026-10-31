package smoke;

import methods.testMethods;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static java.lang.System.in;

public class testMethodsTask1 {

    @BeforeEach
    void startTest() {
        System.out.println("========================Test method start");
    }

    @AfterEach
    void endTest() {
        System.out.println("Test method end");
        System.out.println("========================");
    }

    @Test
    @DisplayName("boolean isEven(int n) — запустить метод один раз со случайным числом от 1 до 100 и проверить его на чётность")
    void isEvenTest() {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 101);
        boolean isEven = testMethods.isEven(randomNum);
        System.out.println("isEven(" + randomNum + ") -> " + isEven);
        if (randomNum  % 2 == 0 ) {
            System.out.println("TEST PASSED");
        } else
            System.out.println("TEST FAILED");
    }

    @RepeatedTest(20)
    @DisplayName("Проверка возраста (Если строго больше 18 тест считается пройденным) — запустить метод 20 раз со случайными числами от 0 до 99;")
    void checkAccessTest() {
        int randomNum = ThreadLocalRandom.current().nextInt(-2, 100);
        String checkAccessResult = testMethods.checkAccess(randomNum);
        System.out.println("Случайный возраст (" + randomNum + ") -> " + checkAccessResult);
        if (randomNum > 18){
            System.out.println("TEST PASSED");
        } else
            System.out.println("TEST FAILED");
    }

    static IntStream intArray() {
        return ThreadLocalRandom.current().ints(20, 0, 204);
    }

    @ParameterizedTest
    @MethodSource("intArray")
    @DisplayName("String getGrade(int score) — запустить метод в параметризованных тестах с массивом случайных чисел от 0 до 100 и проверить на вхождение в границы;")
    void getGradeTest(int score) {
        String getGrade = testMethods.getGrade(score);
        System.out.println("Входит в границу (" + score + ") -> " + getGrade);

        String checkGradeResult;
        if (score >= 0 && score <= 20) {
            checkGradeResult = "E";
        } else if (score >= 21 && score <= 40) {
            checkGradeResult = "D";
        } else if (score >= 41 && score <= 60) {
            checkGradeResult = "C";
        } else if (score >= 61 && score <= 80) {
            checkGradeResult = "B";
        } else if (score >= 81 && score <= 100) {
            checkGradeResult = "A";
        } else {
            checkGradeResult = "Error";
        }
        if (checkGradeResult.equals(getGrade) && !checkGradeResult.equals("Error")){
            System.out.println("TEST PASSED");
        } else
            System.out.println("TEST FAILED");
    }

}
