import java.util.*;
import java.io.*;
import java.math.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of temperatures to analyse
        int dif = 5527;
        int result = 0;

        if (n == 0) { System.out.println("0"); return; }

        for (int i = 0; i < n; i++) {
            int t = in.nextInt(); // a temperature expressed as an integer ranging from -273 to 5526
            if (dif > Math.abs(t) || (dif == Math.abs(t) && t > 0)) { dif = Math.abs(t); result = t; }


        }

        System.out.println(result);
    }
}