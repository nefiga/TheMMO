package network;

import game.GameLoop;
import network.packets.Packet;
import network.packets.PacketType;

public class ClientTranslator {

    GameLoop game;
    private static Client client;

    public ClientTranslator(GameLoop game, Client client) {
        this.game = game;
        ClientTranslator.client = client;
    }

    public void translatePacket(byte[] data) {
        String stringData = new String(data);
        String[] strings = stringData.split(Packet.SEPARATOR);
        PacketType type = PacketType.getType(strings[0]);

        switch (type) {
            case LOG_IN:

                break;
            case LOG_OUT:

                break;
            case MOVE:

                break;
            case MESSAGE:
                game.addMessage(strings[1] + ": " + strings[2]);
                break;
        }
    }

    public static void sendPacket(Packet packet) {
        client.sendData(packet.getPacketData());
    }
}
