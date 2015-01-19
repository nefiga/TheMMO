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
        PacketType type = PacketType.getType(stringData.substring(0, 2));

        switch (type) {
            case LOG_IN:

                break;
            case LOG_OUT:

                break;
            case MOVE:

                break;
            case MESSAGE:
                game.addMessage(stringData.substring(2));
                break;
        }
    }

    public static void sendPacket(Packet packet) {
        client.sendData(packet.getPacketData());
    }
}
