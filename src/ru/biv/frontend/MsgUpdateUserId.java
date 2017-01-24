/**
 * 
 */
package ru.biv.frontend;

import ru.biv.base.Address;
import ru.biv.base.Frontend;
import ru.biv.msgSystem.MsgToFrontend;


/**
 * @author Banshikov Igor (BIV)
 *
 */
public class MsgUpdateUserId extends MsgToFrontend {

	private String name;
	private Integer userId;
	
	public MsgUpdateUserId(Address from, Address to, String name, Integer userId) {
		super(from, to);
		this.name = name;
		this.userId = userId;
	}
	
	/* (non-Javadoc)
	 * @see ru.biv.msgSystem.MsgToFrontend#exec(ru.biv.html.Frontend)
	 */
	@Override
	public void exec(Frontend frontend) {
		frontend.setUserId(name, userId);
	}

}
