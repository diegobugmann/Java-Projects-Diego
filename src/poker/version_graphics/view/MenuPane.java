package poker.version_graphics.view;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class MenuPane extends HBox {
	
	private Label numLabel;
	private RadioButton two, three, four;
	public Button submitButton, resetWinsBtn;
	public CheckBox autoShuffle;
	
	public MenuPane() {
		
		numLabel = new Label("Number of players:");
		
		two = new RadioButton("2");
		three = new RadioButton("3");
		four = new RadioButton("4");
		ToggleGroup tg = new ToggleGroup();
		two.setToggleGroup(tg);
		two.setSelected(true);
		three.setToggleGroup(tg);
		four.setToggleGroup(tg);
		submitButton = new Button("Submit");
		Region spacer = new Region();
		spacer.setMinWidth(90);
		resetWinsBtn = new Button("Reset wins");
		Region spacer2 = new Region();
		spacer2.setMinWidth(150);
		autoShuffle = new CheckBox("Shuffle before every hand");
		
		this.getChildren().addAll(numLabel, two, three, four, submitButton, spacer, resetWinsBtn, spacer2, autoShuffle);
		
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
