package no.Stroehm.DidstrGame.states;


import no.Stroehm.DidstrGame.graphics.Screen;

/**
 * Created by Ole on 22/12/13.
 */
public class StateMenuAbout extends StateMenu {

    public StateMenuAbout() {
        super(1, aboutId);
    }

    protected void press() {
        pressedTimer = 10;
        setState(lastState);
    }

    public void render(Screen screen) {
        screen.renderArea(0x00A9FF, 0, screen.w, 0, screen.h);
        screen.renderText("This game was", 1, 15, 0xFFFFFF, false);
        screen.renderText("made by:", 2, 25, 0xFFFFFF, false);
        screen.renderText("Ole marius strøhm", 2, 35, 0xFFFFFF, false);
        screen.renderText("Back", screen.w - 50, screen.h - 20, getColor(0), false);
    }
}
