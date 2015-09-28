package com.game.utils;

import java.io.Reader;
import java.sql.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.game.mybatis.dao.Role_InfoMapper;
import com.game.mybatis.dao.Skill_InfoMapper;
import com.game.mybatis.dao.UserMapper;
import com.game.mybatis.model.Role_Info;
import com.game.mybatis.model.Role_Info;
import com.game.mybatis.model.Skill_Info;
import com.game.mybatis.model.User;

public class batis_Test {
	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;
	private static final int TRUE = 1;
	private static final int FALSE = 0;
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
        	
        	Role_InfoMapper mapper = session.getMapper(Role_InfoMapper.class);
        	Role_Info info = new Role_Info();
        	UserMapper u_mapper = session.getMapper(UserMapper.class);
        	User user = u_mapper.selectById(2);
        	info.setUser(user);
        	info.setOrder_id(1);
        	User user2 = u_mapper.selectById(3);
        	info.setAttact_target(user2);
        	Skill_InfoMapper sk_mapper = session.getMapper(Skill_InfoMapper.class);
        	Skill_Info skill = sk_mapper.selectById(1);
        	info.setCastSkill(skill);
//        	info.setCastSkillTarget(user2);
//        	info.setLastUpdateAttackTime(new Date(0));
//        	info.setLastUpdatePos_Time(new Date(0));
//        	info.setSkill1(skill);
//        	info.setSkill2(skill);
//        	info.setSkill3(skill);
//        	info.setSkill4(skill);
        	mapper.insertRoleInfo(info);
        	session.commit();
        	
//        	UserMapper mapper = session.getMapper(UserMapper.class);
//        	User user = mapper.selectById(2);
//        	System.out.println(user.getGame_Info().getGameId());
        } finally {
        	session.close();
        }
    }
}
