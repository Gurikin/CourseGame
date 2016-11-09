package ru.biv.base;

import ru.biv.base.Abonent;
import ru.biv.msgSystem.AddressImpl;
import ru.biv.base.MessageSystem;

public interface AccountService extends Abonent {
	
	public Integer getUserId (String userName);
	
	public MessageSystem getMessageSystem();
	
	public AddressImpl getAddress();
}
