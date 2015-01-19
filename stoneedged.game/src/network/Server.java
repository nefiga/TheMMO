package network;

import game.GameLoop;
import network.packets.PacketType;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

public class Server extends Thread {

    private GameLoop game;
    private DatagramSocket socket;

    private Map<String, InetAddress> userIP = new HashMap<String, InetAddress>();
    private Map<String, Integer> userPort = new HashMap<String, Integer>();

    public Server(GameLoop game) {
        this.game = game;
        try {
            this.socket = new DatagramSocket(1351);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);

            try {
                socket.receive(packet);
                translateData(new String(packet.getData()),packet.getAddress(), packet.getPort());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void translateData(String stringData, InetAddress IP, int port) {

        String strings[] = stringData.split(",");
        switch(PacketType.getType(strings[0])) {
            case LOG_IN:
                connect(stringData, IP, port);
                break;
            case LOG_OUT:
                disconnect(stringData);
                break;
            default:
                sendToClients(stringData, strings[1]);
                break;
        }
    }

    public void sendToClients(String stringData, String excludeUser) {
        System.out.println("Server sending to client. Excluding " +  excludeUser);
        byte[] data = stringData.getBytes();
        for (String user : userIP.keySet()) {
            if (user.equals(excludeUser))
                continue;

            try {
                DatagramPacket packet = new DatagramPacket(data, data.length, userIP.get(user), userPort.get(user));
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void connect(String stringData, InetAddress IP, int port) {
        String[] rows = stringData.split(",");
        String userName = rows[1];

        userIP.put(userName, IP);
        userPort.put(userName, port);

        sendToClients(stringData, userName);
    }

    public void disconnect(String stringData) {
        String[] rows = stringData.split(",");
        String user = rows[1];

        userIP.remove(user);
        userPort.remove(user);

        sendToClients(stringData, user);
    }
}
