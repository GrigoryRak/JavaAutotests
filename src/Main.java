//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
//    Задача 1: разработать метод с сигнатурой publiс static boolean isEven(int n). Метод возвращает true, если число чётное, и false — если нечётное.
    public static boolean isEvent(int n) {
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

//    public static boolean hasBug(String[] messages){
//
//    }

    public static void main(String[] args) {

        System.out.println("Задача 1");
        System.out.println(isEvent(4));
        System.out.println(isEvent(7));
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
    }

}