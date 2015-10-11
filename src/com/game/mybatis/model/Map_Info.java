package com.game.mybatis.model;

public class Map_Info {
    private Integer mapId;

    private String mapName;
    
    private String mapResName;
    
    private String mapDataName;
    
    private Integer playermincount;

    private Integer playermaxcount;

    private Boolean supportTangledFight;

    private Boolean supportTeamBattle;

    private Boolean suportFlagBattle;

    private Boolean supportBossAnnihilateBattle;

    public Integer getMapId() {
		return mapId;
	}

	public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}

	public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName == null ? null : mapName.trim();
    }

    public Integer getPlayermincount() {
        return playermincount;
    }

    public void setPlayermincount(Integer playermincount) {
        this.playermincount = playermincount;
    }

    public Integer getPlayermaxcount() {
        return playermaxcount;
    }

    public void setPlayermaxcount(Integer playermaxcount) {
        this.playermaxcount = playermaxcount;
    }

	public Boolean getSupportTangledFight() {
		return supportTangledFight;
	}

	public Boolean getSupportTeamBattle() {
		return supportTeamBattle;
	}

	public Boolean getSuportFlagBattle() {
		return suportFlagBattle;
	}

	public Boolean getSupportBossAnnihilateBattle() {
		return supportBossAnnihilateBattle;
	}

	public void setSupportTangledFight(Boolean supportTangledFight) {
		this.supportTangledFight = supportTangledFight;
	}

	public void setSupportTeamBattle(Boolean supportTeamBattle) {
		this.supportTeamBattle = supportTeamBattle;
	}

	public void setSuportFlagBattle(Boolean suportFlagBattle) {
		this.suportFlagBattle = suportFlagBattle;
	}

	public void setSupportBossAnnihilateBattle(Boolean supportBossAnnihilateBattle) {
		this.supportBossAnnihilateBattle = supportBossAnnihilateBattle;
	}

	public String getMapResName() {
		return mapResName;
	}

	public String getMapDataName() {
		return mapDataName;
	}

	public void setMapResName(String mapResName) {
		this.mapResName = mapResName;
	}

	public void setMapDataName(String mapDataName) {
		this.mapDataName = mapDataName;
	}
}