package graphics;

public class SpriteSheet {

    public static SpriteSheet spriteSheetOne = new SpriteSheet("sprite_sheet_1", 320, 320);

    private String spriteSheet;

    private int width, height;

    private int[] pixels;

    public SpriteSheet(String spriteSheetImage, int width, int height) {
         this.spriteSheet = spriteSheetImage;
        this.width = width;
        this.height = height;
        pixels = ImageUtils.getImage(spriteSheetImage);
    }

    public int[] getImage(int startX, int startY, int imageWidth, int imageHeight) {
        int[] returnPixels = new int[imageWidth * imageHeight];
        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {
                if (x + startX > width || y + startY > height || x < 0 || y < 0) continue;
                returnPixels[x + y * imageWidth] =  pixels[x + startX + (y + startY) * width];
            }
        }
        return returnPixels;
    }
}
