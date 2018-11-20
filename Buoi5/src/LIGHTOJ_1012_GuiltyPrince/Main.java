/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LIGHTOJ_1012_GuiltyPrince;

import java.awt.Point;
import java.util.*;

/**
 *
 * @author King
 */
public class Main {

    static int dx[] = {0, 0, 1, -1};
    static int dy[] = {1, -1, 0, 0};
    static final int MAX = 22;
    static boolean visited[][] = new boolean[MAX][MAX];
    static ArrayList<String> maze = new ArrayList<>();

    static int t, w, h;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Point p = null;
        t = sc.nextInt();
        String temp;

        for (int k = 0; k < t; k++) {
            w = sc.nextInt();
            h = sc.nextInt();
            sc.nextLine();
            for (int i = 0; i < h; i++) {
                temp = sc.nextLine();
                maze.add(temp);
                for (int j = 0; j < w; j++) {
                    if (maze.get(i).charAt(j) == '@') {
                        p = new Point(i, j);
                    }
                    visited[i][j] = false;
                }
            }
            int result = bfs(p);
            System.out.println("Case " + (k + 1) + ": " + result);
            maze.clear();
        }
    }

    public static int bfs(Point s) {
        Queue<Point> q = new LinkedList<>();
        q.add(s);
        int result = 1;
        int x, y;
        Point u;
        visited[s.x][s.y] = true;
        while (!q.isEmpty()) {
            u = q.poll();
            for (int i = 0; i < 4; i++) {
                x = u.x + dx[i];
                y = u.y + dy[i];
                if (x >= 0 && x < h) {
                    if (y >= 0 && y < w) {
                        if (visited[x][y] == false && maze.get(x).charAt(y) == '.') {
                            visited[x][y] = true;
                            result++;
                            q.add(new Point(x, y));
                        }
                    }
                }
            }
        }
        return result;
    }
}
