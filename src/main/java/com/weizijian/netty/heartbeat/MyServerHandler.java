package com.weizijian.netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // 判断事件是否是空闲状态
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            String eventType = null;
            switch (event.state()) {
                case ALL_IDLE:
                    eventType = "all idle";
                    break;
                case READER_IDLE:
                    eventType = "reader idle";
                    break;
                case WRITER_IDLE:
                    eventType = "writer idle";
                    break;
            }
            System.out.println(ctx.channel().remoteAddress() + "over time event: " + eventType);
            ctx.channel().close();
        }
    }
}
