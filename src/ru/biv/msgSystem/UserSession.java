package ru.biv.msgSystem;

import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;

@SuppressWarnings("serial")
public class UserSession implements Serializable{
    private Map<String, Integer> user = new HashMap<String, Integer>();
    private String stone;
    private String enemyName;
    private Integer numStepsUser;
    private Integer numStepsEnemy;
    private int[] lastStep = new int[2];
    private long partyDurationTime;
    private Integer partyStatus;


    private enum auth {AUTHORIZATION, AUTH, DONT_AUTH};

    public UserSession() {
        userClear();
    }

    /**
     * @param setUser the setUser to set
     */
    public synchronized void setUser(String userName, Integer userId) {
        userClear();
        user.put(userName, userId);
    }

    public Map<String, Integer> getUser() {
        return user;
    }

    public synchronized Integer getUserId(String userName){
        return user.get(userName);
    }

    public synchronized String getUserName() {
        String userName = "";
        for (Entry<String, Integer> entry : user.entrySet()) {
            userName = entry.getKey();
        }
        return userName;
    }

    public String getAuth() {
        if (this.getUserName() == null && this.user.get(this.getUserName()) == null) {
            return "";
        }
        if (this.user.get(this.getUserName()) == null) {
            return auth.AUTHORIZATION.toString();
        }
        if (this.user.get(this.getUserName()) == 0) {
            return auth.DONT_AUTH.toString();
        }
        if (this.user.get(this.getUserName()) != null) {
            return auth.AUTH.toString();
        } else {
            return "";
        }
    }

    public void userClear() {
        user.clear();
    }

    /**
     * @return the stone
     */
    public String getStone() {
        return stone;
    }

    /**
     * @param stone the stone to set
     */
    public void setStone(String stone) {
        this.stone = stone;
    }

    public boolean equals(UserSession userSession) {
        if (this.getUserName() == userSession.getUserName() &&
                (this.getUserId(this.getUserName()) == userSession.getUserId(userSession.getUserName()))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return the enemyName
     */
    public String getEnemyName() {
        return this.enemyName;
    }

    /**
     * @param enemyName the enemyName to set
     */
    public void setEnemyName(String enemyName) {
        this.enemyName = enemyName;
    }

    /**
     * @return the numStepsUser
     */
    public Integer getNumStepsUser() {
        return this.numStepsUser;
    }

    /**
     * @param numStepsUser the numStepsUser to set
     */
    public void setNumStepsUser(Integer numStepsUser) {
        this.numStepsUser = numStepsUser;
    }

    /**
     * @return the numStepsEnemy
     */
    public Integer getNumStepsEnemy() {
        return this.numStepsUser;
    }

    /**
     * @param numStepsEnemy the numStepsEnemy to set
     */
    public void setNumStepsEnemy(Integer numStepsEnemy) {
        this.numStepsEnemy = numStepsEnemy;
    }

    /**
     * @return the partyDurationTime
     */
    public long getPartyDurationTime() {
        return partyDurationTime;
    }

    /**
     * @param partyDurationTime the partyDurationTime to set
     */
    public void setPartyDurationTime(long partyDurationTime) {
        this.partyDurationTime = partyDurationTime;
    }

    public Integer getPartyStatus() {
        return partyStatus;
    }

    public void setPartyStatus(Integer partyStatus) {
        this.partyStatus = partyStatus;
    }
}
