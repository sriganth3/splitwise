package com.splitwise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.splitwise.model.ExpenseType;
import com.splitwise.model.User;
import com.splitwise.model.split.EqualSplit;
import com.splitwise.model.split.ExactSplit;
import com.splitwise.model.split.PercentSplit;
import com.splitwise.model.split.Split;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
	public static void main( String[] args ){

		ExpenseManager expenseManager = new ExpenseManager();

		expenseManager.addUser(new User("u1","Sriganth","sg@sg.com","1111111111"));
		expenseManager.addUser(new User("u2","Sriganth","sg@sg.com","1111111111"));

		Scanner scanner = new Scanner(System.in);

		boolean flag = true;


        while (flag) {
        	log.info("Enter command in following sequence: EXPENSE <user-id-of-person-who-paid> <amount> <no-of-users> <space-separated-list-of-users> <EQUAL/EXACT/PERCENT> <space-separated-values-in-case-of-non-equal>");

            String command = scanner.nextLine();
            String[] commands = command.split(" ");
            String commandType = commands[0];

            switch (commandType) {
                case "EXPENSE":
                    String paidBy = commands[1];
                    Double amount = Double.parseDouble(commands[2]);
                    int noOfUsers = Integer.parseInt(commands[3]);
                    String expenseType = commands[4 + noOfUsers];
                    List<Split> splits = new ArrayList<>();
                    switch (expenseType) {
                        case "EQUAL":
                            for (int i = 0; i < noOfUsers; i++) {
                                splits.add(new EqualSplit(expenseManager.getUserMap().get(commands[4 + i])));
                            }
                            expenseManager.addExpense(ExpenseType.EQUAL, amount, paidBy, splits, null);
                            break;
                        case "EXACT":
                            for (int i = 0; i < noOfUsers; i++) {
                                splits.add(new ExactSplit(expenseManager.getUserMap().get(commands[4 + i]), Double.parseDouble(commands[5 + noOfUsers + i])));
                            }
                            expenseManager.addExpense(ExpenseType.EXACT, amount, paidBy, splits, null);
                            break;
                        case "PERCENT":
                            for (int i = 0; i < noOfUsers; i++) {
                                splits.add(new PercentSplit(expenseManager.getUserMap().get(commands[4 + i]), Double.parseDouble(commands[5 + noOfUsers + i])));
                            }
                            expenseManager.addExpense(ExpenseType.PERCENTAGE, amount, paidBy, splits, null);
                            break;
                    }
                    break;
            }
            
            log.info("Press Y to exit:");
            command = scanner.next();
            if("Y".equals(command)) {
            	flag = false;
            }
        }
        
        scanner.close();
	}
}
