package com.game.mybatis.dao;

import com.game.mybatis.model.Kill_Rank;

public interface Kill_RankMapper {
    int deleteById(Integer rankId);

    int insertKillerRank(Kill_Rank info);

    Kill_Rank selectById(Integer rankId);

    int updateByKillerRank(Kill_Rank info);
    
    void deleteAllData();
}