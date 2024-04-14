package com.splitwise;

import java.util.HashMap;
import java.util.Map;

import com.splitwise.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExpenseManager {
	
	private Map<String, User> userMap = new HashMap<>();
	
	public boolean addUser(User user) {
		if(!userMap.containsKey(user.getUserId())) {
			userMap.put(user.getUserId(), user);
			return true;
		}
		
		log.error("user already exists");
		return false;
	}
	

}
