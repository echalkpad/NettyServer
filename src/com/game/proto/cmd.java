package com.game.proto;

public class cmd {
	//���ע���˻�
	public static int USER_REGISTER = 1;
	//��ҵ�½
	public static int USER_LOGIN = 2;
	//��ҽ���������׼����Ϸ
	public static int USER_IDLE = 3;
	//������������д�����Ϸ
	public static int MAIN_REQUEST_CREATE_GAME = 4;
	//����������������������Ϸ
	public static int MAIN_REQUEST_JOIN_TO_GAME = 5;
	//�����������������Ϸ�б�
	public static int MAIN_REQUEST_GAME_LIST = 6;
	//������������Զ�ˢ����Ϸ�б�
	public static int MAIN_FLUSH_GAME_LIST = 7;
	
	//����ҽ�������Ϸ�ȴ�������
	public static int WAITROOM_WAITTING = 8;
	//����뿪�ȴ���
	public static int USER_LEAVE_WAITROOM = 9;
	//�������뿪�ȴ��� �������뿪�ȴ��ң���ô�ȴ��ҹر�
	public static int CREATOR_LEAVE_WAITROOM = 10;
	//�����߿�ʼ����Ϸ
	public static int CREATOR��START_GAME = 11;
}
