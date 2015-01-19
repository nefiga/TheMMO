package graphics;

import game.Screen;

public class Font {

    public static final int fontSize = 8;
    public static final int fontSheetWidth = 296;
    public static final int fontGap = 9;
    public static final int fontCount = 37;
    public static final int[] alphabet = SpriteSheet.spriteSheetOne.getImage(0, 0, fontSheetWidth, fontSize);
    public static int[][] image = new int[fontCount][fontSize * fontSize];

    static {
        for (int i = 0; i < fontCount; i++) {
            for (int y = 0; y < fontSize; y++) {
                for (int x = 0; x < fontSize; x++) {
                    image[i][x + y * fontSize] = alphabet[(x + fontSize * i) + y * fontSheetWidth];
                }
            }
        }
    }

    public static void drawString(Screen screen, String text, int startX, int startY) {
        char[] chars = text.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            int[] pixels = getCharImage(chars[i]);
            if (pixels != null)
                screen.renderOnScreen(pixels, startX  + i * fontGap, startY, fontSize,  fontSize);
        }
    }

    public static int[] getCharImage(int character) {

        // 0 - 9
        if (character > 47 && character < 58)
            return image[character - 48];
        // upper case a - z
        else if (character > 64 && character < 91)
            return image[character - 55];
        // lower case a - x
        else if (character > 96 && character < 123)
            return image[character - 87];
        else if (character == 32)
            return image[36];

        return null;
    }
}
