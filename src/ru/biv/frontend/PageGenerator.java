/**
 * 
 */
package ru.biv.frontend;

import java.io.*;
import java.util.*;

import javax.servlet.http.HttpSession;

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
    
    /*public static String getStartPage(HttpSession httpSession) throws FileNotFoundException {
    	String response = "";
    	response = getUserNamePage("<h2>Input your nick, please. Your session id: " + httpSession.getId() + "</h2>");
  		return response;
    }*/
    
    public static String getStartPage(HttpSession httpSession) throws FileNotFoundException {
    	String response = "";
    	response = "Input your nick, please. Your session id: " + httpSession.getId();
  		return response;
    }
    
    public static String getPage(HttpSession httpSession, UserSession userSession) throws FileNotFoundException {
    	String response = "";
    	if (userSession.getAuth() == "DONT_AUTH") {
    		response = "К сожалению такого пользователя мы не нашли. Хотите зарегестрироваться?";
    	}
    	if (userSession.getAuth() == "AUTH") {
    		response = "User name: " + userSession.getUserName() + " Id: " + userSession.getUserId(userSession.getUserName()) + " SessionId: " + httpSession.getId();
    	} 
    	if (userSession.getAuth() == "AUTHORIZATION") {
    		response = "Авторизация уже на подходе. Your SessionId: " + httpSession.getId();
    	} 
    	return response;
    }
    
    private static String getUserNamePage(String body) throws FileNotFoundException{
    	//System.out.println(FileWorker.read("index.html") + getTime() + pagePart1);PageGenerator.getTime()
    	//String servTime = getTime();
    	return FileWorker.read("getName.html") + body + PAGE_END;
    	//return result;
    }
    
    /*private static String getWaitPage(String body) throws FileNotFoundException{
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
    }*/
}
