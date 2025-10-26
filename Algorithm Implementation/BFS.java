import java.util.*;

public class BFS {
    private Map<Integer, List<Integer>> adjList = new HashMap<>();

    public void addEdge(int src, int dest) 
    {
        adjList.putIfAbsent(src, new ArrayList<>());
        adjList.putIfAbsent(dest, new ArrayList<>());
        adjList.get(src).add(dest);
        adjList.get(dest).add(src); 
    }

    public void bfs(int start) 
    {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        visited.add(start);
        queue.add(start);
        System.out.print("BFS Traversal Order : ");

        while (!queue.isEmpty()) 
        {
            int node = queue.poll();
            System.out.print(node + " ");

            for (int neighbor : adjList.get(node)) 
            {
                if (!visited.contains(neighbor)) 
                {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        BFS graph = new BFS();

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
        graph.bfs(start);
    }
}
