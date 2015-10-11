package com.game.mybatis.session;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.game.mybatis.dao.Base_RoleMapper;
import com.game.mybatis.dao.Battle_InfoMapper;
import com.game.mybatis.dao.Dialog_InfoMapper;
import com.game.mybatis.dao.Killer_InfoMapper;
import com.game.mybatis.dao.Map_InfoMapper;
import com.game.mybatis.dao.Role_InfoMapper;
import com.game.mybatis.dao.Room_InfoMapper;
import com.game.mybatis.dao.Skill_InfoMapper;
import com.game.mybatis.dao.UserMapper;
import com.game.mybatis.model.Base_Role;
import com.game.mybatis.model.Battle_Info;
import com.game.mybatis.model.Dialog_Info;
import com.game.mybatis.model.Killer_Info;
import com.game.mybatis.model.Map_Info;
import com.game.mybatis.model.Role_Info;
import com.game.mybatis.model.Room_Info;
import com.game.mybatis.model.Skill_Info;
import com.game.mybatis.model.User;

public class MySqlAltern {
	public static User createUser(SqlSession session, String username, String country, String province, String location){
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user = new User();
		user.setUsername(username);
		user.setCountry(country);
		user.setProvince(province);
		user.setLocation(location);
		user.setCreateTime(new Date(System.currentTimeMillis()));
		user.setLoginTime(new Date(System.currentTimeMillis()));
		mapper.createUser(user);
		session.commit();
		return user;
	}
	
	public static User createUser(SqlSession session, User user){
		UserMapper mapper = session.getMapper(UserMapper.class);
		mapper.createUser(user);
		session.commit();
		return user;
	}
	
	public static boolean checkUserName(SqlSession session, String username)
	{
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user = mapper.selectByUserName(username);
		if(user == null){
			return true;
		}
		return false;
	}
	
	public static boolean userLogin(SqlSession session, int user_id, String location, String ipAddress){
		try{
			UserMapper mapper = session.getMapper(UserMapper.class);
			User user = mapper.selectById(user_id);
			user.setLocation(location);
			user.setLoginTime(new Date(System.currentTimeMillis()));
			user.setState(UserState.LOGIN);
			user.setIpAddress(ipAddress);
			mapper.updateUser(user);
			session.commit();
			return true;
		}catch(Exception e){
			session.rollback();
			return false;
		}
	}
	
	public static boolean userIdle(SqlSession session, int user_id){
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user = mapper.selectById(user_id);
		user.setState(UserState.IDLE);
		mapper.updateUser(user);
		session.commit();
		return true;
	}
	
	public static List<Room_Info> requestRoomList(SqlSession session, int maxCount){
		Room_InfoMapper mapper = session.getMapper(Room_InfoMapper.class);
		List<Room_Info> list = mapper.getWaitRoom(maxCount);
		return list;
	}
	
	public static Room_Info createGameRoom(SqlSession session, String gamename, int map_id, int playType){
		Room_InfoMapper r_mapper = session.getMapper(Room_InfoMapper.class);
		Map_InfoMapper m_mapper = session.getMapper(Map_InfoMapper.class);
		Room_Info room = new Room_Info();
		Map_Info map = m_mapper.selectById(map_id);
		room.setPlayMap(map);
		room.setRoomName(gamename);
		room.setGameType(playType);
		room.setIsUsed(true);
		r_mapper.insertRoomInfo(room);
		session.commit();
		return room;
	}
	
	public static boolean mainJoinGame(SqlSession session, int room_id, int user_id){
		Room_InfoMapper r_mapper = session.getMapper(Room_InfoMapper.class);
		Room_Info room = r_mapper.selectById(room_id);
		if(room.getUsercount() < room.getPlayMap().getPlayermaxcount()){
			room.setUsercount(room.getUsercount()+1);
			UserMapper u_mapper = session.getMapper(UserMapper.class);
			User user = u_mapper.selectById(user_id);
			user.setRoom_Info(room);
			r_mapper.updateRoomInfo(room);
			u_mapper.updateUser(user);
			session.commit();
			return true;
		}
		return false;
	}
	
	public static Room_Info getRoom(SqlSession session, int room_id){
		Room_InfoMapper r_mapper = session.getMapper(Room_InfoMapper.class);
		Room_Info room = r_mapper.selectById(room_id);
		return room;
	}
	
	public static User getUser(SqlSession session, int user_id){
		UserMapper u_mapper = session.getMapper(UserMapper.class);
		User user = u_mapper.selectById(user_id);
		return user;
	}
	
	public static Role_Info getRole(SqlSession session, int role_id){
		Role_InfoMapper r_mapper = session.getMapper(Role_InfoMapper.class);
		Role_Info role = r_mapper.selectById(role_id);
		return role;
	}
	
	public static void updateRole(SqlSession session, Role_Info role){
		Role_InfoMapper r_mapper = session.getMapper(Role_InfoMapper.class);
		r_mapper.updateRoleInfo(role);
		session.commit();
	}
	
	public static boolean updateUser(SqlSession session, User user){
		try{
			UserMapper u_mapper = session.getMapper(UserMapper.class);
			u_mapper.updateUser(user);
			session.commit();
			return true;
		}catch(Exception e){
			session.rollback();
			return false;
		}
		
	}
	
	public static boolean updateUserLastConnectTime(SqlSession session, int user_id){
		try{
			UserMapper u_mapper = session.getMapper(UserMapper.class);
			User user = u_mapper.selectById(user_id);
			user.setLastConnectTime(new Date(System.currentTimeMillis()));
			u_mapper.updateUser(user);
			session.commit();
			return true;
		}catch(Exception e){
			session.rollback();
			return false;
		}
	}
	
	public static boolean updateRoomInfo(SqlSession session, Room_Info room){
		try{
			Room_InfoMapper r_mapper = session.getMapper(Room_InfoMapper.class);
			r_mapper.updateRoomInfo(room);
			session.commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			session.rollback();
			return false;
		}
	}
	
	public static Battle_Info createBattle(SqlSession session, Room_Info room, String battle_name){
		Battle_InfoMapper b_mapper = session.getMapper(Battle_InfoMapper.class);
		UserMapper u_mapper = session.getMapper(UserMapper.class);
		Battle_Info battle = new Battle_Info();
		try{
			battle.setBattle_name(battle_name);
			battle.setType(room.getGameType());
			battle.setUserCount(room.getUsercount());
			battle.setPlayMap(room.getPlayMap());
			b_mapper.updateBattleInfo(battle);
			List<User> list_u = MySqlAltern.getRoomUserList(session, room.getRoomId());
			for(int i=0; i<list_u.size(); i++){
				User user = list_u.get(i);
				user.setBattle_Info(battle);
				u_mapper.updateUser(user);
			}
			session.commit();
			return battle;
		}catch(Exception e){
			e.printStackTrace();
			session.rollback();
			return null;
		}
	}
	
	public static Battle_Info getBattle(SqlSession session, int battle_id){
		Battle_InfoMapper b_mapper = session.getMapper(Battle_InfoMapper.class);
		Battle_Info battle = b_mapper.selectById(battle_id);
		return battle;
	}
	public static Dialog_Info createDialog(SqlSession session, Dialog_Info dialog){
		try{
			Dialog_InfoMapper d_mapper = session.getMapper(Dialog_InfoMapper.class);
			dialog.setCreateTime(new Date(System.currentTimeMillis()));
			d_mapper.insertDialog(dialog);
			session.commit();
			return dialog;
		}catch(Exception e){
			e.printStackTrace();
			session.rollback();
			return null;
		}
	}
	
	public static List<Base_Role> getBaseRoleList(SqlSession session){
		Base_RoleMapper b_mapper = session.getMapper(Base_RoleMapper.class);
		List<Base_Role> list = b_mapper.getBaseRoleList();
		return list;	
	}
	
	public static Base_Role getBaseRole(SqlSession session, int base_role_id){
		Base_RoleMapper b_mapper = session.getMapper(Base_RoleMapper.class);
		return b_mapper.selectById(base_role_id);
	}
	
	public static List<Map_Info> getMapList(SqlSession session){
		Map_InfoMapper m_mapper = session.getMapper(Map_InfoMapper.class);
		List<Map_Info> list = m_mapper.getMapList();
		return list;	
	}
	
	public static List<Skill_Info> getSkillList(SqlSession session){
		Skill_InfoMapper s_mapper = session.getMapper(Skill_InfoMapper.class);
		List<Skill_Info> list = s_mapper.getSkillList();
		return list;	
	}
	
	public static void insertDialog(SqlSession session, Dialog_Info dialog){
		Dialog_InfoMapper d_mapper = session.getMapper(Dialog_InfoMapper.class);
		d_mapper.insertDialog(dialog);
		session.commit();
	}
	
	public static void updateBattleInfo(SqlSession session, Battle_Info battle){
		Battle_InfoMapper b_mapper = session.getMapper(Battle_InfoMapper.class);
		b_mapper.updateBattleInfo(battle);
		session.commit();
	}
	
	public static List<Killer_Info> getAssistsByTarget(SqlSession session, Battle_Info battle, Role_Info target, Date lastTime){
		Killer_InfoMapper k_mapper = session.getMapper(Killer_InfoMapper.class);
		return k_mapper.getAssistDataByTarget(battle.getBattle_id(), target.getRoleId(), lastTime);
	}
	
	public static List<Killer_Info> getAllKillerData(SqlSession session, Date lastTime, Battle_Info battle){
		Killer_InfoMapper k_mapper = session.getMapper(Killer_InfoMapper.class);
		return k_mapper.getAllKillerData(battle.getBattle_id(), lastTime);
	}
	
	public static List<Killer_Info> getAllKillerDataByKiller(SqlSession session, Battle_Info battle, Role_Info role, Date lastTime){
		Killer_InfoMapper k_mapper = session.getMapper(Killer_InfoMapper.class);
		return k_mapper.getAllKillerDataByKiller(battle.getBattle_id(), role.getRoleId(), lastTime);
	}
	
	public static List<Killer_Info> getAllKillerDataByAssists(SqlSession session, Battle_Info battle, Role_Info role, Date lastTime){
		Killer_InfoMapper k_mapper = session.getMapper(Killer_InfoMapper.class);
		return k_mapper.getAllAssistDataByAssist(battle.getBattle_id(), role.getRoleId(), lastTime);
	}
	
	public static Role_Info createRole(SqlSession session, int base_role_id){
		Base_RoleMapper b_mapper = session.getMapper(Base_RoleMapper.class);
		Role_InfoMapper r_mapper = session.getMapper(Role_InfoMapper.class);
		Base_Role base = b_mapper.selectById(base_role_id);
		if(base == null){
			return null;
		}
		Role_Info role = new Role_Info(base);
		r_mapper.insertRoleInfo(role);
		session.commit();
		return role;
	}
	
	public static Role_Info createRole(SqlSession session, Role_Info role){
		Role_InfoMapper r_mapper = session.getMapper(Role_InfoMapper.class);
		r_mapper.insertRoleInfo(role);
		session.commit();
		return role;
	}
	
	public static Skill_Info selectSkill(SqlSession session, int skill_id){
		Skill_InfoMapper s_mapper = session.getMapper(Skill_InfoMapper.class);
		Skill_Info skill = s_mapper.selectById(skill_id);
		return skill;
	}
	
	public static Dialog_Info selectDialog(SqlSession session, int dialog_id){
		Dialog_InfoMapper d_mapper = session.getMapper(Dialog_InfoMapper.class);
		return d_mapper.selectById(dialog_id);
	}
	
	public static int updateDialog(SqlSession session, Dialog_Info dialog){
		Dialog_InfoMapper d_mapper = session.getMapper(Dialog_InfoMapper.class);
		int count = d_mapper.updateDialog(dialog);
		session.commit();
		return count;
	}
	
	public static int deleteDialog(SqlSession session, int dialog_id){
		Dialog_InfoMapper d_mapper = session.getMapper(Dialog_InfoMapper.class);
		int count = d_mapper.deleteById(dialog_id);
		session.commit();
		return count;
	}
	
	public static int createBaseRole(SqlSession session, Base_Role base){
		Base_RoleMapper b_mapper = session.getMapper(Base_RoleMapper.class);
		int count = b_mapper.insertBaseRole(base);
		session.commit();
		return count;
	}
	
	public static int deleteBaseRole(SqlSession session, int base_role_id){
		Base_RoleMapper b_mapper = session.getMapper(Base_RoleMapper.class);
		int count = b_mapper.deleteById(base_role_id);
		session.commit();
		return count;
	}

	public static int updateBaseRole(SqlSession session, Base_Role base){
		Base_RoleMapper b_mapper = session.getMapper(Base_RoleMapper.class);
		int count = b_mapper.updateBaseRole(base);
		session.commit();
		return count;
	}
	
	public static int createKillerInfo(SqlSession session, Killer_Info info){
		Killer_InfoMapper k_mapper = session.getMapper(Killer_InfoMapper.class);
		int count = k_mapper.insertKillerInfo(info);
		session.commit();
		return count;
	}
	
	public static List<User> getBattleUserList(SqlSession session, Integer battle_id){
		Battle_InfoMapper b_mapper = session.getMapper(Battle_InfoMapper.class);
		return b_mapper.getUserList(battle_id);
	}
	
	public static List<Dialog_Info> getBattleDialogList(SqlSession session, Integer battle_id){
		Battle_InfoMapper b_mapper = session.getMapper(Battle_InfoMapper.class);
		return b_mapper.getDialogList(battle_id);
	}
	
	public static List<User> getRoomUserList(SqlSession session, Integer room_id){
		Room_InfoMapper r_mapper = session.getMapper(Room_InfoMapper.class);
		return r_mapper.getRoomUserList(room_id);
	}
	
	public static List<Dialog_Info> getRoomDialogList(SqlSession session, Integer room_id){
		Room_InfoMapper r_mapper = session.getMapper(Room_InfoMapper.class);
		return r_mapper.getRoomDialogList(room_id);
	}
	
	public static void DeleteAllUserData(SqlSession session){
		UserMapper u_mapper = session.getMapper(UserMapper.class);
		u_mapper.deleteAllData();
		session.commit();
	}
	
	public static void DeleteAllRoleData(SqlSession session){
		Role_InfoMapper r_mapper = session.getMapper(Role_InfoMapper.class);
		r_mapper.deleteAllData();
		session.commit();
	}
}
