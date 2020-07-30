package net.shockado.packetlib.protocol.packet;

import net.shockado.packetlib.protocol.PacketBuffer;
import net.shockado.packetlib.protocol.exception.DecoderException;
import net.shockado.packetlib.protocol.exception.EncoderException;

public abstract class Packet {
    public abstract void encode(PacketBuffer buf) throws EncoderException;

    public abstract void decode(PacketBuffer buf) throws DecoderException;
}