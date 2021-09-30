import java.util.*;
import java.io.*;
import java.math.*;


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
        int width = in.nextInt(); // the number of cells on the X axis
        int height = in.nextInt(); // the number of cells on the Y axis
        if (in.hasNextLine()) {
            in.nextLine();
        }
        int[][] grid = new int[height][width];
        for (int i = 0; i < height; i++) {
            String line = in.nextLine(); // width characters, each either 0 or .
            String[] spl = line.split("");
            for (int j = 0; j < width; j++)
            {
                grid[i][j] = spl[j].equals(".")?0:1;
            }
        }
        printGrid(grid);
        String out = "";
        for (int i = 0; i < height; i++)
        {
            
            for (int j = 0; j < width; j++)
            {
                out = "";
                if (grid[i][j] == 1)
                {
                    out = (j + " " + i);
                    String nextNode = "";
                    for (int k = j+1; k < width; k++)
                    {
                        if (grid[i][k] == 1)
                        {
                            nextNode = k + " " + i;
                            break;
                        }
                    }
                    if (nextNode.equals("")) { out += " -1 -1"; }
                    else { out += " " + nextNode; }

                    nextNode = "";
                    for (int k = i+1; k < height; k++)
                    {
                        if (grid[k][j] == 1)
                        {
                            nextNode = j + " " + k;
                            break;
                        }
                    }
                    if (nextNode.equals("")) { out += " -1 -1"; }
                    else { out += " " + nextNode; }
                    System.out.println(out);
                }
                
            }
        }

    }
}