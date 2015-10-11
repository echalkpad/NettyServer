package com.game.mybatis.model;

public class Kill_Rank {
    private Integer rankId;

    private User user;

    private String username;

    private Integer killusercount;

    private Integer assistsusercount;

    public Integer getRankId() {
        return rankId;
    }

    public void setRankId(Integer rankId) {
        this.rankId = rankId;
    }

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getKillusercount() {
        return killusercount;
    }

    public void setKillusercount(Integer killusercount) {
        this.killusercount = killusercount;
    }

    public Integer getAssistsusercount() {
        return assistsusercount;
    }

    public void setAssistsusercount(Integer assistsusercount) {
        this.assistsusercount = assistsusercount;
    }
}