package com.game.mybatis.session;

public class UserState {
	//��½
	public static final Integer LOGIN = 1;
	//��������ȴ�
	public static final Integer IDLE = 2;
	//�ڵȴ��ҵȴ�
	public static final Integer ROOMWAIT = 3;
	//������Դ
	public static final Integer LOADING = 4;
	//ս������
	//��ս
	public static final Integer TANGLEDFIGHT = 5;
	public static final Integer TEAMBATTLE = 6;
	public static final Integer FLAGBATTLE = 7;
	public static final Integer BOSSANNIHILATEBATTLE = 8;
	//�뿪��Ϸ
	public static final Integer OUTGAME = 99;
	//����
	public static final Integer OUTLINE = 100;
}
