import java.util.*;

class Solution {

    //Used to get score of Word s
    public static int getScore(String s)
    {
        int score = 0;
        
        for (String k : s.split(""))
        {
            String letter = k.toLowerCase();
            if (letter.equals("d") || letter.equals("g")) { score += 2; continue; }
            if (letter.equals("b") || letter.equals("c") || letter.equals("m") || letter.equals("p")) { score += 3; continue; }
            if (letter.equals("f") || letter.equals("h") || letter.equals("v") || letter.equals("w") || letter.equals("y")) { score += 4; continue; }
            if (letter.equals("k")) { score += 5; continue;}
            if (letter.equals("j") || letter.equals("x")) { score += 8; continue; }
            if (letter.equals("q") || letter.equals("z")) { score += 10; continue; }
            score += 1;
        }


        return score;
    }


    //Given an array of letters and a word, return true if the word can be formed using the letters
    //Otherwise false.
    public static boolean validate(String word, String[] letters)
    {
        ArrayList<String> nLetters = new ArrayList<String>();
        for (String i : letters) { nLetters.add(i); }

        for (String i : word.split(""))
        {
            if (!nLetters.contains(i))
            {
                return false;
            }
            else
            {
                nLetters.remove(nLetters.indexOf(i));
            }
        }

        return true;
    }


    public static void main(String args[]) {


        Scanner in = new Scanner(System.in);
        int N = in.nextInt();

        String[] words = new String[N];
        int[] scores = new int[N];

        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < N; i++) 
        {
            String W = in.nextLine();
            words[i] = W;
            scores[i] = getScore(W);
        }
        
        
        String LETTERS = in.nextLine();
        String[] actualLetters = LETTERS.split("");
        String bestWord = "NULL";
        int bestScore = -1;

        //For every word, check if we can form it with LETTERS and if so, check the score.
        for (int i = 0; i < N; i++)
        {
            if (validate(words[i], actualLetters))
            {
                if (scores[i] > bestScore)
                {
                    bestWord = words[i];
                    bestScore = scores[i];
                }
            }
        }


        System.out.println(bestWord);
    }
}