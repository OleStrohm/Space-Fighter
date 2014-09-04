package no.Stroehm.DidstrGame.states;


import no.Stroehm.DidstrGame.FPS;
import no.Stroehm.DidstrGame.graphics.Screen;

/**
 * Created by Ole on 22/12/13.
 */
public class StateMenuOptions extends StateMenu {

    public StateMenuOptions() {
        super(2, optionsId);
    }

    protected void press() {
        switch (selected) {
            case 0:
                FPS.renderFps = !FPS.renderFps;
                break;
            case 1:
                setState(lastState);
                break;
        }
    }

    public void render(Screen screen) {
        screen.renderArea(0x00A9FF, 0, screen.w, 0, screen.h);
        screen.renderText("renderFps: " + FPS.renderFps, 10, 50, getColor(0), false);
        screen.renderText("Back", screen.w - 50, screen.h - 20, getColor(1), false);
    }
}
