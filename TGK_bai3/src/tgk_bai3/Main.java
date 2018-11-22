/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package tgk_bai3;

import java.util.*;

/**
 *
 * @author King
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int n = sc.nextInt();
        int w = sc.nextInt();
        long total = 0;
        for (int i = 1; i <= w; i++) {
            total += i * k;
        }
        if (total - n < 0) {
            System.out.println(0);
        } else {
            System.out.println(total - n);
        }
    }

}
