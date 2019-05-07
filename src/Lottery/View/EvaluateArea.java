package Lottery.View;

import Lottery.Controller.LotteryController;
import Lottery.Model.LotteryModel;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class EvaluateArea extends VBox {
	
	private Label numDraws;
	private Label numTips;
	private Label costLabel;
	private Label winLabel;
	private Label counters;
	public Button oddsBtn;
	
	public EvaluateArea() {
		this.oddsBtn = new Button("Gewinne und Wahrscheinlichkeiten");
		this.numDraws = new Label("Anzahl Ziehungen: "+LotteryModel.drawCount);
		this.numTips = new Label("Tipps gesamt: "+LotteryModel.tipCount);
		this.costLabel = new Label("Kosten gesamt: ");
		this.winLabel = new Label("Gewinne gesamt: ");
		this.counters = new Label(); //TODO counter der verschiedenen wins anzeigen
		this.getChildren().addAll(numDraws, numTips, costLabel, winLabel, oddsBtn, counters);
		
		this.setSpacing(5);
		this.setAlignment(Pos.CENTER);
	}
	
	public void updateStatistics() {
		this.numDraws.setText("Anzahl Ziehungen: "+LotteryModel.drawCount);
		this.numTips.setText("Tipps gesamt: "+LotteryModel.tipCount);
		this.costLabel.setText("Kosten gesamt: "+LotteryController.dm.format(LotteryModel.totalCosts));
		this.winLabel.setText("Gewinne gesamt: "+LotteryController.dm.format(LotteryModel.totalWins));
	}

}
