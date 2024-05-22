package oop.bomberman.graphics;

import javafx.scene.image.Image;

import java.io.InputStream;

public class RenderImage {
    public static Image getHeartImg() {
        InputStream input = RenderImage.class.getClass().getResourceAsStream("/image/heart.png");
        Image image = new Image(input);
        return image;
    }

    public static Image getTheme(int stt) {
        InputStream input = RenderImage.class.getClass().getResourceAsStream("/image/theme" + stt + ".png");
        Image image = new Image(input);
        return image;
    }

    public static Image getWin() {
        InputStream input = RenderImage.class.getClass().getResourceAsStream("/image/win.jpg");
        Image image = new Image(input);
        return image;
    }

    public static Image getPlayerWin(int stt) {
        InputStream input = RenderImage.class.getClass().getResourceAsStream("/image/player" + stt + ".png");
        Image image = new Image(input);
        return image;
    }

    public static Image getLose() {
        InputStream input = RenderImage.class.getClass().getResourceAsStream("/image/lose.jpg");
        Image image = new Image(input);
        return image;
    }

}
