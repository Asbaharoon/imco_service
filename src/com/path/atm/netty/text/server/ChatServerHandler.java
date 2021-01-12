package com.path.atm.netty.text.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.DefaultEventExecutor;

public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static final ChannelGroup channels = new DefaultChannelGroup(new DefaultEventExecutor());

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();

        incoming.writeAndFlush("[SERVER] - " + "WELCOME TO THIS SIMPLE CHAT APP!\r\n");

        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " has joined\r\n");
        }
        channels.add(incoming);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + "has left\r\n");
        }
        channels.remove(incoming);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        Channel incoming = channelHandlerContext.channel();
        if(!"".equals(s))
        {
        	for (Channel channel : channels) {
            	System.out.println("[" + incoming.remoteAddress() + "] " + s + "");
                if (channel != incoming) {
                    channel.writeAndFlush("[" + incoming.remoteAddress() + "] " + s + "");
                } else {
                    channel.writeAndFlush("[you] " + s + "");
                }
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
