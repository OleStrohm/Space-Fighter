package no.Stroehm.DidstrGame.states;

import no.Stroehm.DidstrGame.InputHandler;
import no.Stroehm.DidstrGame.graphics.Screen;

/**
 * Created by Ole on 22/12/13.
 */
public class StateMenuInstructions extends StateMenu {


    public StateMenuInstructions() {
        super(1, instructionsId);
    }

    public void tick(InputHandler input) {
        super.tick(input);
    }

    protected void press() {
        setState(lastState);
    }

    public void render(Screen screen) {
        screen.renderArea(0x00A9FF, 0, screen.w, 0, screen.h);
        screen.renderText("Move with", 2, 10, 0xFFFFFF, false);
        screen.renderText("Left and right.", 2, 20, 0xFFFFFF, false);
        screen.renderText("Shoot with Space.", 2, 30, 0xFFFFFF, false);
        screen.renderText("Back", screen.w - 50, screen.h - 20, getColor(0), false);
    }
}
