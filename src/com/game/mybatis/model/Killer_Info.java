package com.game.mybatis.model;

import java.util.Date;

public class Killer_Info {
    private Integer killerId;

    private Boolean iskiller;

    private Role_Info role;

    private Date createTime;

    private Role_Info taregt;

    private Battle_Info battle;
    
    public Killer_Info(){
    	createTime = new Date(System.currentTimeMillis());
    }
    
    public Integer getKillerId() {
        return killerId;
    }

    public void setKillerId(Integer killerId) {
        this.killerId = killerId;
    }

    public Boolean getIskiller() {
        return iskiller;
    }

    public void setIskiller(Boolean iskiller) {
        this.iskiller = iskiller;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public Role_Info getRole() {
		return role;
	}

	public Role_Info getTaregt() {
		return taregt;
	}

	public void setRole(Role_Info role) {
		this.role = role;
	}

	public void setTaregt(Role_Info taregt) {
		this.taregt = taregt;
	}

	public Battle_Info getBattle() {
		return battle;
	}

	public void setBattle(Battle_Info battle) {
		this.battle = battle;
	}
}