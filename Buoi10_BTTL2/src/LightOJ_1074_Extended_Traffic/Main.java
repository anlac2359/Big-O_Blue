/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LightOJ_1074_Extended_Traffic;

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
    static ArrayList<Edge> graph = new ArrayList<>();
    static int[] dist;
    static int[] weight;
    static int n, m;
    static final int INF = Integer.MAX_VALUE;

    static class Edge {

        public int source;
        public int target;
        public int weight;

        public Edge(int source, int target, int weight) {
            this.source = source;
            this.target = target;
            this.weight = weight;
        }
    }

    static void prepareData() throws IOException {
        Reader r = new Reader();
        n = r.nextInt();
        dist = new int[n];
        weight = new int[n + 1];
        Arrays.fill(dist, INF);
        int w, u, v;
        for (int i = 1; i <= n; i++) {
            w = r.nextInt();
            weight[i] = w;
        }
        m = r.nextInt();
        for (int i = 0; i < m; i++) {
            u = r.nextInt() - 1;
            v = r.nextInt() - 1;
            graph.add(new Edge(u, v, (int) Math.pow(weight[v] - weight[u], 3)));
        }
    }

    static void resetData() {
        graph.clear();
        dist = null;
        weight = null;
    }

    static boolean BellmanFord(int source) {
        int u, v, w;
        dist[source] = 0;
        for (int j = 0; j < dist.length; j++) {
            for (int l = 0; l < graph.size(); l++) {
                u = graph.get(l).source;
                v = graph.get(l).target;
                w = graph.get(l).weight;
                if (dist[u] != INF) {
                    if (dist[u] + w < dist[v]) {
                        dist[v] = dist[u] + w;
                    }
                }
            }
        }
        for (int i = 0; i < graph.size(); i++) {
            u = graph.get(i).source;
            v = graph.get(i).target;
            w = graph.get(i).weight;
            if (dist[u] != INF) {
                if (dist[u] + w < dist[v])
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Reader r = new Reader();
        int T, q, t, result;

        T = r.nextInt();
        for (int i = 1; i <= T; i++) {
            prepareData();
            BellmanFord(0);
            q = r.nextInt();
            System.out.println("Case " + i + ":");
            for (int j = 0; j < q; j++) {
                t = r.nextInt() - 1;
                result = dist[t];
                if (result != INF && result >= 3) {
                    System.out.println(result);
                } else {
                    System.out.println("?");
                }
            }
            resetData();
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
