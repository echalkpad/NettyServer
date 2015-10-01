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

    private Room_Info game_Info;

    private Battle_Info battle_Info;

    private Role_Info role_Info;
    
    private String ipAddress;
    
    public User(){
    	
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

	public Room_Info getGame_Info() {
		return game_Info;
	}

	public Battle_Info getBattle_Info() {
		return battle_Info;
	}

	public Role_Info getRole_Info() {
		return role_Info;
	}

	public void setGame_Info(Room_Info game_Info) {
		this.game_Info = game_Info;
	}

	public void setBattle_Info(Battle_Info battle_Info) {
		this.battle_Info = battle_Info;
	}

	public void setRole_Info(Role_Info role_Info) {
		this.role_Info = role_Info;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
}