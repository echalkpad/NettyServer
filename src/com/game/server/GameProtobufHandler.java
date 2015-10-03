package com.game.server;

import com.game.mybatis.session.UserEvent;
import com.game.proto.CMD;
import com.game.proto.ProtobufRequest;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class GameProtobufHandler extends ChannelInboundHandlerAdapter{
	
	@Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();
        System.out.println("Client:"+incoming.remoteAddress() +"加入");
    }
	
	@Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();
        System.out.println("Client:"+incoming.remoteAddress() +"离开");
    }
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ProtobufRequest.protobufRequest req = (ProtobufRequest.protobufRequest)msg;
		if(req.getCmd() == CMD.USER_LOGIN){
			NettyChannelMap.add(ctx.channel().remoteAddress().toString(), ctx.channel());
		}
		UserEvent event = new UserEvent();
		event.setChannel(ctx.channel());
		ctx.writeAndFlush(event.dealWithProtoBuf(req));
	}   
	
	//这个触发的条件是发送数据或接受数据的时候发生异常
	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("Unexpected exception from downstream." +  cause);
		NettyChannelMap.remove(ctx.channel());
        ctx.writeAndFlush("exceptionCaught").addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
    }
	
}