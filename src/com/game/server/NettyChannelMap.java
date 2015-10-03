package com.game.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.Channel;

public class NettyChannelMap {
	private static Map<String, Channel> map = new ConcurrentHashMap<String, Channel>();
	public static void add(String clientid, Channel socketChannel){
		map.put(clientid, socketChannel);
	}
	public static Channel get(String clientId){
		return map.get(clientId);
	}
	public static void remove(Channel channel){
		for(Map.Entry entry : map.entrySet()){
			if(entry.getValue() == channel){
				map.remove(entry.getKey());
			}
		}
	}
	
	public static void clear(){
		map.clear();
	}
}
