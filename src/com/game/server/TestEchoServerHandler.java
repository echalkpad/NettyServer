package com.game.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Sharable表示此对象在channel间共享
 * handler类是我们的具体业务类
 * */
@Sharable//注解@Sharable可以让它在channels间共享
public class TestEchoServerHandler extends ChannelInboundHandlerAdapter{
	
	@Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("Client:"+incoming.remoteAddress() +"加入");
        ctx.writeAndFlush("欢迎加入\n");
    }
	
	@Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("Client:"+incoming.remoteAddress() +"离开");
    }
	
	@Override
	 public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.print("server received data :" + msg);
        // 返回客户端消息
        ctx.writeAndFlush("我已经接受到了你的消息:"+msg);
    } 
	
	@Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) { 
    	System.out.println("Unexpected exception from downstream." +  cause);  
        ctx.close();//出现异常时关闭channel 
    } 	
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       
   }
    
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        ctx.fireUserEventTriggered(evt);
    }
}
