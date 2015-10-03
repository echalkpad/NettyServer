package com.game.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

//http://www.oschina.net/question/139577_146101
public class HeartBeatHandler extends ChannelInboundHandlerAdapter{
	
	private static final ByteBuf HEARTBEAT_SEQUENCE = Unpooled.
			unreleasableBuffer(Unpooled.copiedBuffer("HEARTBEAT", CharsetUtil.UTF_8));
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
            throws Exception {
		if(evt instanceof IdleStateEvent){
			IdleStateEvent event = (IdleStateEvent)evt;
			if(event.state().equals(IdleState.READER_IDLE)){
				System.out.println("READER_IDLE");
				NettyChannelMap.remove(ctx.channel());
				ctx.writeAndFlush(HEARTBEAT_SEQUENCE.duplicate()).addListener(ChannelFutureListener.CLOSE);
			}else if(event.state().equals(IdleState.WRITER_IDLE)){
				System.out.println("WRITER_IDLE");
			}else if(event.state().equals(IdleState.ALL_IDLE)){
				System.out.println("ALL_IDLE");
				NettyChannelMap.remove(ctx.channel());
				ctx.writeAndFlush(HEARTBEAT_SEQUENCE.duplicate()).addListener(ChannelFutureListener.CLOSE);
			}
		}else{
			super.userEventTriggered(ctx, evt);
		}
	}
}
