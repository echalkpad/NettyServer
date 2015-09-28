package com.game.server;

import com.game.msg.SubscribeReqProto;
import com.game.msg.SubscribeRespProto;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ProtoBufServerHandler extends ChannelInboundHandlerAdapter{
	
	@Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // NOOP
		Channel incoming = ctx.channel();
        System.out.println("Client:"+incoming.remoteAddress() +"加入");
    }
	
	@Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // NOOP
		Channel incoming = ctx.channel();
        System.out.println("Client:"+incoming.remoteAddress() +"离开");
    }
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq)msg;
		System.out.println("SubReqServerHandler channelRead:"+ req.getUserName());
		System.out.println("service accept client subscribe req:["+ req +"]");
		ctx.writeAndFlush(resp(req.getSubReqID()));
		ByteBuf buf = Unpooled.buffer(256);
		ByteBuf dirBuf = Unpooled.directBuffer(256);
		CompositeByteBuf compBuf = Unpooled.compositeBuffer(256);
		compBuf.addComponents(buf, dirBuf);
		
	}   
	
	//这个触发的条件是发送数据或接受数据的时候发生异常
	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("Unexpected exception from downstream." +  cause);
        ctx.writeAndFlush("exceptionCaught").addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
    }
	
	private SubscribeRespProto.SubscribeResp resp(int subReqID){
		SubscribeRespProto.SubscribeResp.Builder builder = SubscribeRespProto.SubscribeResp.newBuilder();
		builder.setSubReqID(subReqID);
		builder.setRespCode("0");
		builder.setDesc("Netty book order succeed, 3 days later, sent to the designated address");
		return builder.build();
	}
}
