/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package SPOJ_SOCIALNE_PossibleFriends_1;

import java.io.*;

/**
 *
 * @author King
 */
public class Main {
    final static int INF = Integer.MAX_VALUE;
    private static int V;
    private static long graph[];
    private static int nFriend[];

    static void init(int v) {
        V = v;
        graph = new long[V];
        nFriend = new int[V];
    }
    
    static long setBit1(long x, int pos) {
        x = x | (1 << pos);
        return x;
    }

    static void readLine(String s, int iLine) {
        for (int j = 0; j < V; j++) {
            if (s.charAt(j) == 'Y') {
                graph[iLine] = setBit1(graph[iLine], j);
                nFriend[iLine]++;
            }
        }
    }
    
    static int friendsPossible(int iLine) {
        int result;
        long tempGraphI;
        long tempGraphI_1;
        long tempGraphJ;
        long graphIJ;
        long temp = 0;
        long temp1 = 0;
        
        tempGraphI = graph[iLine];
        tempGraphI_1 = tempGraphI | (1 << iLine);
        
        for (int j = 0; j < V; j++) {
            
            graphIJ = (tempGraphI >> j) & 1;
            if (graphIJ == 1) {
                //result += nFriend[j] - 1;
                tempGraphJ = graph[j];
                //tempGraphJ = tempGraphJ & ~(1 << iLine);
                temp = tempGraphJ & ~tempGraphI_1;
                temp1 |= temp; 
                
                //tempGraphI = tempGraphI & ~(1 << j);
                //temp = tempGraphI & tempGraphJ;
                //result -= Long.bitCount(temp);
            }
        }
        result = Long.bitCount(temp1);
        return result;
    }

    public static void main(String[] args) throws IOException {
        Reader r = new Reader();
        int nTest = r.nextInt();

        for (int t = 0; t < nTest; t++) {
            String s = r.readLine();
            init(s.length());
            readLine(s, 0);
            for (int i = 1; i < V; i++) {
                s = r.readLine();
                readLine(s, i);
            }

            int max = 0;
            int iMax = 0;
            int fp;
            for (int i = 0; i < V; i++) {
                fp = friendsPossible(i);
                if (fp > max) {
                    max = fp;
                    iMax = i;
                }
                //System.out.println(i + " " + fp);
            }
            graph = null;
            System.out.println(iMax + " " + max);
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
                if (c == '\n') 
                    break; 
                buf[cnt++] = (byte) c; 
            } 
            return new String(buf, 0, cnt); 
        } 
  
        public int nextInt() throws IOException {
            int ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do { 
                ret = ret * 10 + c - '0'; 
            }  while ((c = read()) >= '0' && c <= '9'); 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        public long nextLong() throws IOException { 
            long ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do { 
                ret = ret * 10 + c - '0'; 
            } while ((c = read()) >= '0' && c <= '9'); 
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        public double nextDouble() throws IOException { 
            double ret = 0, div = 1; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
  
            do { 
                ret = ret * 10 + c - '0'; 
            } while ((c = read()) >= '0' && c <= '9'); 
  
            if (c == '.') { 
                while ((c = read()) >= '0' && c <= '9') { 
                    ret += (c - '0') / (div *= 10); 
                } 
            } 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        private void fillBuffer() throws IOException { 
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); 
            if (bytesRead == -1) 
                buffer[0] = -1; 
        } 
  
        private byte read() throws IOException { 
            if (bufferPointer == bytesRead) 
                fillBuffer(); 
            return buffer[bufferPointer++]; 
        } 
  
        public void close() throws IOException { 
            if (din == null) 
                return; 
            din.close(); 
        } 
    }
}
