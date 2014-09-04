package no.Stroehm.DidstrGame.entity;

import no.Stroehm.DidstrGame.world.World;

/**
 * Created by Ole on 26/12/13.
 */
public abstract class Mob extends Entity {

    protected int health = 20;
    protected int shotSpeed;
    protected double speed = .5;
    protected int shotTimer = 0;
    protected Mob lastHurtByEntity = null;
    protected int anim = 0;

    public Mob(double x, double y, World world, String tag) {
        super(x, y, world, tag);
        shotSpeed = 10;
    }

    public void tick() {
        if (anim < 7500) anim++;
        else anim = 0;
    }

    public void hurt(int dmg, Mob source) {
        health -= dmg;
        lastHurtByEntity = source;
    }

    public void die() {
        die(lastHurtByEntity.tag);
    }
}
