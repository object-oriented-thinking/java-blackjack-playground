package nextstep.fp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamStudy {

    public static long countWords() throws IOException {
        String contents = new String(Files.readAllBytes(Paths
            .get("src/main/resources/fp/war-and-peace.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        long count = 0;
        for (String w : words) {
            if (w.length() > 12) count++;
        }
        return count;
    }

    public static void printLongestWordTop100() throws IOException {
        String contents = new String(Files.readAllBytes(Paths
            .get("src/main/resources/fp/war-and-peace.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
        // TODO 이 부분에 구현한다.

        Map<String, Integer> wordMap = new HashMap<>();

        for (String word : words) {
            if (!wordMap.containsKey(word)) {
                wordMap.put(word, 0);
            }
            wordMap.put(word, wordMap.get(word) + 1);
        }

        List<String> collect = wordMap.entrySet()
            .stream().filter(entry -> entry.getValue() > 12)
            .sorted(Comparator.comparing(entry -> - entry.getValue()))
            .map(entry -> entry.getKey().toLowerCase())
            .distinct()
            .collect(Collectors.toList());

        List<String> subList = new ArrayList<>(collect.subList(0, 100));
        System.out.println(subList);
        System.out.println(subList.size());
    }

    public static List<Integer> doubleNumbers(List<Integer> numbers) {
        return numbers.stream().map(x -> 2 * x).collect(Collectors.toList());
    }

    public static long sumAll(List<Integer> numbers) {
        return numbers.stream().reduce(0, Integer::sum);
    }

    public static long sumOverThreeAndDouble(List<Integer> numbers) {
        return Lambda.sumAllOverThree(numbers) * 2L;
    }
}
