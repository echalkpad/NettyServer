package com.game.mybatis.session;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.game.mybatis.model.Base_Role;
import com.game.mybatis.model.Battle_Info;
import com.game.mybatis.model.Dialog_Info;
import com.game.mybatis.model.Map_Info;
import com.game.mybatis.model.Role_Info;
import com.game.mybatis.model.Room_Info;
import com.game.mybatis.model.Skill_Info;
import com.game.mybatis.model.User;
import com.game.proto.CMD;
import com.game.proto.ProtobufRequest;
import com.game.proto.ProtobufResponse;

import io.netty.channel.Channel;

public class UserEvent {
	public ProtobufRequest.protobufRequest req;
	public ProtobufResponse.protobufResponse resp;
	public Channel channel;
	public SqlSession session;
	public ProtobufResponse.protobufResponse dealWithProtoBuf(ProtobufRequest.protobufRequest req){
		this.req = req;
		ProtobufResponse.protobufResponse.Builder builder = ProtobufResponse.protobufResponse.newBuilder();
		builder.setUserId(req.getUserId());
		session = SessionManager.getSession().openSession();
		switch(req.getCmd()){
			//���ע���˻�
		case CMD.USER_REGISTER:
			createUser(builder);
			break;
			//��ҵ�½
		case CMD.USER_LOGIN:
			UserLogin(builder);
			break;
			//��ҽ���������׼����Ϸ
		case CMD.USER_IDLE:
			UserIdle(builder);
			break;
			//������������д�����Ϸ
		case CMD.MAIN_REQUEST_CREATE_GAME:
			createGameRoom(builder);
			break;
			//����������������������Ϸ
		case CMD.MAIN_REQUEST_JOIN_TO_GAME:
			mainJoinGameRoom(builder);
			break;
			//�����������������Ϸ�б�
		case CMD.MAIN_REQUEST_GAME_LIST:
			requestMainGameRoomList(builder);
			break;
			//������������Զ�ˢ����Ϸ�б�
		case CMD.MAIN_FLUSH_GAME_LIST:
			flushMainGameRoomList(builder);
			break;
			//��ҵ�һ�ν�������Ϸ�ȴ������ȡ��Ϸ�е�����
		case CMD.WAITROOM_GETLIST:
			getFirstWaitRoomUserInfo(builder);
			break;
			//�������Ϸ�ȴ������Զ�����
		case CMD.WAITROOM_FLUSH:
			flushWaitRoomUserInfo(builder);
			break;
			//����뿪�ȴ���
		case CMD.USER_LEAVE_WAITROOM:
			UserLeaveWaitRoom(builder);
			break;
			//�������뿪�ȴ��� �������뿪�ȴ��ң���ô�ȴ��ҹر�
		case CMD.CREATOR_LEAVE_WAITROOM:
			creatorLeaveRoom(builder);
			break;
			//�����߿�ʼ����Ϸ
		case CMD.CREATOR_START_GAME:
			creatorStartGame(builder);
			break;
			//�������Ϸ�ȴ������з���
		case CMD.WAITROOM_SPEEK:
			userWaitRoomSpeek(builder);
			break;
			//�������޳���ĳ��
		case CMD.EXCLUDE_USER:
			creatorExcludeUser(builder);
			break;
			//�����������
		case CMD.ROLE_BATTLE_IDLE:
			roleBattleIdle(builder);
			break;
			//��������battle��������
		case CMD.ROLE_BATTLE_REQUEST_START:
			reFlushRoleBattleIdle(builder);
			break;
			//��battle����ҷ���
		case CMD.ROLE_BATTLE_DIALOG:
			roleBattleDialogResponse(builder);
			break;
			//��battle���Զ�ˢ��
		case CMD.ROLE_BATTLE_UPDATE:
			battleUpdateResponse(builder);
			break;
			//��battle����������
		case CMD.BATTLE_CLEARING:
			battleClearInfoResponse(builder);
			break;
			//����˳�battle
		case CMD.USER_EXIT_BATTLE:
			userExitBattleResponse(builder);
			break;
			//����ٴ�����map����
		case CMD.USER_REQUEST_MAP_DATA:
			userGetAllMapResponse(builder);
			break;
			//����ٴ�����skill����
		case CMD.USER_REQUEST_SKILL_DATA:
			userGetAllSkillResponse(builder);
			break;
			//����ٴ�����baseRole����
		case CMD.USER_REQUEST_BASEROLE_DATA:
			userGetAllBaseRoleResponse(builder);
			break;
			//����ڵȴ���ѡ��һ����ɫ
		case CMD.USER_SELECT_BASRROLE:
			break;
		case CMD.USER_SELECT_ROLE_SKILL:
			break;
			//����˳�����
		case CMD.USER_EXIT_GAME:
			break;
		}
		resp = builder.build();
		MySqlAltern.updateUserLastConnectTime(session, req.getUserId());
		session.close();
		return resp;
	}
	
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	//���ע���˻�
	public void createUser(ProtobufResponse.protobufResponse.Builder builder){
		//��Ҫ����û����Ƿ�ʹ�ù�
		if(MySqlAltern.checkUserName(session, req.getMainUIRequestInfo().getRegisterInfo().getUsername())){
			builder.setCmd(CMD.USER_REGISTER);
			ProtobufRequest.registerMessageRequest register = req.getMainUIRequestInfo().getRegisterInfo();
			User user = MySqlAltern.createUser(session, register.getUsername(), register.getCountry(), register.getProvince(), register.getLocation());
			builder.setUserId(user.getUser_id());
			builder.setResult(true);
		}else{
			builder.setCmd(CMD.USER_REGISTER);
			builder.setResult(false);
		}
	}
	
	//��ҵ�½
	public void UserLogin(ProtobufResponse.protobufResponse.Builder builder){
		boolean result = MySqlAltern.userLogin(session, req.getUserId(), req.getMainUIRequestInfo().getLoginInfo().getLocation(), channel.remoteAddress().toString());
		builder.setCmd(CMD.USER_LOGIN);
		builder.setResult(result);
	}
	
	//��ҽ���������׼����Ϸ
	public void UserIdle(ProtobufResponse.protobufResponse.Builder builder){
		boolean result = MySqlAltern.userIdle(session, req.getUserId());
		builder.setCmd(CMD.USER_IDLE);
		builder.setResult(result);
		List<Room_Info> r_list = MySqlAltern.requestRoomList(session, UserDefine.ROOM_LIST_MAXCOUNT);
		List<Base_Role> b_list = MySqlAltern.getBaseRoleList(session);
		List<Skill_Info> s_list = MySqlAltern.getSkillList(session);
		List<Map_Info> m_list = MySqlAltern.getMapList(session);
		ProtobufResponse.mainUIResponse.Builder mainUI = ProtobufResponse.mainUIResponse.newBuilder();
		mainUI.setIdleInfo(ProtobufResponseGenerator.getIdleMessageResponse(session, r_list, b_list, s_list, m_list));
		builder.setMainUIResponseInfo(mainUI);
	}
	
	//������������д�����Ϸ
	public void createGameRoom(ProtobufResponse.protobufResponse.Builder builder){
		ProtobufRequest.createGameRequest roReq = req.getMainUIRequestInfo().getCreateGameInfo();
		Room_Info room = MySqlAltern.createGameRoom(session, roReq.getGameName(), roReq.getPlayMapId(), roReq.getPlayType());
		builder.setCmd(CMD.MAIN_REQUEST_CREATE_GAME);
		builder.setResult(true);
		ProtobufResponse.mainUIResponse.Builder mainUI = ProtobufResponse.mainUIResponse.newBuilder();
		mainUI.setCreateGameInfo(ProtobufResponseGenerator.getCreateGameResponse(session, room));
		builder.setMainUIResponseInfo(mainUI);
	}
	
	//����������������������Ϸ
	public void mainJoinGameRoom(ProtobufResponse.protobufResponse.Builder builder){
		builder.setCmd(CMD.MAIN_REQUEST_JOIN_TO_GAME);
		ProtobufRequest.mainJoinGameRequest mjReq = req.getMainUIRequestInfo().getMainJoinGameInfo();
		boolean result = MySqlAltern.mainJoinGame(session, mjReq.getRoomId(), req.getUserId());
		if(result){
			List<User> list = MySqlAltern.getRoomUserList(session, mjReq.getRoomId());
			Room_Info room = MySqlAltern.getRoom(session, mjReq.getRoomId());
			ProtobufResponse.mainUIResponse.Builder mainUI = ProtobufResponse.mainUIResponse.newBuilder();
			mainUI.setMainJoinGameInfo(ProtobufResponseGenerator.getMainJoinGameResponse(session, room, list));
			builder.setMainUIResponseInfo(mainUI);
		}
		builder.setResult(result);
	}
	
	//�����������������Ϸ�б�
	public void requestMainGameRoomList(ProtobufResponse.protobufResponse.Builder builder){
		builder.setCmd(CMD.MAIN_REQUEST_GAME_LIST);
		builder.setResult(true);
		List<Room_Info> list = MySqlAltern.requestRoomList(session, UserDefine.ROOM_LIST_MAXCOUNT);
		ProtobufResponse.mainUIResponse.Builder mrbuilder = ProtobufResponse.mainUIResponse.newBuilder();
		mrbuilder.setGetGameRoomListInfo(ProtobufResponseGenerator.getGetGameRoomListResponse(session, list));
		builder.setMainUIResponseInfo(mrbuilder);
	}
	
	//������������Զ�ˢ����Ϸ�б�
	//��ǰ�б��Room��������ǵ�ǰˢ�µ�room��userCount
	public void flushMainGameRoomList(ProtobufResponse.protobufResponse.Builder builder){
		builder.setCmd(CMD.MAIN_FLUSH_GAME_LIST);
		builder.setResult(true);
		ProtobufRequest.flushGameListRequest fgR = req.getMainUIRequestInfo().getFlushGameListInfo();
		List<Room_Info> list = new ArrayList<Room_Info>();
		for(int i=0; i<fgR.getRoomIdCount(); i++){
			int room_id = fgR.getRoomId(i);
			Room_Info room = MySqlAltern.getRoom(session, room_id);
			list.add(room);
		}
		ProtobufResponse.mainUIResponse.Builder mrBuilder = ProtobufResponse.mainUIResponse.newBuilder();
		mrBuilder.setFlushMainRoomListInfo(ProtobufResponseGenerator.getFlushGameListResponse(session, list));
		builder.setMainUIResponseInfo(mrBuilder);
	}
	
	//��ҵ�һ�ν�������Ϸ�ȴ������ȡ��Ϸ�е�����
	public void getFirstWaitRoomUserInfo(ProtobufResponse.protobufResponse.Builder builder){
		builder.setCmd(CMD.WAITROOM_GETLIST);
		builder.setResult(true);
		int room_id = req.getWaitRoomRequestInfo().getGetRoomListInfo().getRoomId();
		Room_Info room = MySqlAltern.getRoom(session, room_id);
		User user = MySqlAltern.getUser(session, req.getUserId());
		ProtobufResponse.waitRoomResponse.Builder wrr = ProtobufResponse.waitRoomResponse.newBuilder();
		wrr.setFirstRoomUserDataInfo(ProtobufResponseGenerator.getGetRoomInfoResponse(session, room, user));
		builder.setWaitRoomResponseInfo(wrr);
	}
	
	//�������Ϸ�ȴ������Զ�����
	public void flushWaitRoomUserInfo(ProtobufResponse.protobufResponse.Builder builder){
		builder.setCmd(CMD.WAITROOM_FLUSH);
		builder.setResult(true);
		int room_id = req.getWaitRoomRequestInfo().getFlushRoomInfoList().getRoomId();
		int userCount = req.getWaitRoomRequestInfo().getFlushRoomInfoList().getUserInfoCount();
		List<User> list_u = new ArrayList<User>();
		for(int i=0; i<userCount; i++){
			User user_ = new User();
			user_.setUser_id(req.getWaitRoomRequestInfo().getFlushRoomInfoList().getUserInfo(i).getUserId());
			user_.setUsername(req.getWaitRoomRequestInfo().getFlushRoomInfoList().getUserInfo(i).getUserName());
			list_u.add(user_);
		}
		Room_Info room = MySqlAltern.getRoom(session, room_id);
		User user = MySqlAltern.getUser(session, req.getUserId());
		long last_time = user.getLastConnectTime().getTime();
		ProtobufResponse.waitRoomResponse.Builder wrr = ProtobufResponse.waitRoomResponse.newBuilder();
		wrr.setFlushRoomUserDataInfo(ProtobufResponseGenerator.getFlushRoomInfoResponse(session, room, user, list_u, last_time));
		builder.setWaitRoomResponseInfo(wrr);
	}
	
	//����뿪�ȴ���
	public void UserLeaveWaitRoom(ProtobufResponse.protobufResponse.Builder builder){
		builder.setCmd(CMD.USER_LEAVE_WAITROOM);
		builder.setResult(true);
		User user = MySqlAltern.getUser(session, req.getUserId());
		Room_Info room = user.getRoom_Info();
		user.setRole_Info(null);
		room.setUsercount(room.getUsercount()-1);
		MySqlAltern.updateRoomInfo(session, room);
		MySqlAltern.updateUser(session, user);
	}
	
	//�������뿪�ȴ��� �������뿪�ȴ��ң���ô�ȴ��ҹر�
	public void creatorLeaveRoom(ProtobufResponse.protobufResponse.Builder builder){
		builder.setCmd(CMD.CREATOR_LEAVE_WAITROOM);
		builder.setResult(true);
		Room_Info room = MySqlAltern.getRoom(session, req.getWaitRoomRequestInfo().getCreateLeaveGameInfo().getRoomId());
		room.setIsUsed(false);
		room.setUsercount(0);
		MySqlAltern.updateRoomInfo(session, room);
		List<User> list = MySqlAltern.getRoomUserList(session, room.getRoomId());
		for(int i=0; i<list.size(); i++){
			User user = list.get(i);
			user.setRoom_Info(null);
			MySqlAltern.updateUser(session, user);
		} 
	}
	
	//�����߿�ʼ����Ϸ
	public void creatorStartGame(ProtobufResponse.protobufResponse.Builder builder){
		builder.setCmd(CMD.CREATOR_START_GAME);
		int room_id = req.getWaitRoomRequestInfo().getCreatorStartGameInfo().getRoomId();
		Room_Info room = MySqlAltern.getRoom(session, room_id);
		Battle_Info battle = MySqlAltern.createBattle(session, room, req.getWaitRoomRequestInfo().getCreatorStartGameInfo().getGameName());
		if(battle != null){
			builder.setResult(true);
		}else{
			builder.setResult(false);
		}
	}
	
	//�������Ϸ�ȴ������з���
	public void userWaitRoomSpeek(ProtobufResponse.protobufResponse.Builder builder){
		builder.setCmd(CMD.WAITROOM_SPEEK);
		Dialog_Info dialog = new Dialog_Info();
		User user = MySqlAltern.getUser(session, req.getUserId());
		dialog.setUser(user);
		dialog.setType(UserDefine.ROOM_DIALOG_TYPE);
		dialog.setGroupId(user.getRoom_Info().getRoomId());
		dialog.setContext(req.getWaitRoomRequestInfo().getUserWaitRoomSpeekInfo().getContext());
		dialog.setTarget_id(req.getWaitRoomRequestInfo().getUserWaitRoomSpeekInfo().getTarget());
		if(MySqlAltern.createDialog(session, dialog) != null){
			builder.setResult(true);
		}else{
			builder.setResult(false);
		}
	}
	
	//�������޳���ĳ��
	public void creatorExcludeUser(ProtobufResponse.protobufResponse.Builder builder){
		builder.setCmd(CMD.EXCLUDE_USER);
		builder.setResult(true);
		int user_id = req.getWaitRoomRequestInfo().getExcludeUserInfo().getUser().getUserId();
		String username = req.getWaitRoomRequestInfo().getExcludeUserInfo().getUser().getUserName();
		User creator = MySqlAltern.getUser(session, req.getUserId());
		Room_Info room = creator.getRoom_Info();
		ProtobufResponse.waitRoomResponse.Builder wrr = ProtobufResponse.waitRoomResponse.newBuilder();
		wrr.setExcludeUserInfo(ProtobufResponseGenerator.getExcludeUserResponse(session, room, user_id));
		builder.setWaitRoomResponseInfo(wrr);
	}
	
	//�����������
	public void roleBattleIdle(ProtobufResponse.protobufResponse.Builder builder){
		builder.setCmd(CMD.ROLE_BATTLE_IDLE);
		builder.setResult(true);
		//��������Ҷ�׼�����˲ſ��Է���true
		int role_id = req.getBattleSceneRequestInfo().getRoleBattleIdleInfo().getRoleId();
		int base_role_id = req.getBattleSceneRequestInfo().getRoleBattleIdleInfo().getBaseRoleId();
		int skill1_id = req.getBattleSceneRequestInfo().getRoleBattleIdleInfo().getSkill1Id();
		int skill2_id = req.getBattleSceneRequestInfo().getRoleBattleIdleInfo().getSkill2Id();
		int skill3_id = req.getBattleSceneRequestInfo().getRoleBattleIdleInfo().getSkill3Id();
		int skill4_id = req.getBattleSceneRequestInfo().getRoleBattleIdleInfo().getSkill4Id();
		ProtobufResponse.battleSceneResponse.Builder bsrBuilder = ProtobufResponse.battleSceneResponse.newBuilder();
		bsrBuilder.setRoleIdleInfo(ProtobufResponseGenerator.getRoleBattleIdleResponse(session, role_id, base_role_id,
				skill1_id, skill2_id, skill3_id, skill4_id));
		builder.setBattleSceneResponseInfo(bsrBuilder);
	}
	
	//��������battle��������
	public void reFlushRoleBattleIdle(ProtobufResponse.protobufResponse.Builder builder){
		builder.setCmd(CMD.ROLE_BATTLE_REQUEST_START);
		builder.setResult(true);
		//��������Ҷ�׼�����˲ſ��Է���true
		int role_id = req.getBattleSceneRequestInfo().getReflushRoleBattleIdleInfo().getRoleId();
		int base_role_id = req.getBattleSceneRequestInfo().getReflushRoleBattleIdleInfo().getBaseRoleId();
		int skill1_id = req.getBattleSceneRequestInfo().getReflushRoleBattleIdleInfo().getSkill1Id();
		int skill2_id = req.getBattleSceneRequestInfo().getReflushRoleBattleIdleInfo().getSkill2Id();
		int skill3_id = req.getBattleSceneRequestInfo().getReflushRoleBattleIdleInfo().getSkill3Id();
		int skill4_id = req.getBattleSceneRequestInfo().getReflushRoleBattleIdleInfo().getSkill4Id();
		ProtobufResponse.battleSceneResponse.Builder bsrBuilder = ProtobufResponse.battleSceneResponse.newBuilder();
		bsrBuilder.setRoleIdleInfo(ProtobufResponseGenerator.getRoleBattleIdleResponse(session, role_id, base_role_id,
				skill1_id, skill2_id, skill3_id, skill4_id));
		builder.setBattleSceneResponseInfo(bsrBuilder);
	}
	
	//��battle����ҷ���
	public void roleBattleDialogResponse(ProtobufResponse.protobufResponse.Builder builder){
		builder.setCmd(CMD.ROLE_BATTLE_DIALOG);
		builder.setResult(true);
		int dialogType = req.getBattleSceneRequestInfo().getRoleBattleDialogInfo().getDialogType();
		String context = req.getBattleSceneRequestInfo().getRoleBattleDialogInfo().getContext();
		Dialog_Info dialog = new Dialog_Info();
		User user = MySqlAltern.getUser(session, req.getUserId());
		dialog.setContext(context);
		dialog.setUser(user);
		dialog.setType(dialogType);
		dialog.setGroupId(user.getBattle_Info().getBattle_id());
		MySqlAltern.insertDialog(session, dialog);
		Battle_Info battle = user.getBattle_Info();
		MySqlAltern.updateBattleInfo(session, battle);
		List<Integer> u_list = req.getBattleSceneRequestInfo().getRoleBattleDialogInfo().getOUserIdList();
		ProtobufResponse.battleSceneResponse.Builder bsrBuilder = ProtobufResponse.battleSceneResponse.newBuilder();
		bsrBuilder.setBattleUpdateInfo(ProtobufResponseGenerator.getBattleUpdateResponse(session, user, u_list));
		builder.setBattleSceneResponseInfo(bsrBuilder);
	}
	
	//��battle���Զ�ˢ��
	public void battleUpdateResponse(ProtobufResponse.protobufResponse.Builder builder){
		builder.setCmd(CMD.ROLE_BATTLE_UPDATE);
		builder.setResult(true);
		User user = MySqlAltern.getUser(session, req.getUserId());
		List<Integer> u_list = req.getBattleSceneRequestInfo().getBattleSceneUpdateInfo().getOUserIdList();
		ProtobufResponse.battleSceneResponse.Builder bsrBuilder = ProtobufResponse.battleSceneResponse.newBuilder();
		bsrBuilder.setBattleUpdateInfo(ProtobufResponseGenerator.getBattleUpdateResponse(session, user, u_list));
		builder.setBattleSceneResponseInfo(bsrBuilder);
	}
	
	//��battle����������
	public void battleClearInfoResponse(ProtobufResponse.protobufResponse.Builder builder){
		builder.setCmd(CMD.BATTLE_CLEARING);
		builder.setResult(true);
		User user = MySqlAltern.getUser(session, req.getUserId());
		Battle_Info battle = user.getBattle_Info();
		ProtobufResponse.battleSceneResponse.Builder bsrBuilder = ProtobufResponse.battleSceneResponse.newBuilder();
		bsrBuilder.setBattleClearInfo(ProtobufResponseGenerator.getBattleClearResponse(session, battle, user.getLastConnectTime()));
		builder.setBattleSceneResponseInfo(bsrBuilder);
	}
	
	//����˳�battle
	public void userExitBattleResponse(ProtobufResponse.protobufResponse.Builder builder){
		builder.setCmd(CMD.USER_EXIT_BATTLE);
		builder.setResult(true);
		User user = MySqlAltern.getUser(session, req.getUserId());
		user.setBattle_Info(null);
		MySqlAltern.updateUser(session, user);
	}
	
	//����ٴ�����map����
	public void userGetAllMapResponse(ProtobufResponse.protobufResponse.Builder builder){
		builder.setCmd(CMD.USER_REQUEST_MAP_DATA);
		builder.setResult(true);
		List<Map_Info> m_list = MySqlAltern.getMapList(session);
		ProtobufResponse.getAllMapResponse.Builder gamBuilder = ProtobufResponse.getAllMapResponse.newBuilder();
		for(int i=0; i<m_list.size(); i++){
			Map_Info map = m_list.get(i);
			gamBuilder.addMapInfo(ProtobufResponseGenerator.getGameMapInfo(session, map));
		}
		builder.setGetAllMapInfo(gamBuilder);
	}
	
	//����ٴ�����skill����
	public void userGetAllSkillResponse(ProtobufResponse.protobufResponse.Builder builder){
		builder.setCmd(CMD.USER_REQUEST_SKILL_DATA);
		builder.setResult(true);
		List<Skill_Info> s_list = MySqlAltern.getSkillList(session);
		ProtobufResponse.getAllSkillResponse.Builder gasBuilder = ProtobufResponse.getAllSkillResponse.newBuilder();
		for(int i=0; i<s_list.size(); i++){
			Skill_Info skill = s_list.get(i);
			gasBuilder.addSkillInfo(ProtobufResponseGenerator.getGameSkillInfo(session, skill));
		}
		builder.setGetAllSkillInfo(gasBuilder);
	}
	
	//����ٴ�����baseRole����
	public void userGetAllBaseRoleResponse(ProtobufResponse.protobufResponse.Builder builder){
		builder.setCmd(CMD.USER_REQUEST_BASEROLE_DATA);
		builder.setResult(true);
		List<Base_Role> b_list = MySqlAltern.getBaseRoleList(session);
		ProtobufResponse.getAllBaseRoleResponse.Builder gabBuilder = ProtobufResponse.getAllBaseRoleResponse.newBuilder();
		for(int i=0; i<b_list.size(); i++){
			Base_Role role= b_list.get(i);
			gabBuilder.addBaseRoleInfo(ProtobufResponseGenerator.getBaseRoleInfo(session, role));
		}
		builder.setGetAllBaseRoleInfo(gabBuilder);
	}
	
	public void userSelectBaseRoleResponse(ProtobufResponse.protobufResponse.Builder builder){
		builder.setCmd(CMD.USER_SELECT_BASRROLE);
		builder.setResult(true);
		User user = MySqlAltern.getUser(session, req.getUserId());
		ProtobufRequest.userSelectRole oldRole = req.getWaitRoomRequestInfo().getUserSelectBaseRoleInfo().getOldRole();
		ProtobufRequest.userSelectRole newRole = req.getWaitRoomRequestInfo().getUserSelectBaseRoleInfo().getNewRole();
		Role_Info role = MySqlAltern.createRole(session, newRole.getBaseRoleId());
		user.setRole_Info(role);
		MySqlAltern.updateUser(session, user);
	}
	
	public void userSelectRoleSkillResponse(ProtobufResponse.protobufResponse.Builder builder){
		builder.setCmd(CMD.USER_SELECT_ROLE_SKILL);
		ProtobufRequest.userSelectSkill oldSkill = req.getWaitRoomRequestInfo().getUserSelectSkillInfo().getOldSkill();
		ProtobufRequest.userSelectSkill newSkill = req.getWaitRoomRequestInfo().getUserSelectSkillInfo().getNewSkill();
		Skill_Info skill = MySqlAltern.selectSkill(session, newSkill.getSkillId());
		User user = MySqlAltern.getUser(session, req.getUserId());
		Role_Info role = user.getRole_Info();
		if(newSkill.getSkillIndex() == 1){
			role.setSkill1(skill);
		}else if(newSkill.getSkillIndex() == 2){
			role.setSkill2(skill);
		}else if(newSkill.getSkillIndex() == 3){
			role.setSkill3(skill);
		}else{
			role.setSkill4(skill);
		}
		MySqlAltern.updateRole(session, role);
	}
}
