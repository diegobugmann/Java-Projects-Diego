package Lottery.Controller;

import java.text.DecimalFormat;

import Lottery.Model.Combination;
import Lottery.Model.LotteryModel;
import Lottery.Model.RegularNumber;
import Lottery.Model.SuperNumber;
import Lottery.Model.TipEvaluation;
import Lottery.View.LotteryView;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LotteryController {
	
	private LotteryModel model;
	private LotteryView view;
	public static final DecimalFormat dm = new DecimalFormat("CHF 0.00");
	
	public LotteryController(LotteryModel model, LotteryView view) {
		this.model = model;
		this.view = view;
		
		//Add events to the TipArea
		view.getTipArea().addBtn.setOnAction(e -> model.getTipList().addTip(new Combination()));
		view.getTipArea().deleteBtn.setOnAction(e -> {
			Combination selectedTip = view.getTipArea().getTipTable().getSelectionModel().getSelectedItem();
			model.getTipList().deleteTip(selectedTip);
		});
		view.getTipArea().clearBtn.setOnAction(e -> model.getTipList().clearTips());
		view.getTipArea().hintsBtn.setOnAction(e -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Tipp-Hinweise");
			alert.setContentText("Zahlen von 1-42\n"+
					"Superzahl von 1-6\n"+
					"Preis pro Tipp: 2.50CHF");
			alert.showAndWait();
		});
		
		//handle the editable cells
		createEditCellEvents();
		
		/*
		model.getTipList().getTips().addListener((ListChangeListener<Combination>) c -> {
			while (c.next()) {
				view.tips.scrollTo(c.getFrom());
			}
		});
		*/
		
		//Add events to the DrawArea
		view.getDrawArea().drawBtn.setOnAction(e -> draw());
		view.getTipArea().getTipTable().getItems().addListener((ListChangeListener<Combination>)(c -> {
			double currentPrice = (model.getTipList().getValueInCHF());
			view.getDrawArea().getCurrentPriceLabel().setText("Aktuelle Kosten: "+(dm.format(currentPrice)));
		})); //update the current costs whenever a Tip is added/deleted
			
		
		//Add events to the EvaluateArea
		view.getEvaluateArea().oddsBtn.setOnAction(e -> showAlert());
		
	}

	public void createEditCellEvents() {
				
		view.getTipArea().getTipTable().getColNumOne().setOnEditCommit(e -> {
			String newValue = e.getNewValue();
			if (RegularNumber.isValid(newValue) && !e.getRowValue().contains(newValue)) {
				e.getRowValue().getNumber(0).setNumber(Integer.parseInt(newValue));
				//e.getRowValue().sortNumbers();
			} else
				e.getTableView().refresh();
		});
		
		view.getTipArea().getTipTable().getColNumTwo().setOnEditCommit(e -> {
			String newValue = e.getNewValue();
			if (RegularNumber.isValid(newValue) && !e.getRowValue().contains(newValue)) {
				e.getRowValue().getNumber(1).setNumber(Integer.parseInt(newValue));
				//e.getRowValue().sortNumbers();
			} else
				e.getTableView().refresh();
		});
		
		view.getTipArea().getTipTable().getColNumThree().setOnEditCommit(e -> {
			String newValue = e.getNewValue();
			if (RegularNumber.isValid(newValue) && !e.getRowValue().contains(newValue)) {
				e.getRowValue().getNumber(2).setNumber(Integer.parseInt(newValue));
				//e.getRowValue().sortNumbers();
			} else
				e.getTableView().refresh();
		});
		
		view.getTipArea().getTipTable().getColNumFour().setOnEditCommit(e -> {
			String newValue = e.getNewValue();
			if (RegularNumber.isValid(newValue) && !e.getRowValue().contains(newValue)) {
				e.getRowValue().getNumber(3).setNumber(Integer.parseInt(newValue));
				//e.getRowValue().sortNumbers();
			} else
				e.getTableView().refresh();
		});
		
		view.getTipArea().getTipTable().getColNumFive().setOnEditCommit(e -> {
			String newValue = e.getNewValue();
			if (RegularNumber.isValid(newValue) && !e.getRowValue().contains(newValue)) {
				e.getRowValue().getNumber(4).setNumber(Integer.parseInt(newValue));
				//e.getRowValue().sortNumbers();
			} else
				e.getTableView().refresh();
		});
		
		view.getTipArea().getTipTable().getColNumSix().setOnEditCommit(e -> {
			String newValue = e.getNewValue();
			if (RegularNumber.isValid(newValue) && !e.getRowValue().contains(newValue)) {
				e.getRowValue().getNumber(5).setNumber(Integer.parseInt(newValue));
				//e.getRowValue().sortNumbers();
			} else
				e.getTableView().refresh();
		});
		
		view.getTipArea().getTipTable().getColSupNum().setOnEditCommit(e -> {
			String newValue = e.getNewValue();
			if (SuperNumber.isValid(newValue))
				e.getRowValue().getSupNum().setNumber(Integer.parseInt(newValue));
			else
				e.getTableView().refresh();
		});
		
	}
	
	public void draw() {
		if (!model.getTipList().getTips().isEmpty()) {
			Combination drawing = model.drawNumbers(); //draw numbers
			view.getDrawArea().displayNumbers(drawing); //and display them
			TipEvaluation.evaluateTips(model.getTipList().getTips(), model.getDrawing()); //evaluate the tips
			view.getDrawArea().updateLastDraw(); //show the details of the current draw
			model.updateStatistics(); //update the overall-statistics
			view.getEvaluateArea().updateStatistics(); //and display them
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Keine Tipps eingegeben!");
			alert.setContentText("Geben Sie Tipps ein, um eine Ziehung durchzuführen.");
			alert.showAndWait();
		}
	}
	
	private void showAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Gewinne und Wahrscheinlichkeiten");
		alert.setHeaderText("Gewinne und Wahrscheinlichkeiten");
		alert.setContentText(
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
        alert.showAndWait();
	}

}
