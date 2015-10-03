package com.game.mybatis.dao;

import java.util.List;

import com.game.mybatis.model.Room_Info;

public interface Room_InfoMapper {
    int deleteById(Integer id);

    int insertRoomInfo(Room_Info room);

    Room_Info selectById(Integer id);

    int updateRoomInfo(Room_Info room);
    
    List<Room_Info> getWaitRoom(int maxCount);
    
}