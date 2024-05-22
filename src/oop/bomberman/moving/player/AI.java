package oop.bomberman.moving.player;

import oop.bomberman.entities.Boom;
import oop.bomberman.entities.candead.Bomber;
import oop.bomberman.entities.candead.enemy.*;
import oop.bomberman.entities.candead.items.Item;
import oop.bomberman.graphics.Sprite;
import oop.bomberman.mainplay.BombermanGame;
import oop.bomberman.entities.candead.enemy.*;

import java.util.List;

public class AI {

    private static int[] tx = {0, 0, -1, 1, 0};
    private static int[] ty = {1, -1, 0, 0, 0};

    public static int findingPath(int x, int y, int count, int maxCount, Bomber player, List<Boom> booms, List<Item> items, List<Enemy> enemies) {
        if (count == maxCount) return 2;
        int res = check(x, y, count, player, booms, items, enemies);
        for (int t = 0; t <= 4; ++t) {
            int X = x + tx[t];
            int Y = y + ty[t];
            if (BombermanGame.map[Y].charAt(X) != '*'
                    && BombermanGame.map[Y].charAt(X) != '#') {
                if (BombermanGame.map[y].charAt(x) != 'B' && BombermanGame.map[Y].charAt(X) == 'B') {

                } else {
                    res = Math.min(res, findingPath(X, Y, count + 1, maxCount, player, booms, items, enemies));
                }
            }
        }
        return res;
    }

    public static int check(int x, int y, int count, Bomber player, List<Boom> booms, List<Item> items, List<Enemy> enemies) {
        int time = count * Sprite.SCALED_SIZE / player.getSpeed();
        if (booms.size() > 0) {
            int timeBoom = booms.get(0).getTiming();
            if (180 - timeBoom <= time) {
                for (int i = 0; i < booms.size(); ++i) {
                    int X = booms.get(i).getX() / Sprite.SCALED_SIZE;
                    int Y = booms.get(i).getY() / Sprite.SCALED_SIZE;
                    for (int j = X; j <= X + player.getLengthOfBoom(); ++j) {
                        if (BombermanGame.map[Y].charAt(j) == '*'
                                || BombermanGame.map[Y].charAt(j) == '#') {
                            break;
                        }
                        if (j == x && Y == y) return 0;
                    }
                    for (int j = X - 1; j >= X - player.getLengthOfBoom(); --j) {
                        if (BombermanGame.map[Y].charAt(j) == '*'
                                || BombermanGame.map[Y].charAt(j) == '#') {
                            break;
                        }
                        if (j == x && Y == y) return 0;
                    }
                    for (int j = Y + 1; j <= Y + player.getLengthOfBoom(); ++j) {
                        if (BombermanGame.map[j].charAt(X) == '*'
                                || BombermanGame.map[j].charAt(X) == '#') {
                            break;
                        }
                        if (X == x && j == y) return 0;
                    }
                    for (int j = Y - 1; j >= Y - player.getLengthOfBoom(); --j) {
                        if (BombermanGame.map[j].charAt(X) == '*'
                                || BombermanGame.map[j].charAt(X) == '#') {
                            break;
                        }
                        if (X == x && j == y) return 0;
                    }
                }
            }
        }
        if (enemies.size() > 0) {
            for (Enemy enemy : enemies) {
                if (enemy instanceof CoinOrange || enemy instanceof Oneal) {
                    int buoc = time / 16;
                    int gt = dfs(enemy.getX()/32, enemy.getY()/32, x, y, 1, buoc );
                    if (gt == 1) return 0;
                } else if (enemy instanceof Balloon || enemy instanceof CoinRed) {
                    int buoc = time / 32;
                    int gt = dfs(enemy.getX()/32, enemy.getY()/32, x, y, 1, buoc);
                    if (gt == 1) return 1;
                } else if (enemy instanceof Ghost) {
                    int X = enemy.getX()/32;
                    int Y = enemy.getY()/32;
                    if (Math.abs(X - x) + Math.abs(Y - y) <= time / 32) return 1;
                } else {
                    int X = enemy.getX()/32;
                    int Y = enemy.getY()/32;
                    if (Y == y && Math.abs(X-x) <= time/16) return 1;
                }
            }
        }
        if (items.size() > 0) {
            for (Item item : items) {
                if (item.getX() / 32 == x && item.getY() / 32 == y) {
                    return 4;
                }
            }
        }
        return 3;
    }

    public static int dfs(int x, int y, int pX, int pY, int buoc, int maxBuoc) {
        if (buoc >= maxBuoc) return 0;
        if (x == pX && y == pY) return 1;
        int res = 0;
        for (int t = 0; t <= 3; ++t) {
            int X = x + tx[t];
            int Y = y + ty[t];
            if (BombermanGame.map[Y].charAt(X) == '#'
                    || BombermanGame.map[Y].charAt(X) == '*'
                    || BombermanGame.map[Y].charAt(X) == 'B') {
                continue;
            } else {
                res = Math.max(res, dfs(X, Y, pX, pY, buoc + 1, maxBuoc));
            }
        }
        return res;
    }

    public static int finding(int x, int y, List<Enemy> enemies) {
        int gt = 1000;
        for (Enemy enemy : enemies) {
            int X = enemy.getX() / 32;
            int Y = enemy.getY() / 32;
            if (enemy instanceof Balloon || enemy instanceof Ghost || enemy instanceof Doll) {
                if (Math.abs(X-x) + Math.abs(Y-y) >= 4)
                    gt = Math.min(gt, Math.abs(x - X) + Math.abs(y - Y));
            }
        }
        if (gt != 1000) {
            return gt;
        }
        for (Enemy enemy : enemies) {
            int X = enemy.getX() / 32;
            int Y = enemy.getY() / 32;
            if (Math.abs(X-x) + Math.abs(Y-y) >= 4)
                gt = Math.min(gt, Math.abs(x - X) + Math.abs(y - Y));
        }
        return gt;
    }

    public static boolean placeBomb(Bomber player, List<Boom> booms, List<Item> items, List<Enemy> enemies) {
        if (BombermanGame.map[player.getLocationY()].charAt(player.getLocationX()) == 'B') {
            return false;
        }
        if (booms.size() >= player.getSizeOfBoom()) {
            return false;
        }
        int x = player.getLocationX();
        int y = player.getLocationY();
        Boom boom1 = new Boom(player.getLocationX(), player.getLocationY(), Sprite.bomb.getFxImage());
        booms.add(boom1);
        BombermanGame.map[y] = BombermanGame.map[y].substring(0, x) + "B" + BombermanGame.map[y].substring(x + 1);
        for (Boom boom : booms) {
            if (boom.getTiming() > 180) continue;
            int X = boom.getX() / 32;
            int Y = boom.getY() / 32;
            if ((y == Y && Math.abs(X - x) <= player.getLengthOfBoom())
                    || (x == X && Math.abs(Y - y) <= player.getLengthOfBoom())) {
                int gt = findingPath(x, y, 0,
                        Math.min((180 - boom.getTiming())/(32/player.getSpeed()), 4), player, booms, items, enemies);
                if (gt == 0) return false;
                break;
            }
        }
        BombermanGame.map[y] = BombermanGame.map[y].substring(0, x) + " " + BombermanGame.map[y].substring(x + 1);
        booms.remove(booms.size() - 1);
        for (Enemy enemy : enemies) {
            int X = enemy.getX() / 32;
            int Y = enemy.getY() / 32;
            if (enemy instanceof Doll) {
                if (y == Y && Math.abs(X - x) <= 5) {
                    return true;
                }
            } else {
                if (Math.abs(Y - y) + Math.abs(X - x) <= 5) {
                    return true;
                }
            }
        }
        for (int t = 0; t <= 3; ++t) {
            int X = x + tx[t];
            int Y = y + ty[t];
            if (BombermanGame.map[Y].charAt(X) == '*') {
                return true;
            }
        }
        return false;
    }

    public static int directionToGo(Bomber player, List<Boom> booms, List<Item> items, List<Enemy> enemies) {
        int x = player.getLocationX();
        int y = player.getLocationY();
        int huong = -1;
        int[] a = new int[5];
        int max = 0, gt = 1000;
        for (int t = 0; t <= 4; ++t) {
            int X = x + tx[t];
            int Y = y + ty[t];
            if (BombermanGame.map[Y].charAt(X) == '#' || BombermanGame.map[Y].charAt(X) == '*') {
                continue;
            }
            if (BombermanGame.map[Y].charAt(X) == 'B' && BombermanGame.map[Y].charAt(X) != 'B') {
                continue;
            }
            a[t] = findingPath(X, Y, 1, 4, player, booms, items, enemies);
            if (a[t] > max) {
                max = a[t];
                gt = finding(X, Y, enemies);
                huong = t;
            } else if (a[t] == max) {
                if (finding(X, Y, enemies) < gt) {
                    gt = finding(X, Y, enemies);
                    huong = t;
                }
            }
        }
        return huong;
    }

}
