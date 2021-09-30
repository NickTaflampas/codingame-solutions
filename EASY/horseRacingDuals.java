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
        int N = in.nextInt();
        ArrayList<Integer> strengths = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) {
            int pi = in.nextInt();
            strengths.add(pi);
        }
        Collections.sort(strengths);
        int minDis = Integer.MAX_VALUE;
        for (int i = 0; i < N-1; i++)
        {
            int dis = strengths.get(i+1) - strengths.get(i);
            if (dis < minDis) { minDis = dis; }
        }
        System.out.println(minDis);
    }
}