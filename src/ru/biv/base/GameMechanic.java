package ru.biv.base;

import ru.biv.msgSystem.UserSession;

public interface GameMechanic extends Abonent {
	public void run();
	public void updateGameSession(UserSession userSession);
	public void startGameSession(UserSession userSession);
	public MessageSystem getMessageSystem();
}
