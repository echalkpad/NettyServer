package com.game.mybatis.session;

public class UserState {
	//登陆
	public static final Integer LOGIN = 1;
	//在主界面等待
	public static final Integer IDLE = 2;
	//在等待室等待
	public static final Integer ROOMWAIT = 3;
	//加载资源
	public static final Integer LOADING = 4;
	//战斗类型
	//混战
	public static final Integer TANGLEDFIGHT = 5;
	public static final Integer TEAMBATTLE = 6;
	public static final Integer FLAGBATTLE = 7;
	public static final Integer BOSSANNIHILATEBATTLE = 8;
	//离开游戏
	public static final Integer OUTGAME = 99;
	//离线
	public static final Integer OUTLINE = 100;
}
