package net.shockado.packetlib.protocol;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import net.shockado.packetlib.protocol.packet.Packet;
import net.shockado.packetlib.protocol.packet.PacketListener;

public abstract class NetworkHandler extends ChannelInboundHandlerAdapter {

    private final PacketListener listener;
    private ChannelHandlerContext ctx;

    public NetworkHandler(PacketListener listener) {
        this.listener = listener;
    }

    @Override
    public final void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        this.ctx = ctx;
        channelConnect(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        channelDisconnect(ctx.channel());
    }

    @Override
    public final void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);

        Packet packet = (Packet) msg;
        listener.processPacket(packet, ctx.channel());
    }

    public abstract void channelConnect(Channel channel);

    public abstract void channelDisconnect(Channel channel);


    public final void sendPacket(Packet packet) {
        ctx.writeAndFlush(packet);
    }
}