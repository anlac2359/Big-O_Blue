/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SPOJ_MICEMAZEMiceandMaze;

import java.util.*;

/**
 *
 * @author King
 */
public class Main {

    private static int[] dist;
    private static int[] path;
    private static int N;
    private static int Exit;
    private static int W;
    private static int U;
    private static int nQV;
    private static ArrayList<Integer> QV = new ArrayList<>();
    private static ArrayList<ArrayList<Main.Node>> graph = new ArrayList<>();

    static class Node implements Comparable<Node> {
        public Integer id;
        public Integer dist;

        public Node(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node other) {
            return this.dist.compareTo(other.dist);
        }
    }

    public static void Dijkstra(int s) {
        PriorityQueue<Main.Node> pq = new PriorityQueue<>();
        dist = new int[N + 1];
        path = new int[N + 1];
        for (int i = 0; i < N; i++) {
            dist[i] = -1;
            path[i] = -1;
        }
        pq.add(new Main.Node(s, 0));
        dist[s] = 0;
        while (!pq.isEmpty()) {
            Main.Node top = pq.poll();
            int u = top.id;
            int w = top.dist;
            for (int i = 0; i < graph.get(u).size(); i++) {
                Main.Node neighbor = graph.get(u).get(i);
                if (dist[neighbor.id] == -1 || (w + neighbor.dist < dist[neighbor.id])) {
                    dist[neighbor.id] = w + neighbor.dist;
                    pq.add(new Main.Node(neighbor.id, dist[neighbor.id]));
                    path[neighbor.id] = u;
                }
            }
        }
    }

    static void readInput() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        Exit = sc.nextInt();
        W = sc.nextInt();
        int nE = sc.nextInt();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        int s;
        int f;
        int w;
        Node temp;
        for (int i = 0; i < nE; i++) {
            s = sc.nextInt();
            f = sc.nextInt();
            w = sc.nextInt();
            temp = new Main.Node(f, w);
            graph.get(s).add(temp);
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        readInput();
        Dijkstra(Exit);
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= W) {
                count++;
            }
        }
        System.out.println(count);
    }
}
