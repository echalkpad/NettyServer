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
	
	//����ҽ�������Ϸ�ȴ�������
	public static final int WAITROOM_WAITTING = 8;
	//����뿪�ȴ���
	public static final int USER_LEAVE_WAITROOM = 9;
	//�������뿪�ȴ��� �������뿪�ȴ��ң���ô�ȴ��ҹر�
	public static final int CREATOR_LEAVE_WAITROOM = 10;
	//�����߿�ʼ����Ϸ
	public static final int CREATOR��START_GAME = 11;
	//�������Ϸ�ȴ������з���
	public static final int WAITROOM_SPEEK = 12;
	//�������޳���ĳ��
	public static final int EXCLUDE_USER = 13;
}
