package game;

public class Action {

    private int key;

    private boolean pressed;
    private boolean holding;

    public Action(int key) {
        this.key = key;
    }

    public void press() {
        if (!holding) {
            pressed = true;
            holding = true;
        }
    }

    public void release() {
        pressed = false;
        holding = false;
    }

    public boolean isPressed() {
        boolean returnPressed = pressed;
        pressed = false;
        return returnPressed;
    }

    public boolean isHolding() {
        return holding;
    }

    public int getKey() {
        return key;
    }
}
