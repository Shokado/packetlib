package net.shockado.packetlib.protocol.packet.type.proxy;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.shockado.packetlib.protocol.PacketBuffer;
import net.shockado.packetlib.protocol.exception.DecoderException;
import net.shockado.packetlib.protocol.exception.EncoderException;
import net.shockado.packetlib.protocol.packet.Packet;

@Getter
@RequiredArgsConstructor
public class ProxyPingPacket extends Packet {

    @NonNull
    private String proxyId;
    @NonNull
    private String ip;
    @NonNull
    private int port;
    @NonNull
    private int online;

    @Override
    public void encode(PacketBuffer buf) throws EncoderException {
        proxyId = buf.readString();
        ip = buf.readString();
        port = buf.readInt();
        online = buf.readInt();
    }

    @Override
    public void decode(PacketBuffer buf) throws DecoderException {
        buf.writeString(proxyId);
        buf.writeString(ip);
        buf.writeInt(port);
        buf.writeInt(online);
    }
}
