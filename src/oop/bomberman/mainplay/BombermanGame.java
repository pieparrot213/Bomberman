package oop.bomberman.mainplay;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import oop.bomberman.entities.Boom;
import oop.bomberman.entities.BoomExploded;
import oop.bomberman.entities.Entity;
import oop.bomberman.entities.candead.enemy.*;
import oop.bomberman.entities.candead.items.*;
import oop.bomberman.entities.staticEntity.Door;
import oop.bomberman.entities.staticEntity.Grass;
import oop.bomberman.entities.staticEntity.Portal;
import oop.bomberman.entities.staticEntity.Wall;
import oop.bomberman.entities.candead.Bomber;
import oop.bomberman.entities.candead.Brick;
import oop.bomberman.graphics.ChangeSprite;
import oop.bomberman.graphics.RenderImage;
import oop.bomberman.graphics.Sprite;
import oop.bomberman.loadmap.LoadMap;
import oop.bomberman.moving.player.AI;
import oop.bomberman.sound.AudioPlayer;
import oop.bomberman.sound.Sound;
import oop.bomberman.update.createBoom.BoomUpdate;
import oop.bomberman.update.dead.EnemyDead;
import oop.bomberman.update.dead.ItemDead;
import oop.bomberman.update.dead.PlayerDead;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BombermanGame extends Application {
    
    public static int WIDTH = 31;
    public static int HEIGHT = 13;
    public static String[] map;
    public static int time = 18000;

    private boolean winner = false;
    private boolean pause = false;
    private int startX, startY;
    private int level = 1;
    public static Bomber player1;
    public static List<Door> doorObjects = new ArrayList<>();
    private static GraphicsContext gc;
    private Canvas canvas;
    private List<Enemy> enemyObjects = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    private List<Boom> boomObjects = new ArrayList<>();
    private List<BoomExploded> boomExplodeds = new ArrayList<>();
    private List<Brick> brickObjects = new ArrayList<>();
    private List<Item> itemObjects = new ArrayList<>();
    private List<Portal> portalObjects = new ArrayList<>();


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
        AudioPlayer mainSound = new AudioPlayer("soundtrack");
        time = 18000;
        mainSound.run();
        createMap("res/levels/Level" + level + ".txt");
        ChangeSprite.changeToFirstPlayer();

        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        StackPane root = new StackPane();
        //root.setMinSize(992, 500);
        //root.setPrefSize(992, 500);
        //root.setMaxSize(992, 500);

        // Tao hbox o tren
        HBox front = new HBox();
        front.setMaxHeight(84);
        front.setMinHeight(84);
        front.setPrefHeight(84);

        //Tao vbox pause
        VBox pauseOption = new VBox();
        pauseOption.setAlignment(Pos.CENTER);
        pauseOption.setPrefSize(150, 150);
        pauseOption.setMaxSize(150, 150);
        pauseOption.setMinSize(150, 150);
        pauseOption.setStyle("-fx-background-color:  #FFFFCC");

        Button continueButton = new Button("Continue");
        continueButton.setPrefWidth(120);
        continueButton.setMinWidth(120);
        continueButton.setMaxWidth(120);

        Button soundButton = new Button();
        soundButton.setPrefWidth(120);
        soundButton.setMinWidth(120);
        soundButton.setMaxWidth(120);
        if (AudioPlayer.isMuted()) {
            soundButton.setText("Sound: Off");
        } else {
            soundButton.setText("Sound: On");
        }

        Button menuButton = new Button("Menu");
        menuButton.setPrefWidth(120);
        menuButton.setMinWidth(120);
        menuButton.setMaxWidth(120);

        pauseOption.getChildren().addAll(continueButton, soundButton, menuButton);
        VBox.setMargin(continueButton, new Insets(0, 0, 20, 0));
        VBox.setMargin(soundButton, new Insets(0, 0, 20, 0));
        pauseOption.setVisible(false);

        //Tao image win
        ImageView winnerImage = new ImageView(RenderImage.getWin());
        winnerImage.setFitHeight(500);
        winnerImage.setFitWidth(992);
        winnerImage.setVisible(false);

        //Tao text
        Text levelText = new Text("Level " + level);
        levelText.setFont(Font.font("Centaur", 48));
        Text timeText = new Text("Time 200");
        timeText.setFont(Font.font("Centaur", 30));

        //Tao heart
        ImageView heart1 = new ImageView(RenderImage.getHeartImg());
        heart1.setFitHeight(40); heart1.setFitWidth(40);
        ImageView heart2 = new ImageView(RenderImage.getHeartImg());
        heart2.setFitWidth(40); heart2.setFitHeight(40);
        ImageView heart3 = new ImageView(RenderImage.getHeartImg());
        heart3.setFitWidth(40); heart3.setFitHeight(40);
        ImageView heart4 = new ImageView(RenderImage.getHeartImg());
        heart4.setFitWidth(40); heart4.setFitHeight(40);
        ImageView heart5 = new ImageView(RenderImage.getHeartImg());
        heart5.setFitWidth(40); heart5.setFitHeight(40);


        //Merge into hbox
        front.getChildren().addAll(levelText, timeText, heart5, heart4, heart3, heart2, heart1);
        HBox.setMargin(levelText, new Insets(10, 0, 0, 20));
        HBox.setMargin(timeText, new Insets(23, 0, 0, 250));
        HBox.setMargin(heart5, new Insets(20, 0, 0, 160));
        HBox.setMargin(heart4, new Insets(20, 0, 0 , 0));
        HBox.setMargin(heart3, new Insets(20, 0, 0 , 0));
        HBox.setMargin(heart2, new Insets(20, 0, 0 , 0));
        HBox.setMargin(heart1, new Insets(20, 0, 0 , 0));
        front.setStyle("-fx-border-width: 3px;\n" +
                "    -fx-border-color:  #660000;\n" +
                "    -fx-background-color:  #FFFFCC;");

        root.getChildren().add(front);
        root.getChildren().add(canvas);
        root.getChildren().add(winnerImage);
        root.getChildren().add(pauseOption);
        StackPane.setMargin(canvas, new Insets(84, 0, 0 ,0 ));
        StackPane.setMargin(front, new Insets(0, 0, 416, 0));
        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
                if (time == 18000) {
                    levelText.setText("LEVEL " + level);
                }
                timeText.setText("TIME " + time/60);
                --time;
                //neu thua
                if ((player1.isDead() && player1.getHeart() == 0 && player1.getTiming() == 120)
                        || time == 0) {
                    clearAll();
                    this.stop();
                    mainSound.stop();
                    winnerImage.setImage(RenderImage.getLose());
                    winnerImage.setVisible(true);
                    Sound.play("lose");
                }
                //neu win
                if (winner) {
                    this.stop();
                    clearAll();
                    mainSound.stop();
                    winnerImage.setVisible(true);
                    Sound.play("win");

                }
                //update heart
                if (player1.getHeart() >= 1) heart1.setVisible(true); else heart1.setVisible(false);
                if (player1.getHeart() >= 2) heart2.setVisible(true); else heart2.setVisible(false);
                if (player1.getHeart() >= 3) heart3.setVisible(true); else heart3.setVisible(false);
                if (player1.getHeart() >= 4) heart4.setVisible(true); else heart4.setVisible(false);
                if (player1.getHeart() >= 5) heart5.setVisible(true); else heart5.setVisible(false);
            }
        };

        timer.start();

        //Bat su kien pause
        continueButton.setOnAction(event -> {
                timer.start();
                pause = false;
                mainSound.run();
                pauseOption.setVisible(false);
        });
        soundButton.setOnAction(event -> {
            if (AudioPlayer.isMuted()) {
                soundButton.setText("Sound: On");
            } else {
                soundButton.setText("Sound: Off");
            }
            mainSound.mute();
        });
        menuButton.setOnAction(event -> {
            clearAll();
            BoomExploded.StartBombermanGame main = new BoomExploded.StartBombermanGame();
            timer.stop();
            mainSound.stop();
            try {
                main.start(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //bat su kien ban phim
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:    player1.goNorth = true; break;
                case DOWN:  player1.goSouth = true; break;
                case LEFT:  player1.goWest  = true; break;
                case RIGHT: player1.goEast  = true; break;
                case SPACE: {
                    if (boomObjects.size() >= player1.getSizeOfBoom()) {
                        break;
                    }
                    if (map[player1.getLocationY()].charAt(player1.getLocationX()) == 'B') {
                        break;
                    }
                    if (player1.isDead() && player1.getTiming() <= 40) {
                        break;
                    }
                    Boom boom = new Boom(player1.getLocationX(), player1.getLocationY(), Sprite.bomb.getFxImage());
                    boomObjects.add(boom);
                    break;
                }
                case ESCAPE:
                    if (pause == false) {
                        timer.stop();
                        pause = true;
                        mainSound.stop();
                        pauseOption.setVisible(true);
                    } else {
                        timer.start();
                        pause = false;
                        mainSound.run();
                        pauseOption.setVisible(false);
                    }
            }
        });
        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case UP:    player1.goNorth = false; break;
                case DOWN:  player1.goSouth = false; break;
                case LEFT:  player1.goWest  = false; break;
                case RIGHT: player1.goEast  = false; break;
            }
        });

    }

    public void createMap(String input) {
        Random randomMap = new Random();
        int valueMap = randomMap.nextInt(2);
        if (valueMap == 1) {
            ChangeSprite.changeToFirstTheme();
        } else {
            ChangeSprite.changeToSecondTheme();
        }
        map = LoadMap.loadMap(input);
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                Entity objectEntity;
                if (map[j].charAt(i) == '#') {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                    stillObjects.add(object);
                } else if (map[j].charAt(i) == '*') {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                    objectEntity = new Brick(i, j, Sprite.brick.getFxImage());
                    brickObjects.add((Brick)objectEntity);
                } else if (map[j].charAt(i) == 'x') {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                    object = new Brick(i, j, Sprite.brick.getFxImage());
                    brickObjects.add((Brick)object);
                    objectEntity = new Portal(i, j, Sprite.portal.getFxImage());
                    portalObjects.add((Portal)objectEntity);
                    BombermanGame.map[j] = BombermanGame.map[j].substring(0, i) + "*" + BombermanGame.map[j].substring(i + 1);
                } else if (map[j].charAt(i) == 'p') {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                    objectEntity = new Bomber(i, j, Sprite.player_right.getFxImage());
                    player1 =  (Bomber) objectEntity;
                    startX = i; startY = j;
                } else if (map[j].charAt(i) == '1') {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                    objectEntity = new Balloon(i, j, Sprite.balloom_left1.getFxImage());
                    enemyObjects.add((Enemy)objectEntity);
                } else if (map[j].charAt(i) == '2') {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                    objectEntity = new Oneal(i, j, Sprite.oneal_right1.getFxImage());
                    enemyObjects.add((Enemy)objectEntity);
                } else if (map[j].charAt(i) == '3') {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                    objectEntity = new Ghost(i, j, Sprite.ghost_left1.getFxImage());
                    enemyObjects.add((Enemy)objectEntity);
                }
                else if (map[j].charAt(i) == '4') {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                    objectEntity = new CoinRed(i, j, Sprite.coinRed_left1.getFxImage());
                    enemyObjects.add((Enemy)objectEntity);
                } else if (map[j].charAt(i) == '5') {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                    objectEntity = new Doll(i, j, Sprite.doll_left1.getFxImage());
                    enemyObjects.add((Enemy) objectEntity);
                } else if (map[j].charAt(i) == 'b') {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                    objectEntity = new Brick(i, j, Sprite.brick.getFxImage());
                    brickObjects.add((Brick)objectEntity);
                    Item itemObject = new BombItem(i, j, Sprite.powerup_bombs.getFxImage());
                    itemObjects.add(itemObject);
                    BombermanGame.map[j] = BombermanGame.map[j].substring(0, i) + "*" + BombermanGame.map[j].substring(i + 1);
                } else if (map[j].charAt(i) == 'f') {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                    objectEntity = new Brick(i, j, Sprite.brick.getFxImage());
                    brickObjects.add((Brick)objectEntity);
                    Item itemObject = new FlameItem(i, j, Sprite.powerup_flames.getFxImage());
                    itemObjects.add(itemObject);
                    BombermanGame.map[j] = BombermanGame.map[j].substring(0, i) + "*" + BombermanGame.map[j].substring(i + 1);
                } else if (map[j].charAt(i) == 's') {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                    objectEntity = new Brick(i, j, Sprite.brick.getFxImage());
                    brickObjects.add((Brick)objectEntity);
                    Item itemObject = new SpeedItem(i, j, Sprite.powerup_speed.getFxImage());
                    itemObjects.add(itemObject);
                    BombermanGame.map[j] = BombermanGame.map[j].substring(0, i) + "*" + BombermanGame.map[j].substring(i + 1);
                } else if (map[j].charAt(i) == 'h') {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                    objectEntity = new Brick(i, j, Sprite.brick.getFxImage());
                    brickObjects.add((Brick)objectEntity);
                    Item itemObject = new HeartItem(i, j, Sprite.powerup_detonator.getFxImage());
                    itemObjects.add(itemObject);
                    BombermanGame.map[j] = BombermanGame.map[j].substring(0, i) + "*" + BombermanGame.map[j].substring(i + 1);
                } else if (map[j].charAt(i) == '?') {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                    objectEntity = new Brick(i, j, Sprite.brick.getFxImage());
                    brickObjects.add((Brick)objectEntity);
                    Item itemObject;
                    Random random = new Random();
                    int value = random.nextInt(4);
                    switch (value) {
                        case 0:
                            itemObject = new BombItem(i, j, Sprite.powerup_bombs.getFxImage());
                            break;
                        case 1:
                            itemObject = new FlameItem(i, j, Sprite.powerup_flames.getFxImage());
                            break;
                        case 2:
                            itemObject = new SpeedItem(i, j, Sprite.powerup_speed.getFxImage());
                            break;
                        default:
                            itemObject = new HeartItem(i, j, Sprite.powerup_detonator.getFxImage());
                    }
                    itemObjects.add(itemObject);
                    BombermanGame.map[j] = BombermanGame.map[j].substring(0, i) + "*" + BombermanGame.map[j].substring(i + 1);
                } else if (map[j].charAt(i) == 'd') {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                    objectEntity = new Door(i, j, Sprite.door.getFxImage());
                    doorObjects.add((Door)objectEntity);
                } else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                }
            }
        }
    }

    public void updateObject() {
        for (int i = 0; i < boomObjects.size(); ++i) {
            if (boomObjects.get(i).getTiming() == 180) {
                boomExplodeds = BoomUpdate.createBoomExplosion(boomObjects.get(i), player1,
                        boomExplodeds, brickObjects, boomObjects);
            }
            if (boomObjects.get(i).getTiming() == 195) {
                boomObjects.remove(i);
                --i;
            }
        }
        for (int i = 0; i < boomExplodeds.size(); ++i) {
            if (boomExplodeds.get(i).getTiming() == 15) {
                boomExplodeds.remove(i);
                --i;
            }
        }
        for (int i = 0; i < brickObjects.size(); ++i) {
            int time = brickObjects.get(i).getTiming();
            if (time == 15) {
                brickObjects.remove(i);
                --i;
            }
        }
        for (int i = 0; i < enemyObjects.size(); ++i) {
            Enemy entity = enemyObjects.get(i);
            if (!entity.isDead()) {
                EnemyDead.checkWhenDead(entity, boomExplodeds);
            }
            else {
                if (entity.getTiming() == 20) {
                    enemyObjects.remove(i);
                    --i;
                    if (entity instanceof CoinRed) {
                        Entity newEntity = new CoinOrange(entity.getX()/Sprite.SCALED_SIZE, entity.getY()/Sprite.SCALED_SIZE, Sprite.coinOrange_left1.getFxImage());
                        enemyObjects.add((Enemy)newEntity);
                    }
                }
            }
        }
        itemObjects = ItemDead.checkWhenDead(boomObjects, boomExplodeds, itemObjects);
        if (!player1.isDead()) {
            PlayerDead.checkWhenDead(player1, boomExplodeds, enemyObjects, boomObjects);
            for (Portal portal : portalObjects) {
                if (enemyObjects.size() == 0) {
                    portal.setImg(Sprite.portal_open.getFxImage());
                }
                if (portal.nextLevel(player1) == 1 && enemyObjects.size() == 0) {
                    if (level < 5) {
                        clearAll();
                        ++level;
                        createMap("res/levels/Level" + level + ".txt");
                        time = 18000;
                        return;
                    } else {
                        winner = true;
                    }
                }
            }
        } else if (player1.isDead() && player1.getTiming() == 10) {
            player1.setHeart(player1.getHeart() - 1);
        } else if (player1.isDead() && player1.getHeart() > 0 && player1.getTiming() == 40) {
            player1.setX(startX * Sprite.SCALED_SIZE);
            player1.setY(startY * Sprite.SCALED_SIZE);
            player1.setImg(Sprite.player_left.getFxImage());
            if (player1.getLengthOfBoom() > 1) player1.setLengthOfBoom(player1.getLengthOfBoom() - 1);
            if (player1.getSizeOfBoom() > 2) player1.setSizeOfBoom(player1.getSizeOfBoom() - 1);
            if (player1.getSpeed() > 1) player1.setSpeed(player1.getSpeed() - 1);
        } else if (player1.getTiming() == 220) {
            player1.setDead(false);
            player1.setTiming(0);
        }
    }

    public void clearAll() {
        boomObjects.clear();
        boomExplodeds.clear();
        itemObjects.clear();
        enemyObjects.clear();
        stillObjects.clear();
        portalObjects.clear();
        doorObjects.clear();
        brickObjects.clear();
    }
    public void AIupdate() {
        if (player1.getY() % 32 != 0 || (player1.getX() % 32 != 0 && player1.getX() % 32 != 1 && player1.getY() % 32 != 1 )) {
            return;
        }
        player1.goWest = false;
        player1.goEast = false;
        player1.goSouth = false;
        player1.goNorth = false;
        if (AI.placeBomb(player1, boomObjects, itemObjects, enemyObjects)) {
            Boom boom = new Boom(player1.getLocationX(), player1.getLocationY(), Sprite.bomb.getFxImage());
            boomObjects.add(boom);
        }
        switch (AI.directionToGo(player1, boomObjects, itemObjects, enemyObjects)) {
            case 0: player1.goSouth = true; break;
            case 1: player1.goNorth = true; break;
            case 2: player1.goWest = true; break;
            case 3: player1.goEast = true; break;
        }
    }

    public void update() {
        //AIupdate(); //AI chua hoan thien
        boomObjects.forEach(Boom::update);
        boomExplodeds.forEach(BoomExploded::update);
        brickObjects.forEach(Brick::update);
        itemObjects.forEach(Item::update);
        enemyObjects.forEach(Entity::update);
        player1.update();
        portalObjects.forEach(Portal::update);
        updateObject();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        doorObjects.forEach(g -> g.render(gc));
        portalObjects.forEach(g -> g.render(gc));
        boomObjects.forEach(g -> g.render(gc));
        boomExplodeds.forEach(g -> g.render(gc));
        itemObjects.forEach(g -> g.render(gc));
        brickObjects.forEach(g -> g.render(gc));
        enemyObjects.forEach(g -> g.render(gc));
        player1.render(gc);
    }
}
