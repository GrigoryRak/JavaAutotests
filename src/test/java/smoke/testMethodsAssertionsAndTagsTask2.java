package smoke;

import methods.testMethods;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
    @Tag("workBool")
    @DisplayName("boolean isEven(int n) — запустить метод один раз со случайным числом от 1 до 100 и проверить его на чётность")
    void isEvenTest() {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 101);
        boolean isEven = testMethods.isEven(randomNum);
        System.out.println("isEven(" + randomNum + ") -> " + isEven);
        boolean checkResult = (randomNum % 2 == 0);
        try {
            assertTrue(checkResult, String.format("Тест проверяет метод проверки чётности числа. На вход пришло нечётное число - %d. Метод возвращает - %s", randomNum, isEven));
        } catch (AssertionError e) {
            throw e;
        }
    }

    @RepeatedTest(10)
    @Tag("workBool")
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

    static IntStream intArray() {
        return ThreadLocalRandom.current().ints(10, 0, 301);
    }

    @ParameterizedTest
    @MethodSource("intArray")
    @Tag("workStrings")
    @DisplayName("String getGrade(int score) — запустить метод в параметризованных тестах с массивом случайных чисел от 0 до 100 и проверить на вхождение в границы;")
    void getGradeTest(int score) {

        String getGrade = testMethods.getGrade(score);
        System.out.println("Входит в границу (" + score + ") -> " + getGrade);

        String checkGradeResult;
        if (score >= 0 && score <= 20) {
            checkGradeResult = "K";
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

        Assertions.assertAll("Проверяем число на вход и результат метода",
                () -> Assertions.assertTrue(score >= 0 && score <= 100,
                        () -> "Число на вход " + score + " вышло за пределы диапазона [0, 100]!"),
                () -> Assertions.assertEquals(checkGradeResult, getGrade,
                        String.format("Ошибка вхождения в диапазон чисел %d! Ожидался диапазон: %s, но получен из метода: %s", score, checkGradeResult, getGrade))
        );
    }

    @RepeatedTest(10)
    @Tag("workBool")
    @DisplayName("Тестирование метода по возврату true, если переданное число больше или равно нулю, и false, если переданное число меньше нуля.")
    void isPositiveTest() {
        int randomNum = ThreadLocalRandom.current().nextInt(-10, 10);
        boolean checkResult = (randomNum >= 0);

        boolean isPositiveResult = testMethods.isPositive(randomNum);
        System.out.println("isPositive(" + randomNum + ") -> " + isPositiveResult);

        Assertions.assertTrue(checkResult && isPositiveResult,
                () -> "Число оказалось строго меньше нуля " + randomNum + "\n"
                        + "Проверка теста вернула " + checkResult + "\n"
                        + "Метод вернул " + isPositiveResult);
    }

    @RepeatedTest(10)
    @Tag("workStrings")
    @DisplayName("Тестирование метода, который принимает стартовое число (например, 5) и возвращает строку со всеми числами до 1 и словом «Поехали!» в конце (например, «5 4 3 2 1 Поехали!»).")
    void blastOffTest() {
        int randomNum = ThreadLocalRandom.current().nextInt(-1, 10);
        String blastOffResult = testMethods.blastOff(randomNum);
        System.out.println("blastOff(" + randomNum + ") -> " + blastOffResult);

        String keyWord;
        if (randomNum >= 1) {
            StringJoiner sj = new StringJoiner(" ");
            for (int i = randomNum; i >= 1; i--) sj.add(String.valueOf(i));
            sj.add("Поехали!");
            keyWord = sj.toString();
        } else {
            keyWord = "Поехали!";
        }

        System.out.println("Контрольная проверка вывода -> " + keyWord);

        Assertions.assertTrue(keyWord.equalsIgnoreCase(blastOffResult),
                () -> "Значение на вход " + randomNum + "\n"
                        + "Ответ полученный из метода: " + blastOffResult + "\n"
                        + "Текст проверки: " + keyWord);
    }

    @RepeatedTest(10)
    @Tag("workNumbers")
    @DisplayName("Тестирование метода, который возвращает сумму всех целых чисел от 1 до n")
    void sumToNTest() {
        int randomNum = ThreadLocalRandom.current().nextInt(-1, 5);
        int sumToNResult = testMethods.sumToN(randomNum);
        System.out.println("sumToN(" + randomNum + ") -> " + sumToNResult);

        int checkResultSum = (randomNum < 1) ? 0 : ((randomNum * (randomNum + 1)) / 2);

        Assertions.assertEquals(checkResultSum, sumToNResult);

        Assertions.assertNotEquals(0, sumToNResult,
                () -> "Ошибка! Метод вернул 0 для числа " + randomNum + ", а 0 возвращаться не должен.");
    }

    static Stream<Arguments> generateRandomWordsWithBugArray() {
        String[] wordslib = {"Test", "Autotest", "Java", "Gradle", "Bug", "Abra", "Run", "KoKo", "Suite", "Codabra"};
        return Stream.generate(() -> {
                    List<String> resultList = new ArrayList<>();
                    int totalCount = ThreadLocalRandom.current().nextInt(3, 11);
//           resultList.add("Bug");

                    for (int i = 1; i < totalCount; i++) {
                        int randomIndex = ThreadLocalRandom.current().nextInt(0, wordslib.length);
                        resultList.add(wordslib[randomIndex]);
                    }

                    // Делаем размес слов
                    Collections.shuffle(resultList);
                    String[] arrayOfWords = resultList.toArray(new String[0]);
                    // Передаем массив в тест как единый аргумент
                    // Добавил передачу второго массива для теста метода removeSpecificNameTest
                    return Arguments.arguments((Object) arrayOfWords, (Object) arrayOfWords);
                })
                .limit(10); // <-- Ограничиваем поток ровно 10 итерациями
    }

    @ParameterizedTest
    @Tag("workArray")
    @MethodSource("generateRandomWordsWithBugArray")
    @DisplayName("Тест метода, который проверяет наличие слова Bug")
    void hasBugTest(String[] words) {
        boolean hasBug = testMethods.hasBug(words);
        System.out.println("Массив слов [" + String.join(", ", words) + "] -> " + hasBug);
        boolean checkResult = false;
        for (String word : words) {
            if ("Bug".equals(word)) {
                checkResult = true;
                break;
            }
        }

        final String[] finalWords = words;
        final boolean finalCheckResult = checkResult;
        final boolean finalHasBug = hasBug;

        Assertions.assertAll("Массивы на вход не содержат слова Bug",
                () -> Assertions.assertEquals(finalCheckResult, finalHasBug, ""),
                () -> Assertions.assertTrue(finalCheckResult, "В проверочном массиве не найдено слова Bug"),
                () -> Assertions.assertTrue(finalHasBug, "В массиве метода не найдено слова Bug")
        );
    }

    @RepeatedTest(10)
    @Tag("workNumbers")
    @DisplayName("Тестирование метода, который возвращает чётные значения из диапазона чисел")
    void getEvenInRangeTest() {
        int startRandomNum = ThreadLocalRandom.current().nextInt(-10, 11);
        int endRandomNum = ThreadLocalRandom.current().nextInt(12, 23);
        String getEvenInRangeResult = testMethods.getEvenInRange(startRandomNum, endRandomNum);
        System.out.println("getEvenInRange - диапазон [" + startRandomNum + " : " + endRandomNum + "] -> " + getEvenInRangeResult);

        java.util.StringJoiner result = new java.util.StringJoiner(" ");
        for (int i = startRandomNum; i <= endRandomNum; i++) {
            if (i % 2 == 0) {
                result.add(String.valueOf(i));
            }
        }
        String resultStr = result.toString();
        System.out.println("Контрольная проверка диапазона -> " + resultStr);

        Assertions.assertAll("Проверка возвращаемого диапазона чётных чисел",
                () -> Assertions.assertEquals(resultStr, getEvenInRangeResult,
                        () -> "Ошибка для диапазона [" + startRandomNum + " : " + endRandomNum + "]. "
                                + "Ожидалось: '" + resultStr + "', но получено: '" + getEvenInRangeResult + "'")
        );
    }


    static Stream<Arguments> intStreamArray() {
        return Stream.generate(() -> {
                    int[] array = ThreadLocalRandom.current().ints(10, 0, 101).toArray();
                    return Arguments.arguments((Object) array);
                })
                .limit(10);
    }

    @ParameterizedTest
    @MethodSource("intStreamArray")
    @Tag("workArray")
    @DisplayName("Тестирование метода, который возвращает самое большое число из массива")
    void findMaxTest(int[] findMaxArr) {
        int findMaxResult = testMethods.findMax(findMaxArr);
        System.out.println("Массив значений " + java.util.Arrays.toString(findMaxArr) + " Максимальное число -> " + findMaxResult);

        int findMaxCheck = java.util.Arrays.stream(findMaxArr).max().getAsInt();
        System.out.println("Контрольное максимальное число -> " + findMaxCheck);

        Assertions.assertEquals(findMaxCheck, findMaxResult,
                () -> "Ошибка для массива " + java.util.Arrays.toString(findMaxArr) + ". "
                        + "Ожидалось максимальное число: " + findMaxCheck
                        + ", но метод вернул: " + findMaxResult);
    }

    @ParameterizedTest
    @MethodSource("generateRandomWordsWithBugArray")
    @Tag("workArray")
    @DisplayName("Тестирование метода, который возвращает перевёрнутый массив")
    void reverseTest(String[] words) {
        String[] reverseResultArr = testMethods.reverse(words);
        String reverseResult = Arrays.toString(reverseResultArr);
        System.out.println("Оригинальный массив слов [" + String.join(", ", words) + "] ->  Зеркальный массив слов " + reverseResult);

        String[] mirrorCheck = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            mirrorCheck[i] = words[words.length - 1 - i];
        }
        String mirrorCheckResult = Arrays.toString(mirrorCheck);
        System.out.println("Контрольный перевёрнутый массив -> " + mirrorCheckResult);

        Assertions.assertArrayEquals(mirrorCheck, reverseResultArr, "Массивы должны быть идентичны");

    }

    @ParameterizedTest
    @MethodSource("intStreamArray")
    @Tag("workList")
    @DisplayName("Тестирование метода, который вычисляет и возвращает среднее арифметическое всех чисел в списке")
    void calcAverageTest(int[] numbersArray) {
        List<Integer> numbersArrayList = Arrays.stream(numbersArray)
                .boxed()
                // Массив в список
                .toList();
        double calcAverageResult = testMethods.calcAverage(numbersArrayList);
        System.out.println("Массив значений " + java.util.Arrays.toString(numbersArray) + " Среднее арифмитическое -> " + calcAverageResult);

        double sum = 0;
        for (int num : numbersArrayList) {
            sum += num;
        }
        double calcAverageCheck = sum / numbersArrayList.size();
        System.out.println("Контрольная проверка среднего арифметического значения -> " + calcAverageCheck);

        Assertions.assertEquals(calcAverageCheck, calcAverageResult,
                "Среднее арифметическое вычислено неверно!");
    }

    @RepeatedTest(10)
    @Tag("workList")
    @DisplayName("Тестирование метода, который исключает слово из списка")
    void removeSpecificNameTestSuccess() {
        List<String> startList = List.of("Test", "Autotest", "Java", "Gradle", "Bug", "Abra", "Run", "KoKo", "Suite", "Codabra");
        List<String> removeWordList = List.of("Test5", "KoKo");

        int randomIndex = ThreadLocalRandom.current().nextInt(0, removeWordList.size());
        String randomWord = removeWordList.get(randomIndex);

        if (!startList.contains(randomWord)) {
            try {
                List checkList = testMethods.removeSpecificName(startList, randomWord);
                fail("Слова на удаление " + randomWord + " нет в исходном списке: " + startList + "\n" + "Проверка вернула список без изменения: " + checkList);
            } catch (NoSuchElementException e) {
            }
        } else {
            List removeSpecificNameResult = testMethods.removeSpecificName(startList, randomWord);
            List<String> checkList = new java.util.ArrayList<>(startList);
            checkList.removeAll(java.util.Collections.singleton(randomWord));
            System.out.println("Список на вход: " + startList + "\n"
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
    @Tag("workList")
    @DisplayName("Тестирование метода, который исключает слово из списка. Тест с намеренной ошибкой - 50/50")
    void removeSpecificNameTestFail() {
        List<String> startList = List.of("Test", "Autotest", "Java", "Gradle", "Bug", "Abra", "Run", "KoKo", "Suite", "Codabra");
        List<String> startListFail = List.of("Test", "Autotest", "Java", "Gradle", "Bug", "Abra", "Run", "KoKo", "Suite", "Codabra", "KukU");
        List<String> removeWordList = List.of("Test", "KoKo", "KvaKva");

        int randomIndex = ThreadLocalRandom.current().nextInt(0, removeWordList.size());
        String randomWord = removeWordList.get(randomIndex);

        if (!startList.contains(randomWord)) {
            try {
                List checkList = testMethods.removeSpecificName(startList, randomWord);
                fail("Слова на удаление " + randomWord + " нет в исходном списке: " + startList + "\n" + "Проверка вернула список без изменения: " + checkList);
            } catch (NoSuchElementException e) {
            }
        } else {
            List removeSpecificNameResult = testMethods.removeSpecificName(startListFail, randomWord);
            List<String> checkList = new java.util.ArrayList<>(startList);
            checkList.removeAll(java.util.Collections.singleton(randomWord));
            System.out.println("Список на вход: " + startList + "\n"
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
