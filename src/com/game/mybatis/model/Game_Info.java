package com.game.mybatis.model;

import java.util.List;

public class Game_Info {
    private Integer gameId;

    private String gameName;
    
    private Integer usercount;
    
    private Integer gameType;

    private Map_Info playMap;
    
    private List<User> usersList;
    
    private Dialog_Info	currentDialog;
    
    private List<Dialog_Info> dialogsList;
    
    private Battle_Info battle_Info;
    
    private Integer isUsed;
    
    private User creator;
    
    public Integer getGameId() {
		return gameId;
	}

	public String getGameName() {
		return gameName;
	}

	public Map_Info getPlayMap() {
		return playMap;
	}

	public Dialog_Info getCurrentDialog() {
		return currentDialog;
	}

	public List<Dialog_Info> getDialogsList() {
		return dialogsList;
	}

	public Battle_Info getBattle_Info() {
		return battle_Info;
	}

	public Integer getIsUsed() {
		return isUsed;
	}

	public User getCreator() {
		return creator;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public void setPlayMap(Map_Info playMap) {
		this.playMap = playMap;
	}

	public void setCurrentDialog(Dialog_Info currentDialog) {
		this.currentDialog = currentDialog;
	}

	public void setDialogsList(List<Dialog_Info> dialogsList) {
		this.dialogsList = dialogsList;
	}

	public void setBattle_Info(Battle_Info battle_Info) {
		this.battle_Info = battle_Info;
	}

	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Integer getUsercount() {
        return usercount;
    }

    public void setUsercount(Integer usercount) {
        this.usercount = usercount;
    }

    public Integer getGameType() {
        return gameType;
    }

    public void setGameType(Integer gameType) {
        this.gameType = gameType;
    }

	public List<User> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<User> usersList) {
		this.usersList = usersList;
	}
}