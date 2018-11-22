/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tgk_bai4;

import java.awt.Point;
import java.util.*;

/**
 *
 * @author King
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static int dx[] = {0, 0, 1, -1};
    static int dy[] = {1, -1, 0, 0};
    static ArrayList<ArrayList<Integer>> maze = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> dist = new ArrayList<>();
    static int R;
    static int C;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        while (R != 0 && C != 0) {
            int nBooms = sc.nextInt();
            for (int i = 0; i < R; i++) {
                maze.add(new ArrayList<>());
                dist.add(new ArrayList<>());
                for (int j = 0; j < C; j++) {
                    maze.get(i).add(0);
                    dist.get(i).add(-1);
                }
            }
            for (int k = 0; k < nBooms; k++) {
                int rB = sc.nextInt();
                int nBR = sc.nextInt();
                int cB;
                for (int i = 0; i < nBR; i++) {
                    cB = sc.nextInt();
                    maze.get(rB).set(cB, 1);
                }
            }
            Point s = new Point(sc.nextInt(), sc.nextInt());
            Point f = new Point(sc.nextInt(), sc.nextInt());
            bfs(s);
            System.out.println(dist.get(f.x).get(f.y));
            R = sc.nextInt();
            C = sc.nextInt();
            for (int i = 0; i < R; i++) {
                maze.get(i).clear();
                dist.get(i).clear();
            }
            maze.clear();
            dist.clear();
        }
    }

    public static void bfs(Point s) {
        Queue<Point> q = new LinkedList<>();
        q.add(s);
        dist.get(s.x).set(s.y, 0);
        int x, y;
        Point u;
        while (!q.isEmpty()) {
            u = q.poll();
            for (int i = 0; i < 4; i++) {
                x = u.x + dx[i];
                y = u.y + dy[i];
                if (x >= 0 && x < C && y >= 0 && y < R) {
                    if (dist.get(x).get(y) == -1 && maze.get(x).get(y) == 0) {
                        dist.get(x).set(y, dist.get(u.x).get(u.y) + 1);
                        q.add(new Point(x, y));
                    }
                }
            }
        }
    }

}
