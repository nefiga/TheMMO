package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class Input implements KeyListener, MouseListener {

    private static KeyInputListener keyInputListener;
    private static TypingListener typingListener;
    private static MouseInputListener mouseInputListener;

    private List<Action> keyActions = new ArrayList<Action>();

    private boolean[] pressed = new boolean[128];
    private boolean[] holding = new boolean[128];
    private boolean[] mousePressed = new boolean[2];
    private boolean[] mouseHolding = new boolean[2];

    Action up = new Action(KeyEvent.VK_UP);
    Action down = new Action(KeyEvent.VK_DOWN);
    Action left = new Action(KeyEvent.VK_LEFT);
    Action right = new Action(KeyEvent.VK_RIGHT);

    public Input() {
        keyActions.add(up);
        keyActions.add(down);
        keyActions.add(left);
        keyActions.add(right);
    }

    public void update() {
        if (keyInputListener != null) {
            if (up.isPressed()) keyInputListener.onUpPressed();
            if (up.isHolding()) keyInputListener.onUpHolding();
            if (down.isPressed()) keyInputListener.onDownPressed();
            if (down.isHolding()) keyInputListener.onDownHolding();
            if (left.isPressed()) keyInputListener.onLeftPressed();
            if (left.isHolding()) keyInputListener.onLeftHolding();
            if (right.isPressed()) keyInputListener.onRightPressed();
            if (right.isHolding()) keyInputListener.onRightHolding();
        }
    }

    public void press(int key, char character) {
        if (typingListener != null) {
            if (!holding[key]) {
                typingListener.onKeyPressed(character);
                holding[key] = true;
            }
            typingListener.onKeyHolding(character);
        }
    }

    public void release(int key) {
        pressed[key] = false;
        holding[key] = false;
    }

    public void presMouse(int button , int x, int y) {
        if (mouseInputListener != null) {
            System.out.println("Pressing Mouse. Button: " + button);
            if (!mouseHolding[button]) {
                if (button == 0)
                    mouseInputListener.onLeftButtonPressed(x, y);
                else
                    mouseInputListener.onRightButtonPressed(x, y);
                mouseHolding[button] = true;
            }
            if (button == 0)
                mouseInputListener.onLeftButtonHolding(x, y);
            else
                mouseInputListener.onRightButtonHolding(x, y);
        }
    }

    public void releaseMouse(int button, int x, int y) {
        mousePressed[button] = false;
        mouseHolding[button] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Pressing Key: " + e.getKeyCode());
        for (Action action : keyActions) {
            if (e.getKeyCode() == action.getKey())
                action.press();
        }
        press(e.getKeyCode(), e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (Action action : keyActions) {
            if (e.getKeyCode() == action.getKey())
                action.release();
        }
        release(e.getKeyCode());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Pressing Mouse Button: " + e.getButton());
        presMouse(e.getButton(), e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        releaseMouse(e.getButton(), e.getX(), e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static void setTypingListener(TypingListener typingListener) {
        Input.typingListener = typingListener;
    }

    public static void setKeyInputListener(KeyInputListener keyInputListener) {
        Input.keyInputListener = keyInputListener;
    }

    public static void setMouseInputListener(MouseInputListener mouseInputListener) {
        Input.mouseInputListener = mouseInputListener;
    }

    public interface KeyInputListener {
        public void onUpPressed();

        public void onUpHolding();

        public void onDownPressed();

        public void onDownHolding();

        public void onLeftPressed();

        public void onLeftHolding();

        public void onRightPressed();

        public void onRightHolding();
    }

    public interface TypingListener {
        public void onKeyPressed(char key);

        public void onKeyHolding(char key);
    }

    public interface MouseInputListener {
        public void onLeftButtonPressed(int x, int y);

        public void onLeftButtonHolding(int x, int y);

        public void onRightButtonPressed(int x, int y);

        public void onRightButtonHolding(int x, int y);
    }
}
