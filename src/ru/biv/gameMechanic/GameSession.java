package ru.biv.gameMechanic;

import java.util.Date;

import ru.biv.msgSystem.UserSession;

public class GameSession {

	private UserSession userSession1, userSession2;
	private long startTime;
	private long durationTime;
	private enum stone {BLACK, WHITE};
	private Integer currentStepUserId;
	private Integer[] currentStep = new Integer[2];
	private Integer[][] field;
	private Integer gameSessionStatus = null;
	
	public GameSession(UserSession userSession1, UserSession userSession2) {
		Date date = new Date();
		startTime = date.getTime();
		userSession1.setPartyStatus(0);
		userSession2.setPartyStatus(0);
		userSession1.setStone(stone.WHITE.toString());
		userSession2.setStone(stone.BLACK.toString());
		this.userSession1 = userSession1;
		this.userSession2 = userSession2;
		this.field = new Integer[10][10];
	}
	
	public long getDurationTime() {
		return durationTime;
	}

	public void setDurationTime(long durationTime) {
		this.durationTime = durationTime;
	}

	public long getStartTime() {
		return startTime;
	}
	
	/**
	 * @return the userSession1 if userId equals userSession1
	 * @return the userSession2 if userId equals userSession2
	 */
	public UserSession getUserSession(UserSession userSession) {
		if (userSession.equals(userSession1)) {
			return userSession1;
		} else {
			return userSession2;
		}	
	}

	/**
	 * @param userSession1 the userSession1 to set
	 * @param userSession2 the userSession2 to set
	 */
	public void setUserSession(UserSession userSession) {
		if (userSession.equals(userSession1)) {
			this.userSession1 = userSession;
		} else {
			this.userSession2 = userSession;
		}
	}
	
	public UserSession updateUserSession(UserSession userSession) {
		userSession.setPartyDurationTime(getDurationTime()/1000);
		if (userSession.equals(userSession1)) {
			this.userSession1 = userSession;
			this.userSession1.setEnemyName(this.userSession2.getUserName());
			this.userSession1.setNumStepsEnemy(this.userSession2.getNumStepsUser());
		} else {
			this.userSession2 = userSession;
			this.userSession2.setEnemyName(this.userSession1.getUserName());
			this.userSession2.setNumStepsEnemy(this.userSession1.getNumStepsUser());
		}
		return userSession;
	}

	public Integer getGameSessionStatus() {
		return gameSessionStatus;
	}

	public void setGameSessionStatus(Integer gameSessionStatus) {
		this.gameSessionStatus = gameSessionStatus;
	}
}