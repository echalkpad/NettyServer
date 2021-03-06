package com.game.mybatis.model;

import java.util.Date;

public class Role_Info {
    private Integer roleId;

    private User user;

    private Integer order_id;
    
    private Base_Role baseRole;
    
    private Integer type;
    
    private Boolean isIdle;
    
    private Integer level;
    
    private Integer hp;

    private Integer attack;

    private Integer speed;

    private Integer posX;

    private Integer posY;

    private Integer velocX;
    
    private Integer velocY;
    
    private Date lastUpdatePos_Time;
    
    private User attact_target;
    
    private Skill_Info castSkill;
    
    private User	castSkillTarget;
    
    private Date lastUpdateAttackTime;
    
    private Skill_Info skill1;
    
    private Date selectSkill1Time;
    
    private Skill_Info skill2;

    private Date selectSkill2Time;
    
    private Skill_Info skill3;

    private Date selectSkill3Time;
    
    private Skill_Info skill4;

    private Date selectSkill4Time;
    
    public Role_Info(){
    	this.isIdle = false;
    }
    
    public Role_Info(Base_Role baseRole){
    	this.isIdle = false;
    	setValueFromBaseRole(baseRole);
    }
    
    public void setValueFromBaseRole(Base_Role baseRole){
    	this.baseRole = baseRole;
    	this.type = baseRole.getRoleType();
    	this.hp = baseRole.getBasehp();
    	this.level = baseRole.getMinlevel();
    	this.speed = baseRole.getMinspeed();
    	this.attack = baseRole.getBaseAttack();
    }
    
    public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getPosX() {
        return posX;
    }

    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public void setPosY(Integer posY) {
        this.posY = posY;
    }

	public User getUser() {
		return user;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public Integer getLevel() {
		return level;
	}

	public Integer getVelocX() {
		return velocX;
	}

	public Integer getVelocY() {
		return velocY;
	}

	public Date getLastUpdatePos_Time() {
		return lastUpdatePos_Time;
	}

	public Skill_Info getCastSkill() {
		return castSkill;
	}

	public Date getLastUpdateAttackTime() {
		return lastUpdateAttackTime;
	}

	public Skill_Info getSkill1() {
		return skill1;
	}

	public Skill_Info getSkill2() {
		return skill2;
	}

	public Skill_Info getSkill3() {
		return skill3;
	}

	public Skill_Info getSkill4() {
		return skill4;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setVelocX(Integer velocX) {
		this.velocX = velocX;
	}

	public void setVelocY(Integer velocY) {
		this.velocY = velocY;
	}

	public void setLastUpdatePos_Time(Date lastUpdatePos_Time) {
		this.lastUpdatePos_Time = lastUpdatePos_Time;
	}

	public void setCastSkill(Skill_Info castSkill) {
		this.castSkill = castSkill;
	}
	
	public void setLastUpdateAttackTime(Date lastUpdateAttackTime) {
		this.lastUpdateAttackTime = lastUpdateAttackTime;
	}

	public void setSkill1(Skill_Info skill1) {
		this.selectSkill1Time = new Date(System.currentTimeMillis());
		this.skill1 = skill1;
	}

	public void setSkill2(Skill_Info skill2) {
		this.selectSkill2Time = new Date(System.currentTimeMillis());
		this.skill2 = skill2;
	}

	public void setSkill3(Skill_Info skill3) {
		this.selectSkill3Time = new Date(System.currentTimeMillis());
		this.skill3 = skill3;
	}

	public void setSkill4(Skill_Info skill4) {
		this.selectSkill4Time = new Date(System.currentTimeMillis());
		this.skill4 = skill4;
	}

	public User getAttact_target() {
		return attact_target;
	}

	public User getCastSkillTarget() {
		return castSkillTarget;
	}

	public void setAttact_target(User attact_target) {
		this.attact_target = attact_target;
	}

	public void setCastSkillTarget(User castSkillTarget) {
		this.castSkillTarget = castSkillTarget;
	}

	public Base_Role getBaseRole() {
		return baseRole;
	}

	public void setBaseRole(Base_Role baseRole) {
		this.baseRole = baseRole;
	}

	public Date getSelectSkill1Time() {
		return selectSkill1Time;
	}

	public Date getSelectSkill2Time() {
		return selectSkill2Time;
	}

	public Date getSelectSkill3Time() {
		return selectSkill3Time;
	}

	public Date getSelectSkill4Time() {
		return selectSkill4Time;
	}

	public void setSelectSkill1Time(Date selectSkill1Time) {
		this.selectSkill1Time = selectSkill1Time;
	}

	public void setSelectSkill2Time(Date selectSkill2Time) {
		this.selectSkill2Time = selectSkill2Time;
	}

	public void setSelectSkill3Time(Date selectSkill3Time) {
		this.selectSkill3Time = selectSkill3Time;
	}

	public void setSelectSkill4Time(Date selectSkill4Time) {
		this.selectSkill4Time = selectSkill4Time;
	}

	public Boolean getIsIdle() {
		return isIdle;
	}

	public void setIsIdle(Boolean isIdle) {
		this.isIdle = isIdle;
	}
}