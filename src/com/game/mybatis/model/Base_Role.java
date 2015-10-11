package com.game.mybatis.model;

public class Base_Role {
    private Integer baseroleId;

    private Integer attackType;

    private Integer roleType;

    private String rolename;

    private Integer maxlevel;

    private Integer minlevel;

    private Integer minspeed;

    private Integer maxspeed;

    private Integer basehp;

    private Float attackspeed;

    private Float maxattackspeed;

    private Integer baseAttack;
    
    public Integer getBaseroleId() {
        return baseroleId;
    }

    public void setBaseroleId(Integer baseroleId) {
        this.baseroleId = baseroleId;
    }

    public Integer getAttackType() {
        return attackType;
    }

    public void setAttackType(Integer attackType) {
        this.attackType = attackType;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public Integer getMaxlevel() {
        return maxlevel;
    }

    public void setMaxlevel(Integer maxlevel) {
        this.maxlevel = maxlevel;
    }

    public Integer getMinlevel() {
        return minlevel;
    }

    public void setMinlevel(Integer minlevel) {
        this.minlevel = minlevel;
    }

    public Integer getMinspeed() {
        return minspeed;
    }

    public void setMinspeed(Integer minspeed) {
        this.minspeed = minspeed;
    }

    public Integer getMaxspeed() {
        return maxspeed;
    }

    public void setMaxspeed(Integer maxspeed) {
        this.maxspeed = maxspeed;
    }

    public Integer getBasehp() {
        return basehp;
    }

    public void setBasehp(Integer basehp) {
        this.basehp = basehp;
    }

    public Float getAttackspeed() {
        return attackspeed;
    }

    public void setAttackspeed(Float attackspeed) {
        this.attackspeed = attackspeed;
    }

    public Float getMaxattackspeed() {
        return maxattackspeed;
    }

    public void setMaxattackspeed(Float maxattackspeed) {
        this.maxattackspeed = maxattackspeed;
    }

	public Integer getBaseAttack() {
		return baseAttack;
	}

	public void setBaseAttack(Integer baseAttack) {
		this.baseAttack = baseAttack;
	}
}