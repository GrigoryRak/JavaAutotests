package smoke;

import methods.testMethods;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

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

    @RepeatedTest(5)
    @DisplayName("Тестирование метода, который исключает слово из списка")
    void removeSpecificNameTestSuccess() {
        List<String> startList = List.of("Test", "Autotest", "Java", "Gradle", "Bug", "Abra", "Run", "KoKo", "Suite", "Codabra");
        List<String> removeWordList = List.of("Test5", "KoKo");

        int randomIndex = ThreadLocalRandom.current().nextInt(0, removeWordList.size());
        String randomWord = removeWordList.get(randomIndex);

        if (!startList.contains(randomWord)) {
            try {
                List checkList = testMethods.removeSpecificName(startList, randomWord);
                fail ("Слова на удаление " + randomWord + " нет в исходном списке: " + startList + "\n" + "Проверка вернула список без изменения: " + checkList);
            } catch(NoSuchElementException e) {
            }
        } else {
            List removeSpecificNameResult = testMethods.removeSpecificName(startList, randomWord);
            List<String> checkList = new java.util.ArrayList<>(startList);
            checkList.removeAll(java.util.Collections.singleton(randomWord));
            System.out.println("Список на вход: "  + startList + "\n"
                    + "Список с удаленным словом: " + removeSpecificNameResult + "\n"
                    + "Проверочный список с удаленным словом: " + checkList + "\n"
                    + "Слово на удаление: " + randomWord);
            assertEquals(
                    checkList,
                    removeSpecificNameResult,
                    () -> String.format("Списки не совпадают! Ожидаемый: " + checkList + "\n"
                            + "Пришёл на вход: " + startList + "\n"
                            + "Удаляемое слово: " + randomWord)
            );
        }
    }

    @RepeatedTest(5)
    @DisplayName("Тестирование метода, который исключает слово из списка. Тест с намеренной ошибкой - 50/50")
    void removeSpecificNameTestFail() {
        List<String> startList = List.of("Test", "Autotest", "Java", "Gradle", "Bug", "Abra", "Run", "KoKo", "Suite", "Codabra");
        List<String> startListFail = List.of("Test","Autotest", "Java", "Gradle", "Bug", "Abra", "Run", "KoKo", "Suite", "Codabra", "KukU");
        List<String> removeWordList = List.of("Test", "KoKo", "KvaKva");

        int randomIndex = ThreadLocalRandom.current().nextInt(0, removeWordList.size());
        String randomWord = removeWordList.get(randomIndex);

        if (!startList.contains(randomWord)) {
            try {
                List checkList = testMethods.removeSpecificName(startList, randomWord);
                fail ("Слова на удаление " + randomWord + " нет в исходном списке: " + startList + "\n" + "Проверка вернула список без изменения: " + checkList);
            } catch(NoSuchElementException e) {
            }
        } else {
            List removeSpecificNameResult = testMethods.removeSpecificName(startListFail, randomWord);
            List<String> checkList = new java.util.ArrayList<>(startList);
            checkList.removeAll(java.util.Collections.singleton(randomWord));
            System.out.println("Список на вход: "  + startList + "\n"
                    + "Список с удаленным словом: " + removeSpecificNameResult + "\n"
                    + "Проверочный список с удаленным словом: " + checkList + "\n"
                    + "Слово на удаление: " + randomWord);
            assertEquals(
                    checkList,
                    removeSpecificNameResult,
                    () -> String.format("Списки не совпадают! Ожидаемый: " + checkList + "\n"
                            + "Пришёл на вход: " + removeSpecificNameResult + "\n"
                            + "Удаляемое слово: " + randomWord)
            );
        }
    }

}
