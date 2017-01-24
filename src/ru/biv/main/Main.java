/**
 * 
 */
package ru.biv.main;

import org.eclipse.jetty.server.Server;
//import org.eclipse.jetty.server.ServerConnector;

import ru.biv.accountService.AccountServiceImpl;
//import ru.biv.accountService.AccountServiceImpl;
import ru.biv.base.*;
import ru.biv.frontend.FrontendImpl;
import ru.biv.frontend.FrontendObject;
import ru.biv.msgSystem.*;

/**
 * @author Banchikov Igor (BIV)
 *
 */
public class Main {

	public static void main(String[] args) throws InterruptedException, Exception {
		MessageSystem ms = new MessageSystemImpl();
		
		Frontend frontend = new FrontendObject(ms);
		AccountService accountService = new AccountServiceImpl(ms);
		
		new Thread((Runnable) frontend).start();
		new Thread((Runnable) accountService).start();
		
		Server server = new Server(8080);
		server.setHandler(frontend.getHandlers());
				
		server.start();
	  //server.dumpStdErr();
	  server.join();
	}
}
