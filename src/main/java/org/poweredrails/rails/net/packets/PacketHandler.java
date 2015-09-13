package org.poweredrails.rails.net.packets;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.poweredrails.rails.net.handlers.HandlerRegistry;

public class PacketHandler extends SimpleChannelInboundHandler<Packet> {

    private HandlerRegistry registry;

    public PacketHandler(HandlerRegistry registry) {
        this.registry = registry;
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Packet packet) throws Exception {
        packet.handle(registry);
    }

}
