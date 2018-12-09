/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lightoj_DNAPrefix;

import java.io.*;

/**
 *
 * @author King
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static int ans = 0;
    static int[] d = new int[26];
    
    static final int MAX = 4;
    static class Node {
        int value = 0;
        Node[] child = new Node[MAX];
    }
    static void addNode(Node Root, String s) {
        Node node = Root;
        for (int i = 0; i < s.length(); i++) {
            int next = d[s.charAt(i) - 'A'];

            if (node.child[next] == null) {
                node.child[next] = new Node();
            }
            node = node.child[next];
            node.value++;

            ans = Math.max(node.value * (i + 1), ans);
        }
    }

    static void Delete(Node u) {
        for (int i = 0; i < 4; i++) {
            if (u.child[i] != null) {
                Delete(u.child[i]);
            }
        }
        u = null;
    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Reader r = new Reader();
        int T, N;
        Node Root;

        d['A' - 'A'] = 0;
        d['C' - 'A'] = 1;
        d['G' - 'A'] = 2;
        d['T' - 'A'] = 3;
        T = r.nextInt();
        for (int i = 1; i <= T; i++) {
            ans = 0;
            Root = new Node();
            N = r.nextInt();
            for (int j = 0; j < N; j++) {
                addNode(Root, r.readLine());
            }
            System.out.println("Case " + i + ": " + ans);
            Delete(Root);
            Root = null;
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
