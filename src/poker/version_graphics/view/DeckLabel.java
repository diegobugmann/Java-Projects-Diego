package poker.version_graphics.view;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import poker.version_graphics.model.DeckOfCards;

public class DeckLabel extends Label {
	public DeckLabel() {
		super();
		this.getStyleClass().add("deck");
	}
	
	// Bind the label to the CardsRemaining property of the deck
	public void setDeck(DeckOfCards deck, ProgressBar deckStatus) {
		this.textProperty().bind(deck.getCardsRemainingProperty().asString());
		deck.getCardsRemainingProperty().addListener((o, oldVal, newVal) -> {
			deckStatus.setProgress(newVal.doubleValue()/52.0);
		});
	}
}
