package ru.biv.base;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.HandlerList;

import ru.biv.base.Abonent;
import ru.biv.base.MessageSystem;
import ru.biv.msgSystem.UserSession;

public interface Frontend extends Abonent {
	public HandlerList getHandlers();
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response ) throws IOException, ServletException;
	public void setUserId(String userName, Integer userId);
	public void updateUserSession(UserSession userSession);
	public MessageSystem getMessageSystem();
}
