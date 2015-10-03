package com.game.mybatis.session;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.game.mybatis.dao.Map_InfoMapper;
import com.game.mybatis.dao.Room_InfoMapper;
import com.game.mybatis.dao.UserMapper;
import com.game.mybatis.model.Map_Info;
import com.game.mybatis.model.Room_Info;
import com.game.mybatis.model.User;

public class MySqlAltern {
	public static User createUser(String username, String country, String province, String location){
		SqlSession session = SessionManager.getSession().openSession();
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
	
	public static boolean checkUserName(String username)
	{
		SqlSession session = SessionManager.getSession().openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user = mapper.selectByUserName(username);
		if(user == null){
			return true;
		}
		return false;
	}
	
	public static boolean userLogin(int user_id, String location){
		SqlSession session = SessionManager.getSession().openSession();
		try{
			UserMapper mapper = session.getMapper(UserMapper.class);
			User user = mapper.selectById(user_id);
			user.setLocation(location);
			user.setLoginTime(new Date(System.currentTimeMillis()));
			user.setState(UserState.LOGIN);
			mapper.updateUser(user);
			session.commit();
			return true;
		}catch(Exception e){
			session.rollback();
			return false;
		}
	}
	
	public static boolean userIdle(int user_id){
		SqlSession session = SessionManager.getSession().openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user = mapper.selectById(user_id);
		user.setState(UserState.IDLE);
		mapper.updateUser(user);
		session.commit();
		return true;
	}
	
	public static List<Room_Info> requestRoomList(int maxCount){
		SqlSession session = SessionManager.getSession().openSession();
		Room_InfoMapper mapper = session.getMapper(Room_InfoMapper.class);
		List<Room_Info> list = mapper.getWaitRoom(maxCount);
		return list;
	}
	
	public static Room_Info createGameRoom(String gamename, int map_id, int playType){
		SqlSession session = SessionManager.getSession().openSession();
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
	
	public static boolean updateUserTableConnectTime(int user_id){
		SqlSession session = SessionManager.getSession().openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user = mapper.selectById(user_id);
		user.setLastConnectTime(new Date(System.currentTimeMillis()));
		mapper.updateUser(user);
		session.commit();
		return true;
	}
	
	public static boolean mainJoinGame(int room_id, int user_id){
		SqlSession session = SessionManager.getSession().openSession();
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
	
	public static List<User> getRoomUserList(int room_id){
		SqlSession session = SessionManager.getSession().openSession();
		Room_InfoMapper r_mapper = session.getMapper(Room_InfoMapper.class);
		Room_Info room = r_mapper.selectById(room_id);
		return room.getUsersList();
	}
	
	public static Room_Info getRoom(int room_id){
		SqlSession session = SessionManager.getSession().openSession();
		Room_InfoMapper r_mapper = session.getMapper(Room_InfoMapper.class);
		Room_Info room = r_mapper.selectById(room_id);
		return room;
	}
	
	public static User getUser(int user_id){
		SqlSession session = SessionManager.getSession().openSession();
		UserMapper u_mapper = session.getMapper(UserMapper.class);
		User user = u_mapper.selectById(user_id);
		return user;
	}
	
	public static boolean updateUser(User user){
		SqlSession session = SessionManager.getSession().openSession();
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
	
	public static boolean updateRoomInfo(Room_Info room){
		SqlSession session = SessionManager.getSession().openSession();
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
}
