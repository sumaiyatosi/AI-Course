import java.util.*;

class Node implements Comparable<Node> {
    int vertex;
    int gCost; 
    int fCost; 

    Node(int vertex, int gCost, int fCost) 
    {
        this.vertex = vertex;
        this.gCost = gCost;
        this.fCost = fCost;
    }

    @Override
    public int compareTo(Node other) 
    {
        return this.fCost - other.fCost; 
    }
}

public class A_Star {
    private Map<Integer, List<int[]>> adjList = new HashMap<>(); 
    private Map<Integer, Integer> heuristicMap = new HashMap<>();

    public void addEdge(int src, int dest, int cost) 
    {
        adjList.putIfAbsent(src, new ArrayList<>());
        adjList.putIfAbsent(dest, new ArrayList<>());
        adjList.get(src).add(new int[]{dest, cost});
        adjList.get(dest).add(new int[]{src, cost}); 
    }

    public void setHeuristic(int node, int h) 
    {
        heuristicMap.put(node, h);
    }

    public void aStarSearch(int start, int goal) 
    {
        PriorityQueue<Node> openList = new PriorityQueue<>();
        Set<Integer> closedList = new HashSet<>();
        Map<Integer, Integer> gScore = new HashMap<>();
        Map<Integer, Integer> parent = new HashMap<>();

        gScore.put(start, 0);
        openList.add(new Node(start, 0, heuristicMap.get(start)));
        System.out.println("\nVisiting nodes in A* Search order :");

        while (!openList.isEmpty()) 
        {
            Node current = openList.poll();
            int currNode = current.vertex;

            if (closedList.contains(currNode)) 
               continue;

            System.out.print(currNode + " "); 
            closedList.add(currNode);

            if (currNode == goal) 
            {
                System.out.println("\nGoal node " + goal + " reached.");
                printPath(parent, start, goal);
                return;
            }

            for (int[] neighborArr : adjList.getOrDefault(currNode, new ArrayList<>())) 
            {
                int neighbor = neighborArr[0];
                int cost = neighborArr[1];

                if (closedList.contains(neighbor)) 
                   continue;

                int tentativeG = gScore.getOrDefault(currNode, Integer.MAX_VALUE) + cost;
                if (tentativeG < gScore.getOrDefault(neighbor, Integer.MAX_VALUE)) 
                {
                    gScore.put(neighbor, tentativeG);
                    int f = tentativeG + heuristicMap.get(neighbor);
                    openList.add(new Node(neighbor, tentativeG, f));
                    parent.put(neighbor, currNode);
                }
            }
        }
        System.out.println("\nGoal not reachable from start node.");
    }

    private void printPath(Map<Integer, Integer> parent, int start, int goal) 
    {
        List<Integer> path = new ArrayList<>();
        int node = goal;
        while (node != start) 
        {
            path.add(node);
            node = parent.get(node);
        }
        path.add(start);
        Collections.reverse(path);
        System.out.println("Path: " + path);
    }
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        A_Star graph = new A_Star();

        System.out.print("Enter number of vertices : ");
        int vertices = sc.nextInt();

        System.out.print("Enter number of edges : ");
        int edges = sc.nextInt();

        System.out.println("Enter edges :");
        for (int i = 0; i < edges; i++) 
        {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int cost = sc.nextInt();
            graph.addEdge(src, dest, cost);
        }

        System.out.println("Enter heuristic value for each node :");
        for (int i = 1; i <= vertices; i++) 
        {
            System.out.print("h(" + i + ") = ");
            int h = sc.nextInt();
            graph.setHeuristic(i, h);
        }

        System.out.print("Enter start node : ");
        int start = sc.nextInt();

        System.out.print("Enter goal node : ");
        int goal = sc.nextInt();
        graph.aStarSearch(start, goal);
    }
}
