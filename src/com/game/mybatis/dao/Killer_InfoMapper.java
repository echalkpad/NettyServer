package com.game.mybatis.dao;

import java.util.Date;
import java.util.List;

import com.game.mybatis.model.Killer_Info;

public interface Killer_InfoMapper {
    int deleteById(Integer killerId);

    int insertKillerInfo(Killer_Info info);

    Killer_Info selectById(Integer killerId);

    int updateByKillerInfo(Killer_Info info);
    
    List<Killer_Info> getAllKillerData(Integer battle_id, Date lastTime);
    
    List<Killer_Info> getAllKillerDataByKiller(Integer battle_id, Integer role_id, Date lastTime);
    
    List<Killer_Info> getAllAssistDataByAssist(Integer battle_id, Integer role_id, Date lastTime);
    
    List<Killer_Info> getAssistDataByTarget(Integer battle_id, Integer target_id, Date lastTime);
    
    void deleteAllData();
}