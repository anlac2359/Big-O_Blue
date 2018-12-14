/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SPOJ_CAM5_prayatna_PR;

import java.io.*;
import java.util.*;

/**
 *
 * @author Administrator
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static ArrayList<ArrayList<Integer>> graph;
    static ArrayList<Integer> path;
    static ArrayList<Boolean> visited;
    static int N;

    static void prepareData() {
        graph = new ArrayList<>();
        path = new ArrayList<>();
        visited = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
            path.add(-1);
            visited.add(false);
        }
    }

    static void clearData() {
        for (int i = 0; i < N; i++) {
            graph.get(i).clear();
        }
        graph.clear();
        visited.clear();
        path.clear();
    }
    
    static void DFS(int s) {
        Stack<Integer> stack = new Stack<>();
        visited.set(s, true);
        stack.push(s);
        while (!stack.isEmpty()) {
            s = stack.pop();
            for (int i = 0; i < graph.get(s).size(); i++) {
                if (!visited.get(i)) {
                    visited.set(i, true);
                    stack.push(i);
                    path.set(i, s);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Reader r = new Reader();
        int T = r.nextInt();
        int nE, u, v, count;
        for (int i = 0; i < T; i++) {
            N = r.nextInt();
            prepareData();
            nE = r.nextInt();
            for (int j = 0; j < nE; j++) {
                u = r.nextInt();
                v = r.nextInt();
                graph.get(v).add(u);
                graph.get(u).add(v);
            }
            count = 0;
            for (int j = 0; j < N; j++) {
                if (!visited.get(j)) {
                    DFS(j);
                    count++;
                }
            }
            System.out.println(count);
            clearData();
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
