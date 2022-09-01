package nextstep.blackjack.blackjack.utils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class IOService {
    private final Scanner scanner = new Scanner(System.in);
    private static final String REGEX = ",";

    public Map<String, BigDecimal> inputParticipantNameAndBattingMoney() {
        List<String> names = inputName();
        return inputBattingMoney(names);
    }

    private Map<String, BigDecimal> inputBattingMoney(List<String> names) {
        Map<String, BigDecimal> nameAndBattingMoney = new HashMap<>();
        for (String name : names) {
            System.out.println(name + "씨는 얼마를 배팅할 것인지?");
            nameAndBattingMoney.put(name, scanner.nextBigDecimal());
        }
        return nameAndBattingMoney;
    }

    private List<String> inputName() {
        System.out.println("이름을 입력하시오. 단위는 `,` 입니다. ");
        return Arrays.stream(scanner.nextLine().split(REGEX))
            .map(String::trim).collect(Collectors.toList());
    }

    public boolean weatherToAcceptCard(String username, int score) {
        System.out.println(username + "씨는 카드를 받을건지? (y/n), score : " + score);
        return Objects.equals(scanner.nextLine(), "y");
    }

    public void outputResult(Map<String, Integer> map, BigDecimal money) {
        System.out.println("결과 발표한다.");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println("총 상금은 " + money.toString());
    }
}
