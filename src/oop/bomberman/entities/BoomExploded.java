package oop.bomberman.entities;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.bomberman.graphics.Sprite;
import oop.bomberman.mainplay.BombermanGame;
import oop.bomberman.mainplay.MultiplayerBombermanGame;
import oop.bomberman.sound.AudioPlayer;

import java.io.IOException;
import java.io.InputStream;

public class BoomExploded extends Entity { // bom nổ
    private char typeExplosion; //loại nổ
    private int timing = 0;

    public int getTiming() {
        return timing;
    }

    public BoomExploded(int x, int y, Image img, char typeExplosion) {
        super(x, y, img);
        this.typeExplosion = typeExplosion;
    }

    @Override
    public void update() {
        ++timing;
        if (timing == 5) {
            switch (typeExplosion) {
                case 'h':
                    img = Sprite.explosion_horizontal1.getFxImage();
                    break;
                case 'v':
                    img = Sprite.explosion_vertical1.getFxImage();
                    break;
                case 't':
                    img = Sprite.explosion_vertical_top_last1.getFxImage();
                    break;
                case 'd':
                    img = Sprite.explosion_vertical_down_last1.getFxImage();
                    break;
                case 'r':
                    img = Sprite.explosion_horizontal_right_last1.getFxImage();
                    break;
                case 'l':
                    img = Sprite.explosion_horizontal_left_last1.getFxImage();
                    break;
            }
        } else if (timing == 10) {
            switch (typeExplosion) {
                case 'h':
                    img = Sprite.explosion_horizontal2.getFxImage();
                    break;
                case 'v':
                    img = Sprite.explosion_vertical2.getFxImage();
                    break;
                case 't':
                    img = Sprite.explosion_vertical_top_last2.getFxImage();
                    break;
                case 'd':
                    img = Sprite.explosion_vertical_down_last2.getFxImage();
                    break;
                case 'r':
                    img = Sprite.explosion_horizontal_right_last2.getFxImage();
                    break;
                case 'l':
                    img = Sprite.explosion_horizontal_left_last2.getFxImage();
                    break;
            }
        }
    }

    public static class StartBombermanGame extends Application {

        public static int type;

        public static void main(String[] args) {
            Application.launch(args);
        }

        @Override
        public void start(Stage primaryStage) throws IOException {
            AudioPlayer startMusic = new AudioPlayer("renai");
            startMusic.run();
            StackPane layout = new StackPane();
            layout.setMaxSize(1000, 500);
            layout.setPrefSize(1000, 500);
            layout.setMinSize(1000, 500);

            //Set image background
            InputStream input = this.getClass().getResourceAsStream("/image/background.jpg");
            Image image = new Image(input);
            ImageView imageView = new ImageView(image);
            imageView.setPickOnBounds(true);
            imageView.setFitWidth(1000);
            imageView.setFitHeight(500);


            //Set Vbox
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.BOTTOM_CENTER);
            vBox.setPrefWidth(100);
            vBox.setPrefHeight(200);

            //Set Button
            //Play button
            Button playButton = new Button("Bắt đầu chơi");
            playButton.setMaxWidth(300);
            playButton.setMinWidth(300);
            playButton.setPrefWidth(150);
            playButton.setOnAction(event -> {
                type = 1;
                startMusic.stop();
                BombermanGame newGame = new BombermanGame();
                try {
                    newGame.start(primaryStage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            //Double Play button
            Button doublePlay = new Button("Chế độ 2 người chơi");
            doublePlay.setMaxWidth(300);
            doublePlay.setMinWidth(300);
            doublePlay.setPrefWidth(150);
            doublePlay.setOnAction(event -> {
                type = 2;
                startMusic.stop();
                MultiplayerBombermanGame newGame = new MultiplayerBombermanGame();
                try {
                    newGame.start(primaryStage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            //Sound button
            Button soundButton = new Button();
            if (AudioPlayer.isMuted()) {
                soundButton.setText("Âm nhạc: Tắt");
            } else {
                soundButton.setText("Âm nhạc: Bật");
            }
            soundButton.setPrefWidth(300);
            soundButton.setMinWidth(300);
            soundButton.setMaxWidth(150);
            soundButton.setOnAction(event -> {
                if (AudioPlayer.isMuted()) {
                    soundButton.setText("Âm nhạc: Bật ");
                } else {
                    soundButton.setText("Âm nhạc: Tắt");
                }
                startMusic.mute();
            });

            //Exit button
            Button exitButton = new Button("Thoát");
            exitButton.setMaxWidth(300);
            exitButton.setMinWidth(300);
            exitButton.setPrefWidth(150);
            exitButton.setOnAction(event -> {
                primaryStage.close();
            });


            vBox.getChildren().addAll(playButton, doublePlay, soundButton, exitButton);
            VBox.setMargin(playButton, new Insets(0, 0, 30, 0));
            VBox.setMargin(doublePlay, new Insets(0, 0, 30, 0));
            VBox.setMargin(soundButton, new Insets(0, 0, 30, 0));
            VBox.setMargin(exitButton, new Insets(0, 0, 80, 0));


            layout.getChildren().add(imageView);
            layout.getChildren().add(vBox);
            Scene scene = new Scene(layout);
            scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }
}
