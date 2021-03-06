package network.packets;

public abstract class Packet {

    public static final String SEPARATOR = ",";

    static String userName;

    protected PacketType type;

    public Packet(PacketType type) {
        this.type = type;
    }

    public PacketType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.toString();
    }

    public abstract byte[] getPacketData();
}
