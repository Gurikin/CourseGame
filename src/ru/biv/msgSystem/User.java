package ru.biv.msgSystem;

import java.util.HashMap;
import java.util.Map;

public class User {
	private Map<String, Integer> nameToId = new HashMap<String, Integer>();
	String userName;
			
	public Integer getId(String name) {
		return nameToId.get(name);
	}
	public void setUserNameToId(String userName, Integer userId) {
		nameToId.put(userName, userId);
		this.userName = userName;
	}
	public String getName() {
		return userName;
	}
}
