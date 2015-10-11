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
	
	//玩家第一次将纳入游戏等待界面获取游戏中的数据
	public static final int WAITROOM_GETLIST = 8;
	//玩家在游戏等待室中自动更新
	public static final int WAITROOM_FLUSH = 23;
	//玩家离开等待室
	public static final int USER_LEAVE_WAITROOM = 9;
	//创建者离开等待室 创建者离开等待室，那么等待室关闭
	public static final int CREATOR_LEAVE_WAITROOM = 10;
	//创建者开始了游戏
	public static final int CREATOR_START_GAME = 11;
	//玩家在游戏等待界面中发言
	public static final int WAITROOM_SPEEK = 12;
	//创建者剔出了某人
	public static final int EXCLUDE_USER = 13;
	//场景加载完成
	public static final int ROLE_BATTLE_IDLE = 14;
	//重新请求battle场景数据
	public static final int ROLE_BATTLE_REQUEST_START = 15;
	//在battle中玩家发言
	public static final int ROLE_BATTLE_DIALOG = 16;
	//在battle中自动刷新
	public static final int ROLE_BATTLE_UPDATE = 17;
	//在battle中清算数据
	public static final int BATTLE_CLEARING = 18;
	//玩家退出battle
	public static final int USER_EXIT_BATTLE = 19;
	//玩家再次请求map数据
	public static final int USER_REQUEST_MAP_DATA = 20;
	//玩家再次请求skill数据
	public static final int USER_REQUEST_SKILL_DATA = 21;
	//玩家再次请求baseRole数据
	public static final int USER_REQUEST_BASEROLE_DATA = 22;
	//玩家在等待室选择一个角色 
	public static final int USER_SELECT_BASRROLE = 24;
	//玩家为角色选择技能
	public static final int USER_SELECT_ROLE_SKILL = 25;
	
	
	//玩家退出程序
	public static final int USER_EXIT_GAME = 199;
}
