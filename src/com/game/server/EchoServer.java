package com.game.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

//http://www.tuicool.com/articles/rqua2iB
public class EchoServer {
	private static final int port = 8080;
	private static final String ip = "127.0.0.1";
	/**���ڷ��䴦��ҵ���̵߳��߳������ */
	protected static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors()*2;
	protected static final int BIZTHREADSIZE = 4; 
	/* 
     * NioEventLoopGroupʵ���Ͼ��Ǹ��̳߳�, 
     * NioEventLoopGroup�ں�̨������n��NioEventLoop������Channel�¼�, 
     * ÿһ��NioEventLoop������m��Channel, 
     * NioEventLoopGroup��NioEventLoop�����ﰤ��ȡ��NioEventLoop������Channel 
     */  
    private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);  
    private static final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE); 
    
	public void start() throws InterruptedException {
	    ServerBootstrap b = new ServerBootstrap();// ������������
	    try {
	      b.group(bossGroup, workerGroup);
	      //NioDatagramChannel UDP
	      b.channel(NioServerSocketChannel.class);// ����nio���͵�channel
	      b.childHandler(new ProtoBufServerInitializer());
	      b.option(ChannelOption.SO_BACKLOG, 128);
	      b.childOption(ChannelOption.SO_KEEPALIVE, true);
	      ChannelFuture f = b.bind(ip, port).sync();// ������ɣ���ʼ��server��ͨ������syncͬ����������ֱ���󶨳ɹ�
	      System.out.println(EchoServer.class.getName() + " started and listen on " + f.channel().localAddress());
	      f.channel().closeFuture().sync();// Ӧ�ó����һֱ�ȴ���ֱ��channel�ر�
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	    	shutdown();//�ر�EventLoopGroup���ͷŵ�������Դ�����������߳�
	    }
	  }
	
	  public static void main(String[] args) {
	    try {
	      new EchoServer().start();
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }
	  }
	  
	  protected void shutdown() {  
	        workerGroup.shutdownGracefully();  
	        bossGroup.shutdownGracefully();  
	    }  
}
