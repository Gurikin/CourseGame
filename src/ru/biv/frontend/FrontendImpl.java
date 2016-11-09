/**
 * 
 */
package ru.biv.frontend;

import java.io.*;

import javax.servlet.ServletException;
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
//import ru.biv.accountService.AccountServiceImpl;
import ru.biv.base.*;


/**
 * @author Игорь
 *
 */
public class FrontendImpl extends AbstractHandler implements Runnable, Abonent, Frontend {

	private MessageSystem ms;
	private AddressImpl address;
	private User user = new User();
		
	public FrontendImpl(MessageSystem ms) {
  	this.ms = ms;
		this.address = new AddressImpl();
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
		 //handlers.setHandlers(new Handler[] { resource_handler, new Frontend() });
		 handlers.setHandlers(new Handler[]{resource_handler, new SessionHandler(sessionManager), this});
		 return handlers;
	}
	
	@Override
	public void run() {
		while(true) {
			ms.execForAbonent(this);
			//TimeHelper.sleep(5000);
		}			
	}
	
	public AddressImpl getAddress() {
		return address;
	}
	
	@Override
	public void handle(String target,
      Request baseRequest,
      HttpServletRequest request,
      HttpServletResponse response )
			throws IOException, ServletException {
		
		UserSession userSession = new UserSession();
		//User user = new User();
		
		HttpSession httpSession = request.getSession(true);
		userSession.setUserSession(user, httpSession);
		//PrintWriter out = response.getWriter();

		StringBuffer url = request.getRequestURL();

		httpSession.setAttribute("URL", url);
		httpSession.setMaxInactiveInterval(60*15); //time inactive in seconds
		
		// Declare response encoding and types
    response.setContentType("text/html; charset=utf-8");

    // Declare response status code
    response.setStatus(HttpServletResponse.SC_OK);

    // Inform jetty that this request has now been handled
    baseRequest.setHandled(true);
    		
    String name = request.getParameter("userName");
    Integer id = 0;
    if (name != null) {
    	id = user.getId(name);
    }
    if (id != null) {
    	response.getWriter().println(PageGenerator.getPage(userSession));
    	System.out.println(userSession.toString());
      //System.out.println(user.toString());
    } else {
    	userSession.setUserSession(user, httpSession);
      System.out.println(userSession.toString());
      //System.out.println(user.toString());
    	response.getWriter().println(PageGenerator.getPage(userSession));
    	Address addressAS = ms.getAddressService().getAddress(AccountServiceImpl.class);
    	ms.sendMessage(new MsgGetUserId(getAddress(), addressAS, name));    	
    }
	}
	
	public void setUserId(String userName, Integer userId) {
		user.setUserNameToId(userName, userId);
	}
	
	public MessageSystem getMessageSystem() {
		return ms;
	}
}