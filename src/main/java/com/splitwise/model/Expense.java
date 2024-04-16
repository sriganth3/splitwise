package com.splitwise.model;

import java.util.List;

import com.splitwise.model.split.Split;

public class Expense {
	
	private List<Split> splits;
	
	private MetaData metadata;
	
	private double amount;
	
	private User paidBy;
	
	public Expense(List<Split> splits, MetaData metadata, double amount, User paidBy) {
		super();
		this.splits = splits;
		this.metadata = metadata;
		this.amount = amount;
		this.paidBy = paidBy;
	}

	public List<Split> getSplits() {
		return splits;
	}

	public void setSplits(List<Split> splits) {
		this.splits = splits;
	}

	public MetaData getMetadata() {
		return metadata;
	}

	public void setMetadata(MetaData metadata) {
		this.metadata = metadata;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public User getPaidBy() {
		return paidBy;
	}

	public void setPaidBy(User paidBy) {
		this.paidBy = paidBy;
	}
	
	

}
