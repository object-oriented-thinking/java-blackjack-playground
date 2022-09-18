package blackjack.view;

import blackjack.domain.person.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);
    private static final String regex = ", ";
    public List<Player> makePlayers(){
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        List<Player> players = new ArrayList<>();
        String namesRaw = scanner.nextLine();
        List<String> names = Arrays.asList(namesRaw.split(regex));
        List<Integer> betAmounts = getBetAmounts(names);

        for(int i=0;i<names.size();i++){
            players.add(new Player(names.get(i), betAmounts.get(i)));
        }
        return players;
    }

    private List<Integer> getBetAmounts(List<String> names){
        List<Integer> betAmounts = new ArrayList<>();
        for(String name: names){
            System.out.println(name + "의 배팅 금액은?");
            Integer betAmount = Integer.valueOf(scanner.nextLine());
            betAmounts.add(betAmount);
        }
        return betAmounts;
    }

    public boolean wantMoreCard(Player player) {
        System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String s = scanner.nextLine();

        if(!s.equals("y") && !s.equals("n")){
            throw new IllegalArgumentException("y 혹은 n을 입력해야 합니다.");
        }

        if(s.equals("y")) {
            System.out.println(s);
            return true;
        }
        return false;
    }
}
