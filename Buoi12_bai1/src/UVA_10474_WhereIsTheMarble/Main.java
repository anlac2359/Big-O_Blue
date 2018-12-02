/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package UVA_10474_WhereIsTheMarble;

import java.util.*;

/**
 *
 * @author King
 */
public class Main {

    /**
     * @param args the command line arguments
     */
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

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        int N;
        int Q;
        int[] marbles;
        int querie;
        int result;
        int i = 1;

        N = sc.nextInt();
        marbles = new int[N];
        Q = sc.nextInt();
        
        while (N != 0 && Q != 0) {
            System.out.println("CASE# " + i + ":");
            i++;
            for (int j = 0; j < N; j++) {
                marbles[j] = sc.nextInt();
            }
            Arrays.sort(marbles);
            for (int j = 0; j < Q; j++) {
                querie = sc.nextInt();
                result = lower_bound(marbles, 0, N - 1, querie);
                if (querie == marbles[result]) {
                    result++;
                    System.out.println(querie + " found at " + result);
                } else {
                    System.out.println(querie + " not found");
                }
            }
            marbles = null;
            N = sc.nextInt();
            marbles = new int[N];
            Q = sc.nextInt();
        }
    }

}
