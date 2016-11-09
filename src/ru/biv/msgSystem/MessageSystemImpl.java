/**
 * 
 */
package ru.biv.msgSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import ru.biv.base.Abonent;
import ru.biv.base.Address;
import ru.biv.base.AddressService;
import ru.biv.base.MessageSystem;
import ru.biv.base.Msg;

/**
 * @author Banshikov Igor (BIV)
 *
 */
public class MessageSystemImpl implements MessageSystem{
	private Map<Address, ConcurrentLinkedQueue<Msg>> messages =
			new HashMap<Address, ConcurrentLinkedQueue<Msg>>();
	private AddressService addressService = new AddressServiceImpl();
	
	public void addService(Abonent abonent) {
		addressService.setAddress(abonent);
		messages.put(abonent.getAddress(), new ConcurrentLinkedQueue<Msg>());
	}
	
	public void sendMessage(Msg message) {
		Queue<Msg> messageQueue = messages.get(message.getTo());
		messageQueue.add(message);
	}
	
	public void execForAbonent(Abonent abonent){
		Queue<Msg> messageQueue = messages.get(abonent.getAddress());
		if (messageQueue == null) {
			return;
		}
		while(!messageQueue.isEmpty()){
			Msg message = messageQueue.poll();
			message.exec(abonent);
		}
	}
	
	public AddressService getAddressService(){
		return addressService;
	}
}
