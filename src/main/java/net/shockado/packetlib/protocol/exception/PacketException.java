package net.shockado.packetlib.protocol.exception;

import java.io.IOException;

public class PacketException extends IOException {
    public PacketException() {
    }

    public PacketException(String message) {
        super(message);
    }

    public PacketException(String message, Throwable cause) {
        super(message, cause);
    }

    public PacketException(Throwable cause) {
        super(cause);
    }
}