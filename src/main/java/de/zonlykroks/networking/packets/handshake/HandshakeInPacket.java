package de.zonlykroks.networking.packets;

import de.zonlykroks.Constants;
import de.zonlykroks.networking.decoder.PacketDecoder;
import de.zonlykroks.networking.packet.Packet;
import de.zonlykroks.networking.util.PacketByteBuf;

public class HandshakeInPacket implements Packet {
    @Override
    public void read(PacketByteBuf in, PacketDecoder decoder) throws Exception {
        int protocolVersion = in.readVarInt();
        String serverAddress = in.readString();

        int serverPort = in.readUnsignedShort();
        int nextState = in.readVarInt();

        String output = "Protocol Version: " + protocolVersion + "\n" +
                "Server Address: " + serverAddress + "\n" +
                "Server Port: " + serverPort + "\n" +
                "Next State: " + nextState;

        Constants.LOGGER.info(output);
    }

    @Override
    public void write(PacketByteBuf out, PacketDecoder decoder) throws Exception {

    }

    @Override
    public void apply(PacketDecoder decoder) throws Exception {

    }
}
