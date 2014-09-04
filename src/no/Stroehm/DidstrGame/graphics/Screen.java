package no.Stroehm.DidstrGame.graphics;

/**
 * Created by Ole on 25/12/13.
 */
public class Screen {

    public final int w, h;
    private int[] pixels;
    private int xOffset, yOffset;

    public Screen(int w, int h) {
        this.w = w;
        this.h = h;
        pixels = new int[w * h];
    }

    public void copy(int[] pixels) {
        System.arraycopy(this.pixels, 0, pixels, 0, pixels.length);
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void render(SpriteSheet sheet, int xPix, int yPix, int size, int xPos, int yPos, int effect, boolean offset) {
        if (offset) {
            xPos -= xOffset;
            yPos -= yOffset;
        }
        xPix *= size;
        yPix *= size;
        for (int y = 0; y < size; y++) {
            int yp = yPos + y;
            int yt = yPix + y;
            for (int x = 0; x < size; x++) {
                int xp = xPos + x;
                int xt = xPix + x;
                if (xp < 0 || xp >= w || yp < 0 || yp >= h) break;

                if (effect == 1) {
                    xt = xPix + (size - 1) - x;
                    yt = yPix + (size - 1) - y;
                }

                int col = sheet.pixels[xt + yt * sheet.w];
                if (col != 0xFFFF00FF) pixels[xp + yp * w] = col;
            }
        }
    }

    public void renderHeader(SpriteSheet sheet, int xPix, int yPix, int width, int height, int xPos, int yPos) {
        for (int y = 0; y < height; y++) {
            int yp = yPos + y;
            int yt = yPix + y;
            for (int x = 0; x < width; x++) {
                int xp = xPos + x;
                int xt = xPix + x;
                if (xp < 0 || xp >= w || yp < 0 || yp >= h) break;

                int col = sheet.pixels[xt + yt * sheet.w];
                if (col != 0xFFFF00FF) pixels[xp + yp * w] = col;
            }
        }
    }

    public void renderAtAngle(SpriteSheet sheet, int xPix, int yPix, int size, int xPos, int yPos, int rx, int ry, double angle, boolean offset) {
        if (offset) {
            xPos -= xOffset;
            yPos -= yOffset;
        }
        xPix *= size;
        yPix *= size;
        for (int y = 0; y < size; y++) {
            int yt = yPix + y;
            for (int x = 0; x < size; x++) {
                int xt = xPix + x;

                float rot = (float) (1F / 180 * angle * Math.PI);

                float rotateXPos = xPos + x;
                float rotateYPos = yPos + y;

                int xp = (int) (rotateXPos * Math.cos(angle) - rotateYPos * Math.sin(angle));
                int yp = (int) (rotateXPos * Math.sin(angle) + rotateYPos * Math.cos(angle));

                xp += xPos * 2;
                yp += yPos;
                System.out.println(xp + ", " + yp);

                if (xp < 0 || xp >= w || yp < 0 || yp >= h) break;

                int col = sheet.pixels[xt + yt * sheet.w];
                if (col != 0xFFFF00FF) pixels[xp + yp * w] = col;
            }
        }
    }

    public void renderArea(int col, int xMin, int xMax, int yMin, int yMax) {
        for (int y = yMin; y < yMax; y++) {
            for (int x = xMin; x < xMax; x++) {
                pixels[x + y * w] = col;
            }
        }
    }

    public void renderText(String msg, int xPos, int yPos, int textCol, boolean offset) {
        if (offset) {
            xPos -= xOffset;
            yPos -= yOffset;
        }
        SpriteSheet sheet = SpriteSheet.sheet;
        msg = msg.toUpperCase();
        for (int s = 0; s < msg.length(); s++) {
            int xPix = Text.text.indexOf(msg.charAt(s));
            int yPix = 0;
            if (xPix > 31) {
                xPix -= 32;
                yPix += 1;
            }
            for (int y = 0; y < 8; y++) {
                int yy = yPos + y;
                int yp = ((30 + yPix) * 8) + y;
                for (int x = 0; x < 8; x++) {
                    if (xPos < 0 || xPos >= w || yPos < 0 || yPos >= h) return;
                    int xx = xPos + x + (s * 8);
                    int xp = (xPix * 8) + x;
                    int col = sheet.pixels[xp + yp * sheet.w];
                    if (col != 0xFFFF00FF) pixels[xx + yy * w] = textCol;
                }
            }
        }
    }

}
