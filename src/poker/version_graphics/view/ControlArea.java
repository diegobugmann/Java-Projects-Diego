package poker.version_graphics.view;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import poker.version_graphics.model.DeckOfCards;

public class ControlArea extends HBox {
    private DeckLabel lblDeck = new DeckLabel();
    public Label lblWinner = new Label("Winner: ");
    public FadeTransition winnerTransition;
    private Region spacer = new Region();
    private Region spacer2 = new Region();
    public Button btnShuffle = new Button("Shuffle");
    public Button btnDeal = new Button("Deal");
    public static CardLabel deck = new CardLabel();
    private ProgressBar deckStatus = new ProgressBar();

    public ControlArea() {
    	super(); // Always call super-constructor first !!
    	
    	Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("poker/images/back rounded.png"));
		ImageView imv = new ImageView(image);
		imv.fitWidthProperty().bind(this.widthProperty());
		imv.fitHeightProperty().bind(this.heightProperty());
		imv.setPreserveRatio(true);
    	
    	deck.setGraphic(imv);
    	
    	spacer.setMaxWidth(160);
    	spacer2.setMinWidth(150);
    	deckStatus.setProgress(1); //set the bar to full when starting the program
    	
    	this.getChildren().addAll(spacer, deckStatus, lblDeck, btnShuffle, deck, btnDeal, 
    			spacer2, lblWinner);

        HBox.setHgrow(spacer, Priority.ALWAYS); // Use region to absorb resizing
        this.setId("controlArea"); // Unique ID in the CSS
        this.lblWinner.setId("winnerLabel");
        
        //add animation for winnerLabel
        winnerTransition = new FadeTransition(Duration.millis(500),lblWinner);
        winnerTransition.setFromValue(1.0);
        winnerTransition.setToValue(0.4);
        winnerTransition.setAutoReverse(true);
        winnerTransition.setCycleCount(4);
    }
    
    public void linkDeck(DeckOfCards deck) {
    	lblDeck.setDeck(deck, deckStatus);
    }
}
