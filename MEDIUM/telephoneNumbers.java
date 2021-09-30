import java.util.*;

class Node
{
    int num; //Number representing
    Node[] children = new Node[10]; //Children nodes corresponding to values 0-9

    public Node(int i)
    {
        for (int j = 0; j < 10; j++) { children[j] = null; }
        num = i;
    }
}

class Solution {

    public static void main(String args[]) {

        int counter = 0; //Counter for new additions in our trie.
        Node root = new Node(-1); //Root of trie (Number of this node is not required, set to -1).

        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            String telephone = in.next();
            String[] spl = telephone.split("");

            Node curr = root; //Current traversing node. Init to root.
            int pointer = 0; //Pointer within split number
            while (pointer < spl.length)
            {
                int num = Integer.parseInt(spl[pointer]);
                if (curr.children[num] == null) //If we require a node in the Trie, but does not exist, create it.
                {
                    counter++;
                    curr.children[num] = new Node(num);
                    curr = curr.children[num];
                    pointer++;
                    continue;
                }
                else //If not, just move along.
                {
                    curr = curr.children[num];
                    pointer++;
                    continue;
                }
            }
            
        }
        System.out.println(counter);
    }
}