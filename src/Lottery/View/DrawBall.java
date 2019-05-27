package Lottery.View;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DrawBall extends StackPane {
	
	private Circle circle;
	private Label numberTxt;
	
	public DrawBall() {
		circle = new Circle(25);
		circle.setFill(Color.DEEPSKYBLUE);
		numberTxt = new Label();
		numberTxt.setTextFill(Color.WHITE);
		this.getChildren().addAll(circle, numberTxt);
		this.setLayoutX(30);
		this.setLayoutY(30);
		this.getStyleClass().add("drawBall");
	}
	
	public void setText(String newText) {
		this.numberTxt.setText(newText);
	}
	
	public Circle getCircle() {
		return this.circle;
	}
}