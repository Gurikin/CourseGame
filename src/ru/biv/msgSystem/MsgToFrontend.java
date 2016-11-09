/**
 * 
 */
package ru.biv.msgSystem;

import ru.biv.base.Abonent;
import ru.biv.base.Address;
import ru.biv.base.Frontend;
import ru.biv.base.Msg;


/**
 * @author Banshikov Igor (BIV)
 *
 */
public abstract class MsgToFrontend extends Msg {
	
	public MsgToFrontend(Address from, Address to){
		super(from,to);
	}
	/* (non-Javadoc)
	 * @see ru.biv.msgSystem.Msg#exec(ru.biv.msgSystem.Abonent)
	 */
	@Override
	public void exec(Abonent abonent) {
		if(abonent instanceof Frontend) {
			exec((Frontend)abonent);
		}
	}
	
	public abstract void exec(Frontend frontend);
}