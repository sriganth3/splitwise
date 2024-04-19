package com.splitwise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.splitwise.model.Expense;
import com.splitwise.model.ExpenseType;
import com.splitwise.model.MetaData;
import com.splitwise.model.User;
import com.splitwise.model.split.Split;
import com.splitwise.service.ExpenseService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExpenseManager {

	private Map<String, User> userMap = new HashMap<>();
	private ExpenseService service = new ExpenseService();
	private List<Expense> expenseHistory = new ArrayList<>();
	private Map<String, Map<String, Double>> balanceSheet = new HashMap<String, Map<String, Double>>();

	public boolean addUser(User user) {
		if(!userMap.containsKey(user.getUserId())) {
			userMap.put(user.getUserId(), user);
			return true;
		}

		log.error("user already exists");
		return false;
	}

	public void addExpense(ExpenseType expenseType, double amount, String paidBy, List<Split> splits, MetaData expenseData) {
		Expense expense = service.createExpense(expenseType, amount, userMap.get(paidBy), expenseData, splits);
		expenseHistory.add(expense);

		for(Split split: expense.getSplits()) {
			
			String paidTo = split.getUser().getUserId();
			
			if(!balanceSheet.containsKey(paidBy)) {
				balanceSheet.put(paidBy, new HashMap<>());
			}
			Map<String, Double> balances = balanceSheet.get(paidBy);
			
			if (!balances.containsKey(paidTo)) {
				balances.put(paidTo, 0.0);
			}
			
			if(!balanceSheet.containsKey(paidTo)) {
				balanceSheet.put(paidTo, new HashMap<>());
			}
			balances.put(paidTo, balances.get(paidTo) + split.getAmount());

			balances = balanceSheet.get(paidTo);
			
			if (!balances.containsKey(paidBy)) {
				balances.put(paidBy, 0.0);
			}
			
			balances.put(paidBy, balances.get(paidBy) - split.getAmount());
		}	
	}

	public Map<String, User> getUserMap() {
		return userMap;
	}


}
