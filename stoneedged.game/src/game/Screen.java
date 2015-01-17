package game;

import graphics.ImageUtils;

public class Screen {

    public static final int ROTATION_NONE = 0, HORIZONTAL_180 = 1, VERTICAL_180 = 2, BOTH_180 = 3;

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
        renderOnScreen(image, startX, startY, imageWidth, imageHeight, ROTATION_NONE);
    }

    public void renderOnScreen(int[] image, int startX, int startY, int imageWidth, int imageHeight, int offsetX, int offsetY) {
        renderOnScreen(image, startX, startY, imageWidth, imageHeight, ROTATION_NONE, offsetX, offsetY);
    }

    public void renderOnScreen(int[] image, int startX, int startY, int imageWidth, int imageHeight, int rotation) {
        renderOnScreen(image, startX, startY, imageWidth, imageHeight, rotation, 0, 0);
    }

    public void renderOnScreen(int[] image, int startX, int startY, int imageWidth, int imageHeight, int rotation, int offsetX, int offsetY) {
        switch (rotation) {
            case ROTATION_NONE:
                for (int y = 0; y < imageHeight; y++) {
                    for (int x = 0; x < imageWidth; x++) {
                        if (x + startX > WIDTH || y + startY > HEIGHT) continue;
                        if (image[x + y * imageWidth] == ImageUtils.TRANSPARENT) continue;
                        pixels[x + startX + (y + startY) * WIDTH] = image[x + y * imageWidth];
                    }
                }
                break;
            case HORIZONTAL_180:
                for (int y = 0; y < imageHeight; y++) {
                    for (int x = 0; x < imageWidth; x++) {
                        int rotate = imageWidth - 1 - x;
                        if (x + startX > WIDTH || y + startY > HEIGHT) continue;
                        if (image[rotate + y * imageWidth] == ImageUtils.TRANSPARENT) continue;
                        pixels[x + startX + (y + startY) * WIDTH] = image[rotate + y * imageWidth];
                    }
                }
                break;
            case VERTICAL_180:
                for (int y = 0; y < imageHeight; y++) {
                    for (int x = 0; x < imageWidth; x++) {
                        int rotate = imageHeight - 1 - y;
                        if (x + startX > WIDTH || y + startY > HEIGHT) continue;
                        if (image[x + rotate * imageWidth] == ImageUtils.TRANSPARENT) continue;
                        pixels[x + startX + (y + startY) * WIDTH] = image[x + rotate * imageWidth];
                    }
                }
                break;
            case BOTH_180:
                for (int y = 0; y < imageHeight; y++) {
                    for (int x = 0; x < imageWidth; x++) {
                        int rotateX = imageWidth - 1 - x;
                        int rotateY = imageHeight - 1 - y;
                        if (x + startX > WIDTH || y + startY > HEIGHT) continue;
                        if (image[rotateX + rotateY * imageWidth] == ImageUtils.TRANSPARENT) continue;
                        pixels[x + startX + (y + startY) * WIDTH] = image[rotateX + rotateY * imageWidth];
                    }
                }
                break;
        }
    }

    public int[] getPixels() {
        return pixels;
    }
}
