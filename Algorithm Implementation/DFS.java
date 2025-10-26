import java.util.*;

public class DFS {
    private Map<Integer, List<Integer>> adjList = new HashMap<>();

    public void addEdge(int src, int dest) 
    {
        adjList.putIfAbsent(src, new ArrayList<>());
        adjList.putIfAbsent(dest, new ArrayList<>());
        adjList.get(src).add(dest);
        adjList.get(dest).add(src); 
    }

    public void dfs(int node, Set<Integer> visited) 
    {
        visited.add(node);
        System.out.print(node + " "); 

        for (int neighbor : adjList.get(node)) 
        {
            if (!visited.contains(neighbor)) 
            {
                dfs(neighbor, visited);
            }
        }
    }
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        DFS graph = new DFS();

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

        System.out.print("Enter starting node : ");
        int start = sc.nextInt();
        Set<Integer> visited = new HashSet<>();
        graph.dfs(start, visited);
    }
}

