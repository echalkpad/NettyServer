package com.game.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Sharable��ʾ�˶�����channel�乲��
 * handler�������ǵľ���ҵ����
 * */
@Sharable//ע��@Sharable����������channels�乲��
public class TestEchoServerHandler extends ChannelInboundHandlerAdapter{
	
	@Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("Client:"+incoming.remoteAddress() +"����");
        ctx.writeAndFlush("��ӭ����\n");
    }
	
	@Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("Client:"+incoming.remoteAddress() +"�뿪");
    }
	
	@Override
	 public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.print("server received data :" + msg);
        // ���ؿͻ�����Ϣ
        ctx.writeAndFlush("���Ѿ����ܵ��������Ϣ:"+msg);
    } 
	
	@Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) { 
    	System.out.println("Unexpected exception from downstream." +  cause);  
        ctx.close();//�����쳣ʱ�ر�channel 
    } 	
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       
   }
    
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        ctx.fireUserEventTriggered(evt);
    }
}
