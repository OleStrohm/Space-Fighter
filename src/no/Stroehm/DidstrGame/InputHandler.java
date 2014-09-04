package no.Stroehm.DidstrGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Ole on 25/12/13.
 */
public class InputHandler implements KeyListener {

    private boolean[] keys = new boolean[151];
    public boolean right, left, up, down, enter, shoot, escape;

    public void tick() {
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        enter = keys[KeyEvent.VK_ENTER];
        shoot = keys[KeyEvent.VK_SPACE];
        escape = keys[KeyEvent.VK_ESCAPE];
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
