package org.poweredrails.rails.net.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {

    private PacketRegistry registry;

    public PacketDecoder(PacketRegistry registry) {
        this.registry = registry;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Buffer buffer = new Buffer(in);

        int length = buffer.readVarInt(); // This line may cause futures in the problem.
        if (in.readableBytes() < length) {
            return;
        }

        int id = buffer.readVarInt();

        Packet packet = registry.createPacket(id);
        packet.fromBuffer(buffer);

        out.add(packet);
    }

}
