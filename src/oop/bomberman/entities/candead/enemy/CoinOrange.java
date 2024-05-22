package oop.bomberman.entities.candead.enemy;

import javafx.scene.image.Image;
import oop.bomberman.graphics.Sprite;
import oop.bomberman.mainplay.BombermanGame;
import oop.bomberman.moving.enemy.BFS;
import oop.bomberman.moving.enemy.MovingRandom;
import oop.bomberman.sound.Sound;

public class CoinOrange extends Enemy {

    public CoinOrange(int x, int y, Image img) {
        super(x, y, img);
    }
    private char directionToGo = ' ';
    private int speed = 2;

    @Override
    public void whenDead() {
        if (dead) {
            if (timing == 0) {
                img = Sprite.coinOrange_dead.getFxImage();
                Sound.play("AA126_11");
            }
            timing++;
        }
    }

    public void goLeft() {
        x -= speed;
        img = Sprite.movingSprite(Sprite.coinOrange_left1, Sprite.coinOrange_left2,
                Sprite.coinOrange_left3, timing, 30).getFxImage();
    }

    public void goRight() {
        x += speed;
        img = Sprite.movingSprite(Sprite.coinOrange_right1, Sprite.coinOrange_right2,
                Sprite.coinOrange_right3, timing, 30).getFxImage();
    }

    public void goUp() {
        y -= speed;
        img = Sprite.movingSprite(Sprite.coinOrange_right1, Sprite.coinOrange_right2,
                Sprite.coinOrange_right3, timing, 30).getFxImage();
    }

    public void goDown() {
        y += speed;
        img = Sprite.movingSprite(Sprite.coinOrange_left1, Sprite.coinOrange_left2,
                Sprite.coinOrange_left3, timing, 30).getFxImage();
    }

    @Override
    public void update() {
        if (dead) {
            whenDead();
            return;
        }
        if (x % 32 == 0 && y % 32 == 0) {
            goDoor();
            directionToGo =  BFS.bfs(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE,
                    BombermanGame.player1.getX() / Sprite.SCALED_SIZE,
                    BombermanGame.player1.getY() / Sprite.SCALED_SIZE).charAt(0);
            timing = 0;
            if (directionToGo == ' ') {
                directionToGo = MovingRandom.directionToMove(x, y);
            }
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
