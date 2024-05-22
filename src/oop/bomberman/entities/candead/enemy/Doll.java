package oop.bomberman.entities.candead.enemy;

import javafx.scene.image.Image;
import oop.bomberman.graphics.Sprite;
import oop.bomberman.mainplay.BombermanGame;
import oop.bomberman.sound.Sound;

public class Doll extends Enemy {

    private boolean leftOrRight = true;
    private int speed = 2;

    public Doll(int x, int y, Image img) {
        super(x, y, img);
    }


    @Override
    public void whenDead() {
        if (dead) {
            if (timing == 0) {
                img = Sprite.doll_dead.getFxImage();
                Sound.play("AA126_11");
            }
            timing++;
        }
    }

    public void followRow() {
        if (!leftOrRight) {
            if (BombermanGame.map[y/32].charAt(x/32+1) != '#'
                    && BombermanGame.map[y/32].charAt(x/32+1) != '*'
                    && BombermanGame.map[y/32].charAt(x/32+1) != 'B') {
                x += speed;
                img = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2,
                        Sprite.doll_right3, timing, 30).getFxImage();
                timing++;
            } else {
                leftOrRight = true;
                timing = 0;
            }
        } else {
            if (BombermanGame.map[y/32].charAt((x-1)/32) != '#'
                    && BombermanGame.map[y/32].charAt((x-1)/32) != '*'
                    && BombermanGame.map[y/32].charAt((x-1)/32) != 'B') {
                x -= speed;
                img = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2,
                        Sprite.doll_left3, timing, 30).getFxImage();
                timing++;
            } else {
                leftOrRight = false;
                timing = 0;
            }
        }


    }


    public void ballomAction() {
        followRow();
    }

    @Override
    public void update() {
        whenDead();
        if (!dead) {
            ballomAction();
        }
    }
}
