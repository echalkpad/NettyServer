package com.game.mybatis.session;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.game.mybatis.dao.UserMapper;
import com.game.mybatis.model.User;

public class SessionManager {
	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;
	
	static{
		try{
			reader = Resources.getResourceAsReader("configuration.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception e){
            e.printStackTrace();
        }
	}
	
	public static SqlSessionFactory getSession(){
        return sqlSessionFactory;
    }
	
	public static void main(String[] args) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
        	
        	UserMapper userMapper = session.getMapper(UserMapper.class);
        	User user = userMapper.selectById(5);
        	System.out.println(user.getUsername());
//        	Game_InfoMapper mapper = session.getMapper(Game_InfoMapper.class);
//        	Game_Info team = mapper.selectByPrimaryKey(3);
//        	List<User> lists = team.getUsersList();
//        	for(User user : lists){
//        		System.out.println(user.getUsername());
//        	}
        } finally {
        	session.close();
        }
    }
}
