package smoke;

import methods.testMethods;
import org.junit.jupiter.api.*;

import java.util.concurrent.ThreadLocalRandom;

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

}
