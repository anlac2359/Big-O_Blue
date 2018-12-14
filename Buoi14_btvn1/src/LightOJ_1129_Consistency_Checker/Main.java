/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package LightOJ_1129_Consistency_Checker;

import java.io.*;

/**
 *
 * @author King
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    static final int MAX = 10;

    static class Node {

        public Node[] child;
        public boolean isLeaf;

        public Node() {
            child = new Node[MAX];
            isLeaf = false;
        }
    }

    static class Trie {

        Node root;

        public Trie() {
            root = new Node();
        }

        public boolean addWord(String s) {
            Node node = root;
            boolean flag = false;
            for (int i = 0; i < s.length(); i++) {
                int c = s.charAt(i) - '0';
                if (node.child[c] == null) {
                    node.child[c] = new Node();
                    flag = true;
                }
                node = node.child[c];
                if (node.isLeaf) {
                    flag = false;
                    break;
                }
            }
            node.isLeaf = true;
            return flag;
        }
    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Reader r = new Reader();
        int T = r.nextInt();
        for (int i = 1; i <= T; i++) {
            Trie trie = new Trie();
            int n = r.nextInt();
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                String s = r.readLine();
                if (flag && !trie.addWord(s)) {
                    flag = false;
                }
            }
            System.out.print("Case " + i + ": ");
            if (flag) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
        System.exit(0);
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
