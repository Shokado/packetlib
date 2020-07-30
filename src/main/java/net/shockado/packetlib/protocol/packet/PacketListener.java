package net.shockado.packetlib.protocol.packet;

import io.netty.channel.Channel;
import net.shockado.packetlib.protocol.exception.ProcessException;


public interface PacketListener {
    void processPacket(Packet packet, Channel channel) throws ProcessException;
}
