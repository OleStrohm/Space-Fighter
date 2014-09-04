package no.Stroehm.DidstrGame;

import no.Stroehm.DidstrGame.graphics.Screen;
import no.Stroehm.DidstrGame.states.State;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Created by Ole on 24/12/13.
 */
public class Game extends Canvas implements Runnable {

    public static int SCALE = 4;
    public static int HEIGHT = 640 / SCALE;
    public static int WIDTH = 640 / SCALE;
    private static Dimension d = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
    private static String TITLE = "Space Fighter";

    JFrame frame;
    Screen screen;
    InputHandler input;
    private BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();

    private static boolean running = false;

    public static void main(String[] args) {
        Game game = new Game();
        game.setPreferredSize(d);
        game.setMinimumSize(d);
        game.setMaximumSize(d);

        game.frame = new JFrame(TITLE);
        game.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.frame.add(game);
        game.frame.setResizable(false);
        game.frame.pack();
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
    }

    public void start() {
        running = true;

        State.init();
        screen = new Screen(WIDTH, HEIGHT);
        input = new InputHandler();

        new Thread(this, "Game Loop").start();
    }

    public static void stop() {
        running = false;
    }

    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double ns = 1000000000.0 / 60;
        double unprocessed = 0;
        int ticks = 0;
        int frames = 0;

        addKeyListener(input);
        requestFocus();
        while (running) {
            long now = System.nanoTime();
            unprocessed += (now - lastTime) / ns;
            lastTime = now;
            while (unprocessed >= 1) {
                tick();
                ticks++;
                unprocessed--;
            }
            {
                render();
                frames++;
            }
            if (System.currentTimeMillis() - timer > 1000) {
                FPS.ticks = ticks;
                FPS.frames = frames;
                ticks = 0;
                frames = 0;
                timer += 1000;
            }
        }
        Graphics g = getGraphics();
        String msg = "Exiting!";
        for (int i = 0; i < msg.length(); i++) {
            screen.renderArea(0x00A9FF, 0, WIDTH, 0, HEIGHT);
            screen.renderText("" + msg.charAt(i), 10 + (i * 8), 50, 0x5DF24F + 0x14CC00, false);
            screen.copy(pixels);
            g.drawImage(img, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
            try {
                Thread.sleep(350);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < msg.length(); i++) {
            screen.renderArea(0x00A9FF, 0, WIDTH, 0, HEIGHT);
            for (int j = 0; j < i + 1; j++) {
                screen.renderText("" + msg.charAt(j), 10 + (j * 8), 50, 0x5DF24F + 0x14CC00, false);
            }
            screen.copy(pixels);
            getGraphics().drawImage(img, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
            try {
                Thread.sleep(350);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public void tick() {
        input.tick();
        State.getCurState().tick(input);
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(2);
            return;
        }
        screen.setOffset(0, 0);
        screen.copy(pixels);

        State.getCurState().render(screen);

        Graphics g;
        g = bs.getDrawGraphics();
        g.drawImage(img, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
        g.dispose();
        bs.show();
    }

}
