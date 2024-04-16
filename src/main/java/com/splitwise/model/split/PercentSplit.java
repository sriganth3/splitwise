package com.splitwise.model.split;
import com.splitwise.model.User;

public class PercentSplit extends Split{
		
	private double percent;
	
	public PercentSplit(User user, double percent) {
		super(user);
		this.percent = percent;
		
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}
}
