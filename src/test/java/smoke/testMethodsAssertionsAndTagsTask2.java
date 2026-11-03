package smoke;

import methods.testMethods;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class testMethodsAssertionsAndTagsTask2 {

    @BeforeEach
    void startTest() {
        System.out.println("========================Test method start");
    }

    @AfterEach
    void endTest() {
        System.out.println("Test method end");
        System.out.println("========================");
    }

//    Задача 2: добавить в автотесты разработанные в задаче 2 темы «Gradle и JUnit» информативные ассерты
//    (заменить проверки через if на ассерты). При падении ассерты должны дать информацию, что ожидалось
//    и что было получено в результате падения.
//    Дополнить задачу запуска автотестов фильтрацией, или по аннотации @Tag,
//    или по пакету. Запустить каждый тестовый метод не менее 10 раз.

    @RepeatedTest(10)
    @DisplayName("boolean isEven(int n) — запустить метод один раз со случайным числом от 1 до 100 и проверить его на чётность")
    void isEvenTest() {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 101);
        boolean isEven = testMethods.isEven(randomNum);
        System.out.println("isEven(" + randomNum + ") -> " + isEven);
        boolean checkResult = (randomNum  % 2 == 0 );
        try {
            assertTrue(checkResult, String.format("Тест проверяет метод проверки чётности числа. На вход пришло нечётное число - %d. Метод возвращает - %s", randomNum, isEven));
        } catch (AssertionError e) {
            throw e;
        }
    }

    @RepeatedTest(10)
    @DisplayName("Проверка возраста (Если строго больше 18 тест считается пройденным) — запустить метод 20 раз со случайными числами от 0 до 99;")
    void checkAccessTest() {
        int randomNum = ThreadLocalRandom.current().nextInt(-2, 100);
        String checkAccessResult = testMethods.checkAccess(randomNum);
        System.out.println("Случайный возраст (" + randomNum + ") -> " + checkAccessResult);
        boolean minAge = randomNum > 18;

        assertThat(minAge)
                .as("Возраст менее или равен 18 годам. Значение на вход: " + randomNum)
                .isTrue();
    }

    @RepeatedTest(10)
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

    @RepeatedTest(10)
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
