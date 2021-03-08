package blackjack.view;

import blackjack.domain.ResultType;
import blackjack.domain.player.Name;
import blackjack.domain.player.dto.GameResultDTO;
import blackjack.domain.player.dto.PlayerDTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String DELIMITER = ", ";

    private static void printUserCards(PlayerDTO playerDTO) {
        System.out.print(playerDTO.getName() + "카드: ");
        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        playerDTO.getCards().forEach(card -> stringJoiner.add(card.toString()));
        System.out.print(stringJoiner.toString());
    }

    public static void printUserInitialCards(PlayerDTO playerDTO) {
        printUserCards(playerDTO);
        System.out.println();
    }

    public static void printGiveTwoCardsMessage(List<PlayerDTO> playerDTOs, PlayerDTO dealerDTO) {
        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        playerDTOs.forEach(playerDTO -> stringJoiner.add(playerDTO.getName()));
        System.out.println("딜러와 " + stringJoiner.toString() + "에게 2장을 나누었습니다.");
        printUserInitialCards(dealerDTO);
        playerDTOs.forEach(OutputView::printUserInitialCards);
        System.out.println();
    }

    private static void printUserFinalCards(PlayerDTO playerDTO) {
        printUserCards(playerDTO);
        System.out.println(" - 결과: " + playerDTO.getScore());
    }

    public static void printFinalCardsMessage(List<PlayerDTO> playerDTOs, PlayerDTO dealerDTO) {
        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        playerDTOs.forEach(playerDTO -> stringJoiner.add(playerDTO.getName()));
        printUserFinalCards(dealerDTO);
        playerDTOs.forEach(OutputView::printUserFinalCards);
        System.out.println();
    }

    public static void printDealerDrawCardMessage() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public static void printResultMessage(GameResultDTO gameResultDTO) {
        System.out.println("## 최종 승패");
        Map<ResultType, Integer> resultCount = new HashMap<>();
        Map<Name, ResultType> result = gameResultDTO.getGameResult();
        for (Name name : result.keySet()) {
            resultCount.put(result.get(name), resultCount.getOrDefault(result.get(name), 0) + 1);
        }
        printDealerResultMessage(resultCount);
        System.out.print(getPlayersResultMessage(result));
    }

    private static String getPlayersResultMessage(Map<Name, ResultType> result) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Name name : result.keySet()) {
            stringBuilder
                .append(name.getName())
                .append(": ")
                .append(result.get(name).getResult())
                .append(NEW_LINE);
        }
        return stringBuilder.toString();
    }

    private static void printDealerResultMessage(Map<ResultType, Integer> resultCount) {
        System.out.println("딜러: " +
            resultCount.getOrDefault(ResultType.LOSS, 0) + "승 " +
            resultCount.getOrDefault(ResultType.WIN, 0) + "패 " +
            resultCount.getOrDefault(ResultType.DRAW, 0) + "무승부");
    }
}