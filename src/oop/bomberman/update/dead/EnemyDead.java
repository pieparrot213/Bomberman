package oop.bomberman.update.dead;

import oop.bomberman.entities.BoomExploded;
import oop.bomberman.entities.candead.enemy.Enemy;
import oop.bomberman.graphics.Sprite;

import java.util.List;

public class EnemyDead {
    public static void checkWhenDead(Enemy enemy, List<BoomExploded> boomExplodeds) {
        int X1 = (enemy.getX() + 2) / Sprite.SCALED_SIZE;
        int Y1 = (enemy.getY() + 2) / Sprite.SCALED_SIZE;
        int X2 = (enemy.getX() + 30) / Sprite.SCALED_SIZE;
        int Y2 = (enemy.getY() + 2) / Sprite.SCALED_SIZE;
        int X3 = (enemy.getX() + 2) / Sprite.SCALED_SIZE;
        int Y3 = (enemy.getY() + 30) / Sprite.SCALED_SIZE;
        int X4 = (enemy.getX() + 30) / Sprite.SCALED_SIZE;
        int Y4 = (enemy.getY() + 30) / Sprite.SCALED_SIZE;
        for (BoomExploded exploded : boomExplodeds) {
            int X = exploded.getX() / Sprite.SCALED_SIZE;
            int Y = exploded.getY() / Sprite.SCALED_SIZE;
            if ((X == X1 && Y == Y1)
                    || (X == X2 && Y == Y2)
                    || (X == X3 && Y == Y3)
                    || (X == X4 && Y == Y4)) {
                enemy.setDead(true);
                enemy.setTiming(0);
            }
        }
    }
}
