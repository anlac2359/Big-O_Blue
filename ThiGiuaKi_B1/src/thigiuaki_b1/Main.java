/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package thigiuaki_b1;

import java.util.*;

/**
 *
 * @author King
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static class MaxHeapComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer i1, Integer i2) {
            return i2.compareTo(i1);
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Queue<Integer> q = new LinkedList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(new MaxHeapComparator());
        Scanner sc = new Scanner(System.in);
        int n;
        int m;
        int count;
        int key;
        int keypq;

        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            n = sc.nextInt();
            m = sc.nextInt();
            for (int j = 0; j < n; j++) {
                int temp = sc.nextInt();
                pq.add(temp);
                q.add(temp);
            }
            count = 0;
            while (!pq.isEmpty()) {
                keypq = pq.peek();
                key = q.peek();
                if (key == keypq) {
                    count++;
                    if (m == 0) {
                        System.out.println(count);
                        break;
                    } else {
                        pq.poll();
                        q.poll();
                        m--;
                        if (m < 0) {
                            m = q.size() - 1;
                        }
                    }
                } else {
                    q.add(key);
                    q.poll();
                    m--;
                    if (m < 0) {
                        m = q.size() - 1;
                    }
                }
            }
            q.clear();
            pq.clear();
        }
    }

}
