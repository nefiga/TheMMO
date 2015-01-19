package network.packets;

public class Packet01Message extends Packet{

    String message;

    public Packet01Message(String message) {
        super(PacketType.MESSAGE);
        this.message = message;
    }

    @Override
    public byte[] getPacketData() {
        return (type.toString() + SEPARATOR +  userName + SEPARATOR +message).getBytes();
    }
}
