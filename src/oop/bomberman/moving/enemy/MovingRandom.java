package oop.bomberman.moving.enemy;

import oop.bomberman.graphics.Sprite;
import oop.bomberman.mainplay.BombermanGame;

import java.util.Random;

public class MovingRandom {

    public static char randomDirectionToMove(int x, int y) {
        char[] a = new char[4];
        int dem = -1;
        int X = x / Sprite.SCALED_SIZE;
        int Y = y / Sprite.SCALED_SIZE;
        int newX = X + 1, newY = Y;
        if (newX >= 0 && newX < BombermanGame.WIDTH && newY >= 0 && newY < BombermanGame.HEIGHT) {
            ++dem;
            a[dem] = 'R';
        }
        newX = X - 1; newY = Y;
        if (newX >= 0 && newX < BombermanGame.WIDTH && newY >= 0 && newY < BombermanGame.HEIGHT) {
            ++dem;
            a[dem] = 'L';
        }
        newX = X; newY = Y - 1;
        if (newX >= 0 && newX < BombermanGame.WIDTH && newY >= 0 && newY < BombermanGame.HEIGHT) {
            ++dem;
            a[dem] = 'U';
        }
        newX = X; newY = Y + 1;
        if (newX >= 0 && newX < BombermanGame.WIDTH && newY >= 0 && newY < BombermanGame.HEIGHT) {
            ++dem;
            a[dem] = 'D';
        }
        if (dem != -1) {
            Random random = new Random();
            int value = random.nextInt(dem + 1);
            return a[value];
        }
        return ' ';
    }

    public static char directionToMove(int x, int y) {
        char[] a = new char[4];
        int dem = -1;
        int X = x / Sprite.SCALED_SIZE;
        int Y = y / Sprite.SCALED_SIZE;
        int newX = X + 1, newY = Y;
        if (BombermanGame.map[newY].charAt(newX) != '#'
                && BombermanGame.map[newY].charAt(newX) != '*'
                && BombermanGame.map[newY].charAt(newX) != 'B') {
            ++dem;
            a[dem] = 'R';
        }
        newX = X - 1; newY = Y;
        if (BombermanGame.map[newY].charAt(newX) != '#'
                && BombermanGame.map[newY].charAt(newX) != '*'
                && BombermanGame.map[newY].charAt(newX) != 'B') {
            ++dem;
            a[dem] = 'L';
        }
        newX = X; newY = Y - 1;
        if (BombermanGame.map[newY].charAt(newX) != '#'
                && BombermanGame.map[newY].charAt(newX) != '*'
                && BombermanGame.map[newY].charAt(newX) != 'B') {
            ++dem;
            a[dem] = 'U';
        }
        newX = X; newY = Y + 1;
        if (BombermanGame.map[newY].charAt(newX) != '#'
                && BombermanGame.map[newY].charAt(newX) != '*'
                && BombermanGame.map[newY].charAt(newX) != 'B') {
            ++dem;
            a[dem] = 'D';
        }
        if (dem != -1) {
            Random random = new Random();
            int value = random.nextInt(dem + 1);
            return a[value];
        }
        return ' ';
    }
}
