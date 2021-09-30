import java.util.*;


class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        int H = in.nextInt();

        HashMap<String,String[]> asciiLetters = new HashMap<String,String[]>();
        for (int i = 65; i < 91; i++)
        {
            String toAdd = String.valueOf(((char)i));
            asciiLetters.put(toAdd,new String[H]);
        }
        asciiLetters.put("?",new String[H]);

        if (in.hasNextLine()) {
            in.nextLine();
        }
        String T = in.nextLine();

        for (int i = 0; i < H; i++) {
            String ROW = in.nextLine();
            String[] splitRow = ROW.split("");
            int pointer = 0;
            int letter = 65;
            String value = "";
            for (String j : splitRow)
            {
                if (pointer == L-1)
                {
                    if (letter != 91)
                    {
                        String let = String.valueOf(((char)letter));
                        asciiLetters.get(let)[i] = value+j;
                        value = "";
                        letter++;
                        pointer = 0;
                    }
                    else
                    {
                        asciiLetters.get("?")[i] = value+j;
                        value = "";
                        letter = 65;
                        pointer = 0;
                    }
                }
                else
                {
                    value += j;
                    pointer++;
                }
            }
        }


        
        for (int i = 0; i < H; i++)
        {
            for (String j : T.toUpperCase().split(""))
            {
                int c = (int)j.charAt(0);
                if (c >= 65 && c <= 90)
                {
                    System.out.print(asciiLetters.get(j)[i] );
                }
                else
                {
                    System.out.print(asciiLetters.get("?")[i]);
                }
            }
            System.out.print("\n");
        }
    }
}