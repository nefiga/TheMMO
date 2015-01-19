package game;

import gui.EditText;
import gui.GUI;
import gui.TextBox;
import network.Client;
import network.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class GameLoop extends Canvas implements Runnable {

    public static final int WIDTH = 600;
    public static final int HEIGHT = WIDTH / 16 * 9;
    public static final int SCALE = 2;

    Server server;
    Client client;

    JFrame frame;
    Thread gameThread;
    Screen screen;
    Input input;
    TextBox chatView;
    EditText chat;
    GUI chatGUI;

    boolean running;
    Dimension size;
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public GameLoop() {
        size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
        setSize(size);
        frame = new JFrame();
        init();
    }

    public void init() {
        screen = new Screen(WIDTH, HEIGHT);
        input = new Input();
        frame.addKeyListener(input);
        frame.addMouseListener(input);

        chatView = new TextBox(5, 20, 200, 10);
        chat = new EditText(5, 5, 200, 100);
        chatGUI = new GUI(0, 0, 200, 30);
        chatGUI.addComponent(chatView);
        chatGUI.addComponent(chat);
        chat.setFocus(true);
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        double ns = 1000000000.0 / 60.0;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;

        while (running) {
            long currentTime = System.nanoTime();
            delta += (currentTime - startTime) / ns;
            startTime = currentTime;

            if (delta > 1) {
                update();
                updates++;
                delta--;
            }

            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frame.setTitle("UPS " + updates + "  FPS " + frames);
                updates = 0;
                frames = 0;
            }
        }
    }

    public void update() {
        chatGUI.update();
    }
    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        screen.clearScreen(230851);

        chatGUI.render(screen);

        int[] screenPixels = screen.getPixels();
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screenPixels[i];
        }

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }

    public void addMessage(String message) {
        chatView.setText(message);
    }

    public void start() {
        gameThread = new Thread(this, "Game Thread");
        gameThread.start();
        running = true;

        startClient("Nefiga", "192.168.1.3");
    }

    public void startClient(String userName, String ip) {
        client = new Client(this, userName, ip, 1351);
        client.start();
    }

    public void startServer() {
        server = new Server(this);
        server.start();
    }

    public static void main(String[] args) {
        GameLoop game = new GameLoop();
        game.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.frame.setResizable(false);
        game.frame.add(game);
        game.frame.pack();
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);
        if (JOptionPane.showConfirmDialog(game, "Start Sever?") == 0) {
            game.startServer();
        }
        game.start();
    }
}
