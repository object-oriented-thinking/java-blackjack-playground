package nextstep.fp;

import java.util.List;

public class Lambda {
    public static void printAllOld(List<Integer> numbers) {
        print("printAllOld");
        numbers.forEach(System.out::println);
    }

    public static void printAllLambda(List<Integer> numbers) {
        print("printAllLambda");
        numbers.forEach(System.out::println);
    }

    public static void runThread() {
        new Thread(() -> print("Hello from thread")).start();
    }


    public static int sumAll(List<Integer> numbers) {
        return sumAll(numbers, Lambda::getAll);
    }

    public static int sumAllEven(List<Integer> numbers) {
        return sumAll(numbers, Lambda::getEven);
    }

    public static int sumAllOverThree(List<Integer> numbers) {
        return sumAll(numbers, Lambda::getOverThree);
    }

    private static int sumAll(List<Integer> numbers, Condition condition) {
        return numbers.stream()
            .filter(condition::condition)
            .reduce(Integer::sum).orElse(0);
    }

    private static void print(String message) {
        System.out.println(message);
    }

    private static boolean getAll(Integer integer) {
        return true;
    }

    private static boolean getEven(Integer integer) {
        return integer % 2 == 0;
    }

    private static boolean getOverThree(Integer integer) {
        return integer > 3;
    }
}
