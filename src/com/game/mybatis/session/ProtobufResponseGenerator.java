package com.game.mybatis.session;

import java.util.ArrayList;
import java.util.List;

import com.game.mybatis.model.Dialog_Info;
import com.game.mybatis.model.Room_Info;
import com.game.mybatis.model.User;
import com.game.proto.ProtobufResponse;

public class ProtobufResponseGenerator {
	public static ProtobufResponse.waitingRoomInfo.Builder getWaitingRoomInfo(Room_Info room){
		ProtobufResponse.waitingRoomInfo.Builder info = ProtobufResponse.waitingRoomInfo.newBuilder();
		info.setRoomName(room.getRoomName());
		info.setUserCount(room.getUsercount());
		info.setPlayMapId(room.getPlayMap().getMapId());
		info.setPlayMapName(room.getPlayMap().getMapName());
		info.setMaxUserCount(room.getPlayMap().getPlayermaxcount());
		info.setCreatorId(room.getCreator().getUser_id());
		info.setCreatorName(room.getCreator().getUsername());
		return info;
	}
	
	public static ProtobufResponse.idleMessageResponse.Builder getIdleMessageResponse(List<Room_Info> list){
		ProtobufResponse.idleMessageResponse.Builder idle = ProtobufResponse.idleMessageResponse.newBuilder();
		idle.setListCount(list.size());
		for(int i=0; i<list.size(); i++){
			Room_Info room = list.get(i);
			idle.addInfo(getWaitingRoomInfo(room));
		}
		return idle;
	}
	
	public static ProtobufResponse.createGameResponse.Builder getCreateGameResponse(Room_Info room){
		ProtobufResponse.createGameResponse.Builder gameBuilder = ProtobufResponse.createGameResponse.newBuilder();
		gameBuilder.setRoomId(room.getRoomId());
		gameBuilder.setRoomName(room.getRoomName());
		return gameBuilder;
	}
	
	public static ProtobufResponse.waitingRoomUserInfo.Builder getWaitingRoomUserInfo(User user){
		ProtobufResponse.waitingRoomUserInfo.Builder wrBuilder = ProtobufResponse.waitingRoomUserInfo.newBuilder();
		wrBuilder.setUserId(user.getUser_id());
		wrBuilder.setUserName(user.getUsername());
		if(user.getRoom_Info() != null){
			wrBuilder.setRoleType(user.getRole_Info().getType());
		}
		return wrBuilder;
	}
	
	public static ProtobufResponse.mainJoinGameResponse.Builder getMainJoinGameResponse(Room_Info room, List<User> list){
		ProtobufResponse.mainJoinGameResponse.Builder mjBuilder = ProtobufResponse.mainJoinGameResponse.newBuilder();
		mjBuilder.setUserCount(room.getUsercount());
		mjBuilder.setCreatorId(room.getCreator().getUser_id());
		mjBuilder.setCreatorName(room.getCreator().getUsername());
		for(int i=0; i<list.size(); i++){
			User user = list.get(i);
			mjBuilder.addInfo(getWaitingRoomUserInfo(user));
		}
		return mjBuilder;
	}
	
	public static ProtobufResponse.getGameListResponse.Builder getGetGameListResponse(List<Room_Info> list){
		ProtobufResponse.getGameListResponse.Builder glBuilder = ProtobufResponse.getGameListResponse.newBuilder();
		for(int i=0; i<list.size(); i++){
			Room_Info room = list.get(i);
			glBuilder.addInfo(getWaitingRoomInfo(room));
		}
		glBuilder.setListCount(list.size());
		return glBuilder;
	}
	
	public static ProtobufResponse.flushGameListResponse.Builder getFlushGameListResponse(List<Room_Info> list){
		ProtobufResponse.flushGameListResponse.Builder fgBuilder = ProtobufResponse.flushGameListResponse.newBuilder();
		fgBuilder.setListCount(list.size());
		for(int i=0; i<list.size(); i++){
			Room_Info room = list.get(i);
			fgBuilder.addInfo(getWaitingRoomInfo(room));
		}
		return fgBuilder;
	}
	
	public static ProtobufResponse.dialogInfo.Builder getDialogInfo(Dialog_Info dialog){
		ProtobufResponse.dialogInfo.Builder diBuilder = ProtobufResponse.dialogInfo.newBuilder();
		diBuilder.setUserId(dialog.getUser().getUser_id());
		diBuilder.setUserName(dialog.getUser().getUsername());
		diBuilder.setType(dialog.getType());
		diBuilder.setSpeekTime(dialog.getCreateTime().getTime());
		diBuilder.setContext(dialog.getContext());
		return diBuilder;
	}
	
	public static ProtobufResponse.userBaseInfo.Builder getUserBaseInfo(User user){
		ProtobufResponse.userBaseInfo.Builder ub = ProtobufResponse.userBaseInfo.newBuilder();
		ub.setUserId(user.getUser_id());
		ub.setUserName(user.getUsername());
		return ub;
	}
	
	public static ProtobufResponse.getRoomInfoResponse.Builder getGetRoomInfoResponse(Room_Info room, List<Dialog_Info> dialogList, long userLastTime, User user, List<User> list_u){
		ProtobufResponse.getRoomInfoResponse.Builder griBuilder = ProtobufResponse.getRoomInfoResponse.newBuilder();
		for(int i=0; i<dialogList.size(); i++){
			Dialog_Info dialog = dialogList.get(i);
			if(dialog.getCreateTime().getTime() > userLastTime){
				griBuilder.addDialog(getDialogInfo(dialog));
			}
		}
		if(griBuilder.getDialogCount() > 0){
			griBuilder.setHasDialogInfo(true);
		}else{
			griBuilder.setHasDialogInfo(false);
		}
		List<User> userList = room.getUsersList();
		boolean beExcluding = true;
		for(int i=0; i<userList.size(); i++){
			User user_o = userList.get(i);
			if(user_o.getUser_id() == user.getUser_id()){
				beExcluding = false;
				break;
			}
		}
		if(room.getIsUsed() && !beExcluding){
			List<Integer> conT = new ArrayList<Integer>();
			for(int i=0; i<userList.size(); i++){
				boolean contain = false;
				for(int k = 0; k<list_u.size(); k++){
					if(list_u.get(k).getUser_id() == userList.get(i).getUser_id()){
						contain = true;
						break;
					}
				}
				if(!contain){
					griBuilder.addAddedUserinfo(getUserBaseInfo(userList.get(i)));
				}
			}
			for(int i = 0; i<list_u.size(); i++){
				boolean contain = false;
				for(int k =0; k<userList.size(); k++){
					if(list_u.get(i).getUser_id() == userList.get(k).getUser_id()){
						contain = true;
						break;
					}
				}
				if(!contain){
					griBuilder.addLeaveUserinfo(getUserBaseInfo(list_u.get(i)));
				}
			}
			griBuilder.setLeaveUserCount(griBuilder.getLeaveUserCount());
			griBuilder.setAddUserCount(griBuilder.getAddedUserinfoCount());
			griBuilder.setIsRoomColose(false);
		}else{
			griBuilder.setIsRoomColose(true);
		}
		griBuilder.setBeExcluding(beExcluding);
		return griBuilder;
	}
	
	public static ProtobufResponse.excludeUserResponse.Builder getExcludeUserResponse(Room_Info room, int exclude_id){
		ProtobufResponse.excludeUserResponse.Builder euBuilder = ProtobufResponse.excludeUserResponse.newBuilder();
		List<User> list = room.getUsersList();
		for(int i =0; i<list.size(); i++){
			User user = list.get(i);
			if(user.getUser_id() == exclude_id){
				room.setUsercount(room.getUsercount()-1);
				user.setRoom_Info(null);
				MySqlAltern.updateUser(user);
				MySqlAltern.updateRoomInfo(room);
				euBuilder.setUserListCount(room.getUsercount());
			}else{
				euBuilder.addUser(getUserBaseInfo(user));
			}
		}
		return euBuilder;
	}
}
