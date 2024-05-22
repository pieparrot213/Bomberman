package oop.bomberman.update.dead;

import oop.bomberman.entities.Boom;
import oop.bomberman.entities.BoomExploded;
import oop.bomberman.entities.Entity;
import oop.bomberman.entities.candead.Bomber;
import oop.bomberman.entities.candead.enemy.Enemy;
import oop.bomberman.graphics.Sprite;
import oop.bomberman.entities.*;

import java.util.List;

public class PlayerDead {
    public static void checkWhenDead(Bomber player, List<BoomExploded> boomExplodeds,
                                     List<Enemy> enemyObjects, List<Boom> booms) {
        int X1 = (player.getX() + 12) / Sprite.SCALED_SIZE;
        int Y1 = (player.getY() + 16) / Sprite.SCALED_SIZE;
        for (BoomExploded exploded : boomExplodeds) {
            int X = exploded.getX() / Sprite.SCALED_SIZE;
            int Y = exploded.getY() / Sprite.SCALED_SIZE;
            if (X == X1 && Y == Y1) {
                player.setDead(true);
                player.setTiming(0);
            }
        }
        for (Boom boom : booms) {
            if (boom.getTiming() < 180) {
                continue;
            }
            int X = boom.getX() / Sprite.SCALED_SIZE;
            int Y = boom.getY() / Sprite.SCALED_SIZE;
            if (X == X1 && Y == Y1) {
                player.setDead(true);
                player.setTiming(0);
            }
        }
        X1 = (player.getX() + 8);
        Y1 = (player.getY() + 8);
        int X2 = (player.getX() + 16);
        int Y2 = (player.getY() + 8);
        int X3 = (player.getX() + 8);
        int Y3 = (player.getY() + 24);
        int X4 = (player.getX() + 16);
        int Y4 = (player.getY() + 24);

        for (Entity enemy : enemyObjects) {
            int X = enemy.getX();
            int Y = enemy.getY();
            if ((X1 >= X && X1 <= X + 32 && Y1 >= Y && Y1 <= Y + 32)
                    || (X2 >= X && X2 <= X + 32 && Y2 >= Y && Y3 <= Y + 32)
                    || (X3 >= X && X3 <= X + 32 && Y3 >= Y && Y3 <= Y + 32)
                    || (X4 >= X && X4 <= X + 32 && Y4 >= Y && Y4 <= Y + 32)) {
                player.setDead(true);
                player.setTiming(0);
            }
        }
    }
}
