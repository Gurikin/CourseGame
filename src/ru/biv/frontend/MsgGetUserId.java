/**
 * 
 */
package ru.biv.frontend;

import ru.biv.base.AccountService;
import ru.biv.base.Address;
import ru.biv.msgSystem.*;

/**
 * @author Banshikov Igor (BIV)
 *
 */
public class MsgGetUserId extends MsgToAS {

	final private String name;
	 
	/**
	 * @param from
	 * @param to
	 */
	public MsgGetUserId(Address from, Address to, String name) {
		super(from, to);
		this.name = name;
	}

	@Override
	public void exec(AccountService accountService) {
		Integer userId = accountService.getUserId(this.name);
		accountService.getMessageSystem().sendMessage(new MsgUpdateUserId(getTo(), getFrom(), this.name, userId));
	}
}
