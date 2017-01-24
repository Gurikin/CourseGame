/**
 * 
 */
package ru.biv.msgSystem;

import ru.biv.base.Abonent;
import ru.biv.base.AccountService;
import ru.biv.base.Address;
import ru.biv.base.Msg;

//import ru.biv.accountService.AccountServiceImpl;

/**
 * @author Banshikov Igor (BIV)
 *
 */
public abstract class MsgToAS extends Msg {

	/**
	 * @param from
	 * @param to
	 */
	public MsgToAS(Address from, Address to) {
		super(from, to);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see ru.biv.msgSystem.Msg#exec(ru.biv.msgSystem.Abonent)
	 */
	@Override
	public void exec(Abonent abonent) {
		if(abonent instanceof AccountService) {
			exec((AccountService)abonent);
		}
	}
	
	public abstract void exec(AccountService accountService);

}
