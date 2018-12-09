/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package SPOJ_EKO_Eko;

import java.io.*;

/**
 *
 * @author King
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    // Mang a la mang Giam dan
    static int lower_bound(int[] a, int left, int right, int x) {
        int pos = right;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (a[mid] <= x) {
                pos = mid;
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return pos;
    }
    
    class Fraction {
        long t;
        long m;

        public Fraction() {
            t = 0;
            m = 0;
        }
        public Fraction(long t, long m) {
            this.t = t;
            this.m = m;
        }
        public long getT() {
            return t;
        }
        public long getM() {
            return m;
        }
        public Fraction add(Fraction f) {
            return new Fraction(this.t * f.getM() + f.getT() * this.m, this.m * f.getM());
        }
        public Fraction sub(Fraction f) {
            return new Fraction(this.t * f.getM() - f.getT() * this.m, this.m * f.getM());
        }
    }
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Reader r = new Reader();
        int N, M, count;
        int[] heights;
        
        double avg, avgHeightCut, sumD = 0;
        
        int[] temp;
        int sum = 0;
        int sumTemp = 0;
        int pos;
        double result;

        N = r.nextInt();
        M = r.nextInt();
        heights = new int[N];
        temp = new int[N];
        for (int i = 0; i < N; i++) {
            heights[i] = r.nextInt();
            sum += heights[i];
        }
        avg = (double) sum / N;
        avgHeightCut = (double) (sum - M) / N;
        count = 0;
        for (int i = 0; i < N; i++) {
            if (avgHeightCut - heights[i] > 0) {
                sumD += avgHeightCut - heights[i];
                count++;
            }
        }
        result = avgHeightCut + (sumD / (N - count));
        System.out.println((int)result);
        
        /*
        Arrays.sort(heights);
        for (int i = 0; i < N; i++) {
            temp[i] = sum - sumTemp - heights[i] * (N - i);
            sumTemp += heights[i];
        }
        pos = lower_bound(temp, 0, N - 1, M);
        sumTemp = 0;
        for (int i = pos; i < N; i++) {
            sumTemp += heights[i];
        }
        result = (sumTemp - M) / (N - pos);
        if ((sumTemp - M) % (N - pos) != 0) {
            result--;
        }
        */
        
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