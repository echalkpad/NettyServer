package com.game.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;

public class NettyChannelMap {
	private static Map<String, SocketChannel> map = new ConcurrentHashMap<String, SocketChannel>();
	public static void add(String clientid, SocketChannel socketChannel){
		map.put(clientid, socketChannel);
	}
	public static Channel get(String clientId){
		return map.get(clientId);
	}
	public static void remove(SocketChannel socketChannel){
		for(Map.Entry entry : map.entrySet()){
			if(entry.getValue() == socketChannel){
				map.remove(entry.getKey());
			}
		}
	}
}
