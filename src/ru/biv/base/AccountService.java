package ru.biv.base;

import ru.biv.msgSystem.Abonent;
import ru.biv.msgSystem.MessageSystem;

public interface AccountService extends Abonent {
	
	public Integer getUserId (String userName);
	
	public MessageSystem getMessageSystem();
}
