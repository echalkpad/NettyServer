package com.game.mybatis.model;

public class RoleLevelData {
    private Integer levelId;

    private Integer baseroleId;

    private Integer level;

    private Integer addhpbase;

    private Float addhpfactor;

    private Integer addattackbase;

    private Float addattackfactor;

    private String comment;

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getBaseroleId() {
        return baseroleId;
    }

    public void setBaseroleId(Integer baseroleId) {
        this.baseroleId = baseroleId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getAddhpbase() {
        return addhpbase;
    }

    public void setAddhpbase(Integer addhpbase) {
        this.addhpbase = addhpbase;
    }

    public Float getAddhpfactor() {
        return addhpfactor;
    }

    public void setAddhpfactor(Float addhpfactor) {
        this.addhpfactor = addhpfactor;
    }

    public Integer getAddattackbase() {
        return addattackbase;
    }

    public void setAddattackbase(Integer addattackbase) {
        this.addattackbase = addattackbase;
    }

    public Float getAddattackfactor() {
        return addattackfactor;
    }

    public void setAddattackfactor(Float addattackfactor) {
        this.addattackfactor = addattackfactor;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}