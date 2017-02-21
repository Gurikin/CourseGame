/**
 * 
 */
package ru.biv.frontend;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.SessionManager;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;

import ru.biv.msgSystem.*;
import ru.biv.utils.TimeHelper;
import ru.biv.accountService.AccountServiceImpl;
import ru.biv.base.*;
import ru.biv.gameMechanic.GameMechanicImpl;
import ru.biv.gameMechanic.MsgStartGameSession;


/**
 * @author Banchikov Igor Vladimirovich
 *
 */
public class FrontendObject extends AbstractHandler implements Runnable, Abonent, Frontend {

	private MessageSystem ms;
	private Address address;
	
	private Map<HttpSession, UserSession> sessionIdToSession = new HashMap<HttpSession, UserSession>();
	private HttpSession httpSession;
	private UserSession responseUserSession;
	private UserSession requestUserSession = new UserSession();
	
	private static final String LOGIN_URL = "/hello";
	
	ObjectInputStream objectInputStream;
	ObjectOutputStream objectOutputStream;
	
	public FrontendObject(MessageSystem ms) {
  	this.ms = ms;
		this.address = new Address();
		ms.addService(this);		
  }
	
	public HandlerList getHandlers() {
		 //Create the ResourceHandler. It is the object that will actually handle the request for a given file. It is
		 // a Jetty Handler object so it is suitable for chaining with other handlers as you will see in other examples.
		 ResourceHandler resource_handler = new ResourceHandler();
		 //Для хранения сессии добавляем Session manager в handler list. Создаем и настраиваем сессию в методе handle 
		 SessionManager sessionManager = new HashSessionManager(); 
     sessionManager.setSessionIdPathParameterName("none"); 
     
		 // Configure the ResourceHandler. Setting the resource base indicates where the files should be served out of.
		 // In this example it is the current directory but it can be configured to anything that the jvm has access to.
		 resource_handler.setDirectoriesListed(true);
		 resource_handler.setWelcomeFiles(new String[]{ "index.html" });
		 resource_handler.setResourceBase("./Java/Project/Eclipse/CourseGame/");

		 // Add the ResourceHandler to the server.
		 HandlerList handlers = new HandlerList();
		 handlers.setHandlers(new Handler[]{resource_handler, new SessionHandler(sessionManager), this});
		 return handlers;
	}
	
	@Override
	public void run() {
		while(true) {
			ms.execForAbonent(this);
			TimeHelper.sleep(1000);
		}			
	}
	
	public Address getAddress() {
		return address;
	}
	
	@Override
	public void handle(String target,
      Request baseRequest,
      HttpServletRequest request,
      HttpServletResponse response )
			throws IOException, ServletException {
		
		try {
			objectInputStream = new ObjectInputStream(request.getInputStream());
			requestUserSession = (UserSession) objectInputStream.readObject();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			objectInputStream.close();
		}
		
		this.httpSession = request.getSession(true);
		if (this.sessionIdToSession.get(httpSession) == null) {
			responseUserSession = new UserSession();
			this.sessionIdToSession.put(httpSession, responseUserSession);
		}		
		StringBuffer url = request.getRequestURL();
				
		httpSession.setAttribute("URL", url);
		httpSession.setMaxInactiveInterval(60*15); //time inactive in seconds
		
		// Declare response encoding and types
    response.setContentType("text/html; charset=utf-8");

    // Declare response status code
    response.setStatus(HttpServletResponse.SC_OK);

    // Inform jetty that this request has now been handled
    baseRequest.setHandled(true);
    if(!target.equals(LOGIN_URL))
			return;
    
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
    	for (Cookie cookie : cookies) {
      	if (cookie != null) {
      		System.out.print(cookie.getName().toString()+"\t");
      		System.out.println(cookie.getValue().toString());
      	}    	
      }
    }   

    Integer id = null;
    String name = requestUserSession.getUserName();
    System.out.println("Имя: "+name+"    ");
    if (name != null) {
    	id = sessionIdToSession.get(httpSession).getUserId(name);
    	System.out.println("userId: "+id);
    }
    sessionIdToSession.get(httpSession).setUser(name, id);
    objectOutputStream = new ObjectOutputStream(response.getOutputStream());        
    if (id != null) {
    	Address addressGM = ms.getAddressService().getAddress(GameMechanicImpl.class);
    	ms.sendMessage(new MsgStartGameSession(getAddress(), addressGM, id));
    	objectOutputStream.writeObject(sessionIdToSession.get(httpSession));
    	objectOutputStream.flush();
    	objectOutputStream.close();
    } else {
    	Address addressAS = ms.getAddressService().getAddress(AccountServiceImpl.class);
    	ms.sendMessage(new MsgGetUserId(getAddress(), addressAS, name));
    	objectOutputStream.writeObject(sessionIdToSession.get(httpSession));
    	objectOutputStream.flush();
    	objectOutputStream.close();
    }
  }
	
	public void setUserId(String userName, Integer userId) {
		sessionIdToSession.get(httpSession).setUser(userName, userId);
	}
	
	public MessageSystem getMessageSystem() {
		return ms;
	}
}