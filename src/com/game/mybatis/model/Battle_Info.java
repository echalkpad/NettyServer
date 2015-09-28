package com.game.mybatis.model;

import java.util.List;

public class Battle_Info {
    private Integer battle_id;

    private String battle_name;
    
    private Integer type;

    private Map_Info playMap;
    
    private Integer userCount;
    
    private List<User> userList;
    
    private Dialog_Info current_dialog_Info;
    
    private List<Dialog_Info> dialogList;
    
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

	public List<User> getUserList() {
		return userList;
	}

	public List<Dialog_Info> getDialogList() {
		return dialogList;
	}

	public void setBattle_name(String battle_name) {
		this.battle_name = battle_name;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public void setDialogList(List<Dialog_Info> dialogList) {
		this.dialogList = dialogList;
	}

	public Dialog_Info getCurrent_dialog_Info() {
		return current_dialog_Info;
	}

	public void setCurrent_dialog_Info(Dialog_Info current_dialog_Info) {
		this.current_dialog_Info = current_dialog_Info;
	}
}