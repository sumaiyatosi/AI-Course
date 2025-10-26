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

public class Beam_Search 
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
   
    public void Beam_Search(int start, int goal, int beamWidth) 
    {
        Set<Integer> visited = new HashSet<>();
        List<Integer> currentLevel = new ArrayList<>();
        currentLevel.add(start);
        visited.add(start);
        System.out.println("\nBeam Search visiting nodes :");

        while (!currentLevel.isEmpty()) 
        {
            List<Node> nextLevel = new ArrayList<>();

            for (int node : currentLevel) 
            {
                System.out.print(node + " "); 
                if (node == goal) 
                {
                    System.out.println("\nGoal node " + goal + " reached.");
                    return;
                }
                for (int neighbor : adjList.get(node)) 
                {
                    if (!visited.contains(neighbor)) 
                    {
                        nextLevel.add(new Node(neighbor, heuristicMap.get(neighbor)));
                        visited.add(neighbor);
                    }
                }
            }

            nextLevel.sort(Comparator.naturalOrder());
            currentLevel.clear();
            for (int i = 0; i < Math.min(beamWidth, nextLevel.size()); i++) 
            {
                currentLevel.add(nextLevel.get(i).vertex);
            }
        }
        System.out.println("\nGoal not reachable within beam width " + beamWidth);
    }
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        Beam_Search graph = new Beam_Search();

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

        System.out.print("Enter start node : ");
        int start = sc.nextInt();

        System.out.print("Enter goal node : ");
        int goal = sc.nextInt();

        System.out.print("Enter beam width : ");
        int beamWidth = sc.nextInt();
        graph.Beam_Search(start, goal, beamWidth);
    }
}
