/**
 * @author Banchikov Igor (BIV)
 */
package ru.biv.gameMechanic;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.BiFunction;

import ru.biv.base.Abonent;
import ru.biv.base.Address;
import ru.biv.base.Frontend;
import ru.biv.base.GameMechanic;
import ru.biv.base.MessageSystem;
import ru.biv.msgSystem.UserSession;
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
	private UserSession userSessionFirst = new UserSession();
	private UserSession userSessionSecond = new UserSession();
	private long durationTime;

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
			TimeHelper.sleep(100);
		}
		
	}

	@Override
	public Address getAddress() {
		return address;
	}

	@Override
	public void updateGameSession(UserSession userSession) {
		Integer userId = userSession.getUserId(userSession.getUserName());
		//System.out.println(userSession.getUserName()+"\t"+userSession.getUserId(userSession.getUserName()));
		if (!userToSession.containsKey(userId)) {
			startGameSession(userSession);
		} else {
				gameSession = userToSession.get(userId); 
				if (gameSession != null) {
					if ((durationTime = TimeHelper.getCurrentTime()-gameSession.getStartTime())>15) {
						gameSession.setGameSessionStatus(checkWinner());
					}
					gameSession.setDurationTime(durationTime);
					gameSession.updateUserSession(userSession);
				}
		}
	}

	@Override
	public void startGameSession(UserSession userSession) {
		Integer userId = userSession.getUserId(userSession.getUserName());
		if (userId!=null) {
			if (userSessionFirst.getUserId(userSessionFirst.getUserName()) == null) {
				userSessionFirst = userSession;
			} else {
				if (userSessionFirst.getUserId(userSessionFirst.getUserName()) != userSession.getUserId(userSession.getUserName())) {
					userSessionSecond = userSession;
					gameSession = new GameSession(userSessionFirst, userSessionSecond);
					System.out.println("Игровая сессия\t"+gameSession.hashCode()+"\tдля пользователей:\t\t"+userSessionFirst.getUserName()+"\t"+userSessionSecond.getUserName()+"\tсоздана!");
					userToSession.put(userSessionFirst.getUserId(userSessionFirst.getUserName()), gameSession);
					userToSession.put(userSessionSecond.getUserId(userSessionSecond.getUserName()), gameSession);
					userSessionFirst = null;
					userSessionSecond = null;
				}
			}
		}
	}

	@Override
	public MessageSystem getMessageSystem() {
		return ms;
	}
	
	private Integer checkWinner() {
		Random rand = new Random();
		return rand.nextInt(1);
	}

}
