package no.Stroehm.DidstrGame.states;


import no.Stroehm.DidstrGame.FPS;
import no.Stroehm.DidstrGame.Game;
import no.Stroehm.DidstrGame.InputHandler;
import no.Stroehm.DidstrGame.graphics.Screen;
import no.Stroehm.DidstrGame.world.World;

import java.util.Random;

/**
 * Created by Ole on 15/12/13.
 */
public class StateGame extends State {

    long timer;
    private Random r = new Random();
    public World gameWorld;

    public StateGame() {
        super(gameId);
        gameWorld = new World(Game.WIDTH, Game.HEIGHT * 2);
        timer = System.currentTimeMillis();
    }

    public void tick(InputHandler input) {
        if (input.escape) {
            setState(pauseId);
            return;
        }
        gameWorld.tick(input);
    }

    public void render(Screen screen) {
        gameWorld.render(screen);
        if (FPS.renderFps) screen.renderText(FPS.frames + " fps, " + FPS.ticks + " ticks", 10, screen.h-10, 0xFFFFFF, false);
    }
}
