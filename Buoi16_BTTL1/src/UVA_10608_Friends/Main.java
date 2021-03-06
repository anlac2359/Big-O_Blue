/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UVA_10608_Friends;

import java.io.*;
import java.util.Arrays;

/**
 *
 * @author King
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static int N, M;
    static int[] parent;
    static int[] rank;
    static int[] count;

    static void makeSet() {
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    static int findSet(int u) {
        if (parent[u] != u) {
            parent[u] = findSet(parent[u]);
        }
        return parent[u];
    }

    static void unionSet(int u, int v) {
        int up = findSet(u);
        int vp = findSet(v);
        if (up == vp) {
            return;
        }
        if (rank[up] > rank[vp]) {
            parent[vp] = up;
        } else if (rank[up] < rank[vp]) {
            parent[up] = vp;
        } else {
            parent[up] = vp;
            rank[vp]++;
        }
    }

    static void prepareData() throws IOException {
        Reader r = new Reader();
        N = r.nextInt();
        M = r.nextInt();
        parent = new int[N];
        rank = new int[N];
        count = new int[N];
        Arrays.fill(count, 0);
        makeSet();
        int u, v;
        for (int i = 0; i < M; i++) {
            u = r.nextInt() - 1;
            v = r.nextInt() - 1;
            unionSet(u, v);
        }
    }

    static void resetData() {
        parent = null;
        rank = null;
        count = null;
    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Reader r = new Reader();
        int T = r.nextInt();
        int p, result = 0;
        for (int i = 0; i < T; i++) {
            prepareData();
            for (int j = 0; j < N; j++) {
                p = findSet(j);
                count[p]++;
                if (count[p] > result) {
                    result = count[p];
                }
            }
            System.out.println(result);
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
