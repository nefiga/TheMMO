package network;


import game.GameLoop;
import network.packets.Packet00LogIn;

import java.io.IOException;
import java.net.*;

public class Client extends Thread{

    ClientTranslator translator;

    InetAddress IP;
    DatagramSocket socket;
    String userName;
    int port;

    public Client(GameLoop game, String userName, String ipAddress, int port) {
        translator = new ClientTranslator(game, this);
        this.userName = userName;

        try {
            socket = new DatagramSocket();
            IP = InetAddress.getByName(ipAddress);
            this.port = port;
            sendData(new Packet00LogIn(userName).getPacketData());
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
        System.out.println("Sending Data");
        DatagramPacket packet = new DatagramPacket(data, data.length, IP, port);

        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
