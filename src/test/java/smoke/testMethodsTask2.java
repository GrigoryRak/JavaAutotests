package smoke;

import methods.testMethods;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class testMethodsTask2 {

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
        if (isEven) {
            System.out.println("TEST PASSED");
        } else
            System.out.println("TEST FAILED");
    }

    @RepeatedTest(20)
    @DisplayName("Проверка возраста (Если строго больше 18 тест считается пройденным) — запустить метод 20 раз со случайными числами от 0 до 99;")
    void checkAccessTest() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 100);
        String checkAccessResult = testMethods.checkAccess(randomNum);
        System.out.println("Случайный возраст (" + randomNum + ") -> " + checkAccessResult);
        if (checkAccessResult.equals("Allowed")){
            System.out.println("TEST PASSED");
        } else
            System.out.println("TEST FAILED");
    }

    static IntStream intArray() {
        return ThreadLocalRandom.current().ints(20, 0, 101);
    }

    @ParameterizedTest
    @MethodSource("intArray")
    @DisplayName("String getGrade(int score) — запустить метод в параметризованных тестах с массивом случайных чисел от 0 до 100 и проверить на вхождение в границы;")
    void getGradeTest(int score) {
        String getGrade = testMethods.getGrade(score);
        System.out.println("Входит в границу (" + score + ") -> " + getGrade);
        if (getGrade.matches("[A-E]")){
            System.out.println("TEST PASSED");
        } else
            System.out.println("TEST FAILED");
    }

    @RepeatedTest(10)
    @DisplayName("Тестирование метода по возврату true, если переданное число больше или равно нулю, и false, если переданное число меньше нуля.")
    void isPositiveTest() {
        int randomNum = ThreadLocalRandom.current().nextInt(-10, 10);
        boolean isPositiveResult = testMethods.isPositive(randomNum);
        System.out.println("isPositive(" + randomNum + ") -> " + isPositiveResult);
        if (isPositiveResult) {
            System.out.println("TEST PASSED");
        } else
            System.out.println("TEST FAILED");
    }

    @RepeatedTest(10)
    @DisplayName("Тестирование метода, который принимает стартовое число (например, 5) и возвращает строку со всеми числами до 1 и словом «Поехали!» в конце (например, «5 4 3 2 1 Поехали!»).")
    void blastOffTest() {
        int randomNum = ThreadLocalRandom.current().nextInt(-1, 10);
        String blastOffResult = testMethods.blastOff(randomNum);
        System.out.println("blastOff(" + randomNum + ") -> " + blastOffResult);

        String keyWord = "Поехали!";
        if (randomNum >= 1) {
            StringJoiner sj = new StringJoiner(" ");
            for (int i = randomNum; i >= 1; i--) sj.add(String.valueOf(i));
            sj.add("Поехали!");
            keyWord = sj.toString();
        }

        System.out.println("Контрольная проверка вывода -> " + keyWord);

        if (keyWord.equals(blastOffResult)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }

    }

    @Test
    @DisplayName("Тестирование метода, который возвращает сумму всех целых чисел от 1 до n")
    void sumToNTest() {
        int randomNum = ThreadLocalRandom.current().nextInt(-1, 5);
        int sumToNResult = testMethods.sumToN(randomNum);
        System.out.println("sumToN(" + randomNum + ") -> " + sumToNResult);

        int checkResultSum = (randomNum < 1) ? 0 : ((randomNum * (randomNum + 1)) / 2);

        if (sumToNResult == checkResultSum){
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    @RepeatedTest(10)
    @DisplayName("Тестирование метода, который возвращает сумму всех целых чисел от 1 до n")
    void sumToN2Test() {
        int randomNum = ThreadLocalRandom.current().nextInt(-10, 5);
        int sumToNResult = testMethods.sumToN(randomNum);
        System.out.println("sumToN(" + randomNum + ") -> " + sumToNResult);

        int checkResultSum = (randomNum < 1) ? 0 : ((randomNum * (randomNum + 1)) / 2);

        if (sumToNResult == checkResultSum){
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
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
        if (hasBug) {
            System.out.println("TEST PASSED");
        } else
            System.out.println("TEST FAILED");
    }

    @Test
    @DisplayName("Тест метода, который проверяет наличие слова Bug")
    void searchBugWordTest() {
        String[] wordslib = {"Test", "Autotest", "Java", "Gradle", "Bug", "Abra", "Run", "KoKo", "Suite", "Codabra"};
        boolean hasBug = testMethods.hasBug(wordslib);
        System.out.println("Массив слов ["  + String.join(", ", wordslib) + "] -> " + hasBug);
        if (hasBug) {
            System.out.println("TEST PASSED");
        } else
            System.out.println("TEST FAILED");
    }

    @Test
    @DisplayName("Тестирование метода, который возвращает чётные значения из диапазона чисел")
    void getEvenInRangeTest() {
        int startRandomNum = ThreadLocalRandom.current().nextInt(-10, 11);
        int endRandomNum = ThreadLocalRandom.current().nextInt(12, 22);
        String getEvenInRangeResult = testMethods.getEvenInRange(startRandomNum,endRandomNum);
        System.out.println("getEvenInRange - диапазон [" + startRandomNum + " : " + endRandomNum +  "] -> " + getEvenInRangeResult);

        java.util.StringJoiner result = new java.util.StringJoiner(" ");
        for (int i = startRandomNum; i <= endRandomNum; i++) {
            if (i % 2 == 0) {
                result.add(String.valueOf(i));
            }
        }
        String resultStr = result.toString();
        System.out.println("Контрольная проверка диапазона -> " + resultStr);

        if (resultStr.equals(getEvenInRangeResult)) {
            System.out.println("TEST PASSED");
        } else
            System.out.println("TEST FAILED");
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

        int findMaxCheck = java.util.Arrays.stream(findMaxArr).max().getAsInt();
        System.out.println("Контрольное максимальное число -> " + findMaxCheck);

        if (findMaxResult == findMaxCheck) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    @ParameterizedTest
    @MethodSource("generateRandomWordsWithBugArray")
    @DisplayName("Тестирование метода, который возвращает перевёрнутый массив")
    void reverseTest(String[] words) {
        String reverseResult = Arrays.toString(testMethods.reverse(words));
        System.out.println("Оригинальный массив слов ["  + String.join(", ", words) + "] ->  Зеркальный массив слов " + reverseResult);

        String[] mirrorCheck = new String[words.length];
        for (int i=0; i<words.length; i++){
            mirrorCheck[i]=words[words.length-1-i];
        }
        String mirrorCheckResult = Arrays.toString(mirrorCheck);
        System.out.println("Контрольный перевёрнутый массив -> " + mirrorCheckResult);

        if (mirrorCheckResult.equals(reverseResult)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
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

        double sum = 0;
        for (int num : numbersArrayList) {
            sum += num;
        }
        System.out.println("Контрольная проверка среднего арифметического значения -> " + sum/numbersArrayList.size());

        if (calcAverageResult == (sum/numbersArrayList.size())) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    @ParameterizedTest
    @MethodSource("generateRandomWordsWithBugArray")
    @DisplayName("Тестирование метода, который исключает слово из списка")
    void removeSpecificNameTest(String[] startArray, String[] removeWordArray) {
        List<String> startArrayList = Arrays.stream(startArray).toList();

        // тот же самый массив, но из него я произвольно вытаскиваю любое слов
        List<String> removeWordArrayList = Arrays.stream(removeWordArray).toList();
        int randomIndex = ThreadLocalRandom.current().nextInt(0, removeWordArrayList.size());
        String randomWord = removeWordArrayList.get(randomIndex);

        List removeSpecificNameResult = testMethods.removeSpecificName(startArrayList, randomWord);
        System.out.println("Оригинальный список слов ["  + String.join(", ", startArray) + "] ->  Список " + removeSpecificNameResult + " с удаленным словом  " + randomWord);

        List<String> checkList = new java.util.ArrayList<>(startArrayList);
        checkList.removeAll(java.util.Collections.singleton(randomWord));
        System.out.println("Контрольная проверка удаляемого слова -> " + randomWord);
        System.out.println("Контрольная проверка исходного списка -> " + startArrayList);
        System.out.println("Контрольная проверка списка с удалённым словом -> " + checkList);

        if (checkList.equals(removeSpecificNameResult)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }
}
