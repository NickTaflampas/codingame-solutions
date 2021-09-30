import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void printGrid(int[][] g)
    {
        for (int i = 0; i < g.length; i++)
        {
            for (int j = 0; j < g[0].length; j++)
            {
                System.err.print(g[i][j] + " ");
            }
            System.err.print("\n");
        }
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt(); // number of columns.
        int H = in.nextInt(); // number of rows.

        int[][] grid = new int[H][W];

        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < H; i++) {
            String LINE = in.nextLine();
            grid[i] = Arrays.stream(LINE.split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        int EX = in.nextInt(); // the coordinate along the X axis of the exit (not useful for this first mission, but must be read).

        printGrid(grid);
        while (true) {
            int XI = in.nextInt();
            int YI = in.nextInt();
            String POS = in.next();


            int roomType = grid[YI][XI];

            switch(roomType)
            {
                case 2:
                    if (POS.equals("LEFT")) { System.out.println((XI+1) + " " + YI); }
                    if (POS.equals("RIGHT")) { System.out.println((XI-1) + " " + YI); }
                    break;
                case 4:
                    if (POS.equals("TOP")) { System.out.println((XI-1) + " " + YI); }
                    if (POS.equals("RIGHT")) { System.out.println(XI + " " + (YI+1)); }
                    break;
                case 5:
                    if (POS.equals("TOP")) { System.out.println((XI+1) + " " + YI); }
                    if (POS.equals("LEFT")) { System.out.println(XI + " " + (YI+1)); }
                    break;
                case 6:
                    if (POS.equals("LEFT")) { System.out.println((XI+1) + " " + YI); }
                    if (POS.equals("RIGHT")) { System.out.println((XI-1) + " " + YI); }
                    break;
                case 10:
                     System.out.println((XI-1) + " " + YI);
                     break;
                case 11:
                     System.out.println((XI+1) + " " + YI);
                     break;
                default:
                     System.out.println(XI + " " + (YI+1));
                     break;
            }
        }
    }
}