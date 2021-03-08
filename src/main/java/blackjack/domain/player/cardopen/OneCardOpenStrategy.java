package blackjack.domain.player.cardopen;

import blackjack.domain.card.Card;
import java.util.List;

public class OneCardOpenStrategy implements CardOpenStrategy {
    public static final int ONE_CARD_SIZE = 1;

    @Override
    public List<Card> getCards(List<Card> cards) {
        return cards.subList(0, ONE_CARD_SIZE);
    }
}
