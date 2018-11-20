/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package Hackerearth_RoyAndTrendingTopics;

import java.util.*;

/**
 *
 * @author King
 */
public class Main {

    public static class Topic {

        public int IDs;
        public long new_zscore;
        public long delta_zscore;

        // Return TRUE nếu t1 < t2
        /*
        @Override
        public int compare(Topic t1, Topic t2) {
            if (t1.delta_zscore > t2.delta_zscore) {
                return 1;
            } else if (t1.delta_zscore == t2.delta_zscore) {
                return t2.IDs - t1.IDs;
            }
            return -1;
        }
         */
        public Topic(int IDs, long new_zscore, long delta_zscore) {
            this.IDs = IDs;
            this.new_zscore = new_zscore;
            this.delta_zscore = delta_zscore;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        int IDs;
        long old_zscore, post, like, comment, share, new_zscore, delta_zscore;

        // sắp xếp giảm dần theo delta_zscore và giảm dần theo IDs
        PriorityQueue<Topic> pq = new PriorityQueue<>(new Comparator<Topic>() {
            public int compare(Topic t, Topic t1) {
                if (t.delta_zscore > t1.delta_zscore) {
                    return -1;
                } else if (t.delta_zscore == t1.delta_zscore) {
                    if (t.IDs > t1.IDs) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
                return 1;
            }
        });

        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            IDs = sc.nextInt();
            old_zscore = sc.nextLong();
            post = sc.nextLong();
            like = sc.nextLong();
            comment = sc.nextLong();
            share = sc.nextLong();
            new_zscore = post * 50 + like * 5 + comment * 10 + share * 20;
            delta_zscore = new_zscore - old_zscore;
            pq.add(new Topic(IDs, new_zscore, delta_zscore));
        }
        for (int i = 1; i <= 5; i++) {
            Topic temp;
            temp = pq.poll();
            System.out.println(temp.IDs + " " + temp.new_zscore);
        }
    }
}
