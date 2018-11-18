/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package number7.uva10954addall;

import java.util.*;
/**
 *
 * @author Administrator
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner (System.in);
        ArrayList<Integer> a = new ArrayList<>();
        
        int n = sc.nextInt();
        while (n != 0) {
            for (int i = 0; i < n; i++) {
                a.add(sc.nextInt());
            }
            Collections.sort(a);
            long result = 0;
            for (int i = n - 1; i > 0; i--) {
                System.out.print(a.get(i) + " ");
                result += a.get(i) * (n - i);
            }
            System.out.println(a.get(0));
            result += a.get(0) * (n - 1);
            System.out.println(result);
            a.clear();
            n = sc.nextInt();
        }
    }
    
}
