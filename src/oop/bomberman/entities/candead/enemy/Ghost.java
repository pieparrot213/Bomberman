package oop.bomberman.entities.candead.enemy;

import javafx.scene.image.Image;
import oop.bomberman.graphics.Sprite;
import oop.bomberman.moving.enemy.MovingRandom;
import oop.bomberman.sound.Sound;

public class Ghost extends Enemy {

    public Ghost(int x, int y, Image img) {
        super(x, y, img);
    }
    private char directionToGo = ' ';
    private int speed = 1;

    @Override
    public void whenDead() {
        if (dead) {
            if (timing == 0) {
                img = Sprite.ghost_dead.getFxImage();
                Sound.play("AA126_11");
            }
            timing++;
        }
    }

    public void goLeft() {
        x -= speed;
        img = Sprite.ghost_left3.getFxImage();
    }

    public void goRight() {
        x += speed;
        img = Sprite.ghost_right3.getFxImage();
    }

    public void goUp() {
        y -= speed;
        img = Sprite.movingSprite(Sprite.ghost_right1,
                Sprite.ghost_right2, timing, 20).getFxImage();
    }

    public void goDown() {
        y += speed;
        img = Sprite.movingSprite(Sprite.ghost_left1,
                Sprite.ghost_left2, timing, 20).getFxImage();
    }

    @Override
    public void update() {
        if(dead) {
            whenDead();
            return;
        }
        if(x % 32 == 0 && y % 32 == 0) {
            timing = 0;
            directionToGo = MovingRandom.randomDirectionToMove(x, y);
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
