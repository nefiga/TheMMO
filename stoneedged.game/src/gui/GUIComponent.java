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

    public void addToGUI(GUI gui) {
        x = x + gui.getX();
        y = y + gui.getY();
        gui.addComponent(this);
    }

    //----------Setters-----------

    public void setFocus(boolean focus) {
        hasFocus = focus;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    //------------Getters------------

    public boolean hasFocus() {
        return hasFocus;
    }

    public boolean isVisible() {
        return isVisible;
    }
}
