package Lottery.View;

import Lottery.Model.LotteryModel;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LotteryView {
	
	private LotteryModel model;
	
	public LotteryView(Stage stage, LotteryModel model) {
		this.model = model;
		
		
		
		
		
		// Disallow resizing - which is difficult to get right with images
		stage.setResizable(false);

        // Create and display scene
		//Scene scene = new Scene(root);
		//scene.getStylesheets().add(
				//getClass().getResource("poker.css").toExternalForm());
		stage.setTitle("***Diego's Lottery***");
		//stage.setScene(scene);
		stage.show();
	}

}
