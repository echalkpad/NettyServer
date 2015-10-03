package com.game.mybatis.session;

import java.util.ArrayList;
import java.util.List;

import com.game.mybatis.model.Dialog_Info;
import com.game.mybatis.model.Room_Info;
import com.game.mybatis.model.User;
import com.game.proto.CMD;
import com.game.proto.ProtobufRequest;
import com.game.proto.ProtobufResponse;

import io.netty.channel.Channel;

public class UserEvent {
	public ProtobufRequest.protobufRequest req;
	public ProtobufResponse.protobufResponse resp;
	public Channel channel;
	public ProtobufResponse.protobufResponse dealWithProtoBuf(ProtobufRequest.protobufRequest req){
		this.req = req;
		ProtobufResponse.protobufResponse.Builder builder = ProtobufResponse.protobufResponse.newBuilder();
		switch(req.getCmd()){
		case CMD.USER_REGISTER:
			createUser(builder);
			break;
		case CMD.USER_LOGIN:
			UserLogin(builder);
			break;
		case CMD.USER_IDLE:
			UserIdle(builder);
			break;
		case CMD.MAIN_REQUEST_CREATE_GAME:
			createGameRoom(builder);
			break;
		case CMD.MAIN_REQUEST_JOIN_TO_GAME:
			mainJoinGame(builder);
			break;
		case CMD.MAIN_REQUEST_GAME_LIST:
			requestGameRoomList(builder);
			break;
		case CMD.MAIN_FLUSH_GAME_LIST:
			flushGameList(builder);
			break;
		case CMD.WAITROOM_WAITTING:
			getRoomInfo(builder);
			break;
		case CMD.USER_LEAVE_WAITROOM:
			break;
		case CMD.CREATOR_LEAVE_WAITROOM:
			break;
		case CMD.CREATOR＿START_GAME:
			break;
		case CMD.WAITROOM_SPEEK:
			break;
		case CMD.EXCLUDE_USER:
			break;
		}
		resp = builder.build();
		MySqlAltern.updateUserTableConnectTime(req.getUserId());
		return resp;
	}
	
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	public void createUser(ProtobufResponse.protobufResponse.Builder builder){
		if(MySqlAltern.checkUserName(req.getMainUIRequestInfo().getRegisterInfo().getUsername())){
			builder.setCmd(CMD.USER_REGISTER);
			ProtobufRequest.registerMessageRequest register = req.getMainUIRequestInfo().getRegisterInfo();
			User user = MySqlAltern.createUser(register.getUsername(), register.getCountry(), register.getProvince(), register.getLocation());
			builder.setUserId(user.getUser_id());
			builder.setResult(true);
		}else{
			builder.setCmd(CMD.USER_REGISTER);
			builder.setResult(false);
		}
	}
	
	public void UserLogin(ProtobufResponse.protobufResponse.Builder builder){
		boolean result = MySqlAltern.userLogin(req.getUserId(), req.getMainUIRequestInfo().getLoginInfo().getLocation());
		builder.setCmd(CMD.USER_LOGIN);
		builder.setUserId(req.getUserId());
		builder.setResult(result);
	}
	
	public void UserIdle(ProtobufResponse.protobufResponse.Builder builder){
		boolean result = MySqlAltern.userIdle(req.getUserId());
		builder.setCmd(CMD.USER_IDLE);
		builder.setUserId(req.getUserId());
		builder.setResult(result);
		List<Room_Info> list = MySqlAltern.requestRoomList(UserDefine.ROOM_LIST_MAXCOUNT);
		ProtobufResponse.mainUIResponse.Builder mainUI = ProtobufResponse.mainUIResponse.newBuilder();
		mainUI.setIdleInfo(ProtobufResponseGenerator.getIdleMessageResponse(list));
		builder.setMainUIResponseInfo(mainUI);
	}
	
	public void createGameRoom(ProtobufResponse.protobufResponse.Builder builder){
		ProtobufRequest.createGameRequest roReq = req.getMainUIRequestInfo().getCreateGameInfo();
		Room_Info room = MySqlAltern.createGameRoom(roReq.getGameName(), roReq.getPlayMapId(), roReq.getPlayType());
		builder.setCmd(CMD.MAIN_REQUEST_CREATE_GAME);
		builder.setUserId(req.getUserId());
		builder.setResult(true);
		ProtobufResponse.mainUIResponse.Builder mainUI = ProtobufResponse.mainUIResponse.newBuilder();
		mainUI.setCreateGameInfo(ProtobufResponseGenerator.getCreateGameResponse(room));
		builder.setMainUIResponseInfo(mainUI);
	}
	
	public void mainJoinGame(ProtobufResponse.protobufResponse.Builder builder){
		builder.setCmd(CMD.MAIN_REQUEST_JOIN_TO_GAME);
		builder.setUserId(req.getUserId());
		ProtobufRequest.mainJoinGameRequest mjReq = req.getMainUIRequestInfo().getMainJoinGameInfo();
		boolean result = MySqlAltern.mainJoinGame(mjReq.getRoomId(), req.getUserId());
		if(result){
			List<User> list = MySqlAltern.getRoomUserList(mjReq.getRoomId());
			Room_Info room = MySqlAltern.getRoom(mjReq.getRoomId());
			ProtobufResponse.mainUIResponse.Builder mainUI = ProtobufResponse.mainUIResponse.newBuilder();
			mainUI.setMainJoinGameInfo(ProtobufResponseGenerator.getMainJoinGameResponse(room, list));
			builder.setMainUIResponseInfo(mainUI);
		}
		builder.setResult(result);
	}
	
	public void requestGameRoomList(ProtobufResponse.protobufResponse.Builder builder){
		builder.setCmd(CMD.MAIN_REQUEST_GAME_LIST);
		builder.setUserId(req.getUserId());
		builder.setResult(true);
		List<Room_Info> list = MySqlAltern.requestRoomList(UserDefine.ROOM_LIST_MAXCOUNT);
		ProtobufResponse.waitRoomResponse.Builder wrbuilder = ProtobufResponse.waitRoomResponse.newBuilder();
		wrbuilder.setGetGameListInfo(ProtobufResponseGenerator.getGetGameListResponse(list));
		builder.setWaitRoomResponseInfo(wrbuilder);
	}
	
	//当前列表的Room不会变变的是当前刷新的room的userCount
	public void flushGameList(ProtobufResponse.protobufResponse.Builder builder){
		builder.setCmd(CMD.MAIN_FLUSH_GAME_LIST);
		builder.setUserId(req.getUserId());
		builder.setResult(true);
		ProtobufRequest.flushGameListRequest fgR = req.getMainUIRequestInfo().getFlushGameListInfo();
		List<Room_Info> list = new ArrayList<Room_Info>();
		for(int i=0; i<fgR.getListCount(); i++){
			int room_id = fgR.getRoomId(i);
			Room_Info room = MySqlAltern.getRoom(room_id);
			list.add(room);
		}
		ProtobufResponse.waitRoomResponse.Builder wrBuilder = ProtobufResponse.waitRoomResponse.newBuilder();
		wrBuilder.setFlushGameListInfo(ProtobufResponseGenerator.getFlushGameListResponse(list));
		builder.setWaitRoomResponseInfo(wrBuilder);
	}
	
	public void getRoomInfo(ProtobufResponse.protobufResponse.Builder builder){
		builder.setCmd(CMD.WAITROOM_WAITTING);
		builder.setUserId(req.getUserId());
		builder.setResult(true);
		int room_id = req.getWaitRoomRequestInfo().getGetRoomListInfo().getRoomId();
		int userCount = req.getWaitRoomRequestInfo().getGetRoomListInfo().getUserCount();
		List<User> list_u = new ArrayList<User>();
		for(int i=0; i<userCount; i++){
			User user_ = new User();
			user_.setUser_id(req.getWaitRoomRequestInfo().getGetRoomListInfo().getUserInfo(i).getUserId());
			user_.setUsername(req.getWaitRoomRequestInfo().getGetRoomListInfo().getUserInfo(i).getUserName());
			list_u.add(user_);
		}
		Room_Info room = MySqlAltern.getRoom(room_id);
		User user = MySqlAltern.getUser(req.getUserId());
		long last_time = user.getLastConnectTime().getTime();
		List<Dialog_Info> dialogList = room.getDialogsList();
		ProtobufResponse.waitRoomResponse.Builder wrr = ProtobufResponse.waitRoomResponse.newBuilder();
		wrr.setGetRoomInfoInfo(ProtobufResponseGenerator.getGetRoomInfoResponse(room, dialogList,last_time, user, list_u));
		builder.setWaitRoomResponseInfo(wrr);
	}
	
	public void creatorLeaveRoom(ProtobufResponse.protobufResponse.Builder builder){
		builder.setCmd(CMD.CREATOR_LEAVE_WAITROOM);
		builder.setUserId(req.getUserId());
		builder.setResult(true);
		Room_Info room = MySqlAltern.getRoom(req.getWaitRoomRequestInfo().getCreateLeaveGameInfo().getRoomId());
		room.setIsUsed(false);
		MySqlAltern.updateRoomInfo(room);
		List<User> list = room.getUsersList();
		for(int i=0; i<list.size(); i++){
			User user = list.get(i);
			user.setRoom_Info(null);
			MySqlAltern.updateUser(user);
		}
	}
}
