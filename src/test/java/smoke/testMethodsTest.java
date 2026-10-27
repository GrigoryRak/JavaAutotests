package smoke;

import methods.testMethods;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
    void checkAccessTest() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 100);
        String checkAccessResult = testMethods.checkAccess(randomNum);
        System.out.println("Случайный возраст (" + randomNum + ") -> " + checkAccessResult);
    }

    static IntStream intArray() {
        return ThreadLocalRandom.current().ints(20, 0, 101);
    }

    @ParameterizedTest
    @MethodSource("intArray")
    @DisplayName("String getGrade(int score) — запустить метод в параметризованных тестах с массивом случайных чисел от 0 до 100;")
    void getGradeTest(int score) {
        String getGrade = testMethods.getGrade(score);
        System.out.println("Входит в границу (" + score + ") -> " + getGrade);
    }

    @RepeatedTest(10)
    @DisplayName("Тестирование метода по возврату true, если переданное число больше или равно нулю, и false, если переданное число меньше нуля.")
    void isPositiveTest() {
        int randomNum = ThreadLocalRandom.current().nextInt(-10, 10);
        boolean isPositiveResult = testMethods.isPositive(randomNum);
        System.out.println("isPositive(" + randomNum + ") -> " + isPositiveResult);
    }

    @RepeatedTest(5)
    @DisplayName("Тестирование метода, который принимает стартовое число (например, 5) и возвращает строку со всеми числами до 1 и словом «Поехали!» в конце (например, «5 4 3 2 1 Поехали!»).")
    void blastOffTest() {
        int randomNum = ThreadLocalRandom.current().nextInt(-1, 10);
        String blastOffResult = testMethods.blastOff(randomNum);
        System.out.println("blastOff(" + randomNum + ") -> " + blastOffResult);
    }

    @RepeatedTest(5)
    @DisplayName("Тестирование метода, который возвращает сумму всех целых чисел от 1 до n")
    void sumToNTest() {
        int randomNum = ThreadLocalRandom.current().nextInt(-1, 5);
        int sumToNResult = testMethods.sumToN(randomNum);
        System.out.println("sumToN(" + randomNum + ") -> " + sumToNResult);
    }

    static Stream<Arguments> generateRandomWordsWithBugArray() {
        String[] wordslib = {"Test", "Autotest", "Java", "Gradle", "Bug", "Abra", "Run", "KoKo", "Suite", "Codabra"};
        List<String> resultList = new ArrayList<>();
        int totalCount = ThreadLocalRandom.current().nextInt(3, 11);
//        resultList.add("Bug");

        for (int i = 1; i < totalCount; i++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(0, wordslib.length);
            resultList.add(wordslib[randomIndex]);
        }

        // Делаем размес слов
        Collections.shuffle(resultList);
        String[] arrayOfWords = resultList.toArray(new String[0]);
        // Передаем массив в тест как единый аргумент
        // Добавил передачу второго массива для теста метода removeSpecificNameTest
        return Stream.of(Arguments.arguments((Object) arrayOfWords, (Object) arrayOfWords));
    }

    @ParameterizedTest
    @MethodSource("generateRandomWordsWithBugArray")
    @DisplayName("Тест метода, который проверяет наличие слова Bug")
    void hasBugTest(String[] words) {
        boolean hasBug = testMethods.hasBug(words);
        System.out.println("Массив слов ["  + String.join(", ", words) + "] -> " + hasBug);
    }

    @RepeatedTest(5)
    @DisplayName("Тестирование метода, который возвращает чётные значения из диапазона чисел")
    void getEvenInRangeTest() {
        int startRandomNum = ThreadLocalRandom.current().nextInt(-10, 11);
        int endRandomNum = ThreadLocalRandom.current().nextInt(12, 22);
        String getEvenInRangeResult = testMethods.getEvenInRange(startRandomNum,endRandomNum);
        System.out.println("getEvenInRange - диапазон [" + startRandomNum + " : " + endRandomNum +  "] -> " + getEvenInRangeResult);
    }

    static Stream<Arguments> intStreamArray() {
        int[] array = ThreadLocalRandom.current().ints(20, 0, 101).toArray();
        return Stream.of(Arguments.arguments((Object)array));
    }

    @ParameterizedTest
    @MethodSource("intStreamArray")
    @DisplayName("Тестирование метода, который возвращает самое большое число из массива")
    void findMaxTest(int[] findMaxArr) {
        int findMaxResult = testMethods.findMax(findMaxArr);
        System.out.println("Массив значений "  + java.util.Arrays.toString(findMaxArr) + " Максимальное число -> " + findMaxResult);
    }

    @ParameterizedTest
    @MethodSource("generateRandomWordsWithBugArray")
    @DisplayName("Тестирование метода, который возвращает перевёрнутый массив")
    void reverseTest(String[] words) {
        String reverseResult = Arrays.toString(testMethods.reverse(words));
        System.out.println("Оригинальный массив слов ["  + String.join(", ", words) + "] ->  Зеркальный массив слов " + reverseResult);
    }

    @ParameterizedTest
    @MethodSource("intStreamArray")
    @DisplayName("Тестирование метода, который вычисляет и возвращает среднее арифметическое всех чисел в списке")
    void calcAverageTest(int[] numbersArray) {
        List<Integer> numbersArrayList = Arrays.stream(numbersArray)
                .boxed()
                // Массив в список
                .toList();
        double calcAverageResult = testMethods.calcAverage(numbersArrayList);
        System.out.println("Массив значений "  + java.util.Arrays.toString(numbersArray) + " Среднее арифмитическое -> " + calcAverageResult);
    }

    @ParameterizedTest
    @MethodSource("generateRandomWordsWithBugArray")
    @DisplayName("Тестирование метода, который исключает слово из списка")
    void removeSpecificNameTest(String[] startArray, String[] removeWordArray) {
        List<String> startArrayList = Arrays.stream(startArray).toList();

        // тот же самы массив, но из него я произвольно вытаскиваю любое слов
        List<String> removeWordArrayList = Arrays.stream(removeWordArray).toList();
        int randomIndex = ThreadLocalRandom.current().nextInt(0, removeWordArrayList.size());
        String randomWord = removeWordArrayList.get(randomIndex);

        List removeSpecificNameResult = testMethods.removeSpecificName(startArrayList, randomWord);
        System.out.println("Оригинальный список слов ["  + String.join(", ", startArray) + "] ->  Список " + removeSpecificNameResult + " с удаленным словом  " + randomWord);
    }
}
