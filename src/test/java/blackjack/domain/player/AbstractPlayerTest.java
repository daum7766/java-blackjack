package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AbstractPlayerTest {
    private static final String TEST_NAME = "pobi";

    @DisplayName("카드 뽑기 테스트")
    @Test
    void drawCard() {
        AbstractPlayer user = new User(TEST_NAME);
        Card twoCard = new Card(CardShape.DIAMOND, CardNumber.TWO);

        user.drawCard(twoCard);

        assertThat(user.getCards()).containsExactly(twoCard);
    }

    @DisplayName("A lowValue 테스트")
    @Test
    void aceLowValue() {
        AbstractPlayer user = new User(TEST_NAME);
        Card tenCard = new Card(CardShape.DIAMOND, CardNumber.TEN);
        Card aceCard = new Card(CardShape.DIAMOND, CardNumber.ACE);

        user.drawCard(tenCard);
        user.drawCard(tenCard);
        user.drawCard(aceCard);

        assertThat(user.getValue()).isEqualTo(21);
    }

    @DisplayName("A highValue 테스트")
    @Test
    void aceHighValue() {
        AbstractPlayer user = new User(TEST_NAME);
        Card tenCard = new Card(CardShape.DIAMOND, CardNumber.TEN);
        Card aceCard = new Card(CardShape.DIAMOND, CardNumber.ACE);

        user.drawCard(tenCard);
        user.drawCard(aceCard);

        assertThat(user.getValue()).isEqualTo(21);
    }

    @DisplayName("카드 반환 테스트")
    @Test
    void getCards() {
        AbstractPlayer user = new User(TEST_NAME);
        assertThat(user.getCards().isEmpty()).isTrue();

        Card tenCard = new Card(CardShape.DIAMOND, CardNumber.TEN);
        user.drawCard(tenCard);
        assertThat(user.getCards()).containsExactly(tenCard);
    }

    @DisplayName("랜덤 카드 한 개 뽑기 테스트")
    @Test
    void drawRandomOneCard() {
        AbstractPlayer user = new User(TEST_NAME);
        assertThat(user.getCards()).hasSize(0);

        user.drawRandomOneCard();
        assertThat(user.getCards()).hasSize(1);
    }

    @DisplayName("랜덤 카드 두 개 뽑기 테스트")
    @Test
    void drawRandomTwoCard() {
        AbstractPlayer user = new User(TEST_NAME);
        assertThat(user.getCards()).hasSize(0);

        user.drawRandomTwoCards();
        assertThat(user.getCards()).hasSize(2);
    }

    @DisplayName("이름 반환 테스트")
    @Test
    void getName() {
        AbstractPlayer user = new User(TEST_NAME);
        assertThat(user.getName()).isEqualTo(new Name(TEST_NAME));
    }
}