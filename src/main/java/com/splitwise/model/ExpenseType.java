package com.splitwise.model;

public enum ExpenseType {
	EXACT("EXACT"),
	EQUAL("EQUAL"),
	PERCENTAGE("PERCENTAGE");
	
	private final String displayName;

	ExpenseType(String displayName) {
		this.displayName = displayName;
	}
	
    public String getDisplayName() {
        return displayName;
    }
    
    public static ExpenseType fromString(String text) {
        for (ExpenseType expenseType : ExpenseType.values()) {
            if (expenseType.displayName.equalsIgnoreCase(text)) {
                return expenseType;
            }
        }
        throw new IllegalArgumentException("No constant with the specified display name: " + text);
    }
	
	
}
