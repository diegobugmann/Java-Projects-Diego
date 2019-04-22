package Lottery;

import Lottery.Controller.LotteryController;
import Lottery.Model.LotteryModel;
import Lottery.View.LotteryView;
import javafx.application.Application;
import javafx.stage.Stage;
import poker.version_graphics.controller.PokerGameController;
import poker.version_graphics.model.PokerGameModel;
import poker.version_graphics.view.PokerGameView;

public class LotteryMain extends Application {
	
	private LotteryModel model;
	private LotteryView view;
	private LotteryController controller;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Create and initialize the MVC components
    	model = new LotteryModel();
    	view = new LotteryView(primaryStage, model);
    	controller = new LotteryController(model, view);
	}

}
