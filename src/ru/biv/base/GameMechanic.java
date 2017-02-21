package ru.biv.base;

public interface GameMechanic extends Abonent {
	public void run();
	public void createGameSession(int userId);
	public void startGameSession(Integer userId1, Integer userId2);
	public MessageSystem getMessageSystem();
}
