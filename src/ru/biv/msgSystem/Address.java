/**
 * 
 */
package ru.biv.msgSystem;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Banshikov Igor (BIV)
 *
 */
public class Address {
	static private AtomicInteger abonentIdCreator = new AtomicInteger();
	final private int abonentId;
	
	public Address(){
		this.abonentId = abonentIdCreator.incrementAndGet();
	}
	
	public int hashCode(){
		return abonentId;
	}
}
