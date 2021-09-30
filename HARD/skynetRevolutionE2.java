import java.util.*;

class Node
{
    int id;
    ArrayList<Node> links = new ArrayList<Node>();
    int distance;
    Node prev;
    
    public Node(int i)
    {
        id = i;
        distance = Integer.MAX_VALUE;
        prev = null;
    }

    public void reset()
    {
        distance = Integer.MAX_VALUE;
        prev = null;
    }
}

class Player {

    static ArrayList<Node> graph = new ArrayList<Node>();
    static ArrayList<Node> gates = new ArrayList<Node>();

    public static void dijkstra(int root)
    {
        for (Node i : graph) { i.reset(); }
        graph.get(root).distance = 0;

        ArrayList<Node> q = new ArrayList<Node>();

        for (Node i : graph) { q.add(i); }

        while (!q.isEmpty())
        {
            int minDis = Integer.MAX_VALUE;
            Node bestNode = null;
            for (Node i : q)
            {
                if (i.distance < minDis)
                {
                    minDis = i.distance;
                    bestNode = i;
                }
            }
            if (bestNode == null) { break; }
            q.remove(q.indexOf(bestNode));
            if (gates.contains(bestNode)) { continue; }

            for (Node i : bestNode.links)
            {
                int temp = bestNode.distance+1;
                if (temp < i.distance)
                {
                    i.distance = temp;
                    i.prev = bestNode;
                }
            }

        }
    }

    public static void removeLink(int id1, int id2)
    {
        Node a = graph.get(id1);
        Node b = graph.get(id2);

        for (int i = 0; i < a.links.size(); i++)
        {
            if (a.links.get(i).id == id2)
            {
                a.links.remove(i);
                break;
            }
        }
        for (int i = 0; i < b.links.size(); i++)
        {
            if (b.links.get(i).id == id1)
            {
                b.links.remove(i);
                break;
            }
        }

    }

    public static ArrayList<Node> getThreatNodes()
    {
        ArrayList<Node> dangerNodes = new ArrayList<Node>();
        for (Node i : graph)
        {
            if (i.links.size() < 2) { continue; }
            int gateConnections = 0;
            for (Node j : i.links)
            {
                if (gates.contains(j)) { gateConnections++; }
            }

            if (gateConnections >= 2) { dangerNodes.add(i); }
        }
        return dangerNodes;
    }



    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // the total number of nodes in the level, including the gateways
        int L = in.nextInt(); // the number of links
        int E = in.nextInt(); // the number of exit gateways

        for (int i = 0; i < N; i++)
        {
            graph.add(new Node(i));
        }
        for (int i = 0; i < L; i++) {
            int N1 = in.nextInt(); // N1 and N2 defines a link between these nodes
            int N2 = in.nextInt();
            graph.get(N1).links.add(graph.get(N2));
            graph.get(N2).links.add(graph.get(N1));
        }
        for (int i = 0; i < E; i++) {
            int EI = in.nextInt(); // the index of a gateway node
            gates.add(graph.get(EI));
        }

        // game loop
        while (true) {
            int SI = in.nextInt(); 
            dijkstra(SI);
            boolean removedLink = false;

            //Case 1: Immediate termination! Remove the death-link immediatly (aka the link that will kill us next turn).
            for (Node gate : gates)
            {
                if (gate.distance == 1)
                {
                    System.err.println("Deleting Death Link");
                    System.out.println(gate.id + " " + gate.prev.id);
                    removeLink(gate.id,gate.prev.id);
                    removedLink = true;
                    break;
                }
            }

            //Case 2: The virus needs 2 or more moves to reach a gate. Remove most dangerous links.
            if (!removedLink)
            {
                ArrayList<Node> temp = getThreatNodes();
                int dis = Integer.MAX_VALUE;
                Node n = null;
                if (temp.size() == 0) //Subcase 2.1: We have no nodes with 2 or more links to gates. Remove the closest link.
                {
                    System.err.println("Deleting Normal Link");
                    for (Node i : gates)
                    {
                        if (i.distance < dis) { dis = i.distance; n = i; }
                    }
                    System.out.println(n.id + " " + n.prev.id);
                    removeLink(n.id,n.prev.id);
                }
                else //Subcase 2.2 We have nodes with 2 or more links to gates.
                {
                    System.err.println("Deleting Threat Link");
                    /*In this scenario we find 2 nodes. Node N that is the closest node with 2 immediate links
                    to gate(s). And node N2 that is immediatly linked to a gate, and its sortest path to the virus
                    is constructed by nodes that are also immediatly linked to gates (Chain Path).
                    We choose N2 if it exists, otherwise N. 
                    */
                    Node n2 = null;
                    for (Node i : temp)
                    {
                        if (i.distance < dis) { dis = i.distance; n = i; }

                        Node curr = i;
                        while (true)
                        {
                            if (curr.prev == null) { n2 = i; break; }

                            boolean gateConnected = false;
                            for (Node k : curr.links)
                            {
                                if (gates.contains(k)) { gateConnected = true; break;}
                            }
                            if (!gateConnected) { break; }
                            curr = curr.prev;

                        }
                        System.err.print("\n");

                    }

                    if (n2 != null && dis > 2) { n = n2; System.err.println("Selected Chain Path!"); }

                    Node g = null;
                    for (Node i : n.links)
                    {
                        if (gates.contains(i))
                        {
                            g = i;
                            break;
                        }
                    }
                    System.out.println(n.id + " " + g.id);
                    removeLink(n.id,g.id);
                }
            }

        }
    }
}