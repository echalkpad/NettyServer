package com.game.utils;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.game.mybatis.dao.Battle_InfoMapper;
import com.game.mybatis.dao.Dialog_InfoMapper;
import com.game.mybatis.dao.Game_InfoMapper;
import com.game.mybatis.dao.Map_InfoMapper;
import com.game.mybatis.dao.UserMapper;
import com.game.mybatis.model.Battle_Info;
import com.game.mybatis.model.Dialog_Info;
import com.game.mybatis.model.Game_Info;
import com.game.mybatis.model.Map_Info;
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
//        	Dialog_InfoMapper mapper = session.getMapper(Dialog_InfoMapper.class);
//        	Dialog_Info dialog = mapper.selectById(1);
//        	System.out.println(dialog.getGroupId() + " " + dialog.getUser().getUsername() + dialog.getUser().getGame_Info().getGameName());
//        	UserMapper mapper = session.getMapper(UserMapper.class);
//        	User user = mapper.selectById(2);
//        	System.out.println(user.getGame_Info().getGameName() + user.getRole_Info().getOrder_id());
//        	Game_InfoMapper mapper = session.getMapper(Game_InfoMapper.class);
//        	Game_Info game = mapper.selectById(5);
////        	Map_Info map = game.getPlayMap();
//        	Dialog_Info role = game.getCurrentDialog();
////        	List<Dialog_Info> roleList = game.getDialogsList();
////        	List<User> userList = game.getUsersList();
//        	System.out.println( role.getDialog_id() );
        	
        	selectBattle(session);
        } finally {
        	session.close();
        }
    }
	
	public static void createBattle(SqlSession session){
		Battle_InfoMapper mapper = session.getMapper(Battle_InfoMapper.class);
    	Battle_Info battle = new Battle_Info();
    	battle.setBattle_name("大混战");
    	battle.setType(1);
    	battle.setUserCount(30);
    	Map_InfoMapper m_mapper = session.getMapper(Map_InfoMapper.class);
    	Map_Info map = m_mapper.selectById(1);
    	battle.setPlayMap(map);
    	Dialog_InfoMapper d_mapper = session.getMapper(Dialog_InfoMapper.class);
    	Dialog_Info dialog = d_mapper.selectById(1);
    	if(dialog == null){
    		System.out.println("dialog NULL");
    	}else{
    		System.out.println(dialog.getUser().getUsername());
    	}
    	battle.setCurrent_dialog_Info(dialog);
    	mapper.insertBattleInfo(battle);
    	session.commit();
	}
	
	public static void selectBattle(SqlSession session){
		Battle_InfoMapper mapper = session.getMapper(Battle_InfoMapper.class);
    	Battle_Info battle = mapper.selectById(4);
    	Dialog_Info dialog = battle.getCurrent_dialog_Info();
    	Map_Info map = battle.getPlayMap();
    	List<Dialog_Info> dialogList = battle.getDialogList();
    	List<User> userList = battle.getUserList();
    	System.out.println(dialogList.size() +  " " + userList.size() + userList.get(0).getUsername());
    	System.out.println(map.getMapName());
    	System.out.println(dialog.getContext());
    	session.commit();
	}
	
	//如果其中是空的会导致返回的battle是空的
	public static void updateBattle(SqlSession session){
		Battle_InfoMapper mapper = session.getMapper(Battle_InfoMapper.class);
    	Battle_Info battle = mapper.selectById(2);
    	if(battle == null){
    		System.out.println("battle NULL");
    		return;
    	}
    	battle.setBattle_name("heheheheh");
    	battle.setUserCount(20);
    	Dialog_InfoMapper d_mapper = session.getMapper(Dialog_InfoMapper.class);
    	Dialog_Info dialog = d_mapper.selectById(2);
    	battle.setCurrent_dialog_Info(dialog);
    	mapper.updateBattleInfo(battle);
    	session.commit();
	}
	
	public static void UserSetBattle(SqlSession session){
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user = mapper.selectById(2);
		Battle_InfoMapper b_mapper = session.getMapper(Battle_InfoMapper.class);
		Battle_Info battle = b_mapper.selectById(4);
		System.out.println(battle.getBattle_name());
		user.setBattle_Info(battle);
		Game_InfoMapper g_mapper = session.getMapper(Game_InfoMapper.class);
		Game_Info game = g_mapper.selectById(6);
		user.setGame_Info(game);
		mapper.updateUser(user);
		session.commit();
	}
	
	public static void updateDialog(SqlSession session){
		Dialog_InfoMapper d_mapper = session.getMapper(Dialog_InfoMapper.class);
    	Dialog_Info dialog = d_mapper.selectById(2);
    	System.out.println(dialog.getContext() + " " + dialog.getUser().getUsername());
    	dialog.setContext("hsdsdsdsddfggf");
    	dialog.setType(1);
    	d_mapper.updateDialog(dialog);
    	session.commit();
	}
	
	public static void updateGameInfo(SqlSession session){
		Game_InfoMapper g_mapper = session.getMapper(Game_InfoMapper.class);
		Game_Info game = g_mapper.selectById(3);
		List<Dialog_Info> dialogList = game.getDialogsList();
		List<User> userList = game.getUsersList();
		for(int i=0; i<userList.size(); i++){
			System.out.println(userList.get(i).getUsername());
		}
	}
}
