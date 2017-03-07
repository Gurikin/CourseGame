/**
 * 
 */
package ru.biv.gameMechanic;

import ru.biv.base.Address;
import ru.biv.base.GameMechanic;
import ru.biv.msgSystem.*;

/**
 * @author Banchikov Igor (BIV)
 *
 */
public class MsgUpdateGameSession extends MsgToGM {

	//int userId1, userId2;
	UserSession userSession;
	
	/**
	 * @param from
	 * @param to
	 */
	public MsgUpdateGameSession(Address from, Address to, UserSession userSession) {
		super(from, to);
		this.userSession = userSession;
	}

	@Override
	public void exec(GameMechanic gameMechanic) {
		gameMechanic.updateGameSession(this.userSession);
		//System.out.println("FROM\t\t"+this.getClass()+"\t\tEXEC.");
		//Integer gameSessionId = gameMechanic.createGameSession(this.userId1, this.userId2);
		//gameMechanic.startGameSession(gameSessionId);
	}
}
