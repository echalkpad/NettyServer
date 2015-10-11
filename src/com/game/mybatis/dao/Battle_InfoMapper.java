package com.game.mybatis.dao;

import java.util.List;

import com.game.mybatis.model.Battle_Info;
import com.game.mybatis.model.Dialog_Info;
import com.game.mybatis.model.User;

public interface Battle_InfoMapper {
    int deleteById(Integer id);

    int insertBattleInfo(Battle_Info battle);

    Battle_Info selectById(Integer id);

    int updateBattleInfo(Battle_Info battle);
    
    void deleteAllData();
    
    List<User> getUserList(Integer battle_id);
    
    List<Dialog_Info> getDialogList(Integer battle_id);
}