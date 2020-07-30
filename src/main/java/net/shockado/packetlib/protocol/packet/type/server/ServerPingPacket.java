package net.shockado.packetlib.protocol.packet.type.server;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.shockado.packetlib.protocol.PacketBuffer;
import net.shockado.packetlib.protocol.packet.Packet;

@Getter
@RequiredArgsConstructor
public class ServerPingPacket extends Packet {

    @NonNull
    private String serverId;
    @NonNull
    private String ip;
    @NonNull
    private int port;
    @NonNull
    private int online;
    @NonNull
    private String status;
    @NonNull
    private String map;

    @Override
    public void encode(PacketBuffer buf) {
        serverId = buf.readString();
        ip = buf.readString();
        port = buf.readInt();
        online = buf.readInt();
        status = buf.readString();
        map = buf.readString();
    }

    @Override
    public void decode(PacketBuffer buf) {
        buf.writeString(serverId);
        buf.writeString(ip);
        buf.writeInt(port);
        buf.writeInt(online);
        buf.writeString(status);
        buf.writeString(map);
    }
}
