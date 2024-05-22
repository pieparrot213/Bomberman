package oop.bomberman.entities.staticEntity;

import javafx.scene.image.Image;
import oop.bomberman.entities.candead.Bomber;
import oop.bomberman.entities.Entity;
import oop.bomberman.graphics.Sprite;

public class Portal extends Entity {
    public Portal(int x, int y, Image img) {
        super(x, y, img);
    }

    public int nextLevel(Bomber player) {
        int X = x / Sprite.SCALED_SIZE;
        int Y = y / Sprite.SCALED_SIZE;
        if (player.getLocationX() == X && player.getLocationY() == Y) {
            return 1;
        }
        return 0;
    }
    @Override
    public void update() {

    }
}
