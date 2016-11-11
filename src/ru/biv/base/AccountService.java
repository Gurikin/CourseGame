package ru.biv.base;

public interface AccountService extends Abonent {
	
	public Integer getUserId (String userName);
	
	public MessageSystem getMessageSystem();
	
	public Address getAddress();
}
