/**
 * 
 */
package ru.biv.msgSystem;

import java.util.concurrent.atomic.AtomicInteger;

import ru.biv.base.Address;

/**
 * @author Banshikov Igor (BIV)
 *
 */
public class AddressImpl implements Address{
	static private AtomicInteger abonentIdCreator = new AtomicInteger();
	final private int abonentId;
	
	public AddressImpl(){
		this.abonentId = abonentIdCreator.incrementAndGet();
	}
	
	public int hashCode(){
		return abonentId;
	}
}
