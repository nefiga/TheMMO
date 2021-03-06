package gui;

import game.Screen;

public class GUIComponent {

    protected int width, height;
    protected int x, y;

    protected int[] image;

    protected boolean hasFocus;
    protected boolean isVisible;

    public GUIComponent(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void update() {

    }

    public void render(Screen screen) {
        if (isVisible)
            screen.renderOnScreen(image, x, y, width, height);
    }

    public boolean inBounds(int xPosition, int yPosition) {
        return xPosition > x && xPosition < x + width && yPosition > y && yPosition < y + height;
    }

    public void setParent(GUI gui) {
        x = x + gui.getX();
        y = y + gui.getY();
    }

    //----------Setters-----------

    public void setFocus(boolean focus) {
        hasFocus = focus;
        onFocusChanged(focus);
    }

    public void onFocusChanged(boolean focus) {

    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public void press(int x, int y) {
        if (inBounds(x, y ))
            onFocusChanged(true);
    }

    //------------Getters------------

    public boolean hasFocus() {
        return hasFocus;
    }

    public boolean isVisible() {
        return isVisible;
    }
}
