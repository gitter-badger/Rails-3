/*
 * This file is a part of the multiplayer platform Powered Rails, licensed under the MIT License (MIT).
 *
 * Copyright (c) Powered Rails
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.poweredrails.rails.net.channel;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.poweredrails.rails.net.handler.HandlerRegistry;
import org.poweredrails.rails.net.packet.PacketDecoder;
import org.poweredrails.rails.net.packet.PacketEncoder;
import org.poweredrails.rails.net.packet.PacketHandler;
import org.poweredrails.rails.net.packet.PacketRegistry;

import java.util.logging.Logger;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final Logger logger;

    private final PacketRegistry packetRegistry;
    private final HandlerRegistry handlerRegistry;

    /**
     * <p>
     *     Create a new server channel initializer, injecting the packet and handler registries.
     * </p>
     *
     * @param logger An instance of the server logger.
     * @param packetRegistry An instance of the packet registry.
     * @param handlerRegistry An instance of the handler registry.
     */
    public ServerChannelInitializer(Logger logger, PacketRegistry packetRegistry, HandlerRegistry handlerRegistry) {
        this.logger = logger;
        this.packetRegistry = packetRegistry;
        this.handlerRegistry = handlerRegistry;
    }

    @Override
    protected final void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pl = socketChannel.pipeline();
        pl.addLast("decoder", new PacketDecoder(this.logger, this.packetRegistry));
        pl.addLast("encoder", new PacketEncoder(this.logger));
        pl.addLast("handler", new PacketHandler(this.logger, this.handlerRegistry));
    }

}
