package network;

import game.GameLoop;

import java.io.IOException;
import java.net.*;

public class Client extends Thread{

    ClientTranslator translator;

    InetAddress IP;
    DatagramSocket socket;
    int port;

    public Client(GameLoop game, String ipAddress, int port) {
        translator = new ClientTranslator(game);

        try {
            socket = new DatagramSocket();
            IP = InetAddress.getByName(ipAddress);
            this.port = port;
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);

        try {
            socket.receive(packet);
            translator.translatePacket(packet.getData());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendData(byte[] data) {
        DatagramPacket packet = new DatagramPacket(data, data.length, IP, port);

        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
