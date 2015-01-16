package network;

import game.GameLoop;
import network.packets.Packet;

public class ClientTranslator {

    GameLoop game;

    public ClientTranslator(GameLoop game) {
        this.game = game;
    }

    public void translatePacket(byte[] data) {

    }

    public static void sendPacket(Packet packet) {
        byte[] data = packet.getPacketData();
    }
}
