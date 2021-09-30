import java.util.*;


class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        long L = in.nextLong(); //Number of seats
        long C = in.nextLong(); //Number of functions
        int N = in.nextInt(); //Groups
        int[] groups = new int[N];
        for (int i = 0; i < N; i++) {
            int pi = in.nextInt(); //Number of people in Group i
            groups[i] = pi;
        }

        int[] profits = new int[N];
        int[] nextGroup = new int[N];

        for (int i = 0; i < N; i++)
        {
            int pointer = i;
            profits[i] = 0;
            while (true)
            {
                int next = groups[pointer];
                if ((profits[i]+next) > L)
                {
                    break;
                }
                profits[i] += next;
                pointer++;

                if (pointer == N)
                {
                    pointer = 0;
                }

                if (pointer == i)
                {
                    break;
                }
            }
            nextGroup[i] = pointer;

        }

        long money = 0;
        int pointer = 0;

        for (int i = 0; i < C; i++)
        {
            money += profits[pointer];
            pointer = nextGroup[pointer];
        }

        System.out.println(money);

        
    }
}