/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LightOJ_1074_ExtendedTraffic;

import java.util.*;

/**
 *
 * @author King
 */
public class Main {

    static ArrayList<Edge> graph = new ArrayList<>();
    static ArrayList<Integer> busyness = new ArrayList<>();
    static int[] dist = new int[1005];
    static int n;
    static int m;

    static class Edge {
        public int source;
        public int target;
        public int weight;

        public Edge(int s, int t, int w) {
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
        busyness.clear();
        graph.clear();
        
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            clearData();
            n = sc.nextInt();
            for (int j = 0; j < n; j++) {
                busyness.add(sc.nextInt());
            }
            m = sc.nextInt();
            for (int j = 0; j < m; j++) {
                int s = sc.nextInt();
                int t = sc.nextInt();
                double w = Math.pow(busyness.get(t - 1) - busyness.get(s - 1), 3);
                graph.add(new Main.Edge(s, t, (int) w));
            }
            boolean result = BellmanFord(1);
            int nTarget = sc.nextInt();
            System.out.println("Case " + i + ":");
            for (int j = 1; j <= nTarget; j++) {
                int caseTarget = sc.nextInt();
                if (result && dist[caseTarget] > 2) {
                    System.out.println(dist[caseTarget]);
                } else {
                    System.out.println("?");
                }
            }

        }
    }
}
