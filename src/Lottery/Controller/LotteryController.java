package Lottery.Controller;

import Lottery.Model.LotteryModel;
import Lottery.View.LotteryView;

public class LotteryController {
	
	private LotteryModel model;
	private LotteryView view;
	
	public LotteryController(LotteryModel model, LotteryView view) {
		this.model = model;
		this.view = view;
	}

}
