package no.Stroehm.DidstrGame.entity;

import no.Stroehm.DidstrGame.InputHandler;
import no.Stroehm.DidstrGame.graphics.Screen;
import no.Stroehm.DidstrGame.world.World;

/**
 * Created by Ole on 25/12/13.
 */
public abstract class Entity {

    public String tag;
    public double x, y;
    protected World world;

    public Entity(double x, double y, World world, String tag) {
        this.x = x;
        this.y = y;
        this.world = world;
        this.tag = tag;
    }

    public abstract void tick(InputHandler input);

    public abstract void render(Screen screen);

    protected void die(String cause) {
        System.out.println(tag + " killed by " + cause);
        world.removeEntity(this);
    }

}
