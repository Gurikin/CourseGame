/**
 * 
 */
package ru.biv.msgSystem;

//import ru.biv.accountService.AccountServiceImpl;

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
	public void exec(AccountServiceImpl accountService) {
		Integer userId = accountService.getUserId(this.name);
		accountService.getMessageSystem().sendMessage(new MsgUpdateUserId(getTo(), getFrom(), this.name, userId));
	}
}
