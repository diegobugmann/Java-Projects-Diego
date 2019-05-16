package Lottery.Controller;

import java.text.DecimalFormat;
import java.util.Optional;

import Lottery.Model.Combination;
import Lottery.Model.LotteryModel;
import Lottery.Model.RegularNumber;
import Lottery.Model.SuperNumber;
import Lottery.Model.TipEvaluation;
import Lottery.View.Alerts;
import Lottery.View.LotteryView;
import javafx.collections.ListChangeListener;
import javafx.scene.control.ButtonType;

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
		view.getTipArea().clearBtn.setOnAction(e -> {
			if (model.getTipList().getTips().size() > 0) {
				Optional<ButtonType> choice = Alerts.getClearAlert().showAndWait();
				if (choice.get() == ButtonType.OK) model.getTipList().clearTips();
			}});
		createEditCellEvents(); //handle the editable cells
		
		model.getTipList().getTips().addListener((ListChangeListener<Combination>) c -> {
			while (c.next()) {
				view.getTipArea().getTipTable().scrollTo(c.getFrom());
			}
		});
		
		//Add events to the DrawArea
		view.getDrawArea().drawBtn.setOnAction(e -> draw());
		view.getTipArea().getTipTable().getItems().addListener((ListChangeListener<Combination>)(c -> {
			double currentPrice = (model.getTipList().getValueInCHF());
			view.getDrawArea().getCurrentPriceLabel().setText("Aktuelle Kosten: "+(dm.format(currentPrice)));
		})); //update the current costs whenever a Tip is added/deleted
			
		//Add events to the Menu
		view.getMenuPane().hints.setOnAction(e -> Alerts.getHintsAlert().showAndWait());
		view.getMenuPane().odds.setOnAction(e -> Alerts.getOddsAlert().showAndWait());
		view.getMenuPane().stats.setOnAction(e -> Alerts.getStatsAlert().showAndWait());
	}

	private void createEditCellEvents() {
				
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
	
	private void draw() {
		if (!model.getTipList().getTips().isEmpty()) {
			view.getDrawArea().drawBtn.setDisable(true); //disable draw Button while drawing
			Combination drawing = model.drawNumbers(); //draw numbers
			view.getDrawArea().displayNumbers(drawing); //and display them
			TipEvaluation.evaluateTips(model.getTipList().getTips(), model.getDrawing()); //evaluate the tips
			view.getDrawArea().updateLastDraw(); //show the details of the current draw
			model.updateStatistics(); //update the overall-statistics
			view.getDrawArea().playAnimations(); //show the animations
		} else
			Alerts.getNoTipsAlert().showAndWait();
	}
}