package Lottery.View;

import java.util.ArrayList;

import Lottery.Controller.LotteryController;
import Lottery.Model.Combination;
import Lottery.Model.LotteryModel;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class DrawArea extends BorderPane {
	
	private VBox content = new VBox();
	private LotteryModel model;
	private Label currentTipInfo;
	public Button drawBtn;
	private HBox balls = new HBox();
	private Label lastDraw;
	private Label lastDrawInfo;
	private ArrayList<ParallelTransition> animations;
	private FadeTransition fade;
	
	public DrawArea(LotteryModel model) {
		this.model = model;
		this.currentTipInfo = new Label("Anzahl Tipps: 0\nAktuelle Kosten: CHF 0.00");
		this.lastDraw = new Label();
		this.lastDraw.setId("header");
		this.lastDrawInfo = new Label();
		this.lastDrawInfo.setMinWidth(240);
		this.drawBtn = new Button("LOTTO SPIELEN");
		this.drawBtn.setPrefSize(100, 30);
		this.animations = new ArrayList<ParallelTransition>();
		for (int i = 0; i < 7; i++) {
			DrawBall db = new DrawBall();
			balls.getChildren().add(db);
			db.getCircle().setId("regNum");
			if (i == 6) db.getCircle().setId("supNum"); //superNumber has another color
		}
		
		this.balls.setSpacing(5);
		this.balls.setOpacity(0); //make them invisible in the beginning
		this.balls.setAlignment(Pos.TOP_CENTER);
		
		this.content.setSpacing(20);
		this.content.setAlignment(Pos.TOP_CENTER);
		this.content.getChildren().addAll(currentTipInfo, drawBtn, lastDraw, lastDrawInfo);
		
		this.setCenter(content);
		this.setBottom(balls);
		this.getStyleClass().add("drawArea");
	}
	
	public void displayNumbers(Combination drawing) {
		this.balls.setOpacity(1); //the balls have to be visible to be displayed
		int num = 0;
		for (int i = 0; i < 7; i++) {
			if (i != 6) num = drawing.getNumber(i).getValue();
			else num = drawing.getSupNum().getValue();
			DrawBall db = (DrawBall) balls.getChildren().get(i);
			db.setText(Integer.toString(num));
		}
	}
	
	public Label getCurrentTipInfoLabel() {
		return this.currentTipInfo;
	}
	
	public void updateLastDraw() {
		this.lastDraw.setText("Letzte Ziehung");
		this.lastDrawInfo.setText("Anzahl Tipps: "+model.getTipList().getTips().size()+"\n"+
				"Kosten: "+LotteryController.dm.format(model.getTipList().getValueInCHF())+"\n"+
				(model.getTipList().displayWins() != "" ? "Gewinn: CHF "+
				model.getTipList().getWinSum()+"\n"+model.getTipList().displayWins() : "Leider kein Gewinn!"));
	}
	
	public void playAnimations() {
		if (animations.isEmpty()) //singleton
			generateAnimations();
		lastDrawInfo.setOpacity(0); //only show the information after drawing
		for (int i = 0; i < animations.size(); i++) {
			Animation a = animations.get(i);
			a.play();
			if (i == animations.size()-1) 
				a.setOnFinished(e -> fade.play()); //show the information after the last BallAnimation
			fade.setOnFinished(e -> drawBtn.setDisable(false)); //enable button after fade is finished
		}
	}
	
	private void generateAnimations() {
		for (int i = 0; i < 7; i++) {
			DrawBall db = (DrawBall) balls.getChildren().get(i);
			Path path = new Path();
			path.getElements().add(new MoveTo(25, -470));
			path.getElements().add(new LineTo(25, 15));
			PathTransition move = new PathTransition(Duration.millis(1200+(i*200)), path, db);
			RotateTransition rotate = new RotateTransition(Duration.millis(200), db);
			rotate.setCycleCount(6+(i*1));
			rotate.setByAngle(360);
			ParallelTransition animation = new ParallelTransition(move, rotate);
			animations.add(animation);
		}
		fade = new FadeTransition(Duration.millis(500), this.lastDrawInfo);
		fade.setFromValue(0);
	    fade.setToValue(1);
	    fade.setCycleCount(1);
	}
}