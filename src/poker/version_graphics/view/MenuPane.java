package poker.version_graphics.view;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class MenuPane extends HBox {
	
	private Label numLabel;
	private RadioButton two, three, four;
	public Button submitButton;
	
	public MenuPane() {
		
		Label numLabel = new Label("Number of players:");
		
		two = new RadioButton("2");
		three = new RadioButton("3");
		four = new RadioButton("4");
		ToggleGroup tg = new ToggleGroup();
		two.setToggleGroup(tg);
		two.setSelected(true);
		three.setToggleGroup(tg);
		four.setToggleGroup(tg);
		submitButton = new Button("Submit");
		
		this.getChildren().addAll(numLabel, two, three, four, submitButton);
		
		this.setSpacing(10);
		this.setPrefHeight(35);
		
	}
	
	public int getSelectedButton() {
		if (two.isSelected()) return 2;
		if (three.isSelected()) return 3;
		if (four.isSelected()) return 4;
		return 0; //never happens
	}
	
}
