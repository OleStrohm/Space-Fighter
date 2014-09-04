package no.Stroehm.DidstrGame.entity;

import no.Stroehm.DidstrGame.Game;
import no.Stroehm.DidstrGame.InputHandler;
import no.Stroehm.DidstrGame.graphics.Screen;
import no.Stroehm.DidstrGame.graphics.SpriteSheet;
import no.Stroehm.DidstrGame.world.World;

import java.util.List;
import java.util.Random;

/**
 * Created by Ole on 26/12/13.
 */
public class Enemy extends Mob {

    private int dir;
    private Random random = new Random();

    public Enemy(double x, double y, World world) {
        super(x, y, world, "Enemy");
        dir = random.nextInt(2);
        if(dir == 0) dir = -1;
    }

    public void tick(InputHandler input) {
        super.tick();
        if(health <= 0) {
            die();
            return;
        }

        x += 0.8 * dir;
        if(x <= 5 || x >= Game.WIDTH - 5) dir *= -1;
        y -= speed;

        List<Player> players = world.getPlayers();
        boolean shoot = false;
        for(int i = 0; i < players.size(); i++) {
            if(players.get(i).x < x+8 && players.get(i).x > x -8) {
                shoot = true;
            }
            if(shoot) break;
        }

        if(shotTimer > 0) shotTimer--;

        if (shoot && shotTimer <= 0) {
            world.addEntity(new Shot(x - 4, y + 4, world, this));
            world.addEntity(new Shot(x + 1, y + 4, world, this));
            shotTimer = shotSpeed;
        }
    }

    public void render(Screen screen) {
        screen.render(SpriteSheet.sheet, 4, 0, 8, (int) x - 4, (int) y, 1, true);
    }

    public void die() {
        super.die();
        if(lastHurtByEntity instanceof Player) {
            Player player = (Player)lastHurtByEntity;
            player.points += 10;
        }
        world.addEntity(new Enemy(random.nextInt(Game.WIDTH - 10) + 5, y, world));
    }
}
