package oop.bomberman.entities.candead.enemy;

import javafx.scene.image.Image;
import oop.bomberman.entities.candead.EntityCanDead;

public abstract class Enemy extends EntityCanDead { //kẻ thu
    public Enemy(int x, int y, Image img) {
        super(x, y, img);
    }
}
