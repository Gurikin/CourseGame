/**
 * 
 */
package ru.biv.base;

import ru.biv.base.Address;
import ru.biv.base.Abonent;

/**
 * @author Banshikov Igor (BIV)
 *
 */
public abstract class Msg {
	final private Address from;
	final private Address to;
	
	public Msg(Address from, Address to) {
		this.from = from;
		this.to = to;
	}
	
	public Address getFrom(){
		return from;
	}
	
	public Address getTo(){
		return to;
	}
	
	public abstract void exec(Abonent abonent);
}
