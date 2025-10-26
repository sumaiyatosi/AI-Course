import java.util.*;

public class Depth_Limited_Search {
    private Map<Integer, List<Integer>> adjList = new HashMap<>();

    public void addEdge(int src, int dest) 
    {
        adjList.putIfAbsent(src, new ArrayList<>());
        adjList.putIfAbsent(dest, new ArrayList<>());
        adjList.get(src).add(dest);
        adjList.get(dest).add(src);
    }

    public boolean dls(int current, int goal, int limit, Set<Integer> visited) 
    {
        System.out.println("Visiting node : " + current + ", Depth limit : " + limit);
        if (current == goal) 
        {
            System.out.println("Goal node " + goal + " found.");
            return true;
        }

        if (limit <= 0) 
        return false;
        visited.add(current);

        for (int neighbor : adjList.get(current)) 
        {
            if (!visited.contains(neighbor)) 
            {
                boolean found = dls(neighbor, goal, limit - 1, visited);
                if (found) 
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        Depth_Limited_Search graph = new Depth_Limited_Search();

        System.out.print("Enter number of vertices : ");
        int vertices = sc.nextInt();

        System.out.print("Enter number of edges : ");
        int edges = sc.nextInt();

        System.out.println("Enter edges :");
        for (int i = 0; i < edges; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            graph.addEdge(src, dest);
        }

        System.out.print("Enter start node : ");
        int start = sc.nextInt();

        System.out.print("Enter goal node : ");
        int goal = sc.nextInt();

        System.out.print("Enter depth limit : ");
        int limit = sc.nextInt();

        Set<Integer> visited = new HashSet<>();
        boolean found = graph.dls(start, goal, limit, visited);

        if (!found)
          System.out.println("\nGoal not found within depth limit " + limit);
    }
}

