/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package SPOJ_Makemaze_ValidateTheMaze;

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

    static int t, m, n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Point> points = new ArrayList<>();
        t = sc.nextInt();
        String temp;

        for (int k = 0; k < t; k++) {
            m = sc.nextInt();
            n = sc.nextInt();
            sc.nextLine();
            for (int i = 0; i < m; i++) {
                temp = sc.nextLine();
                maze.add(temp);
                for (int j = 0; j < n; j++) {
                    if (maze.get(i).charAt(j) == '.' && (i == 0 || i == (m - 1) || j == 0 || j == (n - 1))) {
                        points.add(new Point(i, j));
                    }
                    visited[i][j] = false;
                }
            }
            if (points.size() == 2) {
                boolean flag = bfs(points.get(0), points.get(1));
                if (flag == true) {
                    System.out.println("valid");
                } else {
                    System.out.println("invalid");
                }
            } else {
                System.out.println("invalid");
            }
            maze.clear();
            points.clear();
        }
    }

    public static boolean bfs(Point s, Point f) {
        Queue<Point> q = new LinkedList<>();
        q.add(s);
        int x, y;
        Point u;
        visited[s.x][s.y] = true;
        while (!q.isEmpty()) {
            u = q.element();
            q.remove();
            for (int i = 0; i < 4; i++) {
                x = u.x + dx[i];
                y = u.y + dy[i];
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    if (visited[x][y] == false && maze.get(x).charAt(y) == '.') {
                        visited[x][y] = true;
                        if (x == f.x && y == f.y) {
                            return true;
                        }
                        q.add(new Point(x, y));
                    }
                }
            }
        }
        return false;
    }
}
