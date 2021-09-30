import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    static String[][] matrix;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }

        matrix = new String[N][N];

        for (int i = 0; i < N; i++) {
            String L = in.nextLine();
            String[] s = L.split("");
            for (int j = 0; j < N; j++)
            {
                matrix[j][i] = s[j];
            }
            if (N == 1) { System.out.println(L); return; }
            System.err.println(L);
        }

        int fold = 0;
        int temp = N*N;

        while (temp != 1)
        {
            switch (fold)
            {
                case 0:
                    rightToLeft();
                    break;
                case 1:
                    bottomToUp();
                    break;
                case 2:
                    leftToRight();
                    break;
                case 3:
                    upToBottom();
                    break;
                default:
                    break;
            }
            printMatrix();
            fold++;
            temp = temp / 2;

            if ((fold == 2 || fold == 4) && matrix.length != 2)
            {
                matrix = cpMatrix(matrix);
            }

            if (fold == 4) { fold = 0; }
            System.err.println("Fold " + fold + ":");
            
        }

        N = matrix.length;
        if (!matrix[0][0].equals("")) { System.out.println(matrix[0][0]); return; }
        if (!matrix[N-1][0].equals("")) { System.out.println(matrix[N-1][0]); return; }
        if (!matrix[0][N-1].equals("")) { System.out.println(matrix[0][N-1]); return; }
        if (!matrix[N-1][N-1].equals("")) { System.out.println(matrix[N-1][N-1]); return; }



    }

    public static String[][] cpMatrix(String[][] m)
    {
        String[][] out = new String[m.length/2][m.length/2];
        int l = m.length;

        if (!m[0][0].equals(""))
        {
            for (int i = 0; i < m.length/2; i++)
            {
                for (int j = 0; j < m.length/2; j++)
                {
                    out[i][j] = m[i][j];
                }
            }
        }
        else if (!m[m.length-1][0].equals(""))
        {
            for (int i = 0; i < m.length/2; i++)
            {
                for (int j = 0; j < m.length/2; j++)
                {
                    out[i][j] = m[m.length-1+i][j];
                }
            }
        }
        else if (!m[0][m.length-1].equals(""))
        {
            for (int i = 0; i < m.length/2; i++)
            {
                for (int j = 0; j < m.length/2; j++)
                {
                    out[i][j] = m[i][m.length-1+j];
                }
            }
        }
        else
        {
            for (int i = 0; i < m.length/2; i++)
            {
                for (int j = 0; j < m.length/2; j++)
                {
                    out[i][j] = m[i+(m.length/2)][j+(m.length/2)];
                }
            }
        }

        return out;

    }
    public static String reverse(String s)
    {
        String[] spl = s.split("");
        String out = "";
        for (int i = 0; i < spl.length; i++)
        {
            out += spl[spl.length-1-i];
        }
        return out;
    }


    public static void printMatrix()
    {
        int N = matrix.length;
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                System.err.print(matrix[j][i] + ", ");
            }
            System.err.print("\n");
        }
    }



    public static void rightToLeft()
    {
        int N = matrix.length;
        for (int i = 0; i < N/2; i++)
        {
            for (int j = 0; j < N; j++)
            {
                matrix[i][j] = reverse(matrix[N-1-i][j]) + matrix[i][j];
                matrix[N-1-i][j] = "";
            }
        }
    }

    public static void bottomToUp()
    {
        int N = matrix.length;
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N/2; j++)
            {
                matrix[i][j] = reverse(matrix[i][N-1-j]) + matrix[i][j];
                matrix[i][N-1-j] = "";
            }
        }
    }

    public static void leftToRight()
    {
        int N = matrix.length;
        for (int i = 0; i < N/2; i++)
        {
            for (int j = 0; j < matrix.length; j++)
            {
                matrix[N-1-i][j] =  reverse(matrix[i][j]) + matrix[N-1-i][j];
                matrix[i][j] = "";
            }
        }
    }

    public static void upToBottom()
    {
        int N = matrix.length;
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N/2; j++)
            {
                matrix[i][N-1-j] = reverse(matrix[i][j]) + matrix[i][N-1-j];
                matrix[i][j] = "";
            }
        }
    }

}