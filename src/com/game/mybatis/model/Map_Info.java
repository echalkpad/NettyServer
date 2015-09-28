package com.game.mybatis.model;

public class Map_Info {
    private Integer mapId;

    private String mapName;

    private Integer playermincount;

    private Integer playermaxcount;

    private Integer supportTangledFight;

    private Integer supportTeamBattle;

    private Integer suportFlagBattle;

    private Integer supportBossAnnihilateBattle;

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

	public Integer getSupportTangledFight() {
		return supportTangledFight;
	}

	public Integer getSupportTeamBattle() {
		return supportTeamBattle;
	}

	public Integer getSuportFlagBattle() {
		return suportFlagBattle;
	}

	public Integer getSupportBossAnnihilateBattle() {
		return supportBossAnnihilateBattle;
	}

	public void setSupportTangledFight(Integer supportTangledFight) {
		this.supportTangledFight = supportTangledFight;
	}

	public void setSupportTeamBattle(Integer supportTeamBattle) {
		this.supportTeamBattle = supportTeamBattle;
	}

	public void setSuportFlagBattle(Integer suportFlagBattle) {
		this.suportFlagBattle = suportFlagBattle;
	}

	public void setSupportBossAnnihilateBattle(Integer supportBossAnnihilateBattle) {
		this.supportBossAnnihilateBattle = supportBossAnnihilateBattle;
	}
}