import java.util.*;

class Tile
{
    int x;
    int y;
    String type;
    ArrayList<Tile> adjacent = new ArrayList<Tile>();
    int distance;
    Tile prev;
    boolean explored = false;

    public Tile(String t, int xx, int yy)
    {
        x = xx;
        y = yy;
        type = t;
    }

    public void reset()
    {
        distance = Integer.MAX_VALUE;
        prev = null;
    }
}

class Player {

    static Tile[][] grid;

    
    public static Tile[][] init(int r, int c)
    {
        Tile[][] t = new Tile[r][c];
        for (int i = 0; i < r; i++){ for (int j = 0; j < c; j++){t[i][j] = new Tile("?",j,i);}}
        for (int i = 0; i < r; i++)
        { 
            for (int j = 0; j < c; j++)
            {
                try {t[i][j].adjacent.add(t[i-1][j]);} catch (Exception e) {}
                try {t[i][j].adjacent.add(t[i+1][j]);} catch (Exception e) {}
                try {t[i][j].adjacent.add(t[i][j-1]);} catch (Exception e) {}
                try {t[i][j].adjacent.add(t[i][j+1]);} catch (Exception e) {}
            }
        }
        return t;
    }

    public static void printGrid()
    {
        for (int i = 0; i < grid.length; i++)
        { 
            for (int j = 0; j < grid[0].length; j++)
            {
                System.err.print(grid[i][j].distance + "|");
            }
            System.err.print("\n");
        }
    }

    public static void dijkstra(int rootX, int rootY)
    {
        ArrayList<Tile> q = new ArrayList<Tile>();
        for (int i = 0; i < grid.length; i++){ for (int j = 0; j < grid[0].length; j++){grid[i][j].reset(); q.add(grid[i][j]);}}
        grid[rootY][rootX].distance = 0;
        
        while(!q.isEmpty())
        {
            Tile curr = null;
            int index = -1;
            int minDis = Integer.MAX_VALUE;
            for (int i = 0; i < q.size(); i++)
            {
                if (q.get(i).distance < minDis)
                {
                    minDis = q.get(i).distance;
                    index = i;
                }
            }
            if (index == -1) { break; }
            curr = q.remove(index);

            for (Tile i : curr.adjacent)
            {
                if (i.type.equals("#") || i.type.equals("?")) { continue; }
                int temp = curr.distance+1;
                if (temp < i.distance)
                {
                    i.distance = temp;
                    i.prev = curr;
                }
            }
        }

    }

    public static String getMove(Tile target, int x, int y)
    {
        if (target.x == x)
        {
            if (y+1 == target.y)
            {
                return "DOWN";
            }
            else
            {
                return "UP";
            }
        }
        else if (target.y == y)
        {
            if (x+1 == target.x)
            {
                return "RIGHT";
            }
            else
            {
                return "LEFT";
            }
        }
        return "FUCK";
    }

    public static Tile getNext(int x, int y, int rootX, int rootY)
    {
        Tile curr = grid[rootY][rootX];
        if (curr.prev == null) { return curr; }
        while(true)
        {
            if (curr.prev.x == x && curr.prev.y == y) { break; }
            curr = curr.prev;
        }
        return curr;
    }

    public static Tile getExplorationPos()
    {
        Tile bestTile = null;
        int minDis = Integer.MAX_VALUE-1;
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[0].length; j++)
            {
                if (!grid[i][j].explored)
                {
                    Tile k = grid[i][j];
                    if (k.type.equals(".") && k.distance < minDis) 
                    { 
                        bestTile = k; 
                        minDis = k.distance;
                    }
                }
            }
        }
        return bestTile;
    }

    public static void floodFill(int x, int y)
    {
        ArrayList<Tile> q = new ArrayList<Tile>();
        ArrayList<Tile> bl = new ArrayList<Tile>();
        int maxFlood = 20;
        q.add(grid[y][x]);
        while (!q.isEmpty() && maxFlood != 0)
        {
            Tile curr = q.remove(0);
            curr.explored = true;
            bl.add(curr);
            maxFlood--;
            try{ if (!bl.contains(grid[curr.y-1][curr.x])) { q.add(grid[curr.y-1][curr.x]); }} catch(Exception e) {}
            try{ if (!bl.contains(grid[curr.y+1][curr.x])) { q.add(grid[curr.y+1][curr.x]); }} catch(Exception e) {}
            try{ if (!bl.contains(grid[curr.y][curr.x-1])) { q.add(grid[curr.y][curr.x-1]); }} catch(Exception e) {}
            try{ if (!bl.contains(grid[curr.y][curr.x+1])) { q.add(grid[curr.y][curr.x+1]); }} catch(Exception e) {}
        }
    }


    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int R = in.nextInt(); // number of rows.
        int C = in.nextInt(); // number of columns.
        int A = in.nextInt(); 

        String controlCoords = null;
        boolean underControl = false;
        boolean accessControl = false;
        boolean reachControl = false;

        int startX = -1;
        int startY = -1;


        grid = init(R,C);


        while (true) {
            int KR = in.nextInt(); // row where Kirk is located.
            int KC = in.nextInt(); // column where Kirk is located.
            for (int i = 0; i < R; i++) {
                String ROW = in.next(); // C of the characters in '#.TC?' (i.e. one line of the ASCII maze).
                String[] spl = ROW.split("");
                for (int j = 0; j < C; j++)
                {
                    grid[i][j].type = spl[j];
                    if (spl[j].equals("C")) { controlCoords = j + " " + i; grid[i][j].explored = true; }
                    if (spl[j].equals("T")) { startX = j; startY = i; }
                }
            }

            grid[KR][KC].explored = true;
            dijkstra(KC, KR);
            //printGrid();

            if (controlCoords != null)
            {
                int[] coords = Arrays.stream(controlCoords.split(" ")).mapToInt(x->Integer.parseInt(x)).toArray();
                if (!underControl && coords[0] == KC && coords[1] == KR) { underControl = true; System.err.println("Control point reached!");}
                if (!accessControl && grid[coords[1]][coords[0]].prev != null) 
                { 
                    accessControl = true;
                    floodFill(coords[0], coords[1]);         
                }
                
                dijkstra(startX,startY);
                if (accessControl)
                {
                    if (grid[coords[1]][coords[0]].distance <= A)
                    {
                        reachControl = true;
                    }
                }
            }
            dijkstra(KC, KR);

            String out = "";
            Tile t = null;

            //Case 1: We have not found our Control Coordinates.
            if ((controlCoords == null) || (!underControl && !accessControl) || (!underControl && !reachControl))
            {   
                System.err.print("Exploring Map from " + KC + ", " + KR);
                 t = getExplorationPos();
                 t = getNext(KC,KR,t.x,t.y);
                 System.err.print(" at " + t.x + ", " + t.y + " \n");
                 out = getMove(t,KC,KR);
            }
            //Case 2: Found the control and have a valid path for it.
            else if (!underControl && accessControl && reachControl)
            {
                System.err.println("Moving to Control!");
                int[] coords = Arrays.stream(controlCoords.split(" ")).mapToInt(x->Integer.parseInt(x)).toArray();
                t = getNext(KC,KR,coords[0],coords[1]);
                out = getMove(t,KC,KR);               
            }//Case 3: Reached Control, now return back as fast as possible.
            else if (underControl && accessControl)
            {
                System.err.println("Moving to Starting Position");
                t = getNext(KC,KR,startX,startY);
                out = getMove(t,KC,KR);
            }

            System.out.println(out);
        }
    }
}