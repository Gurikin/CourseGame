/**
 * 
 */
package ru.biv.accountService;

import java.util.HashMap;
import java.util.Map;

import ru.biv.base.*;
import ru.biv.utils.TimeHelper;

/**
 * @author Banshikov Igor (BIV)
 *
 */
public class AccountServiceImpl implements Runnable, Abonent, AccountService{
	
	private Address address;
	private MessageSystem ms;
	
	
	private Map<String, Integer> takeAccounter = new HashMap<String, Integer>();
	

	/**
	 * 
	 */
	public AccountServiceImpl(MessageSystem ms) {
		this.ms = ms;
		this.address = new Address();
		ms.addService(this);
		this.takeAccounter.put("r", 1);
		this.takeAccounter.put("ger", 2);
		this.takeAccounter.put("g", 3);
		this.takeAccounter.put("a", 4);
	}
	
	@Override
	public void run() {
		while (true) {
			ms.execForAbonent(this);
			TimeHelper.sleep(30);
		}
	}
	
	public Integer getUserId (String userName){
		if (takeAccounter.get(userName) != null) {
			return takeAccounter.get(userName);
		} else {
			return 0;
		}
		
		
	}

	@Override
	public Address getAddress() {
		return address;
	}

	public MessageSystem getMessageSystem() {
		return ms;
	}
}
