package org.poweredrails.rails.net.packets;

import org.poweredrails.rails.net.handlers.HandlerRegistry;

public class TestPacket implements Packet {

    private int foo;

    @Override
    public void fromBuffer(Buffer buf) {

    }

    @Override
    public Buffer toBuffer() {
        return null;
    }

    @Override
    public void handle(HandlerRegistry registry) {

    }

}
