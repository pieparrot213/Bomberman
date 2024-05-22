package oop.bomberman.update.dead;

import oop.bomberman.entities.Boom;
import oop.bomberman.entities.BoomExploded;
import oop.bomberman.entities.candead.items.Item;
import oop.bomberman.graphics.Sprite;
import oop.bomberman.entities.*;

import java.util.List;

public class ItemDead {



    public static List<Item> checkWhenDead(List<Boom> boomObjects,
                                           List<BoomExploded> boomExplodeds,
                                           List<Item> itemObjects) {
        for (int i = 0; i < itemObjects.size(); ++i) {
            Item item = itemObjects.get(i);
            if (item.isDead()) {
                itemObjects.remove(i);
                --i;
                continue;
            }
            int X1 = (item.getX()) / Sprite.SCALED_SIZE;
            int Y1 = (item.getY()) / Sprite.SCALED_SIZE;
            boolean check = false;
            for (BoomExploded exploded : boomExplodeds) {
                int X = exploded.getX() / Sprite.SCALED_SIZE;
                int Y = exploded.getY() / Sprite.SCALED_SIZE;
                if (X == X1 && Y == Y1) {
                    check = true;
                    break;
                }
            }
            for (Boom boom : boomObjects) {
                if (boom.getTiming() < 120) {
                    continue;
                }
                int X = boom.getX() / Sprite.SCALED_SIZE;
                int Y = boom.getY() / Sprite.SCALED_SIZE;
                if (X == X1 && Y == Y1) {
                    check = true;
                    break;
                }
            }
            if (check) {
                itemObjects.remove(i);
                --i;
            }
        }
        return itemObjects;
    }
}
