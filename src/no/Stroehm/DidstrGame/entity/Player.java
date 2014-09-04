package no.Stroehm.DidstrGame.entity;

import no.Stroehm.DidstrGame.Game;
import no.Stroehm.DidstrGame.InputHandler;
import no.Stroehm.DidstrGame.graphics.Screen;
import no.Stroehm.DidstrGame.graphics.SpriteSheet;
import no.Stroehm.DidstrGame.states.State;
import no.Stroehm.DidstrGame.world.World;

/**
 * Created by Ole on 25/12/13.
 */
public class Player extends Mob {

    public int points = 0;

    public Player(double x, double y, World world) {
        super(x, y, world, "Player");
    }

    public void tick(InputHandler input) {
        super.tick();
        if (health <= 0) {
            //die();
            //return;
        }
        if (input.left) x -= 1;
        if (input.right) x += 1;
        if (x < 4) x = 4;
        if (x > Game.WIDTH - 4) x = Game.WIDTH - 4;
        y -= speed;

        if (shotTimer > 0) shotTimer--;

        if (input.shoot && shotTimer <= 0) {
            world.addEntity(new Shot(x - 4, y + 4, world, this));
            world.addEntity(new Shot(x + 1, y + 4, world, this));
            shotTimer = shotSpeed;
        }
    }

    public void render(Screen screen) {
        //screen.render(SpriteSheet.sheet, 4, 0, 8, 32, 48, 0, false);
        screen.renderAtAngle(SpriteSheet.sheet, 4, 0, 8,40,40, 43, 43, 90, false);
        screen.renderText("POINTS: " + points, 5, 5, 0xFFFFFF, false);
    }

    public void die() {
        super.die();
        Player player = new Player(x, y, world);
        player.points = points;
        world.addEntity(player);
        State.setState(State.gameOverId);
    }
}
