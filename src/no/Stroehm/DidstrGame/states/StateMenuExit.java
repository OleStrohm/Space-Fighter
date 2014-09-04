package no.Stroehm.DidstrGame.states;


import no.Stroehm.DidstrGame.Game;
import no.Stroehm.DidstrGame.graphics.Screen;

/**
 * Created by Ole on 22/12/13.
 */
public class StateMenuExit extends StateMenu {


    public StateMenuExit() {
        super(2, exitId);
    }

    protected void press() {
        switch (selected) {
            case 0:
                Game.stop();
                break;
            case 1:
                setState(lastState);
                break;
        }
    }

    public void render(Screen screen) {
        screen.renderArea(0x00A9FF, 0, screen.w, 0, screen.h);
        screen.renderText("Do you want to quit?", (screen.w - "Do you want to quit?".length() * 8) / 2, 50, col1 + col2, false);
        screen.renderText("yes", 50, screen.h / 2, getColor(0), false);
        screen.renderText("no", 50, screen.h / 2 + 16, getColor(1), false);
    }
}
