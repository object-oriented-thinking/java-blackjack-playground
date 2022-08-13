package nextstep.fp;

import java.util.List;

public class Lambda {
    public static void printAllOld(List<Integer> numbers) {
        System.out.println("printAllOld");

        for (int number : numbers) {
            System.out.println(number);
        }
    }

    public static void printAllLambda(List<Integer> numbers) {
        System.out.println("printAllLambda");

        numbers.forEach(System.out::println);
    }

    public static void runThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from thread");
            }
        }).start();
    }

    public static int sumAll(List<Integer> numbers) {
        return numbers.stream().reduce(Integer::sum).get();
    }

    public interface Conditional {
        boolean test(Integer number);
    }

    public static int sumAll(List<Integer> numbers,
                             Conditional c) {
        // c.test(number)를 활용해 구현할 수 있다.
        return numbers.stream().filter(c::test).reduce(Integer::sum).get();
    }
    public static int sumAllEven(List<Integer> numbers) {
        return sumAll(numbers, (integer)->{return integer%2==0;});
    }

    public static int sumAllOverThree(List<Integer> numbers) {
        return sumAll(numbers, (integer)->{return integer>3;});
    }
}
