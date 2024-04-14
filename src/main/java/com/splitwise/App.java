package com.splitwise;

import com.splitwise.model.User;

public class App {
    public static void main( String[] args ){
        
    	ExpenseManager expenseManager = new ExpenseManager();
 
    	expenseManager.addUser(new User("u1","Sriganth","sg@sg.com","1111111111"));
    	expenseManager.addUser(new User("u1","Sriganth","sg@sg.com","1111111111"));
    	
    	
    }
}
