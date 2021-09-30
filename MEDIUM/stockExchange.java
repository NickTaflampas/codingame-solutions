import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int max = in.nextInt();
        int min = max;
        int greatestLoss = 0;

        for (int i = 0; i < n-1; i++) 
        {
            int v = in.nextInt();
            if (v < min)
            {
                min = v;
            }
            if (v > max)
            {
                if (greatestLoss > (min-max)) { greatestLoss = min-max; }
                max = v;
                min = v;
            }           
        }
        if (greatestLoss > (min-max)) { greatestLoss = min-max; }
        System.out.print(greatestLoss);

    }
}