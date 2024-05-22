package oop.bomberman.entities.candead.items;

import javafx.scene.image.Image;
import oop.bomberman.entities.candead.Bomber;
import oop.bomberman.graphics.Sprite;
import oop.bomberman.sound.Sound;

public class BombItem extends Item {
    public BombItem(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void takingItem(Bomber player) {
        int bomberX = player.getLocationX();
        int bomberY = player.getLocationY();
        int X = x / Sprite.SCALED_SIZE;
        int Y = y / Sprite.SCALED_SIZE;
        if (bomberX == X && bomberY == Y) {
            Sound.play("Item");
            if (player.getSizeOfBoom() <= 5) {
                player.setSizeOfBoom(player.getSizeOfBoom() + 1);
            }
            dead = true;
        }
    }

    @Override
    public void whenDead() {

    }

}
