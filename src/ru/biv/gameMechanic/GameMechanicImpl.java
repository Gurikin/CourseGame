/**
 * @author Banchikov Igor (BIV)
 */
package ru.biv.gameMechanic;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

import ru.biv.base.Abonent;
import ru.biv.base.Address;
import ru.biv.base.Frontend;
import ru.biv.base.GameMechanic;
import ru.biv.base.MessageSystem;
import ru.biv.utils.TimeHelper;

/**
 * @author Banchikov Igor (BIV)
 *
 */
public class GameMechanicImpl implements Runnable, Abonent, GameMechanic {
	
	private MessageSystem ms;
	private Address address;
	
	private Map<Integer, GameSession> userToSession = new HashMap<Integer, GameSession>();
	private GameSession gameSession;
	private int[] userPair = new int[2];
	private Integer userIdFirst = null;
	private Integer userIdSecond = null;
	private int userCount=0;

	/**
	 * 
	 */
	public GameMechanicImpl(MessageSystem ms) {
		this.ms = ms;
		this.address = new Address();
		ms.addService(this);
	}
	
	@Override
	public void run() {
		while(true) {
			ms.execForAbonent(this);
			//System.out.println("From\t"+this.getClass()+"\texec.");
			TimeHelper.sleep(100);
		}
		
	}

	@Override
	public Address getAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createGameSession(int userId) {
		
		if (!userToSession.containsKey(userId) && userId!=0) {
			if (userIdFirst == null) {
				userIdFirst = userId;
			} else {
				if (userIdFirst.intValue()!=userId) {
					userIdSecond = userId;
					startGameSession(userIdFirst, userIdSecond);
					userIdFirst = null;
					userIdSecond = null;
				}				
			}
		}		
	}

	@Override
	public void startGameSession(Integer userId1, Integer userId2) {
		gameSession = new GameSession();
		System.out.println("Игровая сессия\t"+gameSession.hashCode()+"\tдля пользователей:\t\t"+userId1+"\t"+userId2+"\tсоздана!");
		userToSession.put(userId1, gameSession);
		userToSession.put(userId2, gameSession);
	}

	@Override
	public MessageSystem getMessageSystem() {
		return ms;
	}

}
