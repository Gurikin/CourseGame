package ru.biv.msgSystem;

import java.util.*;

import javax.servlet.http.HttpSession;

public class UserSession {
	private Map<HttpSession, User> sessionToUser = new HashMap<HttpSession, User>();
	private User user;
	private HttpSession session;
	enum auth {AUTHORIZATING, AUTH, DONT_AUTH};
		
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
		if (session == null & user == null) {
			return auth.DONT_AUTH.toString();
		}
		if (session != null & user.getId(user.getName()) != null) {
			return auth.AUTH.toString();
		} else {
			return auth.AUTHORIZATING.toString();
		}
		
	}	
}
