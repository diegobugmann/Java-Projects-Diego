package Lottery.View;

import Lottery.Model.LotteryModel;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class TipArea extends GridPane {
	
	private TipTable tips;
	public Button addBtn;
	public Button deleteBtn;
	public Button clearBtn;
	public Button hintsBtn;
	
	public TipArea(LotteryModel model) {
		tips = new TipTable(model);
		addBtn = new Button("Tipp hinzufügen");
		addBtn.setPrefWidth(258);
		deleteBtn = new Button("Tipp löschen");
		deleteBtn.setPrefWidth(258);
		clearBtn = new Button("Liste leeren");
		clearBtn.setPrefWidth(258);
		hintsBtn = new Button("Tipp-Hinweise");
		hintsBtn.setPrefWidth(258);
		
		this.add(tips, 0, 0, 3, 1);
		this.add(addBtn, 0, 1);
		this.add(hintsBtn, 0, 2);
		this.add(deleteBtn, 2, 1);
		this.add(clearBtn, 2, 2);

		
		this.setHgap(9.45);
		this.setVgap(4);
	}
	
	public TipTable getTipTable() {
		return this.tips;
	}

}
