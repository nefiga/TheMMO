package gui;

import game.Screen;
import graphics.SpriteSheet;

public class EditText extends GUIComponent{

    private final int TILE_SIZE = 8;
    private int[] corner, side, middle;

    public EditText(int x, int y, int width, int height) {
        super(x, y, width * 8, height * 8);
        corner = SpriteSheet.spriteSheetOne.getImage(0, 16, 8, 8);
        side = SpriteSheet.spriteSheetOne.getImage(8, 16, 8, 8);
        middle = SpriteSheet.spriteSheetOne.getImage(16, 16, 8, 8);
    }

    @Override
    public void render(Screen screen) {
        screen.renderOnScreen(corner, x - 15, y, TILE_SIZE, TILE_SIZE);
        screen.renderOnScreen(corner, x, y, TILE_SIZE, TILE_SIZE, Screen.HORIZONTAL_180);
        screen.renderOnScreen(corner, x + 15, y, TILE_SIZE, TILE_SIZE, Screen.VERTICAL_180);
        screen.renderOnScreen(corner, x + 30, y, TILE_SIZE, TILE_SIZE, Screen.BOTH_180);
    }
}
