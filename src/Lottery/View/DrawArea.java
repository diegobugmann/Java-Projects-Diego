package Lottery.View;

import Lottery.Controller.LotteryController;
import Lottery.Model.Combination;
import Lottery.Model.LotteryModel;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class DrawArea extends VBox {
	
	private LotteryModel model;
	private Label currentTipPrice;
	public Button drawBtn;
	private HBox balls = new HBox();
	private Label lastDraw;
	private Label lastDrawInfo;
	
	public DrawArea(LotteryModel model) {
		this.model = model;
		this.currentTipPrice = new Label("Aktuelle Tipp-Kosten: CHF 0.00");
		this.lastDraw = new Label();
		this.lastDraw.setId("lastDrawLabel");
		this.lastDrawInfo = new Label();
		this.lastDrawInfo.setMinWidth(240);
		this.drawBtn = new Button("***DRAW NUMBERS***");
		for (int i = 0; i < 7; i++) {
			DrawBall db = new DrawBall();
			balls.getChildren().add(db);
			if (i == 6) db.getCircle().setFill(Color.MIDNIGHTBLUE); //superNumber has another color
		}
		
		balls.setSpacing(5);
		
		this.setSpacing(20);
		this.setAlignment(Pos.TOP_CENTER);
		this.getChildren().addAll(currentTipPrice, drawBtn, balls, lastDraw, lastDrawInfo);
	}
	
	public void displayNumbers(Combination drawing) {
		int num = 0;
		for (int i = 0; i < 7; i++) {
			if (i != 6) num = drawing.getNumber(i).getValue();
			else num = drawing.getSupNum().getValue();
			DrawBall db = (DrawBall) balls.getChildren().get(i);
			db.setText(Integer.toString(num));
		}
	}
	
	public Label getCurrentPriceLabel() {
		return this.currentTipPrice;
	}
	
	public void updateLastDraw() {
		this.lastDraw.setText("Letzte Ziehung");
		this.lastDrawInfo.setText("Anzahl Tipps: "+model.getTipList().getSize()+"\n"+
				"Kosten: "+LotteryController.dm.format(model.getTipList().getValueInCHF())+"\n"+
				"Gewinn: \n"+
				(model.getTipList().displayWins() != "" ? model.getTipList().displayWins() : "Leider kein Gewinn"));
	}

}
