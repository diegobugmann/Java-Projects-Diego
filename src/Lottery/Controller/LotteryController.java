package Lottery.Controller;

import Lottery.Model.Combination;
import Lottery.Model.LotteryModel;
import Lottery.Model.RegularNumber;
import Lottery.Model.SuperNumber;
import Lottery.View.LotteryView;
import javafx.collections.ListChangeListener;

public class LotteryController {
	
	private LotteryModel model;
	private LotteryView view;
	
	public LotteryController(LotteryModel model, LotteryView view) {
		this.model = model;
		this.view = view;
		
		view.getTipArea().addRandBtn.setOnAction(e -> model.getTipList().addTip(new Combination(null, null)));
		view.getTipArea().addRandBtn.setOnAction(e -> model.getTipList().addTip(new Combination()));
		view.getTipArea().deleteBtn.setOnAction(e -> {
			Combination selectedTip = view.getTipArea().getTipTable().getSelectionModel().getSelectedItem();
			model.getTipList().deleteTip(selectedTip);
		});
		view.getTipArea().clearBtn.setOnAction(e -> model.getTipList().clearTips());
		
		//handle the editable cells
		createEditCellEvents();
		
		/*
		model.getTipList().getTips().addListener((ListChangeListener<Combination>) c -> {
			while (c.next()) {
				view.tips.scrollTo(c.getFrom());
			}
		});
		*/
	}
	
	public void createEditCellEvents() {
				
		view.getTipArea().getTipTable().getColNumOne().setOnEditCommit(e -> {
			String newValue = e.getNewValue();
			if (RegularNumber.isValid(newValue) && !e.getRowValue().contains(newValue))
				e.getRowValue().getNumber(0).setNumber(Integer.parseInt(newValue));
			else
				e.getTableView().refresh();
		});
		
		view.getTipArea().getTipTable().getColNumTwo().setOnEditCommit(e -> {
			String newValue = e.getNewValue();
			if (RegularNumber.isValid(newValue) && !e.getRowValue().contains(newValue))
				e.getRowValue().getNumber(1).setNumber(Integer.parseInt(newValue));
			else
				e.getTableView().refresh();
		});
		
		view.getTipArea().getTipTable().getColNumThree().setOnEditCommit(e -> {
			String newValue = e.getNewValue();
			if (RegularNumber.isValid(newValue) && !e.getRowValue().contains(newValue))
				e.getRowValue().getNumber(2).setNumber(Integer.parseInt(newValue));
			else
				e.getTableView().refresh();
		});
		
		view.getTipArea().getTipTable().getColNumFour().setOnEditCommit(e -> {
			String newValue = e.getNewValue();
			if (RegularNumber.isValid(newValue) && !e.getRowValue().contains(newValue))
				e.getRowValue().getNumber(3).setNumber(Integer.parseInt(newValue));
			else
				e.getTableView().refresh();
		});
		
		view.getTipArea().getTipTable().getColNumFive().setOnEditCommit(e -> {
			String newValue = e.getNewValue();
			if (RegularNumber.isValid(newValue) && !e.getRowValue().contains(newValue))
				e.getRowValue().getNumber(4).setNumber(Integer.parseInt(newValue));
			else
				e.getTableView().refresh();
		});
		
		view.getTipArea().getTipTable().getColNumSix().setOnEditCommit(e -> {
			String newValue = e.getNewValue();
			if (RegularNumber.isValid(newValue) && !e.getRowValue().contains(newValue))
				e.getRowValue().getNumber(5).setNumber(Integer.parseInt(newValue));
			else
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

}
