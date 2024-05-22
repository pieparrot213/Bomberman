package oop.bomberman.entities;

import javafx.scene.image.Image;
import oop.bomberman.graphics.Sprite;
import oop.bomberman.mainplay.BombermanGame;
import oop.bomberman.sound.Sound;

public class Boom extends Entity { //lop bom
    public Boom(int x, int y, Image img) {
        super(x, y, img);
    }
    private int timing;
    private int type = 1;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTiming() {
        return timing;
    }

    public void setTiming(int timing) {
        this.timing = timing;
    }


    @Override
    public void update() {
        timing++;
        if (timing == 1) {
            Sound.play("BOM_SET");
        }
        if (timing >= 190) {
            img = Sprite.bomb_exploded2.getFxImage();
        } else if (timing >= 185) {
            img = Sprite.bomb_exploded1.getFxImage();
        } else if (timing >= 180) {
            img = Sprite.bomb_exploded.getFxImage();
            if (timing == 180) Sound.play("BOM_11_M");
        } else if (timing % 30 == 0) {
            img = Sprite.bomb.getFxImage();
        } else if (timing % 30 == 10) {
            img = Sprite.bomb_1.getFxImage();
        } else if (timing % 30 == 20) {
            img = Sprite.bomb_2.getFxImage();
        }
        if (timing == 1) {
            int X = x / Sprite.SCALED_SIZE;
            int Y = y / Sprite.SCALED_SIZE;
            BombermanGame.map[Y] = BombermanGame.map[Y].substring(0, X) + "B"
                    + BombermanGame.map[Y].substring(X + 1);
        }
        if (timing == 189) {
            int X = x / Sprite.SCALED_SIZE;
            int Y = y / Sprite.SCALED_SIZE;
            BombermanGame.map[Y] = BombermanGame.map[Y].substring(0, X) + " "
                    + BombermanGame.map[Y].substring(X + 1);
        }
    }
}
