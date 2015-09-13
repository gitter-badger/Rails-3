package org.poweredrails.rails.net.packets;

import io.netty.buffer.ByteBuf;
import org.poweredrails.rails.net.handlers.HandlerRegistry;

public interface Packet {

    /**
     * <p>
     *     ...
     * </p>
     *
     * @param buf
     */
    public void fromBuffer(Buffer buf);

    /**
     * <p>
     *     ...
     * </p>
     *
     * @return
     */
    public Buffer toBuffer();

    /**
     * <p>
     *     ...
     * </p>
     *
     * @param registry
     */
    public void handle(HandlerRegistry registry);

}