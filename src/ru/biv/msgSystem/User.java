package ru.biv.msgSystem;

//import java.util.HashMap;
//import java.util.Map;
//import java.util.Map.Entry;

public class User {
	//private Map<String, Integer> nameToId = new HashMap<String, Integer>();
	private String userName;
	private Integer userId;
			
	public void setUserNameToId(String userName, Integer userId) {
		//nameToId.put(userName, userId);
		this.userName = userName;
		this.userId = userId;
	}
	
	public Integer getId() {//String name) {
		//return nameToId.get(name);
		return userId;
	}
	
	public String getName() {
		return userName;
	}
	
	public void clear() {//String name) {
		//for( Entry<String, Integer> entry : nameToId.entrySet() ){
			//if (entry.getKey() == name) {
				//nameToId.clear();
			//}
		//}
		this.userName = null;
		this.userId = null;
	}
	
	public String toString() {
		String result = "";
		if (this.userName != null) {
			result += this.userName + " ";
		}
		if (this.userId != null) {
			result += this.userId.toString();
		}
		//for( Entry<String, Integer> entry : nameToId.entrySet() ){
		//	result += entry.getKey() + " " + entry.getValue() + "\n";
		//}
		return result;
	}
	
}
