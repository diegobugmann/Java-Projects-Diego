package poker.version_graphics.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import poker.version_graphics.model.DeckOfCards;

public class ControlArea extends HBox {
    private DeckLabel lblDeck = new DeckLabel();
    Label lblWinner = new Label("Winner: ");
    private Region spacer = new Region();
    private Region spacer2 = new Region();
    Button btnShuffle = new Button("Shuffle");
    Button btnDeal = new Button("Deal");
    public static CardLabel deck = new CardLabel();
    private ProgressBar deckStatus = new ProgressBar();

    public ControlArea() {
    	super(); // Always call super-constructor first !!
    	
    	deck.setGraphic(null);
    	
    	spacer.setMaxWidth(222);
    	spacer2.setMinWidth(200);
    	deckStatus.setProgress(1); //set the bar to full when starting the program
    	
    	this.getChildren().addAll(spacer, deckStatus, lblDeck, btnShuffle, deck, btnDeal, 
    			spacer2, lblWinner);

        HBox.setHgrow(spacer, Priority.ALWAYS); // Use region to absorb resizing
        this.setId("controlArea"); // Unique ID in the CSS
    }
    
    public void linkDeck(DeckOfCards deck) {
    	lblDeck.setDeck(deck, deckStatus);
    }
}
