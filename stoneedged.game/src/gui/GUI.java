package gui;

import game.Screen;

import java.util.ArrayList;
import java.util.List;

public class GUI {

    // Should have a position and size on the screen
    private int width, height;
    private int x, y;

    // Should have a background that can be turned on or off
    private int[] background;

    private boolean isVisible;
    private boolean hasFocus;

    // should be able to hold components
    private List<GUIComponent> components = new ArrayList<GUIComponent>();

    public GUI(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void update() {
        // Should update its components
        for (GUIComponent component : components) {
            component.update();
        }
    }

    public void render(Screen screen) {
        if (isVisible)
            screen.renderOnScreen(background, x, y, width, height);

        // Should render its components
        for (GUIComponent component : components) {
            component.render(screen);
        }
    }

    public void addComponent(GUIComponent component) {
        components.add(component);
    }

    public boolean inBounds(int xPosition, int yPosition) {
        return xPosition > x && xPosition < x + width && yPosition > y && yPosition < y + height;
    }

    // Collect input and send to appropriate component

    //--------Setters---------

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public void setFocus(boolean focus) {
        hasFocus = focus;
    }

    //--------Getters---------

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean hasFocus() {
        return hasFocus;
    }

    public boolean isVisible() {
        return isVisible;
    }
}
