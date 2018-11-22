/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package thigiuaky_b2;

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
        int n;
        String s;
        n = sc.nextInt();
        //sc.nextLine();
        s = sc.next();
        s = s.toUpperCase();
        Boolean[] flag = new Boolean[26];
        if (n < 26) {
            System.out.println("NO");
        } else {
            for (int i = 0; i < 26; i++) {
                flag[i] = false;
            }
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (c >= 'A' && c <= 'Z') {
                    flag[c - 'A'] = true;
                }
            }
            int i;
            for (i = 0; i < 26; i++) {
                if (!flag[i]) {
                    System.out.println("NO");
                    break;
                }
            }
            if (i == 26) {
                System.out.println("YES");
            }
        }

    }

}
