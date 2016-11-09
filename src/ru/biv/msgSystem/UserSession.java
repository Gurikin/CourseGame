package ru.biv.msgSystem;

import java.util.*;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

public class UserSession {
	private Map<HttpSession, User> sessionToUser = new HashMap<HttpSession, User>();
	private User user;
	private HttpSession session;
	enum auth {AUTHORIZATING, AUTH, DONT_AUTH, START};
		
	public UserSession() {
	}
	
	public void setUserSession(User user, HttpSession session) {
		sessionToUser.put(session, user);
		this.user = user;
		this.session = session;
	}

	public User getUser() {
		return this.user;
	}
	
	public HttpSession getSession() {
		return this.session;
	}
	
	public String getAuth () {
		if (session != null & user != null) {
			if (user.getName() != null & user.getId(user.getName()) == null) {
				user.clear();
				return auth.AUTHORIZATING.toString();
			}
			if (user.getName() == null) {
				return auth.DONT_AUTH.toString();
			}
			if (user.getId(user.getName()) != null) {
				return auth.AUTH.toString();
			} else {
				return auth.START.toString();
			}			
		} else {
			return auth.START.toString();
		}		
	}
	
	public String toString() {
		String result = "";
		for( Entry<HttpSession, User> entry : sessionToUser.entrySet() ){
			result += "Session: " + entry.getKey().getId() + "\nUser: " + entry.getValue().toString() + "\n";
		}
		return result;
	}
}
