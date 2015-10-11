package com.game.mybatis.model;

import java.util.List;

public class Room_Info {
    private Integer roomId;

    private String roomName;
    
    private Integer usercount;
    
    private Integer gameType;

    private Map_Info playMap;
    
    private Battle_Info battle_Info;
    
    private Boolean isUsed;
    
    private User creator;
    
    public Integer getRoomId() {
		return roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public Map_Info getPlayMap() {
		return playMap;
	}

	public Battle_Info getBattle_Info() {
		return battle_Info;
	}

	public Boolean getIsUsed() {
		return isUsed;
	}

	public User getCreator() {
		return creator;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public void setPlayMap(Map_Info playMap) {
		this.playMap = playMap;
	}

	public void setBattle_Info(Battle_Info battle_Info) {
		this.battle_Info = battle_Info;
	}

	public void setIsUsed(Boolean isUsed) {
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
}