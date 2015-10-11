package com.game.mybatis.dao;

import java.util.List;

import com.game.mybatis.model.Dialog_Info;
import com.game.mybatis.model.Room_Info;
import com.game.mybatis.model.User;

public interface Room_InfoMapper {
    int deleteById(Integer id);

    int insertRoomInfo(Room_Info room);

    Room_Info selectById(Integer id);

    int updateRoomInfo(Room_Info room);
    
    List<Room_Info> getWaitRoom(int maxCount);
    
    void deleteAllData();
    
    List<User> getRoomUserList(Integer room_id);
    
    List<Dialog_Info> getRoomDialogList(Integer room_id);
}