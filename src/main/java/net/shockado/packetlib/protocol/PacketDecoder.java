package net.shockado.packetlib.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import net.shockado.packetlib.protocol.exception.DecoderException;
import net.shockado.packetlib.protocol.exception.EncoderException;
import net.shockado.packetlib.protocol.exception.PacketException;
import net.shockado.packetlib.protocol.packet.Packet;
import net.shockado.packetlib.protocol.packet.PacketRegistry;

import java.util.List;

public class PacketDecoder extends ReplayingDecoder<Packet> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() == 0) throw new DecoderException("Packet not readable");

        PacketBuffer buf = new PacketBuffer(byteBuf);
        int id = buf.readInt();
        Class<? extends Packet> packetClass = PacketRegistry.getInstance().getPacketClass(id);

        if (packetClass == null) throw new EncoderException("Packet not registered");

        try {
            Packet packet = packetClass.newInstance();
            packet.decode(buf);
            list.add(packet);
        } catch (IllegalAccessException e) {
            throw new PacketException("Packets must have an empty constructor");
        }
    }
}