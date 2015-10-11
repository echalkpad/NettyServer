package com.game.proto;

public class CMD {
	//���ע���˻�
	public static final int USER_REGISTER = 1;
	//��ҵ�½
	public static final int USER_LOGIN = 2;
	//��ҽ���������׼����Ϸ
	public static final int USER_IDLE = 3;
	//������������д�����Ϸ
	public static final int MAIN_REQUEST_CREATE_GAME = 4;
	//����������������������Ϸ
	public static final int MAIN_REQUEST_JOIN_TO_GAME = 5;
	//�����������������Ϸ�б�
	public static final int MAIN_REQUEST_GAME_LIST = 6;
	//������������Զ�ˢ����Ϸ�б�
	public static final int MAIN_FLUSH_GAME_LIST = 7;
	
	//��ҵ�һ�ν�������Ϸ�ȴ������ȡ��Ϸ�е�����
	public static final int WAITROOM_GETLIST = 8;
	//�������Ϸ�ȴ������Զ�����
	public static final int WAITROOM_FLUSH = 23;
	//����뿪�ȴ���
	public static final int USER_LEAVE_WAITROOM = 9;
	//�������뿪�ȴ��� �������뿪�ȴ��ң���ô�ȴ��ҹر�
	public static final int CREATOR_LEAVE_WAITROOM = 10;
	//�����߿�ʼ����Ϸ
	public static final int CREATOR_START_GAME = 11;
	//�������Ϸ�ȴ������з���
	public static final int WAITROOM_SPEEK = 12;
	//�������޳���ĳ��
	public static final int EXCLUDE_USER = 13;
	//�����������
	public static final int ROLE_BATTLE_IDLE = 14;
	//��������battle��������
	public static final int ROLE_BATTLE_REQUEST_START = 15;
	//��battle����ҷ���
	public static final int ROLE_BATTLE_DIALOG = 16;
	//��battle���Զ�ˢ��
	public static final int ROLE_BATTLE_UPDATE = 17;
	//��battle����������
	public static final int BATTLE_CLEARING = 18;
	//����˳�battle
	public static final int USER_EXIT_BATTLE = 19;
	//����ٴ�����map����
	public static final int USER_REQUEST_MAP_DATA = 20;
	//����ٴ�����skill����
	public static final int USER_REQUEST_SKILL_DATA = 21;
	//����ٴ�����baseRole����
	public static final int USER_REQUEST_BASEROLE_DATA = 22;
	//����ڵȴ���ѡ��һ����ɫ 
	public static final int USER_SELECT_BASRROLE = 24;
	//���Ϊ��ɫѡ����
	public static final int USER_SELECT_ROLE_SKILL = 25;
	
	
	//����˳�����
	public static final int USER_EXIT_GAME = 199;
}
