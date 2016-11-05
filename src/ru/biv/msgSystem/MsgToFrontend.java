/**
 * 
 */
package ru.biv.msgSystem;

import ru.biv.frontend.FrontendImpl;

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
		if(abonent instanceof FrontendImpl) {
			exec((FrontendImpl)abonent);
		}
	}
	
	public abstract void exec(FrontendImpl frontend);
}