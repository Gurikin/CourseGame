package ru.biv.msgSystem;

import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;

//@SuppressWarnings("serial")
@SuppressWarnings("serial")
public class UserSession implements Serializable{
	private Map<String, Integer> user = new HashMap<String, Integer>();
	private String enemyName;
	private int[] currentStep = new int[2];
	private int numStepsUser;
	private int numStepsEnemy;
	private enum stone {BLACK, WHITE};
	private enum auth {AUTHORIZATION, AUTH, DONT_AUTH};
		
	public UserSession() {
		userClear();
	}
	
	public void setUser(String userName, Integer userId) {
		userClear();
		user.put(userName, userId);
	}
	
	public Map<String, Integer> getUser() {
		return user;
	}
	
	public Integer getUserId(String userName){
		return user.get(userName);
	}
	
	public String getUserName() {
		String userName = "";
		for (Entry<String, Integer> entry : user.entrySet()) {
			userName = entry.getKey();
		}
		return userName;
	}
	
	public String getAuth() {
		if (this.getUserName() == null && this.user.get(this.getUserName()) == null) {
			return "";
		}
		if (this.user.get(this.getUserName()) == null) {
			return auth.AUTHORIZATION.toString(); 
		}
		if (this.user.get(this.getUserName()) == 0) {
			return auth.DONT_AUTH.toString(); 
		}
		if (this.user.get(this.getUserName()) != null) {
			return auth.AUTH.toString(); 
		} else {
			return ""; 
		}		
	}
	
	public void userClear() {
		user.clear();
	}
	
	/*public String toString() {
		String result = "";
		for (Entry<String, Integer> entry : user.entrySet()) {
			if (entry.getKey() != null) {
				result += "UserName: " + entry.getKey(); 
			}
			if (entry.getValue().toString() != null) {
				result += "\nUserID: " + entry.getValue().toString() + "\n";
			}
		}
		return result;
	}*/
}
