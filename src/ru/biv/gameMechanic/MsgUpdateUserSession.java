/**
 * 
 */
package ru.biv.gameMechanic;

import ru.biv.base.Address;
import ru.biv.base.Frontend;
import ru.biv.msgSystem.*;

/**
 * @author Banchikov Igor (BIV)
 *
 */
public class MsgUpdateUserSession extends MsgToFrontend {

	//int userId1, userId2;
	UserSession userSession;
	
	/**
	 * @param from
	 * @param to
	 */
	public MsgUpdateUserSession(Address from, Address to, UserSession userSession) {
		super(from, to);
		this.userSession = userSession;
	}

	@Override
	public void exec(Frontend frontend) {
		frontend.updateUserSession(userSession);
	}
}
