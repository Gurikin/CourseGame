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
public class MsgStartGameSession extends MsgToGM {

	//int userId1, userId2;
	int userId;
	
	/**
	 * @param from
	 * @param to
	 */
	public MsgStartGameSession(Address from, Address to, int userId) {
		super(from, to);
		this.userId = userId;
	}

	@Override
	public void exec(GameMechanic gameMechanic) {
		gameMechanic.createGameSession(this.userId);
		//System.out.println("FROM\t\t"+this.getClass()+"\t\tEXEC.");
		//Integer gameSessionId = gameMechanic.createGameSession(this.userId1, this.userId2);
		//gameMechanic.startGameSession(gameSessionId);
	}
}
