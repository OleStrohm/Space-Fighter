package no.Stroehm.DidstrGame.world;

import no.Stroehm.DidstrGame.Game;
import no.Stroehm.DidstrGame.InputHandler;
import no.Stroehm.DidstrGame.entity.Enemy;
import no.Stroehm.DidstrGame.entity.Entity;
import no.Stroehm.DidstrGame.entity.Mob;
import no.Stroehm.DidstrGame.entity.Player;
import no.Stroehm.DidstrGame.graphics.Screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Ole on 25/12/13.
 */
public class World {

    public int w, h;
    private List<Entity> entities = new ArrayList<Entity>();
    private Random rand = new Random();

    public World(int w, int h) {
        this.w = w;
        this.h = h;
        addEntity(new Player(w / 2, 0, this));
        addEntity(new Enemy(rand.nextInt(w - 12) + 6, -Game.HEIGHT + 30, this));
    }

    public void addEntity(Entity e) {
        entities.add(e);
    }

    public void removeEntity(Entity e) {
        if (entities.contains(e)) entities.remove(e);
    }

    public List<Mob> getMobs() {
        List<Mob> mobs = new ArrayList<Mob>();
        for (Entity e : entities) {
            if (e instanceof Mob) mobs.add((Mob) e);
        }
        return mobs;
    }

    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<Player>();
        for (Mob m : getMobs()) {
            if (m instanceof Player) players.add((Player) m);
        }
        return players;
    }

    public void tick(InputHandler input) {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).tick(input);
        }
    }

    public void render(Screen screen) {
        int yOffset = (int) getPlayers().get(0).y - screen.w + 20;
        screen.setOffset(0, yOffset);

        int y0 = yOffset >> 5;
        int y1 = (yOffset + screen.h + 32) >> 5;

        for (int y = y0; y < y1; y++) {
            for (int x = 0; x < w; x++) {
                BackgroundTile.tiles[0].render(screen, x << 5, y << 5);
            }
        }

        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(screen);
        }
    }

}
