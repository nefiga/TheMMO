package network.packets;

public class Packet00LogIn extends Packet{

    String userName;

    public Packet00LogIn(String userName) {
        super(PacketType.LOG_IN);
        this.userName = userName;
    }

    @Override
    public byte[] getPacketData() {
        return (type.toString() + userName).getBytes();
    }
}
