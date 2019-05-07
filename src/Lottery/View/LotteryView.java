package Lottery.View;

import Lottery.Model.LotteryModel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LotteryView {
	
	private LotteryModel model;
	private TipArea tipArea;
	private DrawArea drawArea;
	private EvaluateArea evaluateArea;
	
	public LotteryView(Stage stage, LotteryModel model) {
		this.model = model;
		
		tipArea = new TipArea(model);
		drawArea = new DrawArea(model);
		evaluateArea = new EvaluateArea();
		
		HBox root = new HBox();
		root.setSpacing(10);
		root.getChildren().addAll(tipArea, drawArea, evaluateArea);
		

		stage.setResizable(true);
		
        // Create and display scene
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
	
	public EvaluateArea getEvaluateArea() {
		return this.evaluateArea;
	}
}
