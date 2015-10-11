package com.game.mybatis.model;

import java.util.List;

public class Battle_Info {
    private Integer battle_id;

    private String battle_name;
    
    private Integer type;

    private Map_Info playMap;
    
    private Integer userCount;
    
    public Integer getBattle_id() {
		return battle_id;
	}

	public void setBattle_id(Integer battle_id) {
		this.battle_id = battle_id;
	}

	public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

	public Map_Info getPlayMap() {
		return playMap;
	}

	public void setPlayMap(Map_Info playMap) {
		this.playMap = playMap;
	}

	public String getBattle_name() {
		return battle_name;
	}

	public Integer getUserCount() {
		return userCount;
	}

	public void setBattle_name(String battle_name) {
		this.battle_name = battle_name;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}
}