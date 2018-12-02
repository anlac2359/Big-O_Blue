/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UVA_10611_ThePlayboyChimp;

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
    static int upper_bound(int[] a, int left, int right, int x) {
        int pos = right;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (a[mid] > x) {
                pos = mid;
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return pos;

    }

    static int lower_bound(int[] a, int left, int right, int x) {
        int pos = right;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (a[mid] >= x) {
                pos = mid;
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return pos;

    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Reader r = new Reader();
        int N, Q, queries, lower, upper;
        int[] heights;
        N = r.nextInt();
        heights = new int[N];
        for (int i = 0; i < N; i++) {
            heights[i] = r.nextInt();
        }
        Arrays.sort(heights);
        Q = r.nextInt();
        for (int i = 0; i < Q; i++) {
            queries = r.nextInt();
            lower = lower_bound(heights, 0, N - 1, queries);
            if (lower > 0) {
                System.out.print(heights[lower - 1] + " ");
            } else {
                System.out.print("X ");
            }
            upper = upper_bound(heights, 0, N - 1, queries);
            if (upper == 0) {
                System.out.println(heights[upper]);
            } else {
                    System.out.println(heights[upper]);
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
