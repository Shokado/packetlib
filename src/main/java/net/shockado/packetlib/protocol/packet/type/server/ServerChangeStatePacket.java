package net.shockado.packetlib.protocol.packet.type.server;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.shockado.packetlib.protocol.PacketBuffer;
import net.shockado.packetlib.protocol.packet.Packet;

@Getter
@RequiredArgsConstructor
public class ServerChangeStatePacket extends Packet {

    @NonNull
    private String serverId;
    @NonNull
    private String status;
    @NonNull
    private String map;

    @Override
    public void encode(PacketBuffer buf) {
        serverId = buf.readString();
        status = buf.readString();
        map = buf.readString();
    }

    @Override
    public void decode(PacketBuffer buf) {
        buf.writeString(serverId);
        buf.writeString(status);
        buf.writeString(map);
    }
}
