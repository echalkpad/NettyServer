package com.game.server;


import java.util.concurrent.TimeUnit;

import com.game.msg.SubscribeReqProto;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.timeout.IdleStateHandler;

public class ProtoBufServerInitializer  extends ChannelInitializer<SocketChannel>{
	@Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("idleState", new IdleStateHandler(0, 0, 30, TimeUnit.SECONDS))
        	//.addLast("frameDecoder", new ProtobufVarint32FrameDecoder())	
        	.addLast("decoder", new ProtobufDecoder(SubscribeReqProto.SubscribeReq.getDefaultInstance()))
        	//.addLast(new ProtobufVarint32LengthFieldPrepender())
			.addLast("encoder", new ProtobufEncoder())
			.addLast("heart_handler", new HeartBeatHandler())
			.addLast("protobuf_handler", new ProtoBufServerHandler());
	}
}
