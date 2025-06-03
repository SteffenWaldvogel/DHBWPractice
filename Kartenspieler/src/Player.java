import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import lombok.data;

@Data
public class Player {
	private final String name;
	private final List<Card> handCards;
	private final Map<Card, Integer> playedCards;
	private int actionPoints;

	public Optional<Card> getMostPowerfulCard(int row) {
		Optional<Card> mostPowerful = playedCards.entrySet().stream().filter(entry -> entry.getValue() == row)
				.map(Entry::getKey).max(Comparator.comparingInt(Card::power));
		return mostPowerful;
	}

	public void playCard(Card card, int row) throws CardNotFoundException, NotEnoughActionPointsException {
		if (actionPoints < card.cost()) {
			throw new NotEnoughActionPointsException();
		} else {
			if (handCards.contains(card)) {
				handCards.remove(card);
				playedCards.put(card, row);
				actionPoints -= card.cost();
			} else {
				throw new CardNotFoundException();
			}
		}
	}

}
