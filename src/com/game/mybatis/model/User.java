package com.game.mybatis.model;

import java.util.Date;

public class User {
    private Integer user_id;

    private String username;

    private Date createTime;

    private Date loginTime;

    private Date logoutTime;

    private String country;

    private String province;

    private String location;

    private Integer state;

    private Room_Info room_Info;

    private Date joinRoomTime;
    
    private Battle_Info battle_Info;

    private Date joinBattleTime;
    
    private Role_Info role_Info;
    
    private Date selectRoleTime;
    
    private String ipAddress;
    
    private Date lastConnectTime;
    
    
    public void updateLastConnectTime(){
    	lastConnectTime = new Date(System.currentTimeMillis());
    }
    
    public User(){
    	createTime = new Date(System.currentTimeMillis());
    }
    
    public User(String username){
    	this.username = username;
    	this.createTime = new Date(0);
    	this.loginTime = new Date(0);
    }
    
    public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Room_Info getRoom_Info() {
		return room_Info;
	}

	public Battle_Info getBattle_Info() {
		return battle_Info;
	}

	public Role_Info getRole_Info() {
		return role_Info;
	}

	public void setBattle_Info(Battle_Info battle_Info) {
		this.joinBattleTime = new Date(System.currentTimeMillis());
		this.battle_Info = battle_Info;
	}

	public void setRole_Info(Role_Info role_Info) {
		this.selectRoleTime = new Date(System.currentTimeMillis());
		this.role_Info = role_Info;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Date getLastConnectTime() {
		return lastConnectTime;
	}

	public void setRoom_Info(Room_Info room_Info) {
		this.joinRoomTime = new Date(System.currentTimeMillis());
		this.room_Info = room_Info;
	}

	public void setLastConnectTime(Date lastConnectTime) {
		this.lastConnectTime = lastConnectTime;
	}

	public Date getJoinRoomTime() {
		return joinRoomTime;
	}

	public Date getJoinBattleTime() {
		return joinBattleTime;
	}

	public Date getSelectRoleTime() {
		return selectRoleTime;
	}

	public void setJoinRoomTime(Date joinRoomTime) {
		this.joinRoomTime = joinRoomTime;
	}

	public void setJoinBattleTime(Date joinBattleTime) {
		this.joinBattleTime = joinBattleTime;
	}

	public void setSelectRoleTime(Date selectRoleTime) {
		this.selectRoleTime = selectRoleTime;
	}
	
}