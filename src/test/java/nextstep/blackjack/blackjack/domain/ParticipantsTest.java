package nextstep.blackjack.blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ParticipantsTest {
    private static final String USERNAME = "tis";
    private static final BigDecimal BETTING_MONEY = BigDecimal.valueOf(1000L);
    private static final Card one = new Card(CardType.CLOVER, CardNumber.FOUR);
    private static final Card two = new Card(CardType.CLOVER, CardNumber.JACK);
    private static final Cards CARDS = new Cards(one, two);

    @Test
    @DisplayName("참여자는 이름과 배팀 금액, 카드 패를 가집니다.")
    void participant_create() {
        assertDoesNotThrow(
            () -> new Participant(USERNAME, BETTING_MONEY, CARDS)
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    void participant_username_is_not_blank(String username) {
        assertThatThrownBy(
            () -> new Participant(username, BETTING_MONEY, CARDS)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -1000})
    void participant_battingMoney_is_not_minus(int money) {
        assertThatThrownBy(
            () -> new Participant(USERNAME, BigDecimal.valueOf(money), CARDS)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
