package Lottery.View;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class DrawBall extends StackPane {
	
	private Circle circle;
	private Text numberTxt;
	
	public DrawBall() {
		circle = new Circle(25);
		circle.setFill(Color.DEEPSKYBLUE);
		numberTxt = new Text();
		numberTxt.setFill(Color.WHITE);
		this.getChildren().addAll(circle, numberTxt);
		this.setLayoutX(30);
		this.setLayoutY(30);
	}
	
	public void setText(String newText) {
		this.numberTxt.setText(newText);
	}
	
	public Circle getCircle() {
		return this.circle;
	}

}
