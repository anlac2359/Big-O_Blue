/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SPOJ_TravellingCost;

import java.util.*;

/**
 *
 * @author Administrator
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static Scanner sc = new Scanner(System.in);
    static final int maxn = 10000;
    static int dist[] = new int[maxn];
    static ArrayList<pair> a[] = new ArrayList[maxn];
    static int n;
    static final int oo = Integer.MAX_VALUE;
    
    static class pair {
        int w, node;
    }
    static pair make(int x, int y) {
        pair a = new pair();
        a.node = x;
        a.w = y;
        return a;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        n = sc.nextInt();
        for (int i = 0; i < maxn; i++) {
            a[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int W = sc.nextInt();
            a[x].add(make(y, W));
            a[y].add(make(x, W));
        }
        int s = sc.nextInt();
        dijktra(s);
        int m = sc.nextInt();
        for (int i = 1; i <= m; i++) {
            int t = sc.nextInt();
            if (dist[t] == oo) {
                System.out.println("NO PATH");
            } else {
                System.out.println(dist[t]);
            }
        }
    }
    static void dijktra(int s) {
        PriorityQueue<pair> q = new PriorityQueue<>(new Comparator<pair>() {
            @Override
            public int compare(pair a, pair b) {
                return (a.w < b.w) ? 1 : 0;
            }
        });
        for (int i = 0; i < maxn; i++) {
            dist[i] = oo;
        }
        dist[s] = 0;
        q.add(make(s, 0));
        while (q.size() != 0) {
            pair u = q.poll();
            for (int i = 0; i < a[u.node].size(); i++) {
                int v = a[u.node].get(i).node;
                if (dist[v] > u.w + a[u.node].get(i).w) {
                    dist[v] = u.w + a[u.node].get(i).w;
                    q.add(make(v, dist[v]));
                }
            }
        }
    }
}
