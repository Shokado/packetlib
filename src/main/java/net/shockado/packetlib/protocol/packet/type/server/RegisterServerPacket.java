package net.shockado.packetlib.protocol.packet.type.server;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.shockado.packetlib.protocol.PacketBuffer;
import net.shockado.packetlib.protocol.exception.DecoderException;
import net.shockado.packetlib.protocol.exception.EncoderException;
import net.shockado.packetlib.protocol.packet.Packet;

@Getter
@RequiredArgsConstructor
public class RegisterServerPacket extends Packet {

    @NonNull
    private String serverId;
    @NonNull
    private String ip;
    @NonNull
    private int port;
    @NonNull
    private String map;
    @NonNull
    private String status;

    @Override
    public void encode(PacketBuffer buf) throws EncoderException {
        serverId = buf.readString();
        ip = buf.readString();
        port = buf.readInt();
        map = buf.readString();
        status = buf.readString();
    }

    @Override
    public void decode(PacketBuffer buf) throws DecoderException {
        buf.writeString(serverId);
        buf.writeString(ip);
        buf.writeInt(port);
        buf.writeString(map);
        buf.writeString(status);
    }
}
