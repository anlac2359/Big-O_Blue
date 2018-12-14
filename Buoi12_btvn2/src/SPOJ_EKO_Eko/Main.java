/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package SPOJ_EKO_Eko;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author King
 */
public class Main {

    /**
     * @param args the command line arguments
     */

    static ArrayList<Integer> heights = new ArrayList<>();
    static int N, M;

    public static long check(long val) {
        long sum = 0;
        for (int i = 0; i < N; i++) {
            if (heights.get(i) > val) {
                sum += heights.get(i) - val;
            }
        }
        return sum;
    }

    public static long BinarySearch(long left, long right) {
        long result = right;
        while (left <= right)
        {
            long mid = left + (right - left) / 2;
            if (check(mid) < M) {
                right = mid - 1;
            }
            else {
                result = mid;
                left = mid + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Reader r = new Reader();
        long left = 0, right = 0;
        int temp;

        N = r.nextInt();
        M = r.nextInt();
        for (int i = 0; i < N; i++) {
            temp = r.nextInt();
            heights.add(temp);
            if (temp > right) {
                right = temp;
            }
        }
        System.out.println(BinarySearch(left, right));
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
