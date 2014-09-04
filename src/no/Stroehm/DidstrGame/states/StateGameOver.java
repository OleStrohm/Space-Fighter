package no.Stroehm.DidstrGame.states;

import no.Stroehm.DidstrGame.Game;
import no.Stroehm.DidstrGame.InputHandler;
import no.Stroehm.DidstrGame.graphics.Screen;
import no.Stroehm.DidstrGame.world.World;

/**
 * Created by Ole on 26/12/13.
 */
public class StateGameOver extends State {

    private boolean reset = false;
    private int points;
    private int timer = 60 * 5;

    public StateGameOver() {
        super(gameOverId);
    }

    public void tick(InputHandler input) {
        if (!reset) {
            System.out.println("resetting");
            StateGame gameState = (StateGame)states.get(gameId);
            points = gameState.gameWorld.getPlayers().get(0).points;
            gameState.gameWorld = new World(Game.WIDTH, Game.HEIGHT);
            reset = true;
        }
        timer--;
        if(timer <= 0){
            setState(startId);
            timer = 60 * 5;
            reset = false;
        }
    }

    public void render(Screen screen) {
        screen.renderArea(0x00A9FF, 0, screen.w, 0, screen.h);
        screen.renderText("Game Over", 5, 5, 0xFFFFFF, false);
        if(reset)screen.renderText("You got " + points + " points", 5, 50, 0xFFFFFF, false);
    }
}
