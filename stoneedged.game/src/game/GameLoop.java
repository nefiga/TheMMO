package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class GameLoop extends Canvas implements Runnable {

    public static final int WIDTH = 300;
    public static final int HEIGHT = WIDTH / 16 * 9;
    public static final int SCALE = 3;

    JFrame frame;
    JTextField textField;
    Thread gameThread;
    Screen screen;

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

    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        screen.clearScreen(120511);
        int[] screenPixels = screen.getPixels();
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screenPixels[i];
        }

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }

    public void start() {
        gameThread = new Thread(this, "Game Thread");
        gameThread.start();
        running = true;
    }

    public void startClient(String userName, String ip) {

    }

    public void startServer() {

    }

    public static void main(String[] args) {
        GameLoop game = new GameLoop();
        game.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.frame.setResizable(false);
        game.frame.add(game);
        game.frame.pack();
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);
        game.start();
    }
}
