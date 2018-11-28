/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package UVA_558_Wormholes;

import java.util.*;

/**
 *
 * @author King
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static ArrayList<Edge> graph = new ArrayList<>();
    static int[] dist = new int[1005];
    static int n;
    static int m;
    
    static class Edge {
        public int source;
        public int target;
        public int weight;
        public Edge (int s, int t, int w) {
            source = s;
            target = t;
            weight = w;
        }
    }
    
    public static boolean BellmanFord(int s) {
        int u, v, w;
        dist[s] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                u = graph.get(j).source;
                v = graph.get(j).target;
                w = graph.get(j).weight;
                if (dist[u] != Integer.MAX_VALUE && (dist[u] + w < dist[v])) {
                    dist[v] = dist[u] + w;
                }
            }
        }
        for (int j = 0; j < m; j++) {
                u = graph.get(j).source;
                v = graph.get(j).target;
                w = graph.get(j).weight;
                if (dist[u] != Integer.MAX_VALUE && (dist[u] + w < dist[v])) {
                    return false;
                }
            }
        return true;
    }
    
    static void clearData() {
        for (int i = 0; i < 1005; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        graph.clear();
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
        for (int i = 0; i < c; i++) {
            clearData();
            n = sc.nextInt();
            m = sc.nextInt();
            for (int j = 0; j < m; j++) {
                graph.add(new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt()));
            }
            if (!BellmanFord(graph.get(0).source)) {
                System.out.println("possible");
            } else {
                System.out.println("not possible");
            }
        }
    }
    
}
