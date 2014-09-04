package no.Stroehm.DidstrGame.states;


import no.Stroehm.DidstrGame.graphics.Screen;
import no.Stroehm.DidstrGame.graphics.SpriteSheet;

/**
 * Created by Ole on 15/12/13.
 */
public class StateMenuMain extends StateMenu {

    private int textPosX = 10;
    private int textPosY = 100;

    public StateMenuMain() {
        super(4, startId);
    }

    protected void press() {
        switch (selected) {
            case 0:
                setState(gameId);
                break;
            case 1:
                setState(instructionsId);
                break;
            case 2:
                setState(aboutId);
                break;
            case 3:
                setState(exitId);
                break;
        }
    }

    public void render(Screen screen) {
        screen.renderArea(0x0C00BE, 0, screen.w, 0, screen.h);
        screen.renderHeader(SpriteSheet.sheet, 0, 32, 43, 11, (screen.w - 42) / 2, 10);
        screen.renderHeader(SpriteSheet.sheet, 0, 43, 59, 11, (screen.w - 59) / 2, 21);
        screen.renderText("Play Game", textPosX, textPosY, getColor(0), false);
        screen.renderText("Instructions", textPosX, textPosY + 10, getColor(1), false);
        screen.renderText("About", textPosX, textPosY + 20, getColor(2), false);
        screen.renderText("Exit", textPosX, textPosY + 30, getColor(3), false);
    }
}
