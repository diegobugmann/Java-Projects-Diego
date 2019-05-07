package Lottery.View;

import Lottery.Model.Combination;
import Lottery.Model.LotteryModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;

public class TipTable extends TableView<Combination> {
	
	private TableColumn<Combination, Number> colTipNum;
	private TableColumn<Combination, String> colNumOne;
	private TableColumn<Combination, String> colNumTwo;
	private TableColumn<Combination, String> colNumThree;
	private TableColumn<Combination, String> colNumFour;
	private TableColumn<Combination, String> colNumFive;
	private TableColumn<Combination, String> colNumSix;
	private TableColumn<Combination, String> colSupNum;

	public TipTable(LotteryModel model) {
		
		this.setEditable(true);
		
		colTipNum = new TableColumn<>("Nr.");
		colTipNum.setSortable(false);
		colTipNum.setPrefWidth(45);
		colTipNum.setCellValueFactory(c -> 
			new ReadOnlyObjectWrapper<Number>((c.getTableView().getItems().indexOf(c.getValue()))+1));
		this.getColumns().add(colTipNum);

		colNumOne = new TableColumn<>("1. Zahl");
		colNumOne.setPrefWidth(70);
		colNumOne.setCellValueFactory(c -> c.getValue().getNumber(0).getValueProperty());
		colNumOne.setCellFactory(TextFieldTableCell.forTableColumn());
		this.getColumns().add(colNumOne);

		colNumTwo = new TableColumn<>("2. Zahl");
		colNumTwo.setPrefWidth(70);
		colNumTwo.setCellValueFactory(c -> c.getValue().getNumber(1).getValueProperty());
		colNumTwo.setCellFactory(TextFieldTableCell.forTableColumn());
		this.getColumns().add(colNumTwo);

		colNumThree = new TableColumn<>("3. Zahl");
		colNumThree.setPrefWidth(70);
		colNumThree.setCellValueFactory(c -> c.getValue().getNumber(2).getValueProperty());
		colNumThree.setCellFactory(TextFieldTableCell.forTableColumn());
		this.getColumns().add(colNumThree);
		
		colNumFour = new TableColumn<>("4. Zahl");
		colNumFour.setPrefWidth(70);
		colNumFour.setCellValueFactory(c -> c.getValue().getNumber(3).getValueProperty());
		colNumFour.setCellFactory(TextFieldTableCell.forTableColumn());
		this.getColumns().add(colNumFour);
		
		colNumFive = new TableColumn<>("5. Zahl");
		colNumFive.setPrefWidth(70);
		colNumFive.setCellValueFactory(c -> c.getValue().getNumber(4).getValueProperty());
		colNumFive.setCellFactory(TextFieldTableCell.forTableColumn());
		this.getColumns().add(colNumFive);
		
		colNumSix = new TableColumn<>("6. Zahl");
		colNumSix.setPrefWidth(70);
		colNumSix.setCellValueFactory(c -> c.getValue().getNumber(5).getValueProperty());
		colNumSix.setCellFactory(TextFieldTableCell.forTableColumn());
		this.getColumns().add(colNumSix);
		
		colSupNum = new TableColumn<>("Superzahl");
		colSupNum.setPrefWidth(70);
		colSupNum.setCellValueFactory(c -> c.getValue().getSupNum().getValueProperty());
		colSupNum.setCellFactory(TextFieldTableCell.forTableColumn());
		this.getColumns().add(colSupNum);
		
		this.setItems(model.getTipList().getTips());
	}
	
	public TableColumn<Combination, String> getColNumOne() {
		return colNumOne;
	}

	public TableColumn<Combination, String> getColNumTwo() {
		return colNumTwo;
	}

	public TableColumn<Combination, String> getColNumThree() {
		return colNumThree;
	}

	public TableColumn<Combination, String> getColNumFour() {
		return colNumFour;
	}

	public TableColumn<Combination, String> getColNumFive() {
		return colNumFive;
	}

	public TableColumn<Combination, String> getColNumSix() {
		return colNumSix;
	}

	public TableColumn<Combination, String> getColSupNum() {
		return colSupNum;
	}

}
