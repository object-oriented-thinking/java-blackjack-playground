package nextstep.blackjack.blackjack.service;

import nextstep.blackjack.blackjack.member.Dealer;
import nextstep.blackjack.blackjack.member.Participant;
import nextstep.blackjack.blackjack.member.Participants;
import nextstep.blackjack.blackjack.onecards.Card;
import nextstep.blackjack.blackjack.onecards.CardNumber;
import nextstep.blackjack.blackjack.onecards.CardType;
import nextstep.blackjack.blackjack.onecards.Cards;
import nextstep.blackjack.blackjack.utils.IOService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BlackjackOutputServiceTest {


    private static final Cards 합_21_카드_3장 = new Cards(Arrays.asList(new Card(CardType.CLOVER, CardNumber.ACE), new Card(CardType.DIAMOND, CardNumber.ACE), new Card(CardType.DIAMOND, CardNumber.NINE)));
    private static final Cards 합_21_카드_2장 = new Cards(Arrays.asList(new Card(CardType.CLOVER, CardNumber.JACK), new Card(CardType.DIAMOND, CardNumber.ACE)));
    private static final Cards 합_24_카드 = new Cards(Arrays.asList(new Card(CardType.CLOVER, CardNumber.EIGHT), new Card(CardType.DIAMOND, CardNumber.EIGHT), new Card(CardType.HEART, CardNumber.EIGHT)));
    private static final Cards 합이_18인_카드 = new Cards(Arrays.asList(new Card(CardType.CLOVER, CardNumber.JACK), new Card(CardType.DIAMOND, CardNumber.EIGHT)));
    private static final Cards 합이_16인_카드 = new Cards(Arrays.asList(new Card(CardType.CLOVER, CardNumber.JACK), new Card(CardType.DIAMOND, CardNumber.SIX)));
    private static final Participant 합이_21_인_사용자 = new Participant("첫 번째 참여자", BigDecimal.valueOf(20_000L), 합_21_카드_3장);
    private static final Participant 한_번에_합_21_인_사용자 = new Participant("첫 번째 참여자", BigDecimal.valueOf(20_000L), 합_21_카드_2장);
    private static final Participant 합이_18_인_사용자 = new Participant("세 번째 참여자", BigDecimal.valueOf(20_000L), 합이_18인_카드);
    private static final Participant 합이_16_인_사용자 = new Participant("두 번째 참여자", BigDecimal.valueOf(20_000L), 합이_16인_카드);

    private BlackjackOutputService outputService;

    @BeforeEach
    void setUp() {
        outputService = new BlackjackOutputService(new IOService());
    }

    @Test
    @DisplayName("딜러와 참여자 한 명의 카드 합이 같으면 배팅 금액을 돌려받고, 나머지 참여자는 금액을 잃는다..")
    void getResult_winner_dealer_and_participants() {
        // given
        Dealer dealer = new Dealer(합_21_카드_3장);
        Participants participants = new Participants(Arrays.asList(합이_16_인_사용자, 합이_18_인_사용자, 합이_21_인_사용자));

        // when
        Map<String, BigDecimal> 결과 = outputService.distributeMoney(participants, dealer);

        // then
        assertThat(결과.get("dealer")).isEqualTo(BigDecimal.valueOf(20_000L));
        assertThat(결과.get(합이_21_인_사용자.getUsername())).isEqualTo(BigDecimal.valueOf(20_000L));
        assertThat(결과.get(합이_16_인_사용자.getUsername())).isEqualTo(BigDecimal.valueOf(-20_000L));
        assertThat(결과.get(합이_18_인_사용자.getUsername())).isEqualTo(BigDecimal.valueOf(-20_000L));
    }

    @Test
    @DisplayName("우승자가 딜러인 경우 배팅 금액을 전부 가져가고 참여자들은 모두 잃는다.")
    void getResult_winner_dealer() {
        // given
        Dealer dealer = new Dealer(합_21_카드_3장);
        Participants participants = new Participants(Arrays.asList(합이_18_인_사용자, 합이_16_인_사용자));

        // when
        Map<String, BigDecimal> 결과 = outputService.distributeMoney(participants, dealer);

        // then
        assertThat(결과.get("dealer")).isEqualTo(BigDecimal.valueOf(40_000L));
        assertThat(결과.get(합이_16_인_사용자.getUsername())).isEqualTo(BigDecimal.valueOf(-20_000L));
        assertThat(결과.get(합이_18_인_사용자.getUsername())).isEqualTo(BigDecimal.valueOf(-20_000L));
    }


    @Test
    @DisplayName("딜러 카드 합이 21을 초과하는 경우 모든 참여자에게 배팅 금액만큼 지불한다.")
    void getResult_over_dealer() {
        // given
        Dealer dealer = new Dealer(합_24_카드);
        Participants participants = new Participants(Arrays.asList(합이_16_인_사용자, 합이_18_인_사용자, 합이_21_인_사용자));

        // when
        Map<String, BigDecimal> 결과 = outputService.distributeMoney(participants, dealer);

        // then
        assertThat(결과.get("dealer")).isEqualTo(BigDecimal.valueOf(-60_000L));
        assertThat(결과.get(합이_16_인_사용자.getUsername())).isEqualTo(BigDecimal.valueOf(20_000L));
        assertThat(결과.get(합이_18_인_사용자.getUsername())).isEqualTo(BigDecimal.valueOf(20_000L));
        assertThat(결과.get(합이_21_인_사용자.getUsername())).isEqualTo(BigDecimal.valueOf(20_000L));
    }

    @Test
    @DisplayName("참여자의 처음 두 장 합이 21인 경우 1.5 배의 배팅 금액을 받고, 그 외 참여자는 돈을 잃는다.")
    void getResult_winner_first_blackjack() {
        // given
        Dealer dealer = new Dealer(합이_18인_카드);
        Participants participants = new Participants(Arrays.asList(합이_16_인_사용자, 합이_18_인_사용자, 한_번에_합_21_인_사용자));

        // when
        Map<String, BigDecimal> 결과 = outputService.distributeMoney(participants, dealer);

        // then
        assertThat(결과.get("dealer")).isEqualTo(BigDecimal.valueOf(10_000L));
        assertThat(결과.get(합이_16_인_사용자.getUsername())).isEqualTo(BigDecimal.valueOf(-20_000L));
        assertThat(결과.get(합이_18_인_사용자.getUsername())).isEqualTo(BigDecimal.valueOf(-20_000L));
        assertThat(결과.get(한_번에_합_21_인_사용자.getUsername())).isEqualTo(BigDecimal.valueOf(30_000L));
    }

    @Test
    @DisplayName("우승자가 참여자인 경우 배팅 금액 만큼 딜러에게 받아가고 그 외 참여자는 돈을 잃는다.")
    void getResult_winner_participants() {
        // given
        Dealer dealer = new Dealer(합이_16인_카드);
        Participants participants = new Participants(Arrays.asList(합이_16_인_사용자, 합이_18_인_사용자));

        // when
        Map<String, BigDecimal> 결과 = outputService.distributeMoney(participants, dealer);

        // then
        assertThat(결과.get("dealer")).isEqualTo(BigDecimal.ZERO);
        assertThat(결과.get(합이_16_인_사용자.getUsername())).isEqualTo(BigDecimal.valueOf(-20_000L));
        assertThat(결과.get(합이_18_인_사용자.getUsername())).isEqualTo(BigDecimal.valueOf(20_000L));
    }

}
