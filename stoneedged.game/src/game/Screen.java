package game;

import graphics.ImageUtils;

public class Screen {

    private final int WIDTH, HEIGHT;
    private int[] pixels;

    public Screen(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
        pixels = new int[WIDTH * HEIGHT];
    }

    public void clearScreen(int color) {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = color;
        }
    }

    public void renderOnScreen(int[] image, int startX, int startY, int imageWidth, int imageHeight) {
        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {
                if (x + startX > WIDTH || x  < 0 || y + startY > HEIGHT || y < 0) continue;
                if (image[x + y * imageWidth] == ImageUtils.TRANSPARENT) continue;
                pixels[x + startX + (y + startY) * WIDTH] = image[x + y * imageWidth];
            }
        }
    }

    public int[] getPixels() {
        return pixels;
    }
}
