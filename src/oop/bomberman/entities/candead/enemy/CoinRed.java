package oop.bomberman.entities.candead.enemy;

import javafx.scene.image.Image;
import oop.bomberman.graphics.Sprite;
import oop.bomberman.moving.enemy.MovingRandom;
import oop.bomberman.sound.Sound;

public class CoinRed extends Enemy {

    public CoinRed(int x, int y, Image img) {
        super(x, y, img);
    }
    private char directionToGo = ' ';
    private int speed = 1;


    @Override
    public void whenDead() {
        if (dead) {
            if (timing == 0) {
                img = Sprite.coinRed_dead.getFxImage();
                Sound.play("AA126_11");
            }
            timing++;
        }
    }

    public void goLeft() {
        x -= speed;
        img = Sprite.movingSprite(Sprite.coinRed_left1, Sprite.coinRed_left2,
                Sprite.coinRed_left3, timing, 30).getFxImage();
    }

    public void goRight() {
        x += speed;
        img = Sprite.movingSprite(Sprite.coinRed_right1, Sprite.coinRed_right2,
                Sprite.coinRed_right3, timing, 30).getFxImage();
    }

    public void goUp() {
        y -= speed;
        img = Sprite.movingSprite(Sprite.coinRed_right1, Sprite.coinRed_right2,
                Sprite.coinRed_right3, timing, 30).getFxImage();
    }

    public void goDown() {
        y += speed;
        img = Sprite.movingSprite(Sprite.coinRed_left1, Sprite.coinRed_left2,
                Sprite.coinRed_left3, timing, 30).getFxImage();
    }

    @Override
    public void update() {
        if (dead) {
            whenDead();
            return;
        }
        if (x % 32 == 0 && y % 32 == 0) {
            goDoor();
            directionToGo = MovingRandom.directionToMove(x, y);
        }
        switch (directionToGo) {
            case 'L': goLeft(); break;
            case 'R': goRight(); break;
            case 'U': goUp(); break;
            case 'D': goDown(); break;
        }
        timing++;
    }

}
