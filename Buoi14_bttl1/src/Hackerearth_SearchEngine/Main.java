/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hackerearth_SearchEngine;

import com.sun.org.apache.xerces.internal.dom.ParentNode;
import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author King
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    class Node {
        static final int MAX = 26;
        public Node[] child;
        public int countWord;
        public Node() {
            child = new Node[MAX];
            countWord = 0;
        }
    }
    
    class Trie {
        public static final int MAX = 26;
        private Node root;
        public Trie() {
            root = new Node();
        }
        public void addWord(String s) {
            int ch;
            Node temp = root;
            for (int i = 0; i < s.length(); i++) {
                ch = s.charAt(i) - 'a';
                if (temp.child[ch] == null) {
                    Node x =  new Node();
                    temp.child[ch] = x;
                }
                temp = temp.child[ch];
            }
            temp.countWord++;
        }
        public boolean findWord(String s) {
            int ch;
            Node temp = root;
            for (int i = 0; i < s.length(); i++) {
                ch = s.charAt(i) - 'a';
                if (temp.child[ch] == null) {
                    return false;
                }
                temp = temp.child[ch];
            }
            return temp.countWord > 0;
        }
        private boolean isWord(Node pNode) {
            return (pNode.countWord != 0);
        }
        private boolean isEmpty(Node pNode) {
            for (int i = 0; i < MAX; i++) {
                if (pNode.child[i] != null) {
                    return false;
                }
            }
            return true;
        }
        public boolean removeWord(String s) {
            return removeWord(root, s, 0, s.length());
        }
        public boolean removeWord(Node root, String s, int level, int len) {
            if (root == null) {
                return false;
            }
            if (level == len) {
                if (root.countWord > 0) {
                    root.countWord--;
                    return true;
                }
                return false;
            }
            int ch = s.charAt(level) - 'a';
            boolean flag = removeWord(root.child[ch], s, level + 1, len);
            if (flag && !isWord(root.child[ch]) && isEmpty(root.child[ch])) {
                root.child[ch] = null;
            }
            return flag;
        }
    }
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Reader r = new Reader();
        int N, Q;
        Trie trie;
        N = r.nextInt();
        Q = r.nextInt();
        for (int i = 0; i < N; i++) {
            
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
