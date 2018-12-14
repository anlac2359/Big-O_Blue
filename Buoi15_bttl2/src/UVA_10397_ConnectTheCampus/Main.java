/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package UVA_10397_ConnectTheCampus;

import java.awt.Point;
import java.io.*;
import java.util.*;

/**
 *
 * @author King
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static ArrayList<ArrayList<Node>> graph;
    static ArrayList<Point> pList;
    static double[] dist;
    static int[] path;
    static boolean[] visited;
    static int N, M;

    static class Node implements Comparable<Node> {

        public Integer id;
        public Integer dist;

        public Node(Integer id_in, Integer dist_in) {
            id = id_in;
            dist = dist_in;
        }

        @Override
        public int compareTo(Node other) {
            return dist.compareTo(other.dist);
        }
    }

    static int distance(Point pi, Point pj) {
        return (pi.x - pj.x) * (pi.x - pj.x) + (pi.y - pj.y) * (pi.y - pj.y);
    }

    static double result() {
        double ans = 0;
        for (int i = 0; i < N; i++) {
            ans += Math.sqrt(dist[i]);
            if (ans >= Integer.MAX_VALUE) {
                return -1;
            }
        }
        return ans;
    }

    static void prims(int src) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(src, 0));
        dist[src] = 0;
        
        while (!pq.isEmpty()) {
            int u = pq.peek().id;
            pq.poll();
            visited[u] = true;
            for (int i = 0; i < graph.get(u).size(); i++) {
                int v = graph.get(u).get(i).id;
                int w = (int) graph.get(u).get(i).dist;
                if (!visited[v] && dist[v] > w) {
                    dist[v] = w;
                    pq.add(new Node(v, w));
                    path[v] = u;
                }
            }
        }
    }

    static void Prims(int src) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(src, 0));
        dist[src] = 0;
        
        while (!pq.isEmpty()) {
            Node top = pq.poll();
            int u = top.id;
            visited[u] = true;
            for (int i = 0; i < graph.get(u).size(); i++) {
                Node neighbor = graph.get(u).get(i);
                int v = neighbor.id;
                int w = neighbor.dist;
                if (!visited[v] && w < dist[v]) {
                    dist[v] = w;
                    pq.add(new Node(v, w));
                    path[v] = u;
                }
            }
        }
    }

    static void resetData() {
        dist = null;
        path = null;
        visited = null;
        for (int i = 0; i < N; i++) {
            graph.get(i).clear();
        }
        graph = null;
        pList = null;
    }

    static void prepareData() {
        dist = new double[N];
        path = new int[N];
        visited = new boolean[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(path, -1);
        Arrays.fill(visited, false);
        graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        pList = new ArrayList<>();
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Reader r = new Reader();
        int x, y;

        while (true) {
            try {
                N = r.nextInt();
                prepareData();
                for (int i = 0; i < N; i++) {
                    x = r.nextInt();
                    y = r.nextInt();
                    pList.add(new Point(x, y));
                }
                boolean isExistingCables[][] = new boolean[N][N];
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        isExistingCables[i][j] = false;
                    }
                }
                M = r.nextInt();
                for (int i = 0; i < M; i++) {
                    x = r.nextInt();
                    y = r.nextInt();
                    isExistingCables[x - 1][y - 1] = true;
                    isExistingCables[y - 1][x - 1] = true;
                }
                for (int i = 0; i < N - 1; i++) {
                    for (int j = i + 1; j < N; j++) {
                        if (isExistingCables[i][j] == false) {
                            int w = distance(pList.get(i), pList.get(j));
                            graph.get(i).add(new Node(j, w));
                            graph.get(j).add(new Node(i, w));
                        } else {
                            graph.get(i).add(new Node(j, 0));
                            graph.get(j).add(new Node(i, 0));
                        }
                    }
                }
                Prims(0);
                double result = result();
                System.out.printf("%.2f%n", result);
                resetData();
            } catch (IOException e) {
                break;
            }
        }
    }

    static class Reader {

        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64];
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    break;
                }
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg) {
                return -ret;
            }
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg) {
                return -ret;
            }
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }

            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg) {
                return -ret;
            }
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) {
                buffer[0] = -1;
            }
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead) {
                fillBuffer();
            }
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null) {
                return;
            }
            din.close();
        }
    }
}
