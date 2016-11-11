/**
 * 
 */
package ru.biv.frontend;

import java.io.*;
import java.util.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import ru.biv.fileSystem.*;
import ru.biv.msgSystem.*;


/**
 * @author Игорь
 *
 */
public class PageGenerator {

    private final static String PAGE_END = "</div>" + "</form>" + "</body>" + "</html>";
    		
    			
    public static String getTime(){
    	Date date = new Date();
    	date.getTime();
    	DateFormat formatter = new SimpleDateFormat("HH.mm.ss");
    	return formatter.format(date);
    }
    		
    public static String getPage(UserSession userSession) throws FileNotFoundException {
    	String auth = "";
    	if (userSession.getAuth() == "DONT_AUTH") {
    		auth = "К сожалению такого пользователя мы не нашли. Хотите зарегестрироваться?";
    		//userSession.getUser().clear();
    	}
    	if (userSession.getAuth() == "AUTH") {
    		//return getUserNamePage("<h1>User name: " + userSession.getUser().getName() + " Id: " + userSession.getUser().getId(userSession.getUser().getName()) + " SessionId: " + userSession.getSession().getId() + "</h1>");
    		//return "<h1>User name: " + userSession.getUser().getName() + " Id: " + userSession.getUser().getId(userSession.getUser().getName()) + " SessionId: " + userSession.getSession().getId() + "</h1>";
    		auth = "User name: " + userSession.getUser().getName() + " Id: " + userSession.getUser().getId(userSession.getUser().getName()) + " SessionId: " + userSession.getSession().getId();
    	} 
    	if (userSession.getAuth() == "START") {
    		//return getUserNamePage("<h2>Input your nick, please.</h2>");
    		//return "<h2>Input your nick, please.</h2>";
    		auth = getUserNamePage("<h2>Input your nick, please. Your session id: " + userSession.getSession().getId() + "</h2>");
    		//return "Input your nick, please.";
    	} 
    	if (userSession.getAuth() == "AUTHORIZATING") {
    		auth = "Авторизация уже на подходе. Your SessionId: " + userSession.getSession().getId();
    		//userSession.getUser().clear();
    	} 
    	return auth;
    	/*if (user.getId(user.getUserName()) == (-1)) {
    		return getUserNamePage("<h2>Input your nick, please.</h2>");
    	}
    	return getWaitPage("<h2>Авторизация уже на подходе. Your SessionId: " + httpSession.getId() + "</h2>");*/
    }
    
    private static String getUserNamePage(String body) throws FileNotFoundException{
    	//System.out.println(FileWorker.read("index.html") + getTime() + pagePart1);PageGenerator.getTime()
    	//String servTime = getTime();
    	return FileWorker.read("getName.html") + body + PAGE_END;
    	//return result;
    }
    
    private static String getWaitPage(String body) throws FileNotFoundException{
    	//System.out.println(FileWorker.read("index.html") + getTime() + pagePart1);PageGenerator.getTime()
    	//String servTime = getTime();
    	return FileWorker.read("wait.html") + body + PAGE_END;
    	//return result;
    }
    private static String getIndexPage(String body) throws FileNotFoundException{
    	//System.out.println(FileWorker.read("index.html") + getTime() + pagePart1);PageGenerator.getTime()
    	//String servTime = getTime();
    	return FileWorker.read("index.html") + body + PAGE_END;
    	//return result;
    }
}
