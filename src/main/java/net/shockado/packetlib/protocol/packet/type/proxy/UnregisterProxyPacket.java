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
public class UnregisterProxyPacket extends Packet {

    @NonNull
    private String proxyId;

    @Override
    public void encode(PacketBuffer buf) throws EncoderException {
        proxyId = buf.readString();
    }

    @Override
    public void decode(PacketBuffer buf) throws DecoderException {
        buf.writeString(proxyId);
    }
}
