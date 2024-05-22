package oop.bomberman.loadmap;

import oop.bomberman.mainplay.BombermanGame;

import java.io.File;
import java.util.Scanner;

public class LoadMap {
    public static String[] loadMap(String address) {
        try {
            Scanner sc = new Scanner(new File(address));
            int level = sc.nextInt();
            int height = sc.nextInt();
            int width = sc.nextInt();
            BombermanGame.WIDTH = width;
            BombermanGame.HEIGHT = height;
            String[] s = new String[height];
            s[0] = sc.nextLine();
            for (int i = 0; i < height; ++i) {
                s[i] = sc.nextLine();
            }
            return s;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            String[] s = new String[0];
            return s;
        }
    }
}
