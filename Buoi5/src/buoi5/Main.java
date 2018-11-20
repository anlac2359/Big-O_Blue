/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buoi5;

import java.awt.Point;
import java.util.*;

/**
 *
 * @author King UCV2013H - Slick https://www.spoj.com/problems/UCV2013H/
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static int N;
    static int M;
    static ArrayList<ArrayList<Integer>> graph;
    static ArrayList<ArrayList<Boolean>> visited;
    static ArrayList<Integer> path;
    Point p;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static void readMatrix() {
        graph = new ArrayList<>();
        visited = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
            visited.add(new ArrayList<>());
        }
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                graph.get(i).add(sc.nextInt());
                visited.get(i).add(false);
            }
        }
    }

    static void clearMatrix() {
        for (int i = 0; i < N; i++) {
            graph.get(i).clear();
        }
        graph.clear();
        for (int i = 0; i < N; i++) {
            visited.get(i).clear();
        }
        visited.clear();
    }

    public static int bfs(Point p) {
        Queue<Point> q = new LinkedList<>();
        path = new ArrayList<>();
        q.add(p);

        while (!q.isEmpty()) {
            Point u = q.poll();
            for (int i = 0; i < N; i++) {
                /*
                Long value = a.get(i).longValue() * s;
                value %= 100000;
                if (patht[value.intValue()] == -1) {
                    patht[value.intValue()] = patht[s] + 1;
                    q.add(value.intValue());
                    if (value.intValue() == d) {
                        return patht[d];
                    }
                }
*/
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        while (N != 0 && M != 0) {
            readMatrix();

            clearMatrix();
            N = sc.nextInt();
            M = sc.nextInt();
        }
    }

}
