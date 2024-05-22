package oop.bomberman.entities.candead.enemy;

import javafx.scene.image.Image;
import oop.bomberman.graphics.Sprite;
import oop.bomberman.mainplay.BombermanGame;
import oop.bomberman.moving.enemy.BFS;
import oop.bomberman.moving.enemy.MovingRandom;
import oop.bomberman.sound.Sound;

public class Oneal extends Enemy {

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
    }
    private char directionToGo = ' ';
    private int speed = 1;



    @Override
    public void whenDead() {
        if (dead) {
            if (timing == 0) {
                img = Sprite.oneal_dead.getFxImage();
                Sound.play("AA126_11");
            }
            timing++;
        }
    }

    public void goLeft() {
        x -= speed;
        img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2,
                Sprite.oneal_left3, timing, 30).getFxImage();
    }

    public void goRight() {
        x += speed;
        img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2,
                Sprite.oneal_right3, timing, 30).getFxImage();
    }

    public void goUp() {
        y -= speed;
        img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2,
                Sprite.oneal_right3, timing, 30).getFxImage();
    }

    public void goDown() {
        y += speed;
        img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2,
                Sprite.oneal_left3, timing, 30).getFxImage();
    }


    @Override
    public void update() {
        if (dead) {
            whenDead();
            return;
        }
        if (x % 32 == 0 && y % 32 == 0) {
            goDoor();
            String road =  BFS.bfs(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE,
                    BombermanGame.player1.getX() / Sprite.SCALED_SIZE,
                    BombermanGame.player1.getY() / Sprite.SCALED_SIZE);
            if (!road.equals(" ") && road.length() <= 10) {
                speed = 2;
            } else {
                speed = 1;
            }
            directionToGo = road.charAt(0);
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
