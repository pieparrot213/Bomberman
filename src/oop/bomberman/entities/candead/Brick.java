package oop.bomberman.entities.candead;

import javafx.scene.image.Image;
import oop.bomberman.graphics.Sprite;

public class Brick extends EntityCanDead { //lop brick

    public Brick(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public int getX() {
        return x / Sprite.SCALED_SIZE;
    }

    @Override
    public int getY() {
        return y / Sprite.SCALED_SIZE;
    }

    @Override
    public void whenDead() {
        if (dead) {
            if (timing == 0) {
                img = Sprite.brick_exploded.getFxImage();
            }
            timing++;
        }
        if (timing == 5) {
            img = Sprite.brick_exploded1.getFxImage();
        } else if (timing == 10) {
            img = Sprite.brick_exploded2.getFxImage();
        }
    }

    @Override
    public void update() {
        whenDead();
    }
}
