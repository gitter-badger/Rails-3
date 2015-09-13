package org.poweredrails.rails.net.packets;

import io.netty.buffer.ByteBuf;

public class Buffer {

    private ByteBuf buf;

    public Buffer(ByteBuf buf) {
        this.buf = buf;
    }

    public ByteBuf getByteBuf() {
        return buf;
    }

    public int readInt() {
        return buf.readInt();
    }

    public long readLong() {
        return buf.readLong();
    }

    public short readShort() {
        return buf.readShort();
    }

    public int readUnsignedShort() {
        return buf.readUnsignedShort();
    }

    public byte readByte() {
        return buf.readByte();
    }

    public short readUnsignedByte() {
        return buf.readUnsignedByte();
    }

    public String readString() {
        int length = buf.readInt();

        byte[] array = new byte[length];
        buf.readBytes(array, 0, length);

        return new String(array);
    }

    public int readVarInt() {
        int result = 0;
        int count = 0;
        while (true) {
            byte in = readByte();
            result |= (in & 0x7f) << (count++ * 7);
            if (count > 5) {
                throw new RuntimeException("VarInt byte count > 5");
            }
            if ((in & 0x80) != 0x80) {
                break;
            }
        }
        return result;
    }

}
