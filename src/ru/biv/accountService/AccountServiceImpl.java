/**
 * 
 */
package ru.biv.accountService;

import java.util.HashMap;
import java.util.Map;

import ru.biv.base.*;
import ru.biv.msgSystem.*;
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
		this.address = new AddressImpl();
		ms.addService(this);
		this.takeAccounter.put("Garry", 1);
		this.takeAccounter.put("Molly", 2);
	}
	
	@Override
	public void run() {
		while (true) {
			ms.execForAbonent(this);
			//TimeHelper.sleep(6000);
		}
	}
	
	public Integer getUserId (String userName){
		return takeAccounter.get(userName);
	}

	@Override
	public AddressImpl getAddress() {
		return (AddressImpl) address;
	}

	public MessageSystem getMessageSystem() {
		return ms;
	}

}
