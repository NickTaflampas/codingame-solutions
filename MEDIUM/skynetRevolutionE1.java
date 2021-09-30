import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    static Node[] nodes;
    static Node[] gates;

    public static void dijkstra(Node source)
    {
        for (int i = 0; i < nodes.length; i++) { nodes[i].reset(); }
        source.distance = 0;
        ArrayList<Node> queu = new ArrayList<Node>();
        for (int i = 0; i < nodes.length; i++) { queu.add(nodes[i]); }

        while (!queu.isEmpty())
        {
            Node u = queu.get(0);
            for (int i = 0; i < queu.size(); i++) { if (queu.get(i).distance < u.distance) { u = queu.get(i); } }
            queu.remove(u);

            for (Node v : u.connections)
            {
                int temp = u.distance+1;
                if (temp < v.distance)
                {
                    v.distance = temp;
                    v.prev = u;
                }
            }
        }




    }


    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // the total number of nodes in the level, including the gateways
        int L = in.nextInt(); // the number of links
        int E = in.nextInt(); // the number of exit gateways

        nodes = new Node[N];
        gates = new Node[E];
        for (int i = 0; i < N; i++) { nodes[i] = new Node(i); }


        for (int i = 0; i < L; i++) {
            int N1 = in.nextInt(); // N1 and N2 defines a link between these nodes
            int N2 = in.nextInt();

            nodes[N1].addChild(nodes[N2]);
            nodes[N2].addChild(nodes[N1]);
        }
        for (int i = 0; i < E; i++) {
            int EI = in.nextInt(); // the index of a gateway node
            gates[i] = nodes[EI];
            nodes[EI].setGate(true);
        }

        // game loop
        while (true) {
            int SI = in.nextInt();

            dijkstra(nodes[SI]);
            
            int dangerGate = -1;
            for (int i = 0; i < gates.length; i++)
            {
                if (gates[i].distance == 1) { dangerGate = i; }
            }

            if (dangerGate != -1)
            {
                System.out.println(gates[dangerGate].id + " " + nodes[SI].id);
                gates[dangerGate].connections.remove(nodes[SI]);
                nodes[SI].connections.remove(gates[dangerGate]);
            }
            else
            {
                Node bestCut = null;
                int followers = -1;
                for (Node x : nodes[SI].connections)
                {
                    int n = 0;
                    ArrayList<Node> queu = new ArrayList<Node>();
                    ArrayList<Node> blackList = new ArrayList<Node>();
                    queu.add(x);
                    while (!queu.isEmpty())
                    {
                        Node u = queu.get(0);
                        queu.remove(u);
                        blackList.add(u);
                        for (Node i : u.connections)
                        {
                            if (!blackList.contains(i))
                            {
                                queu.add(i);
                                if (i.prev != null)
                                {
                                    if (i.prev.id == x.id) { n++; }
                                }
                            }
                        }
                    }

                    if (n > followers) { followers = n; bestCut = x;} 
                }

                System.out.println(bestCut.id + " " + nodes[SI].id);
                bestCut.connections.remove(nodes[SI]);
                nodes[SI].connections.remove(bestCut);
            }


                
            

            //System.out.println(gates[dangerGate].prev.id + " " + gates[dangerGate].id);
           // gates[dangerGate].prev.connections.remove(gates[dangerGate]);
            //gates[dangerGate].connections.remove(gates[dangerGate].prev);

        }
    }



}


class Node
{
    int id;
    ArrayList<Node> connections = new ArrayList<Node>();
    boolean isGate;

    int distance = Integer.MAX_VALUE;
    Node prev = null;

    public Node(int i) { id = i;}
    public void addChild(Node n) { connections.add(n); }
    public void setGate(boolean g) { isGate = g; }

    public void reset() { distance = Integer.MAX_VALUE; prev = null; }
}