package gui;

import game.Screen;
import graphics.Font;

public class TextBox extends GUIComponent{

    private String text = "This is a text field";
    private boolean hasFocusTimer;
    private int loseFocusDelay = 15 * 60;
    private int focusTimer = 0;

    public TextBox(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void update() {
        if (!hasFocus)
            focusTimer++;
    }

    public void render(Screen screen) {
        if (focusTimer < loseFocusDelay)
            Font.drawString(screen, text, x, y);
    }

    public void onFocusChanged(boolean focus) {
        focusTimer = 0;
    }
}
