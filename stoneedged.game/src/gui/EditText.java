package gui;

import game.Input;
import game.Screen;
import graphics.Font;
import graphics.SpriteSheet;
import network.ClientTranslator;
import network.packets.Packet01Message;

import java.awt.event.KeyEvent;

public class EditText extends GUIComponent implements Input.TypingListener{

    private final int TILE_SIZE = 8;
    private int[] corner, side, middle;

    private StringBuilder text = new StringBuilder("");

    public EditText(int x, int y, int width, int height) {
        super(x, y, width * 8, height * 8);
        corner = SpriteSheet.spriteSheetOne.getImage(0, 16, 8, 8);
        side = SpriteSheet.spriteSheetOne.getImage(8, 16, 8, 8);
        middle = SpriteSheet.spriteSheetOne.getImage(16, 16, 8, 8);
    }

    @Override
    public void render(Screen screen) {
        Font.drawString(screen, text.toString(), x, y);
    }

    public void onFocusChanged(boolean focus) {
        if (focus)
            Input.setTypingListener(this);
    }
    
    public void backSpace() {
        if (text.length() > 0)
            text.deleteCharAt(text.length() - 1);
    }

    public void postText(String text) {
        ClientTranslator.sendPacket(new Packet01Message(text));
    }

    @Override
    public void onKeyPressed(char character) {
        if (hasFocus) {
            if (character == KeyEvent.VK_BACK_SPACE)
                backSpace();
            else if (character == KeyEvent.VK_ENTER)
                postText(text.toString());
            else
                text.append(character);

        }
    }

    @Override
    public void onKeyHolding(char character) {

    }
}
