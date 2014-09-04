package no.Stroehm.DidstrGame.states;

import no.Stroehm.DidstrGame.InputHandler;
import no.Stroehm.DidstrGame.graphics.Screen;
import no.Stroehm.DidstrGame.graphics.SpriteSheet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ole on 14/12/13.
 */
public abstract class State {

    protected static List<State> states = new ArrayList<State>();
    private static int curState = 0;
    public static int lastState = 0;
    public int statePos;
    public int id;
    protected SpriteSheet sheet = SpriteSheet.sheet;

    public static final int startId = 0;
    public static final int gameId = 1;
    public static final int pauseId = 2;
    public static final int instructionsId = 3;
    public static final int aboutId = 4;
    public static final int optionsId = 5;
    public static final int exitId = 6;
    public static final int gameOverId = 7;

    public State(int id) {
        this.id = id;
        addState(this);
    }

    public static void init() {
        new StateMenuMain();
        new StateGame();
        new StateMenuPause();
        new StateMenuInstructions();
        new StateMenuAbout();
        new StateMenuOptions();
        new StateMenuExit();
        new StateGameOver();
    }

    public static void addState(State state) {
        state.statePos = states.size();
        states.add(state);
    }

    public abstract void tick(InputHandler input);

    public abstract void render(Screen screen);

    public void remove(State state) {
        states.remove(state.statePos);
    }

    public static void setState(int id) {
        for (int i = 0; i < getStatesLength(); i++) {
            State state = states.get(i);
            if (state.id == id) {
                lastState = curState;
                curState = i;
                return;
            }
        }
        System.out.println("Couldn't locate State with ID: " + id);
    }

    public static State getCurState() {
        return states.get(curState);
    }

    public static int getStatesLength() {
        return states.size();
    }
}
