package de.zonlykroks.networking.packets.play;

import de.zonlykroks.networking.decoder.PacketDecoder;
import de.zonlykroks.networking.packet.Packet;
import de.zonlykroks.networking.util.PacketByteBuf;

import java.util.function.Consumer;

public class S2CDisconnectPacket implements Packet {
    @Override
    public void read(PacketByteBuf in, PacketDecoder decoder, Consumer<Packet> packetWriter) throws Exception {

    }

    @Override
    public void write(PacketByteBuf out, PacketDecoder decoder) throws Exception {
        out.writeVarInt(0x1D );
        out.writeString("Play not currently implemented!!!!");
    }

    @Override
    public void apply(PacketDecoder decoder) throws Exception {

    }
}
