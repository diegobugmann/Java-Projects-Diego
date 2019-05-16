package Lottery.View;

import Lottery.Model.LotteryModel;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class TipArea extends GridPane {
	
	private TipTable tips;
	public Button addBtn;
	public Button deleteBtn;
	public Button clearBtn;
	
	public TipArea(LotteryModel model) {
		tips = new TipTable(model);
		addBtn = new Button("Tipp hinzufügen");
		addBtn.setPrefWidth(508);
		deleteBtn = new Button("Tipp löschen");
		deleteBtn.setPrefWidth(249);
		clearBtn = new Button("Liste leeren");
		clearBtn.setPrefWidth(249);
		
		this.add(tips, 0, 0, 2, 1);
		this.add(addBtn, 0, 1, 2, 1);
		this.add(deleteBtn, 0, 2);
		this.add(clearBtn, 1, 2);
		
		this.setHgap(9.45);
		this.setVgap(4);
		this.getStyleClass().add("tipArea");
	}
	
	public TipTable getTipTable() {
		return this.tips;
	}
}