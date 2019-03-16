package poker.version_graphics.view;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import poker.version_graphics.model.Card;
import poker.version_graphics.model.HandType;
import poker.version_graphics.model.Player;

public class PlayerPane extends VBox {
    private Label lblName = new Label();
    private HBox hboxCards = new HBox();
    private Label lblEvaluation = new Label("--");
    private Label lblWinCount = new Label("Wins: 0");
    private ParallelTransition bounce;
    private Timeline timeline;
    
    // Link to player object
    private Player player;
    
    public PlayerPane() {
        super(); // Always call super-constructor first !!
        this.getStyleClass().add("player"); // CSS style class
        this.lblName.setId("playerName");
        
        // Add child nodes
        this.getChildren().addAll(lblName, hboxCards, lblEvaluation, lblWinCount);
        
        // Add CardLabels for the cards
        for (int i = 0; i < 5; i++) {
            Label lblCard = new CardLabel();
            hboxCards.getChildren().add(lblCard);
        }
        hboxCards.setSpacing(5);
        
        //Add animation-features
        ScaleTransition scale = new ScaleTransition(Duration.millis(400));
        scale.setToX(5);
        scale.setToY(5);
        scale.setAutoReverse(true);
        scale.setCycleCount(4);
        RotateTransition rotate = new RotateTransition(Duration.millis(800));
        rotate.setCycleCount(1);
        rotate.setByAngle(360);
        bounce = new ParallelTransition(scale, rotate);
        bounce.setNode(lblWinCount);
        
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(lblWinCount.textFillProperty(), Color.WHITE)),
                new KeyFrame(Duration.seconds(1), new KeyValue(lblWinCount.textFillProperty(), Color.RED))
        );
        timeline.setAutoReverse(true);
        timeline.setCycleCount(2);
    }
    
    public void setPlayer(Player player) {
    	this.player = player;
    	lblName.setText(player.getPlayerName()); //all you need to display is the name
    }
    
    public SequentialTransition updatePlayerDisplay(int p) {
    	SequentialTransition cardAnimations = new SequentialTransition();
    	for (int i = 0; i < Player.HAND_SIZE; i++) {
    		Card card = null;
    		CardLabel cl = (CardLabel) hboxCards.getChildren().get(i);
    		if (player.getCards().size() > i) //only true for deal(), not for shuffle()
    			card = player.getCards().get(i);
    		cl.setCard(card);
    		if (card != null) {
    			PathTransition move = cl.prepareAnimation(); //prepare the animation for each card
    			cardAnimations.getChildren().add(move);
    		}
    	}
    	//play the Animation (MACHTS MOMENTAN AU BIM SHUFFLE, D TRANSITION ISCH EIFACH LEER)
    	cardAnimations.setDelay(Duration.millis(p*250)); 
    	cardAnimations.play();
    	HandType evaluation = player.evaluateHand(); //evaluate the hand
    	lblEvaluation.setText("--"); //always delete the old evaluation while the animation is playing
    	cardAnimations.setOnFinished(( e -> lblEvaluation.setText(evaluation.toString()))); //dispaly hand after animation
    	
    	return cardAnimations; //needed to display winner after the last animation
    }
    
    public Label getLblEvaluation() {
		return lblEvaluation;
	}

    //updates the winLabel and return its animation
	public ParallelTransition updateWinLabel() {
    	int newWins = this.player.increaseWins();
    	lblWinCount.setText("Wins: "+newWins);
    	return bounce;
    }
    
    public CardLabel getCardLabel(int i) {
    	return (CardLabel) hboxCards.getChildren().get(i);
    }
    
    public void resetWins() {
    	this.lblWinCount.setText("Wins: 0");
    	this.player.resetWins();
    	timeline.play();
    }
}
