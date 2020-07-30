package net.shockado.packetlib.protocol.packet;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class PacketRegistry {
    private static PacketRegistry instance;
    private HashMap<Integer, Class<? extends Packet>> packets = new HashMap<>();

    private PacketRegistry() {
    }

    public static PacketRegistry getInstance() {
        if (instance == null) {
            instance = new PacketRegistry();
        }
        return instance;
    }

    public void registerPacket(Class<? extends Packet> packet) {
        if (packets.containsValue(packet)) return;
        packets.put(getNextId(), packet);
    }

    public void registerPackets(Class<? extends Packet>... packets) {
        for (Class<? extends Packet> packet : packets) {
            registerPacket(packet);
        }
    }

    @Nullable
    public Class<? extends Packet> getPacketClass(int id) {
        return packets.get(id);
    }

    @Nullable
    public Integer getPacketId(Packet packet) {
        for (Map.Entry<Integer, Class<? extends Packet>> entry : packets.entrySet()) {
            if (entry.getValue().equals(packet.getClass())) return entry.getKey();
        }

        return null;
    }

    public HashMap<Integer, Class<? extends Packet>> getPackets() {
        return packets;
    }

    private int getNextId() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            if (!packets.containsKey(i)) return i;
        }
        return -1;
    }
}
