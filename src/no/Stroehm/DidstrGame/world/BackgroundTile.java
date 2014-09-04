package no.Stroehm.DidstrGame.world;

import no.Stroehm.DidstrGame.graphics.Screen;
import no.Stroehm.DidstrGame.graphics.SpriteSheet;

/**
 * Created by Ole on 25/12/13.
 */
public class BackgroundTile {

    public static BackgroundTile[] tiles = {
            new BackgroundTile()
    };

    public void render(Screen screen, int xPos, int yPos) {
        screen.render(SpriteSheet.sheet, 0, 0, 32, xPos, yPos, xPos+ yPos % 2, true);
    }
}
