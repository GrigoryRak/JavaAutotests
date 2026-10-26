package methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class testMethods {
    //    Задача 1: разработать метод с сигнатурой publiс static boolean isEven(int n). Метод возвращает true, если число чётное, и false — если нечётное.
    public static boolean isEven(int n) {
        return n % 2 == 0;
    }

    //    Задача 2: разработать метод с сигнатурой public static String checkAccess(int age).
//    Метод возвращает Allowed, если число строго больше 18, и Denied — если меньше.
    public static String checkAccess(int age) {
        if (age > 18) {
            return "Allowed";
        } else return "Denied";
    }
    //    Задача 3: разработать метод с сигнатурой public static boolean isPositive(int n).
//    Метод должен возвращать true, если переданное число больше или равно нулю, и false, если переданное число меньше нуля.
//    Проверка внутри метода должна происходить с помощью тернарного оператора.
    public static boolean isPositive(int n){
        return (n>=0) ? true:false;
    }

//    Задача 4: разработать метод с сигнатурой public static String getGrade(int score). Метод возвращает строку, соответствующую строгому вхождению в границы:
//0–20: E;
//21–40: D;
//41–60: C;
//61–80: B;
//81–100: A.
//Если переданное число не входит в границы — вернуть строку Error.

    public static String getGrade(int score){
        return (score>=0 && score<=20) ? "E" :
                (score>=21 && score<=40) ? "D" :
                        (score>=41 && score<=60) ? "C" :
                                (score>=61 && score<=80) ? "B" :
                                        (score>=81 && score<=100) ? "A" : "Error";
    }

    //    Задача 5: разработать метод с сигнатурой public static String blastOff(int start).
//    Метод принимает стартовое число (например, 5) и возвращает строку со всеми числами до 1 и словом «Поехали!» в конце (например, «5 4 3 2 1 Поехали!»).
    public static String blastOff(int start){
        if (start > 0) {
            String result = "";
            for (int i=start; i>=1; i--){
                result += i + " ";
            }
            result += "Поехали!";
            return result;
        } else return "Введено число <=0. Вы ввели число: " + start;
    }

    //    Задача 6: разработать метод с сигнатурой public static int sumToN(int n). Метод возвращает сумму всех целых чисел от 1 до n.
    public static int sumToN(int n){
        if ( n < 1 ) {
            return 0;
        }
        return (n * (n + 1)) / 2;
    }

//    Задача 7: разработать метод с сигнатурой public static boolean hasBug(String[] messages).
//    Метод принимает массив строк и возвращает true, если хотя бы одна строка в массиве равна Bug. Сравнение можно выполнять без учёта регистра.

    public static boolean hasBug(String[] messages){
        if (messages == null || messages.length == 0) {
            return false;
        }
        for (String word:messages){
            if (word!=null && word.equalsIgnoreCase("bug")){
                return true;
            }
        }
        return false;
    }

//    Задача 8: разработать метод с сигнатурой public static getEvenInRange(int start, int end).
//    Метод принимает границы диапазона и возвращает строку, состоящую только из чётных чисел внутри этого промежутка (включая границы), разделённых пробелом.
//    Перед первым и после последнего числа пробел не ставится. Например: (2, 5) -> “2 4”

    public static String getEvenInRange(int start, int end) {
        StringBuilder stB = new StringBuilder();

        for (int i=start; i<=end; i++){
            if (i % 2 == 0) {
                stB.append(i).append(" ");
            }
        }
        return stB.toString().trim();
    }

    //    Задача 9: разработать метод с сигнатурой public static int findMax(int[] arr). Метод находит и возвращает самое большое число в переданном массиве.
    public static int findMax(int[] arr){
        Arrays.sort(arr);
        int i = arr[arr.length - 1];
        return i;
    }

//    Задача 10: разработать метод с сигнатурой publiс static String[] reverse(String[] arr).
//    Метод возвращает новый массив, в котором элементы исходного массива расположены в обратном порядке.
//    Например, {“One”, “Two”, “Zero”} -> {“Zero”, “Two”, “One}.

    public static String[] reverse(String[] arr){
        if (arr == null) {
            return null;
        }

        String[] mirror = new String[arr.length];
        for (int i=0; i<arr.length; i++){
            mirror[i]=arr[arr.length-1-i];
        }
        return mirror;
    }

//    Задача 11: разработать метод с сигнатурой public static calcAverage(List list). Метод вычисляет и возвращает среднее арифметическое всех чисел в списке.

    public static double calcAverage(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return 0.0;
        }

        double sum = 0;
        for (int num : list) {
            sum += num;
        }

        return sum/list.size();
    }

//    Задача 12: разработать метод с сигнатурой public static List removeSpecificName(List list, String nameToRemove).
//    Метод принимает список и имя, которое нужно исключить. Возвращает новый список, не содержащий указанного имени.

    public static List removeSpecificName(List<String> list, String nameToRemove) {
        if (list == null ) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();

        for (String name : list) {
            if (name == null) {
                result.add(null);
            } else if (!name.equals(nameToRemove)){
                result.add(name);
            }
        }
        return result;
    }

    public static void main(String[] args) {

        System.out.println("Задача 1");
        System.out.println(isEven(4));
        System.out.println(isEven(7));
        System.out.println();
        System.out.println("Задача 2");
        System.out.println(checkAccess(19));
        System.out.println(checkAccess(18));
        System.out.println();
        System.out.println("Задача 3");
        System.out.println(isPositive(0));
        System.out.println(isPositive(1));
        System.out.println(isPositive(-1));
        System.out.println();
        System.out.println("Задача 4");
        System.out.println(getGrade(0));
        System.out.println(getGrade(67));
        System.out.println(getGrade(-1));
        System.out.println();
        System.out.println("Задача 5");
        System.out.println(blastOff(0));
        System.out.println(blastOff(-1));
        System.out.println(blastOff(5));
        System.out.println();
        System.out.println("Задача 6");
        System.out.println(sumToN(0));
        System.out.println(sumToN(-1));
        System.out.println(sumToN(100));
        System.out.println();
        System.out.println("Задача 7");
        String[] text1 = {"1", "bug", "dog", "привет"};
        String[] text2 = {"", "Bug", "dog", "привет"};
        String[] text3 = {"", "", "", ""};
        String[] text4 = {};
        System.out.println(hasBug(text1));
        System.out.println(hasBug(text2));
        System.out.println(hasBug(text3));
        System.out.println(hasBug(text4));
        System.out.println();
        System.out.println("Задача 8");
        System.out.println(getEvenInRange(0,10));
        System.out.println(getEvenInRange(-11,13));
        System.out.println();
        System.out.println("Задача 9");
        int[] findMax1 = {1, 2, 3, 4};
        int[] findMax2 = {-1000, 11, 2, 3, 4, 45, 100000, -89, -400};
        System.out.println(findMax(findMax1));
        System.out.println(findMax(findMax2));
        System.out.println();
        System.out.println("Задача 10");
        String[] origArr = {"1", "bug", "dog", "привет"};
        String[] mirrArr = reverse(origArr);
        System.out.println(Arrays.toString(origArr));
        System.out.println(Arrays.toString(mirrArr));
        System.out.println();
        System.out.println("Задача 11");
        List<Integer> num = Arrays.asList(1,2,3,5);
        System.out.println(calcAverage(num));
        System.out.println();
        System.out.println("Задача 12");
        List<String> names = Arrays.asList("Батон", "Фифон", "Макрон");
        List<String> delNames = removeSpecificName(names,"Макрон");
        System.out.println(names);
        System.out.println(delNames);
    }
}
