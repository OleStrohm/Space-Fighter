package no.Stroehm.DidstrGame.entity;

import no.Stroehm.DidstrGame.Game;
import no.Stroehm.DidstrGame.InputHandler;
import no.Stroehm.DidstrGame.graphics.Screen;
import no.Stroehm.DidstrGame.graphics.SpriteSheet;
import no.Stroehm.DidstrGame.world.World;

import java.util.List;

/**
 * Created by Ole on 26/12/13.
 */
public class Shot extends Entity {

    private int lifeTime = 60;
    private double speed;
    private Mob source;
    private int effect = 0;
    private int dmg = 5;

    public Shot(double x, double y, World world, Mob source) {
        super(x, y, world, "Bullet");
        this.x = x;
        this.y = y;
        this.source = source;
        if(source instanceof Enemy) effect = 1;
        speed = (source instanceof Player ? 4.5 : -4.5);
    }

    public void tick(InputHandler input) {
        if (y - source.y > Game.HEIGHT) die("living too long");
        lifeTime--;
        if (lifeTime <= 0) die("living too long");
        y -= speed;

        if(source.tag.equalsIgnoreCase("player")) {
            List<Mob> mobs = world.getMobs();
            for(int i = 0; i < mobs.size(); i++) {
                Mob enemy = mobs.get(i);
                if(!(enemy instanceof Player)) {
                    if(enemy.x < x + 5 && enemy.x > x - 5 && enemy.y < y + 5 && enemy.y > y - 5) {
                        enemy.hurt(dmg, source);
                        die("Hitting an enemy");
                    }
                }
            }
        } else {
            List<Player> players = world.getPlayers();
            for(int i = 0; i < players.size(); i++) {
                Player player = players.get(i);
                if(player.x < x + 5 && player.x > x - 5 && player.y < y + 5 && player.y > y - 5) {
                    player.hurt(dmg, source);
                    die("Hitting the player");
                }
            }
        }
    }

    public void render(Screen screen) {
        screen.render(SpriteSheet.sheet, 14, 0, 3, (int) x, (int) y, effect, true);
    }

}
