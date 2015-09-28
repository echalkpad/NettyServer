package com.game.mybatis.model;

public class Dialog_Info {
    private Integer dialog_id;

    private User user;

    private Integer type;

    private Integer groupId;

    private String context;

    public Integer getDialog_id() {
		return dialog_id;
	}

	public void setDialog_id(Integer dialog_id) {
		this.dialog_id = dialog_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }
}