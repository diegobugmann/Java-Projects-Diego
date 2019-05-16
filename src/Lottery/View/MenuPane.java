package Lottery.View;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuPane extends MenuBar {
	
	public MenuItem hints;
	public MenuItem odds;
	public MenuItem stats;
	
	public MenuPane() {
		Menu hintMenu = new Menu("Hilfe");
		hints = new MenuItem("Tipp-Hinweise");
		odds = new MenuItem("Gewinne & Wahrscheinlichkeiten");
		hintMenu.getItems().addAll(hints, odds);
		Menu statMenu = new Menu("Statistik");
		stats = new MenuItem("Overall-Statistik anzeigen");
		statMenu.getItems().add(stats);
		
		this.getMenus().addAll(hintMenu, statMenu);
		this.getStyleClass().add("menuArea");
	}
}