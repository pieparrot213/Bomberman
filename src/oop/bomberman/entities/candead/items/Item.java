package oop.bomberman.entities.candead.items;

import javafx.scene.image.Image;
import oop.bomberman.entities.BoomExploded;
import oop.bomberman.mainplay.BombermanGame;
import oop.bomberman.mainplay.MultiplayerBombermanGame;
import oop.bomberman.entities.candead.Bomber;
import oop.bomberman.entities.candead.EntityCanDead;

public abstract class Item extends EntityCanDead {

    public Item(int x, int y, Image img) {
        super(x, y, img);
    }

    public abstract void takingItem(Bomber player);

    @Override
    public void whenDead() {

    }

    @Override
    public void update() {
        if (BoomExploded.StartBombermanGame.type == 1) {
            takingItem(BombermanGame.player1);
        } else {
            takingItem(MultiplayerBombermanGame.player1);
            takingItem(MultiplayerBombermanGame.player2);
        }
    }
}
