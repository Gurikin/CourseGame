package ru.biv.msgSystem;

import java.util.HashMap;
import java.util.Map;

import ru.biv.base.Abonent;
import ru.biv.base.Address;
import ru.biv.base.AddressService;



public class AddressServiceImpl implements AddressService{
	private Map<Class<?>, Address> addresses = new HashMap<Class<?>,Address>();
	
	public Address getAddress(Class<?> abonentClass) {
		return addresses.get(abonentClass);
	}
	
	public void setAddress(Abonent abonent){
		addresses.put(abonent.getClass(), abonent.getAddress());
	}
}
