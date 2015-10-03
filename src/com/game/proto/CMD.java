package com.game.proto;

public class CMD {
	//玩家注册账户
	public static final int USER_REGISTER = 1;
	//玩家登陆
	public static final int USER_LOGIN = 2;
	//玩家进入主界面准备游戏
	public static final int USER_IDLE = 3;
	//玩家在主界面中创建游戏
	public static final int MAIN_REQUEST_CREATE_GAME = 4;
	//玩家在主界面中请求加入游戏
	public static final int MAIN_REQUEST_JOIN_TO_GAME = 5;
	//玩家在主界面请求游戏列表
	public static final int MAIN_REQUEST_GAME_LIST = 6;
	//玩家在主界面自动刷新游戏列表
	public static final int MAIN_FLUSH_GAME_LIST = 7;
	
	//当玩家进入了游戏等待界面中
	public static final int WAITROOM_WAITTING = 8;
	//玩家离开等待室
	public static final int USER_LEAVE_WAITROOM = 9;
	//创建者离开等待室 创建者离开等待室，那么等待室关闭
	public static final int CREATOR_LEAVE_WAITROOM = 10;
	//创建者开始了游戏
	public static final int CREATOR＿START_GAME = 11;
	//玩家在游戏等待界面中发言
	public static final int WAITROOM_SPEEK = 12;
	//创建者剔出了某人
	public static final int EXCLUDE_USER = 13;
}
