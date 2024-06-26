package oop.bomberman.moving.enemy;

import oop.bomberman.mainplay.BombermanGame;

import java.util.LinkedList;

public class BFS {
    public static String bfs(int x1, int y1, int x2, int y2) {
        if (BombermanGame.player1.isDead()) {
            return " ";
        }
        if (x1 == x2 && y1 == y2) {
            return " ";
        }
        char[][] path = new char[BombermanGame.HEIGHT][BombermanGame.WIDTH];
        int[][] go = new int[BombermanGame.HEIGHT][BombermanGame.WIDTH];
        LinkedList<Integer> intX = new LinkedList<>();
        LinkedList<Integer> intY = new LinkedList<>();
        for (int i = 0; i < BombermanGame.HEIGHT; ++i) {
            for (int j = 0; j < BombermanGame.WIDTH; ++j) {
                go[i][j] = -1;
            }
        }
        go[y1][x1] = 1;
        intX.add(x1);
        intY.add(y1);
        while (!intX.isEmpty()) {
            int X = intX.pollFirst();
            int Y = intY.pollFirst();
            int newX, newY;
            newX = X + 1; newY = Y;
            if (go[newY][newX] == -1 && BombermanGame.map[newY].charAt(newX) != '#'
                    && BombermanGame.map[newY].charAt(newX) != '*'
                    && BombermanGame.map[newY].charAt(newX) != 'B') {
                go[newY][newX] = 1;
                path[newY][newX] = 'R';
                intX.add(newX); intY.add(newY);
                if (newX == x2 && newY == y2) {
                    break;
                }
            }
            newX = X - 1; newY = Y;
            if (go[newY][newX] == -1 && BombermanGame.map[newY].charAt(newX) != '#'
                    && BombermanGame.map[newY].charAt(newX) != '*'
                    && BombermanGame.map[newY].charAt(newX) != 'B') {
                go[newY][newX] = 1;
                path[newY][newX] = 'L';
                intX.add(newX); intY.add(newY);
                if (newX == x2 && newY == y2) {
                    break;
                }
            }
            newX = X; newY = Y + 1;
            if (go[newY][newX] == -1 && BombermanGame.map[newY].charAt(newX) != '#'
                    && BombermanGame.map[newY].charAt(newX) != '*'
                    && BombermanGame.map[newY].charAt(newX) != 'B') {
                go[newY][newX] = 1;
                path[newY][newX] = 'D';
                intX.add(newX); intY.add(newY);
                if (newX == x2 && newY == y2) {
                    break;
                }
            }
            newX = X; newY = Y - 1;
            if (go[newY][newX] == -1 && BombermanGame.map[newY].charAt(newX) != '#'
                    && BombermanGame.map[newY].charAt(newX) != '*'
                    && BombermanGame.map[newY].charAt(newX) != 'B') {
                go[newY][newX] = 1;
                path[newY][newX] = 'U';
                intX.add(newX); intY.add(newY);
                if (newX == x2 && newY == y2) {
                    break;
                }
            }
        }
        String result = "";
        if (go[y2][x2] == -1) {
            return " ";
        } else {
            while (y2 != y1 || x2 != x1) {
                char type = path[y2][x2];
                result = type + result;
                if (type == 'R') {
                    x2--;
                } else if (type == 'L') {
                    x2++;
                } else if (type == 'U') {
                    y2++;
                } else if (type == 'D') {
                    y2--;
                }
            }
            return result;
        }
    }


}
