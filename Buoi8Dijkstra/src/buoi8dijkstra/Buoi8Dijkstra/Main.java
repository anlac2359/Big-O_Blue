/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package buoi8dijkstra.Buoi8Dijkstra;

import java.util.*;

/**
 *
 * @author King
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    private static int[] dist;
    private static int[] path;
    private static int N = 550;
    private static int U;
    private static int nQV;
    private static ArrayList<Integer> QV = new ArrayList<>();
    private static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();

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
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist = new int[N];
        path = new int[N];
        for (int i = 0; i < N; i++) {
            dist[i] = -1;
            path[i] = -1;
        }
        pq.add(new Node(s, 0));
        dist[s] = 0;
        while (!pq.isEmpty()) {
            Node top = pq.poll();
            int u = top.id;
            int w = top.dist;
            for (int i = 0; i < graph.get(u).size(); i++) {
                Node neighbor = graph.get(u).get(i);
                if (dist[neighbor.id] == -1 || (w + neighbor.dist < dist[neighbor.id])) {
                    dist[neighbor.id] = w + neighbor.dist;
                    pq.add(new Node(neighbor.id, dist[neighbor.id]));
                    path[neighbor.id] = u;
                }
            }
        }
    }

    static void readInput() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s;
        int f;
        int w;
        Node temp;
        Node temp2;
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            s = sc.nextInt();
            f = sc.nextInt();
            w = sc.nextInt();
            temp = new Node(f, w);
            temp2 = new Node(s, w);
            graph.get(s).add(temp);
            graph.get(f).add(temp2);
            /*
            boolean flag = true;
            for (int j = 0; j < graph.get(s).size(); j++) {
                Node nodeTemp = graph.get(s).get(j);
                if (nodeTemp.id == f) {
                    if (w < nodeTemp.dist) {
                        nodeTemp.dist = w;
                        flag = false;
                    }
                }
            }
            if (flag) {
                graph.get(s).add(temp);
            }*/

        }
        U = sc.nextInt();
        nQV = sc.nextInt();
        for (int i = 0; i < nQV; i++) {
            QV.add(sc.nextInt());
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        readInput();
        Dijkstra(U);
        for (int i = 0; i < nQV; i++) {
            if (dist[QV.get(i)] != -1) {
                System.out.println(dist[QV.get(i)]);
            } else {
                System.out.println("NO PATH");
            }
        }
    }

}
