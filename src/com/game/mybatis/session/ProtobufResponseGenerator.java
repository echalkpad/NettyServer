package com.game.mybatis.session;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.game.mybatis.model.Base_Role;
import com.game.mybatis.model.Battle_Info;
import com.game.mybatis.model.Dialog_Info;
import com.game.mybatis.model.Killer_Info;
import com.game.mybatis.model.Map_Info;
import com.game.mybatis.model.Role_Info;
import com.game.mybatis.model.Room_Info;
import com.game.mybatis.model.Skill_Info;
import com.game.mybatis.model.User;
import com.game.proto.ProtobufResponse;

public class ProtobufResponseGenerator {
	public static ProtobufResponse.waitingRoomInfo.Builder getWaitingRoomInfo(SqlSession session, Room_Info room){
		ProtobufResponse.waitingRoomInfo.Builder info = ProtobufResponse.waitingRoomInfo.newBuilder();
		info.setRoomName(room.getRoomName());
		info.setUserCount(room.getUsercount());
		info.setPlayMapId(room.getPlayMap().getMapId());
		info.setPlayMapName(room.getPlayMap().getMapName());
		info.setMaxUserCount(room.getPlayMap().getPlayermaxcount());
		info.setCreatorId(room.getCreator().getUser_id());
		info.setCreatorName(room.getCreator().getUsername());
		return info;
	}
	
	public static ProtobufResponse.gameBaseRoleInfo.Builder getBaseRoleInfo(SqlSession session, Base_Role base){
		ProtobufResponse.gameBaseRoleInfo.Builder info = ProtobufResponse.gameBaseRoleInfo.newBuilder();
		info.setBaseRoleId(base.getBaseroleId());
		info.setAttackType(base.getAttackType());
		info.setRoleType(base.getRoleType());
		info.setRolename(base.getRolename());
		info.setMaxLevel(base.getMaxlevel());
		info.setMinLevel(base.getMinlevel());
		info.setMinSpeed(base.getMinspeed());
		info.setMaxSpeed(base.getMaxspeed());
		info.setBaseHp(base.getBasehp());
		info.setAttackSpeed(base.getAttackspeed());
		info.setMaxAttackSpeed(base.getMaxattackspeed());
		return info;
	}
	
	public static ProtobufResponse.gameSkillInfo.Builder getGameSkillInfo(SqlSession session, Skill_Info skill){
		ProtobufResponse.gameSkillInfo.Builder info = ProtobufResponse.gameSkillInfo.newBuilder();
		info.setSkillId(skill.getSkillId());
		info.setSkillName(skill.getSkillName());
		info.setSkillType(skill.getSkillType());
		info.setDuration(skill.getDuration());
		info.setInteval(skill.getInterval());
		info.setIsGroup(skill.getIsGroup());
		info.setSkillBaseHurt(skill.getSkillBaseHurt());
		info.setCanVertigo(skill.getCanVertigo());
		info.setCanDeceleration(skill.getCanDeceleration());
		info.setSkillHurtFactor(skill.getSkillHurtFactor());
		info.setSkillBufferTime(skill.getSkillBufferTime());
		info.setSkillBufferFactor(skill.getSkillBufferFactor());
		info.setSkillAddBaseHp(skill.getSkillAddBaseHp());
		info.setSkillAddBaseAttack(skill.getSkillAddBaseAttack());
		info.setSkillAddBaseSpeed(skill.getSkillAddBaseSpeed());
		info.setSkillAddHpFactor(skill.getSkillAddHpFactor());
		info.setSkillAddAttackFactor(skill.getSkillAddAttackFactor());
		info.setSkillAddSpeedFactor(skill.getSkillAddSpeedFactor());
		return info;
	}
	
	public static ProtobufResponse.gameMapInfo.Builder getGameMapInfo(SqlSession session, Map_Info map){
		ProtobufResponse.gameMapInfo.Builder info = ProtobufResponse.gameMapInfo.newBuilder();
		info.setMapId(map.getMapId());
		info.setMapName(map.getMapName());
		info.setMapPngname(map.getMapResName());
		info.setMapDataname(map.getMapDataName());
		info.setPlayerMinCount(map.getPlayermincount());
		info.setPlayerMaxCount(map.getPlayermaxcount());
		info.setSupportTangledFight(map.getSupportTangledFight());
		info.setSupportTeamBattle(map.getSupportTeamBattle());
		info.setSupportFlagBattle(map.getSuportFlagBattle());
		info.setSupportBoosAnnihilateBattle(map.getSupportBossAnnihilateBattle());
		return info;
	}
	
	//Î´Íê³É
	public static ProtobufResponse.idleMessageResponse.Builder getIdleMessageResponse(SqlSession session, List<Room_Info> r_list, 
			List<Base_Role> b_list, List<Skill_Info> s_list, List<Map_Info> m_list){
		ProtobufResponse.idleMessageResponse.Builder idle = ProtobufResponse.idleMessageResponse.newBuilder();
		for(int i=0; i<r_list.size(); i++){
			Room_Info room = r_list.get(i);
			idle.addRoomInfo(getWaitingRoomInfo(session, room));
		}
		for(int i=0; i<b_list.size(); i++){
			Base_Role base = b_list.get(i);
		
		}
		for(int i=0; i<m_list.size(); i++){
				
		}
		for(int i=0; i<m_list.size(); i++){
			
		}
		return idle;
	}
	
	public static ProtobufResponse.createGameResponse.Builder getCreateGameResponse(SqlSession session, Room_Info room){
		ProtobufResponse.createGameResponse.Builder gameBuilder = ProtobufResponse.createGameResponse.newBuilder();
		gameBuilder.setRoomId(room.getRoomId());
		gameBuilder.setRoomName(room.getRoomName());
		return gameBuilder;
	}
	
	public static ProtobufResponse.waitingRoomUserInfo.Builder getWaitingRoomUserInfo(SqlSession session, User user){
		ProtobufResponse.waitingRoomUserInfo.Builder wrBuilder = ProtobufResponse.waitingRoomUserInfo.newBuilder();
		wrBuilder.setUserId(user.getUser_id());
		wrBuilder.setUserName(user.getUsername());
		if(user.getRoom_Info() != null){
			wrBuilder.setRoleType(user.getRole_Info().getType());
		}
		return wrBuilder;
	}
	
	public static ProtobufResponse.mainJoinGameResponse.Builder getMainJoinGameResponse(SqlSession session, Room_Info room, List<User> list){
		ProtobufResponse.mainJoinGameResponse.Builder mjBuilder = ProtobufResponse.mainJoinGameResponse.newBuilder();
		mjBuilder.setUserCount(room.getUsercount());
		mjBuilder.setCreatorId(room.getCreator().getUser_id());
		mjBuilder.setCreatorName(room.getCreator().getUsername());
		for(int i=0; i<list.size(); i++){
			User user = list.get(i);
			mjBuilder.addInfo(getWaitingRoomUserInfo(session, user));
		}
		return mjBuilder;
	}
	
	public static ProtobufResponse.getGameRoomListResponse.Builder getGetGameRoomListResponse(SqlSession session, List<Room_Info> list){
		ProtobufResponse.getGameRoomListResponse.Builder glBuilder = ProtobufResponse.getGameRoomListResponse.newBuilder();
		for(int i=0; i<list.size(); i++){
			Room_Info room = list.get(i);
			glBuilder.addRoomInfo(getWaitingRoomInfo(session, room));
		}
		return glBuilder;
	}
	
	public static ProtobufResponse.flushMainRoomListResponse.Builder getFlushGameListResponse(SqlSession session, List<Room_Info> list){
		ProtobufResponse.flushMainRoomListResponse.Builder fgBuilder = ProtobufResponse.flushMainRoomListResponse.newBuilder();
		for(int i=0; i<list.size(); i++){
			Room_Info room = list.get(i);
			fgBuilder.addInfo(getWaitingRoomInfo(session, room));
		}
		return fgBuilder;
	}
	
	public static ProtobufResponse.userDialogInfo.Builder getDialogInfo(SqlSession session, Dialog_Info dialog){
		ProtobufResponse.userDialogInfo.Builder diBuilder = ProtobufResponse.userDialogInfo.newBuilder();
		diBuilder.setUserId(dialog.getUser().getUser_id());
		diBuilder.setUserName(dialog.getUser().getUsername());
		diBuilder.setType(dialog.getType());
		diBuilder.setSpeekTime(dialog.getCreateTime().getTime());
		diBuilder.setContext(dialog.getContext());
		return diBuilder;
	}
	
	public static ProtobufResponse.userBaseInfo.Builder getUserBaseInfo(SqlSession session, User user){
		ProtobufResponse.userBaseInfo.Builder ub = ProtobufResponse.userBaseInfo.newBuilder();
		ub.setUserId(user.getUser_id());
		ub.setUserName(user.getUsername());
		return ub;
	}
	
	public static ProtobufResponse.userSelectRoleData.Builder getUserSelectRoleData(SqlSession session, Role_Info role){
		ProtobufResponse.userSelectRoleData.Builder usrBuilder = ProtobufResponse.userSelectRoleData.newBuilder();
		usrBuilder.setBaseRoleId(role.getBaseRole().getBaseroleId());
		usrBuilder.setUserId(role.getUser().getUser_id());
		return usrBuilder;
	}
	
	public static ProtobufResponse.userSelectSkillData.Builder getUserSelectSkillData(SqlSession session, Role_Info role, int skill_index){
		ProtobufResponse.userSelectSkillData.Builder ussBuilder = ProtobufResponse.userSelectSkillData.newBuilder();
		ussBuilder.setUserId(role.getUser().getUser_id());
		if(skill_index == 1){
			ussBuilder.setSkillId(1);
			ussBuilder.setSkillId(role.getSkill1().getSkillId());
		}else if(skill_index == 2){
			ussBuilder.setSkillId(2);
			ussBuilder.setSkillId(role.getSkill2().getSkillId());
		}else if(skill_index == 3){
			ussBuilder.setSkillId(3);
			ussBuilder.setSkillId(role.getSkill3().getSkillId());
		}else{
			ussBuilder.setSkillId(4);
			ussBuilder.setSkillId(role.getSkill4().getSkillId());
		}
		return ussBuilder;
	}
	
	public static ProtobufResponse.firstRoomUserDataResponse.Builder getGetRoomInfoResponse(SqlSession session, Room_Info room, User user){
		ProtobufResponse.firstRoomUserDataResponse.Builder griBuilder = ProtobufResponse.firstRoomUserDataResponse.newBuilder();
		List<User> list = MySqlAltern.getRoomUserList(session, room.getRoomId());
		for(int i=0; i<list.size(); i++){
			User user_ = list.get(i);
			griBuilder.addUserData(getUserBaseInfo(session, user_));
			Role_Info role = user_.getRole_Info();
			if(role != null){
				griBuilder.addUserSelectRole(getUserSelectRoleData(session, role));
				if(role.getSkill1() != null){
					griBuilder.addUserSelectSkill(getUserSelectSkillData(session, role, 1));
				}
				if(role.getSkill2() != null){
					griBuilder.addUserSelectSkill(getUserSelectSkillData(session, role, 2));
				}
				if(role.getSkill3() != null){
					griBuilder.addUserSelectSkill(getUserSelectSkillData(session, role, 3));
				}
				if(role.getSkill4() != null){
					griBuilder.addUserSelectSkill(getUserSelectSkillData(session, role, 4));
				}
			}
		}
		return griBuilder;
	}
	
	
	public static ProtobufResponse.flushRoomUserDataResponse.Builder getFlushRoomInfoResponse(SqlSession session, Room_Info room, User user, 
			List<User> list_u, long userLastTime){
		ProtobufResponse.flushRoomUserDataResponse.Builder fiBuilder = ProtobufResponse.flushRoomUserDataResponse.newBuilder();
		List<Dialog_Info> dialogList = MySqlAltern.getRoomDialogList(session, room.getRoomId());
		for(int i=0; i<dialogList.size(); i++){
			Dialog_Info dialog = dialogList.get(i);
			if(dialog.getCreateTime().getTime() > userLastTime){
				fiBuilder.addUserDialogInfo(getDialogInfo(session, dialog));
			}
		}
		List<User> userList = MySqlAltern.getRoomUserList(session, room.getRoomId());
		boolean beExcluding = true;
		for(int i=0; i<userList.size(); i++){
			User user_o = userList.get(i);
			if(user_o.getUser_id() == user.getUser_id()){
				beExcluding = false;
				break;
			}
		}
		
		for(int i=0; i<userList.size(); i++){
			User user_o = userList.get(i);
			Role_Info role = user_o.getRole_Info();
			if(role != null && user_o.getSelectRoleTime().getTime() > userLastTime){
				fiBuilder.addUserSelectRole(getUserSelectRoleData(session, role));
			}
			if(role != null){
				Skill_Info skill1 = role.getSkill1();
				Skill_Info skill2 = role.getSkill2();
				Skill_Info skill3 = role.getSkill3();
				Skill_Info skill4 = role.getSkill4();
				if(skill1 != null && role.getSelectSkill1Time().getTime() > userLastTime){
					fiBuilder.addUserSelectSkill(getUserSelectSkillData(session, role, 1));
				}
				if(skill2 != null && role.getSelectSkill2Time().getTime() > userLastTime){
					fiBuilder.addUserSelectSkill(getUserSelectSkillData(session, role, 2));				
				}
				if(skill3 != null && role.getSelectSkill3Time().getTime() > userLastTime){
					fiBuilder.addUserSelectSkill(getUserSelectSkillData(session, role, 3));
				}
				if(skill4 != null && role.getSelectSkill4Time().getTime() > userLastTime){
					fiBuilder.addUserSelectSkill(getUserSelectSkillData(session, role, 4));
				}
			}
		}
		if(room.getIsUsed() && !beExcluding){
			for(int i=0; i<userList.size(); i++){
				boolean contain = false;
				for(int k = 0; k<list_u.size(); k++){
					if(list_u.get(k).getUser_id() == userList.get(i).getUser_id()){
						contain = true;
						break;
					}
				}
				if(!contain){
					fiBuilder.addAddedUserinfo(getUserBaseInfo(session, userList.get(i)));
				}
			}
			for(int i = 0; i<list_u.size(); i++){
				boolean contain = false;
				for(int k =0; k<userList.size(); k++){
					if(list_u.get(i).getUser_id() == userList.get(k).getUser_id()){
						contain = true;
						break;
					}
				}
				if(!contain){
					fiBuilder.addLeaveUserinfo(getUserBaseInfo(session, list_u.get(i)));
				}
			}
			fiBuilder.setIsRoomColose(false);
		}else{
			fiBuilder.setIsRoomColose(true);
		}
		fiBuilder.setBeExcluding(beExcluding);
		return fiBuilder;
	}
	
	public static ProtobufResponse.excludeUserResponse.Builder getExcludeUserResponse(SqlSession session, Room_Info room, int exclude_id){
		ProtobufResponse.excludeUserResponse.Builder euBuilder = ProtobufResponse.excludeUserResponse.newBuilder();
		List<User> list = MySqlAltern.getRoomUserList(session, room.getRoomId());
		for(int i =0; i<list.size(); i++){
			User user = list.get(i);
			if(user.getUser_id() == exclude_id){
				room.setUsercount(room.getUsercount()-1);
				user.setRoom_Info(null);
				MySqlAltern.updateUser(session, user);
				MySqlAltern.updateRoomInfo(session, room);
			}else{
				euBuilder.addUserInfo(getUserBaseInfo(session, user));
			}
		}
		return euBuilder;
	}
	
	public static ProtobufResponse.roleBattleInfo.Builder getRoleBattleInfo(SqlSession session, Role_Info role){
		ProtobufResponse.roleBattleInfo.Builder rbiBuilder = ProtobufResponse.roleBattleInfo.newBuilder();
		rbiBuilder.setRoleId(role.getRoleId());
		rbiBuilder.setUserId(role.getUser().getUser_id());
		rbiBuilder.setOrderId(role.getOrder_id());
		rbiBuilder.setBaseRoleId(role.getBaseRole().getBaseroleId());
		rbiBuilder.setUsername(role.getUser().getUsername());
		rbiBuilder.setType(role.getType());
		rbiBuilder.setLevel(role.getLevel());
		rbiBuilder.setHp(role.getHp());
		rbiBuilder.setAttack(role.getAttack());
		rbiBuilder.setSpeed(role.getSpeed());
		rbiBuilder.setSkill1Id(role.getSkill1().getSkillId());
		rbiBuilder.setSkill2Id(role.getSkill2().getSkillId());
		rbiBuilder.setSkill3Id(role.getSkill3().getSkillId());
		rbiBuilder.setSkill4Id(role.getSkill4().getSkillId());
		return rbiBuilder;
	}
	
	public static ProtobufResponse.roleBattleIdleResponse.Builder getRoleBattleIdleResponse(SqlSession session, int role_id,
			int base_role_id, int skill1_id, int skill2_id, int skill3_id, int skill4_id){
		ProtobufResponse.roleBattleIdleResponse.Builder rbiBuilder = ProtobufResponse.roleBattleIdleResponse.newBuilder();
		Role_Info role = MySqlAltern.getRole(session, role_id);
		if(role.getIsIdle() == false){
			role.setIsIdle(true);
			MySqlAltern.updateRole(session, role);
		}
		User user = role.getUser();
		if(user.getState() != UserState.TANGLEDFIGHT){
			user.setState(UserState.TANGLEDFIGHT);
			MySqlAltern.updateUser(session, user);
		}
		Battle_Info battle = user.getBattle_Info();
		rbiBuilder.setBattleId(battle.getBattle_id());
		rbiBuilder.setBattleName(battle.getBattle_name());
		rbiBuilder.setType(battle.getType());
		rbiBuilder.setPlayMapId(battle.getPlayMap().getMapId());
		rbiBuilder.setPlayMapName(battle.getPlayMap().getMapName());
		List<User> list = MySqlAltern.getBattleUserList(session, battle.getBattle_id());
		boolean isIdle = true;
		for(int i=0; i<list.size(); i++){
			Role_Info role_ = list.get(i).getRole_Info();
			if(role.getIsIdle() == false){
				isIdle = false;
				break;
			}
		}
		rbiBuilder.setIsIdle(isIdle);
		if(isIdle){
			for(int i=0; i<list.size(); i++){
				Role_Info role_ = list.get(i).getRole_Info();
				rbiBuilder.addRoleInfo(getRoleBattleInfo(session, role_));
			}
		}
		return rbiBuilder;
	}
	
	public static ProtobufResponse.battleKillerData.Builder getBattleKillerDate(SqlSession session, Date lastTime, Killer_Info killer){
		ProtobufResponse.battleKillerData.Builder bkdBuilder = ProtobufResponse.battleKillerData.newBuilder();
		bkdBuilder.setKillerTime(killer.getCreateTime().getTime());
		bkdBuilder.setKillerId(killer.getRole().getRoleId());
		bkdBuilder.setKillerName(killer.getRole().getUser().getUsername());
		bkdBuilder.setTargetId(killer.getTaregt().getRoleId());
		bkdBuilder.setTargetName(killer.getTaregt().getUser().getUsername());
		List<Killer_Info> assistList = MySqlAltern.getAssistsByTarget(session, killer.getRole().getUser().getBattle_Info(), 
				killer.getTaregt(), lastTime);
		for(int i=0; i<assistList.size(); i++){
			bkdBuilder.addAssistsData(getBattleAssistsData(session, lastTime, assistList.get(i)));
		}
		return bkdBuilder;
	}
	
	public static ProtobufResponse.battleAssistsData.Builder getBattleAssistsData(SqlSession session, Date lastTime, Killer_Info assist){
		ProtobufResponse.battleAssistsData.Builder badBuilder = ProtobufResponse.battleAssistsData.newBuilder();
		badBuilder.setAssisterId(assist.getRole().getRoleId());
		badBuilder.setAssisterName(assist.getRole().getUser().getUsername());
		return badBuilder;
	}
	
	public static ProtobufResponse.battleUpdateResponse.Builder getBattleUpdateResponse(SqlSession session, User user, List<Integer> i_list){
		ProtobufResponse.battleUpdateResponse.Builder bupBuilder = ProtobufResponse.battleUpdateResponse.newBuilder();
		long startTime = user.getLastConnectTime().getTime();
		bupBuilder.setStartTime(startTime);
		Battle_Info battle = user.getBattle_Info();
		List<Dialog_Info> d_list = MySqlAltern.getBattleDialogList(session, battle.getBattle_id());
		for(int i=0; i<d_list.size(); i++){
			Dialog_Info dialog = d_list.get(i);
			if(dialog.getCreateTime().getTime() > startTime){
				bupBuilder.addUserDialogInfo(getDialogInfo(session, dialog));
			}
		}
		List<User> userList = MySqlAltern.getBattleUserList(session, battle.getBattle_id());
		for(int i=0; i<userList.size(); i++){
			boolean contain = false;
			for(int k = 0; k<i_list.size(); k++){
				if(i_list.get(k) == userList.get(i).getUser_id()){
					contain = true;
					break;
				}
			}
			if(!contain){
				bupBuilder.addAddedUserinfo(getUserBaseInfo(session, userList.get(i)));
			}
		}
		for(int i = 0; i<i_list.size(); i++){
			boolean contain = false;
			for(int k =0; k<userList.size(); k++){
				if(i_list.get(i) == userList.get(k).getUser_id()){
					contain = true;
					break;
				}
			}
			if(!contain){
				User user_ = MySqlAltern.getUser(session, i_list.get(i));
				bupBuilder.addLeaveUserinfo(getUserBaseInfo(session, user_));
			}
		}
		List<Killer_Info> k_list = MySqlAltern.getAllKillerData(session, user.getLastConnectTime(), battle);
		for(int i=0; i<k_list.size(); i++){
			bupBuilder.addKillerData(getBattleKillerDate(session, user.getLastConnectTime(), k_list.get(i)));
		}
		bupBuilder.setOverTime(System.currentTimeMillis());
		return bupBuilder;
	}
	
	public static ProtobufResponse.battleClearResponse.Builder getBattleClearResponse(SqlSession session, Battle_Info battle, Date lastTime){
		ProtobufResponse.battleClearResponse.Builder bcrBuilder = ProtobufResponse.battleClearResponse.newBuilder();
		List<User> list = MySqlAltern.getBattleUserList(session, battle.getBattle_id());
		for(int i = 0; i<list.size(); i++){
			User user = list.get(i);
			bcrBuilder.addRoleClearData(getBattleRoleClearData(session, user.getRole_Info(), battle, lastTime));
		}
		return bcrBuilder;
	}
	
	public static ProtobufResponse.battleRoleClearData.Builder getBattleRoleClearData(SqlSession session, Role_Info role, Battle_Info battle, Date lastTime){
		ProtobufResponse.battleRoleClearData.Builder brcBuilder = ProtobufResponse.battleRoleClearData.newBuilder();
		brcBuilder.setRoleId(role.getRoleId());
		brcBuilder.setOrderId(role.getOrder_id());
		brcBuilder.setRoleName(role.getUser().getUsername());
		List<Killer_Info> k_list = MySqlAltern.getAllKillerDataByKiller(session, battle,
				role, lastTime);
		List<Killer_Info> s_list = MySqlAltern.getAllKillerDataByAssists(session, battle, role, lastTime);
		brcBuilder.setKillCount(k_list.size());
		brcBuilder.setAssistCount(s_list.size());
		for(int i=0; i<k_list.size(); i++){
			brcBuilder.addKillData(getKillInfoData(session, k_list.get(i)));
		}
		for(int i=0; i<s_list.size(); i++){
			brcBuilder.addKillData(getKillInfoData(session, s_list.get(i)));
		}
		return brcBuilder;
	}
	
	public static ProtobufResponse.killInfoData.Builder getKillInfoData(SqlSession session, Killer_Info killer){
		ProtobufResponse.killInfoData.Builder kidBuilder = ProtobufResponse.killInfoData.newBuilder();
		kidBuilder.setRoleId(killer.getRole().getRoleId());
		kidBuilder.setTargetId(killer.getTaregt().getRoleId());
		kidBuilder.setRoleName(killer.getRole().getUser().getUsername());
		kidBuilder.setTargetName(killer.getTaregt().getUser().getUsername());
		if(killer.getIskiller()){
			kidBuilder.setType(0);
		}else{
			kidBuilder.setType(1);
		}
		return kidBuilder;
	}

}
