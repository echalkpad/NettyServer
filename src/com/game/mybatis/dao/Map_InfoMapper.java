package com.game.mybatis.dao;

import com.game.mybatis.model.Map_Info;

public interface Map_InfoMapper {
    int deleteById(Integer id);

    int insertMapInfo(Map_Info info);

    Map_Info selectById(Integer id);

    int updateMapInfo(Map_Info info);
}