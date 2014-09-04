package no.Stroehm.DidstrGame.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Ole on 25/12/13.
 */
public class SpriteSheet {

    public int w, h;
    public int[] pixels;
    public static SpriteSheet sheet = new SpriteSheet("/textures/SpriteSheet.png");

    public SpriteSheet(String path) {
        load(path);
    }

    private void load(String path) {
        try {
            BufferedImage img = ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
            w = img.getWidth();
            h = img.getHeight();
            pixels = img.getRGB(0, 0, w, h, null, 0, w);
        }catch (IOException e){
            e.printStackTrace();
            System.err.println("SHIT!");
        }
    }

}
