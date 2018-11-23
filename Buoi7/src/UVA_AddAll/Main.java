/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UVA_AddAll;

import java.util.*;

/**
 *
 * @author Administrator
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static int N;

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        while (N != 0) {
            PriorityQueue<Long> pq = new PriorityQueue<>();
            long ans = 0;
            for (int i = 0; i < N; i++) {
                long t;
                t = sc.nextLong();
                pq.add(t);
            }
            while (pq.size() > 1) {
                long x = pq.poll();
                long y = pq.poll();
                ans = x + y + ans;
                pq.add(x + y);
            }
            System.out.println(ans);
            N = sc.nextInt();
        }
    }

}
