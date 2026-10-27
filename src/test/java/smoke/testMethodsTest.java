package smoke;

import methods.testMethods;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class testMethodsTest {

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
    @DisplayName("boolean isEven(int n) — запустить метод один раз со случайным числом от 1 до 100")
    void isEvenTest() {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 101);
        boolean isEven = testMethods.isEven(randomNum);
        System.out.println("isEven(" + randomNum + ") -> " + isEven);
    }

    @RepeatedTest(20)
    @DisplayName("String checkAccess(int age) — запустить метод 20 раз со случайными числами от 0 до 99;")
    void checkAccess() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 100);
        String checkAccessResult = testMethods.checkAccess(randomNum);
        System.out.println("Случайный возраст (" + randomNum + ") -> " + checkAccessResult);
    }

    static IntStream intArray() {
        return ThreadLocalRandom.current().ints(20,0,101);
    }

    @ParameterizedTest
    @MethodSource("intArray")
    @DisplayName("String getGrade(int score) — запустить метод в параметризованных тестах с массивом случайных чисел от 0 до 100;")
    void getGrade(int score) {
        String getGrade = testMethods.getGrade(score);
        System.out.println("Входит в границу (" + score + ") -> " + getGrade);
    }

}
