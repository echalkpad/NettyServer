package com.game.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class TestServerInitializer extends ChannelInitializer<SocketChannel>{
	@Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //��read��ʱ��
        pipeline.addLast("decoder", new StringDecoder());
        //��send��ʱ��
        pipeline.addLast("encoder", new StringEncoder());
        pipeline.addLast("handler", new TestEchoServerHandler());
	}
}
