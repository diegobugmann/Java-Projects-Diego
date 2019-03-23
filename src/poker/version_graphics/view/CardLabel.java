package poker.version_graphics.view;

import javafx.animation.PathTransition;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.util.Duration;
import poker.version_graphics.PokerGame;
import poker.version_graphics.model.Card;

public class CardLabel extends Label {
	
	private ImageView imv;
	
	public CardLabel() {
		super();
		this.getStyleClass().add("card2");
	}

	public void setCard(Card card) {
		if (card != null) {
			String fileName = cardToFileName(card);
			Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("poker/images/" + fileName));
			ImageView imv = new ImageView(image);
			imv.fitWidthProperty().bind(this.widthProperty());
			imv.fitHeightProperty().bind(this.heightProperty());
			imv.setPreserveRatio(true);
			this.imv = imv; //set as instance to prepare the animation later
			//prepare the back image
			Image image2 = new Image(this.getClass().getClassLoader().getResourceAsStream("poker/images/back rounded.png"));
			ImageView imv2 = new ImageView(image2);
			imv2.fitWidthProperty().bind(this.widthProperty());
			imv2.fitHeightProperty().bind(this.heightProperty());
			imv2.setPreserveRatio(true);
			this.setGraphic(imv2);
			this.getStyleClass().clear();
			this.getStyleClass().add("card"); //update the css file (make cardLabel visible)
		} else {
			this.getStyleClass().clear();
			this.getStyleClass().add("card2"); //make cardLabel invisible
			this.setGraphic(null);
		}
	}

	private String cardToFileName(Card card) {
		String rank = card.getRank().toString();
		String suit = card.getSuit().toString();
		return rank + "_of_" + suit + ".png";
	}
	
	public PathTransition prepareAnimation() {
		//generating the path transitions for each card using the cardLabel location
		Bounds sceneCoord = this.localToScene(this.getBoundsInLocal()); //gets the coordinates in the scene
        int xCoordinates = (int) ((sceneCoord.getMinX() + sceneCoord.getMaxX())/2);
        int yCoordinates = (int) ((sceneCoord.getMinY() + sceneCoord.getMaxY())/2);
        PathElement p1 = new MoveTo(478 - xCoordinates, 630 - yCoordinates); //sets the start to where the deck is
        PathElement p2 = new LineTo(40, 57); //sets the end to where the card should be
		Path path = new Path();
		path.getElements().add(p1);
		path.getElements().add(p2);
		PathTransition move = new PathTransition(Duration.millis(PokerGame.numPlayers*250), path, this);
		move.setCycleCount(1);	
		move.setOnFinished((event) -> this.setGraphic(this.imv)); //set the picture after the animation
		return move;
	}

}
