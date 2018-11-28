/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SPOJ_SOCIALNE_PossibleFriends;

import java.util.*;

/**
 *
 * @author King
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    final static int INF = Integer.MAX_VALUE;
    private static int V;
    private static int path[][];
    private static int dist[][];
    private static int graph[][];
    private static int nPossibleFriends[];
    private static int nFriends[];

    public static void printPath(int s, int t) {
        ArrayList<Integer> b = new ArrayList<>();
        while (s != t) {
            b.add(t);
            t = path[s][t];
        }
        b.add(s);
        for (int i = b.size() - 1; i >= 0; i--) {
            System.out.print(b.get(i) + " ");
        }
        System.out.println();
    }

    public static boolean Floyd_Warshall() {
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        path[i][j] = path[k][j];
                    }
                }
            }
        }
        for (int i = 0; i < V; i++) {
            if (dist[i][i] < 0) {
                return false;
            }
        }

        return true;
    }

    static void init(int v) {
        V = v;
        graph = new int[V][V];
        nPossibleFriends = new int[V];
        nFriends = new int[V];
    }

    static void readLine(String s, int iLine) {
        for (int j = 0; j < V; j++) {
            if (s.charAt(j) == 'Y') {
                graph[iLine][j] = 1;
                nFriends[iLine] += 1;
            } else {
                graph[iLine][j] = 0;
            }
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        int nTest = sc.nextInt();

        for (int t = 0; t < nTest; t++) {
            String s = sc.next();
            init(s.length());
            readLine(s, 0);
            for (int i = 1; i < V; i++) {
                s = sc.next();
                readLine(s, i);
            }
            //Floyd_Warshall();
            int max = 0;
            int iMax = 0;
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (graph[i][j] == 1) {
                        nPossibleFriends[i] += nFriends[j] - 1;
                    }
                }
                if (nPossibleFriends[i] > max) {
                    max = nPossibleFriends[i];
                    iMax = i;
                }
            }
            System.out.println(iMax + " " + max);
        }

    }

}
