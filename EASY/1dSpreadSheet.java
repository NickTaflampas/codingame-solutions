import java.util.*;


class Block
{
    int value;
    String op;
    String arg1;
    String arg2;

    public Block(String o, String a, String b)
    {
        op = o;
        arg1 = a;
        arg2 = b;
        value = -1;
    }

    public int getValue(Block[] blocks)
    {
        if (value != -1) { return value; }
        
        switch (op)
        {
            case "VALUE":
                if (arg1.contains("$"))
                {
                    int num = Integer.parseInt(arg1.substring(1,arg1.length()));
                    return blocks[num].getValue(blocks);
                }
                else
                {
                    int t = Integer.parseInt(arg1);
                    value = t;
                    return t;
                }
            default:
                int a,b;
                if (arg1.contains("$"))
                {
                    int num = Integer.parseInt(arg1.substring(1,arg1.length()));
                    a = blocks[num].getValue(blocks);
                } else { a = Integer.parseInt(arg1); }
                if (arg2.contains("$"))
                {
                    int num = Integer.parseInt(arg2.substring(1,arg2.length()));
                    b = blocks[num].getValue(blocks);
                } else { b = Integer.parseInt(arg2); }
                if (op.equals("ADD"))
                {
                    int t = a+b;
                    value = t;
                    return t;
                } 
                else if (op.equals("SUB"))
                {
                    int t = a-b;
                    value = t;
                    return t;
                }
                else
                {
                    int t = a*b;
                    value = t;
                    return t;
                }

        }

    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        Block[] blocks = new Block[N];
        for (int i = 0; i < N; i++) {
            String operation = in.next();
            String arg1 = in.next();
            String arg2 = in.next();
            blocks[i] = new Block(operation, arg1, arg2);
            
        }
        for (int i = 0; i < N; i++) 
        {
            if (blocks[i].value == -1)
            {
                System.out.println(blocks[i].getValue(blocks));
            }
            else
            {
                System.out.println(blocks[i].value);
            }

        }
    }
}