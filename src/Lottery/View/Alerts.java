package Lottery.View;

import Lottery.Controller.LotteryController;
import Lottery.Model.LotteryModel;
import Lottery.Model.TipEvaluation;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerts {
	
	private static Alert hintsAlert;
	private static Alert oddsAlert;
	private static Alert statsAlert;
	private static Alert noTipsAlert;
	private static Alert clearAlert;
	
	public static Alert getHintsAlert() {
		if (hintsAlert == null) createHintsAlert();
		return hintsAlert;
	}
	
	public static Alert getOddsAlert() {
		if (oddsAlert == null) createOddsAlert();
		return oddsAlert;
	}
	
	public static Alert getStatsAlert() {
		if (statsAlert == null) createStatsAlert();
		else updateStatsAlert(); //always update the stats before displaying them
		return statsAlert;
	}
	
	public static Alert getNoTipsAlert() {
		if (noTipsAlert == null) createNoTipsAlert();
		return noTipsAlert;
	}
	
	public static Alert getClearAlert() {
		if (clearAlert == null) createClearAlert();
		return clearAlert;
	}
	
	private static void createHintsAlert() {
		hintsAlert = new Alert(AlertType.INFORMATION);
		hintsAlert.setHeaderText("Tipp-Hinweise");
		hintsAlert.setContentText("Zahlen von 1-42\n"+
				"Superzahl von 1-6\n"+
				"Preis pro Tipp: 2.50CHF\n"+
				"Um Tipps nach belieben abzuändern, einfach in der Tabelle die gewünschten Werte eintragen!\n"+
				"Viel Erfolg!");
	}
	
	private static void createOddsAlert() {
		oddsAlert = new Alert(AlertType.INFORMATION);
		oddsAlert.setTitle("Gewinne und Wahrscheinlichkeiten");
		oddsAlert.setHeaderText("Gewinne und Wahrscheinlichkeiten");
		oddsAlert.setContentText(
				"Richtige Zahlen\t  Gewinn\t\t\t Wahrscheinlichkeit\n" + 
				"\t 6+1\t\t	  Jackpot\t\t\t 1 : 31'474'716 \n" + 
				"\t 6\t\t	  CHF 1'000'000.-\t 1 : 6.294.943 \n" + 
				"\t 5+1\t\t	  CHF 10'000.-\t\t 1 : 145.716 \n" + 
				"\t 5\t\t	  CHF 1'000.-\t\t 1 : 29.143 \n" + 
				"\t 4+1\t\t	  CHF 150.-\t\t 1 : 3.331 \n" + 
				"\t 4\t\t	  CHF 75.-\t\t\t 1 : 666 \n" + 
				"\t 3+1\t\t	  CHF 25.-\t\t\t 1 : 220 \n" + 
				"\t 3\t\t	  CHF 10.-\t\t\t 1 : 44"
				);
	}

	private static void createStatsAlert() {
		statsAlert = new Alert(AlertType.INFORMATION);
		statsAlert.setTitle("Statisiken");
		statsAlert.setHeaderText("Langzeit-Statistik");
		statsAlert.setContentText(
				"Anzahl Ziehungen: "+LotteryModel.drawCount+"\n"+
				"Tipps gesamt: "+LotteryModel.tipCount+"\n"+
				"Kosten gesamt: "+LotteryController.dm.format(LotteryModel.totalCosts)+"\n"+
				"Gewinne gesamt: "+LotteryController.dm.format(LotteryModel.totalWins)+"\n\n"+
				"Übersicht der gewonnenen Beiträge:\n"+
				"10 CHF (3+0) -> "+TipEvaluation.counters[0]+"x\n"+
				"25 CHF (3+1) -> "+TipEvaluation.counters[1]+"x\n"+
				"75 CHF (4+0) -> "+TipEvaluation.counters[2]+"x\n"+
				"150 CHF (4+1) -> "+TipEvaluation.counters[3]+"x\n"+
				"1000 CHF (5+0) -> "+TipEvaluation.counters[4]+"x\n"+
				"10000CHF (5+1) -> "+TipEvaluation.counters[5]+"x\n"+
				"1000000 CHF (6+0) -> "+TipEvaluation.counters[6]+"x\n"+
				"9999999 CHF (6+1) -> "+TipEvaluation.counters[7]+"x\n"
				);
	}
	
	private static void updateStatsAlert() {
		statsAlert.setContentText(
				"Anzahl Ziehungen: "+LotteryModel.drawCount+"\n"+
				"Tipps gesamt: "+LotteryModel.tipCount+"\n"+
				"Kosten gesamt: "+LotteryController.dm.format(LotteryModel.totalCosts)+"\n"+
				"Gewinne gesamt: "+LotteryController.dm.format(LotteryModel.totalWins)+"\n\n"+
				"Übersicht der gewonnenen Beiträge:\n"+
				"10 CHF (3+0) -> "+TipEvaluation.counters[0]+"x\n"+
				"25 CHF (3+1) -> "+TipEvaluation.counters[1]+"x\n"+
				"75 CHF (4+0) -> "+TipEvaluation.counters[2]+"x\n"+
				"150 CHF (4+1) -> "+TipEvaluation.counters[3]+"x\n"+
				"1000 CHF (5+0) -> "+TipEvaluation.counters[4]+"x\n"+
				"10000CHF (5+1) -> "+TipEvaluation.counters[5]+"x\n"+
				"1000000 CHF (6+0) -> "+TipEvaluation.counters[6]+"x\n"+
				"9999999 CHF (6+1) -> "+TipEvaluation.counters[7]+"x\n"
				);
	}
	
	private static void createNoTipsAlert() {
		noTipsAlert = new Alert(AlertType.ERROR);
		noTipsAlert.setHeaderText("Keine Tipps eingegeben!");
		noTipsAlert.setContentText("Geben Sie Tipps ein, um eine Ziehung durchzuführen.");
	}
	
	private static void createClearAlert() {
		clearAlert = new Alert(AlertType.CONFIRMATION);
		clearAlert.setContentText("Sind Sie sicher, dass Sie alle Tipps löschen möchten?");
	}
}