package Lottery.View;

import Lottery.Model.LotteryModel;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class TipArea extends GridPane {
	
	private TipTable tips;
	public Button addBtn;
	public Button addRandBtn;
	public Button deleteBtn;
	public Button clearBtn;
	private Label explanation;
	private Label explanation2;
	private Label explanation3;
	
	public TipArea(LotteryModel model) {
		tips = new TipTable(model);
		addBtn = new Button("Tipp hinzufügen");
		addBtn.setPrefWidth(130);
		addRandBtn = new Button("Zufallstipp generieren");
		addRandBtn.setPrefWidth(130);
		deleteBtn = new Button("Tipp löschen");
		deleteBtn.setPrefWidth(130);
		clearBtn = new Button("Liste leeren");
		clearBtn.setPrefWidth(130);
		explanation = new Label("Tipp-Hinweise:");
		explanation2 = new Label("Zahlen 1-42");
		explanation3 = new Label("Superzahlen 1-6");
		explanation.setId("explanation");
		explanation2.setId("explanation");
		explanation3.setId("explanation");
		
		this.add(tips, 0, 0, 7, 1);
		this.add(addBtn, 0, 1);
		this.add(addRandBtn, 1, 1);
		this.add(deleteBtn, 2, 1);
		this.add(clearBtn, 3, 1);
		this.add(explanation, 0, 2, 2, 2);
		this.add(explanation2, 1, 2, 2, 2);
		this.add(explanation3, 2, 2, 2, 2);
		
		this.setHgap(10);
		this.setVgap(2);
		
		
	}
	
	public TipTable getTipTable() {
		return this.tips;
	}

}
