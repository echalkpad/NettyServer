package com.game.mybatis.session;

import org.apache.ibatis.session.SqlSession;

import com.game.mybatis.dao.UserMapper;
import com.game.mybatis.model.User;

public class UserEvent {
	
//	public User createUser(String username){
//		SqlSession session = SessionManager.getSession().openSession();
//		User result = null;
//		try{
//			UserMapper mapper = session.getMapper(UserMapper.class);
//			User user = mapper.selectByUsername(username);
//			if(user == null){
//				User newUser = new User(username);
//				mapper.createUser(newUser);
//				session.commit();
//				result = mapper.selectByUsername(username);
//			}
//		}finally {
//        	session.close();
//        }
//		return result;
//	}
//	
//	public Boolean loginUser(Integer id){
//		SqlSession session = SessionManager.getSession().openSession();
//		Boolean result = false;
//		try{
//			UserMapper mapper = session.getMapper(UserMapper.class);
//			User user = mapper.selectById(id);
//			if(user != null){
//				result = true;
//				user.setState(UserState.LOGIN);
//				mapper.updateUser(user);
//				session.commit();
//			}
//		}finally{
//			session.close();
//		}
//		if(result){
//			return true;
//		}else{
//			return false;
//		}
//		
//	}
}
