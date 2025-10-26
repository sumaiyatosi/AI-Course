import java.util.*;

class Node implements Comparable<Node> {
    int vertex;
    int heuristic; 

    Node(int vertex, int heuristic) 
    {
        this.vertex = vertex;
        this.heuristic = heuristic;
    }

    @Override
    public int compareTo(Node other) 
    {
        return this.heuristic - other.heuristic; 
    }
}

public class Best_First_Search 
{
    private Map<Integer, List<Integer>> adjList = new HashMap<>();
    private Map<Integer, Integer> heuristicMap = new HashMap<>();

    public void addEdge(int src, int dest) 
    {
        adjList.putIfAbsent(src, new ArrayList<>());
        adjList.putIfAbsent(dest, new ArrayList<>());
        adjList.get(src).add(dest);
        adjList.get(dest).add(src); 
    }

    public void setHeuristic(int node, int h) 
    {
        heuristicMap.put(node, h);
    }

    
    public void bestFirstSearch(int start, int goal) 
    {
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, heuristicMap.get(start)));
        System.out.println("\nVisiting nodes in Best First Search order :");

        while (!pq.isEmpty()) 
        {
            Node currentNode = pq.poll();
            int current = currentNode.vertex;

            if (visited.contains(current)) 
            continue;

            System.out.print(current + " ");
            visited.add(current);

            if (current == goal) {
                System.out.println("\nGoal node " + goal + " reached.");
                return;
            }

            for (int neighbor : adjList.get(current)) 
            {
                if (!visited.contains(neighbor)) 
                {
                    pq.add(new Node(neighbor, heuristicMap.get(neighbor)));
                }
            }
        }
        System.out.println("\nGoal not reachable from start node.");
    }
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        Best_First_Search graph = new Best_First_Search();

        System.out.print("Enter number of vertices : ");
        int vertices = sc.nextInt();

        System.out.print("Enter number of edges : ");
        int edges = sc.nextInt();

        System.out.println("Enter edges :");
        for (int i = 0; i < edges; i++) 
        {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            graph.addEdge(src, dest);
        }

        System.out.println("Enter heuristic value for each node :");
        for (int i = 1; i <= vertices; i++) 
        {
            System.out.print("h(" + i + ") = ");
            int h = sc.nextInt();
            graph.setHeuristic(i, h);
        }

        System.out.print("Enter start node: ");
        int start = sc.nextInt();

        System.out.print("Enter goal node: ");
        int goal = sc.nextInt();
        graph.bestFirstSearch(start, goal);
    }
}
