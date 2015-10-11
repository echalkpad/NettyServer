package com.game.mybatis.model;

public class Skill_Info {
    private Integer skillId;

    private String skillName;
    
    private Integer skillType;

    private Float duration;
    
    private Float interval;
    
    private Boolean isGroup;

    private Integer skillBaseHurt;

    private Boolean canVertigo;

    private Boolean canDeceleration;

    private Float skillHurtFactor;
    
    private Float skillBufferTime;
    
    private Float skillBufferFactor;
    
    private Integer skillAddBaseHp;

    private Integer skillAddBaseAttack;

    private Integer skillAddBaseSpeed;

    private Float skillAddHpFactor;

    private Float skillAddAttackFactor;

    private Float skillAddSpeedFactor;

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName == null ? null : skillName.trim();
    }
    
    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public Boolean getIsGroup() {
		return isGroup;
	}

	public void setIsGroup(Boolean isGroup) {
		this.isGroup = isGroup;
	}

	public Integer getSkillBaseHurt() {
        return skillBaseHurt;
    }

    public void setSkillBaseHurt(Integer skillBaseHurt) {
        this.skillBaseHurt = skillBaseHurt;
    }
    
    public Float getSkillHurtFactor() {
        return skillHurtFactor;
    }

    public void setSkillHurtFactor(Float skillHurtFactor) {
        this.skillHurtFactor = skillHurtFactor;
    }

    public Integer getSkillAddBaseHp() {
        return skillAddBaseHp;
    }

    public void setSkillAddBaseHp(Integer skillAddBaseHp) {
        this.skillAddBaseHp = skillAddBaseHp;
    }

    public Integer getSkillAddBaseAttack() {
        return skillAddBaseAttack;
    }

    public void setSkillAddBaseAttack(Integer skillAddBaseAttack) {
        this.skillAddBaseAttack = skillAddBaseAttack;
    }

    public Integer getSkillAddBaseSpeed() {
        return skillAddBaseSpeed;
    }

    public void setSkillAddBaseSpeed(Integer skillAddBaseSpeed) {
        this.skillAddBaseSpeed = skillAddBaseSpeed;
    }

    public Float getSkillAddHpFactor() {
        return skillAddHpFactor;
    }

    public void setSkillAddHpFactor(Float skillAddHpFactor) {
        this.skillAddHpFactor = skillAddHpFactor;
    }

    public Float getSkillAddAttackFactor() {
        return skillAddAttackFactor;
    }

    public void setSkillAddAttackFactor(Float skillAddAttackFactor) {
        this.skillAddAttackFactor = skillAddAttackFactor;
    }

    public Float getSkillAddSpeedFactor() {
        return skillAddSpeedFactor;
    }

    public void setSkillAddSpeedFactor(Float skillAddSpeedFactor) {
        this.skillAddSpeedFactor = skillAddSpeedFactor;
    }

	public Integer getSkillType() {
		return skillType;
	}

	public Boolean getCanVertigo() {
		return canVertigo;
	}

	public Boolean getCanDeceleration() {
		return canDeceleration;
	}

	public Float getSkillBufferTime() {
		return skillBufferTime;
	}

	public Float getSkillBufferFactor() {
		return skillBufferFactor;
	}

	public void setSkillType(Integer skillType) {
		this.skillType = skillType;
	}

	public void setCanVertigo(Boolean canVertigo) {
		this.canVertigo = canVertigo;
	}

	public void setCanDeceleration(Boolean canDeceleration) {
		this.canDeceleration = canDeceleration;
	}

	public void setSkillBufferTime(Float skillBufferTime) {
		this.skillBufferTime = skillBufferTime;
	}

	public void setSkillBufferFactor(Float skillBufferFactor) {
		this.skillBufferFactor = skillBufferFactor;
	}

	public Float getInterval() {
		return interval;
	}

	public void setInterval(Float interval) {
		this.interval = interval;
	}
}