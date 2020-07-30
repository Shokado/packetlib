package net.shockado.packetlib.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.shockado.packetlib.protocol.exception.EncoderException;
import net.shockado.packetlib.protocol.packet.Packet;
import net.shockado.packetlib.protocol.packet.PacketRegistry;

public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        Integer id = PacketRegistry.getInstance().getPacketId(packet);
        if (id == null) throw new EncoderException("Unregistered packet.");

        PacketBuffer buf = new PacketBuffer(byteBuf);
        buf.writeInt(id);
        packet.encode(buf);
    }
}