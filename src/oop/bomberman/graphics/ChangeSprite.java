package oop.bomberman.graphics;

public class ChangeSprite {
    public static void changeToSecondTheme() {
        Sprite.grass = new Sprite(Sprite.DEFAULT_SIZE, 8, 4, SpriteSheet.newTile, 16, 16);
        Sprite.brick = new Sprite(Sprite.DEFAULT_SIZE, 9, 9, SpriteSheet.newTile, 16, 16);
        Sprite.wall = new Sprite(Sprite.DEFAULT_SIZE, 3, 13, SpriteSheet.newTile, 16, 16);
        Sprite.brick_exploded = new Sprite(Sprite.DEFAULT_SIZE, 9, 9, SpriteSheet.newTile, 16, 16);

    }

    public static void changeToFirstTheme() {
        Sprite.grass = new Sprite(Sprite.DEFAULT_SIZE, 6, 0, SpriteSheet.tiles, 16, 16);
        Sprite.brick = new Sprite(Sprite.DEFAULT_SIZE, 7, 0, SpriteSheet.tiles, 16, 16);
        Sprite.wall = new Sprite(Sprite.DEFAULT_SIZE, 5, 0, SpriteSheet.tiles, 16, 16);
        Sprite.brick_exploded = new Sprite(Sprite.DEFAULT_SIZE, 7, 1, SpriteSheet.tiles, 16, 16);
    }

    public static void changeToFirstPlayer() {
        Sprite.player_up = new Sprite(Sprite.DEFAULT_SIZE, 0, 0, SpriteSheet.tiles, 12, 16);
        Sprite.player_down = new Sprite(Sprite.DEFAULT_SIZE, 2, 0, SpriteSheet.tiles, 12, 15);
        Sprite.player_left = new Sprite(Sprite.DEFAULT_SIZE, 3, 0, SpriteSheet.tiles, 10, 15);
        Sprite.player_right = new Sprite(Sprite.DEFAULT_SIZE, 1, 0, SpriteSheet.tiles, 10, 16);

        Sprite.player_up_1 = new Sprite(Sprite.DEFAULT_SIZE, 0, 1, SpriteSheet.tiles, 12, 16);
        Sprite.player_up_2 = new Sprite(Sprite.DEFAULT_SIZE, 0, 2, SpriteSheet.tiles, 12, 15);

        Sprite.player_down_1 = new Sprite(Sprite.DEFAULT_SIZE, 2, 1, SpriteSheet.tiles, 12, 15);
        Sprite.player_down_2 = new Sprite(Sprite.DEFAULT_SIZE, 2, 2, SpriteSheet.tiles, 12, 16);

        Sprite.player_left_1 = new Sprite(Sprite.DEFAULT_SIZE, 3, 1, SpriteSheet.tiles, 11, 16);
        Sprite.player_left_2 = new Sprite(Sprite.DEFAULT_SIZE, 3, 2, SpriteSheet.tiles, 12 ,16);

        Sprite.player_right_1 = new Sprite(Sprite.DEFAULT_SIZE, 1, 1, SpriteSheet.tiles, 11, 16);
        Sprite.player_right_2 = new Sprite(Sprite.DEFAULT_SIZE, 1, 2, SpriteSheet.tiles, 12, 16);

        Sprite.player_dead1 = new Sprite(Sprite.DEFAULT_SIZE, 4, 2, SpriteSheet.tiles, 14, 16);
        Sprite.player_dead2 = new Sprite(Sprite.DEFAULT_SIZE, 5, 2, SpriteSheet.tiles, 13, 15);
        Sprite.player_dead3 = new Sprite(Sprite.DEFAULT_SIZE, 6, 2, SpriteSheet.tiles, 16, 16);
    }

    public static void changeToSecondPlayer() {
        Sprite.player_up = new Sprite(16, 4, 0, SpriteSheet.player, 12, 15);
        Sprite.player_down = new Sprite(16, 2, 0, SpriteSheet.player, 12, 16);
        Sprite.player_left = new Sprite(16, 0, 0, SpriteSheet.player, 12, 16);
        Sprite.player_right = new Sprite(16, 7, 0, SpriteSheet.player, 12, 16);

        Sprite.player_up_1 = new Sprite(16, 5, 0, SpriteSheet.player, 12, 15);
        Sprite.player_up_2 = Sprite.player_up; //new Sprite(16, 0, 2, SpriteSheet.player, 12, 15);

        Sprite.player_down_1 = new Sprite(16, 3, 0, SpriteSheet.player, 12, 16);
        Sprite.player_down_2 = Sprite.player_down; //new Sprite(16, 2, 2, SpriteSheet.player, 12, 16);

        Sprite.player_left_1 = new Sprite(16, 1, 0, SpriteSheet.player, 12, 15);
        Sprite.player_left_2 = Sprite.player_left; //new Sprite(16, 3, 2, SpriteSheet.player, 12 ,16);

        Sprite.player_right_1 = new Sprite(16, 6, 0, SpriteSheet.player, 12, 15);
        Sprite.player_right_2 = Sprite.player_right; //new Sprite(16, 1, 2, SpriteSheet.player, 12, 16);

        Sprite.player_dead1 = new Sprite(16, 0, 1, SpriteSheet.player, 16, 16);
        Sprite.player_dead2 = new Sprite(16, 1, 1, SpriteSheet.player, 16, 16);
        Sprite.player_dead3 = new Sprite(16, 2, 1, SpriteSheet.player, 16, 15);
    }
}