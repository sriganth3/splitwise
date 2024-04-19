package com.splitwise.service;

import java.util.List;

import com.splitwise.model.Expense;
import com.splitwise.model.ExpenseType;
import com.splitwise.model.MetaData;
import com.splitwise.model.split.PercentSplit;
import com.splitwise.model.split.Split;
import com.splitwise.model.User;

public class ExpenseService {

	public Expense createExpense(ExpenseType expenseType, double amount, User paidBy, MetaData expenseData, List<Split> splits) {
		switch(expenseType) {
			case EQUAL :  
				int size = splits.size();
				double equalAmount = (double) amount / size;
				for(Split split: splits) {
					split.setAmount(equalAmount);
				}
				return new Expense(splits, expenseData, amount, paidBy);
				

			case EXACT : 
				return new Expense(splits, expenseData, amount, paidBy);
				
			case PERCENTAGE :
				for(Split split: splits) {
					PercentSplit percentSplit = (PercentSplit) split;
					split.setAmount(amount * percentSplit.getPercent());
				}
				return new Expense(splits, expenseData, amount, paidBy);
				
			default : 
				return null;
			
		}

	}
}
