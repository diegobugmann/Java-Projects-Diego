package Lottery.View;

import Lottery.Model.LotteryModel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LotteryView {
	
	private LotteryModel model;
	private TipArea tipArea;
	private DrawArea drawArea;
	private MenuPane menu;
	
	public LotteryView(Stage stage, LotteryModel model) {
		this.model = model;
		
		tipArea = new TipArea(model);
		drawArea = new DrawArea(model);
		menu = new MenuPane();
		
		BorderPane root = new BorderPane();
		root.setLeft(tipArea);
		root.setCenter(drawArea);
		root.setTop(menu);
		root.getStyleClass().add("root");
		
		stage.setResizable(true);
        //Create and display scene
		Scene scene = new Scene(root);
		scene.getStylesheets().add(
				getClass().getResource("lottery.css").toExternalForm());
		stage.setTitle("***Diego's Lottery***");
		stage.setScene(scene);
		stage.show();
	}
	
	public TipArea getTipArea() {
		return this.tipArea;
	}
	
	public DrawArea getDrawArea() {
		return this.drawArea;
	}
	
	public MenuPane getMenuPane() {
		return this.menu;
	}
}