import java.util.*;

public class Bidirectional_Search {
    private Map<Integer, List<Integer>> adjList = new HashMap<>();

    public void addEdge(int src, int dest) 
    {
        adjList.putIfAbsent(src, new ArrayList<>());
        adjList.putIfAbsent(dest, new ArrayList<>());
        adjList.get(src).add(dest);
        adjList.get(dest).add(src);
    }

    public int bidirectionalSearch(int start, int goal) 
    {
        if (start == goal) {
            System.out.println("Start and goal are the same!");
            return start;
        }

        Set<Integer> visitedFromStart = new HashSet<>();
        Set<Integer> visitedFromGoal = new HashSet<>();

        Queue<Integer> queueStart = new LinkedList<>();
        Queue<Integer> queueGoal = new LinkedList<>();

        queueStart.add(start);
        queueGoal.add(goal);
        visitedFromStart.add(start);
        visitedFromGoal.add(goal);

        System.out.println("From Start " + start);
        System.out.println("From Goal " + goal + "\n");

        while (!queueStart.isEmpty() && !queueGoal.isEmpty()) 
        {
            int intersectNode = expandFront(queueStart, visitedFromStart, visitedFromGoal, "Start");
            if (intersectNode != -1)
                return intersectNode;

            intersectNode = expandFront(queueGoal, visitedFromGoal, visitedFromStart, "Goal");
            if (intersectNode != -1)
                return intersectNode;
        }
        return -1;
    }

    private int expandFront(Queue<Integer> queue, Set<Integer> visitedThisSide,
    Set<Integer> visitedOtherSide, String label) 
    {
        if (queue.isEmpty()) 
        return -1;

        int current = queue.poll();
        System.out.println(label + " visiting : " + current);

        for (int neighbor : adjList.get(current)) 
        {
            if (!visitedThisSide.contains(neighbor)) 
            {
                visitedThisSide.add(neighbor);
                queue.add(neighbor);

                if (visitedOtherSide.contains(neighbor)) 
                {
                    System.out.println("\n Intersection found at node : " + neighbor);
                    return neighbor;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        Bidirectional_Search graph = new Bidirectional_Search();

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

        System.out.print("Enter start node : ");
        int start = sc.nextInt();

        System.out.print("Enter goal node : ");
        int goal = sc.nextInt();

        int intersection = graph.bidirectionalSearch(start, goal);

        if (intersection == -1)
            System.out.println("\nNo path found between " + start + " and " + goal);
        else
            System.out.println("\nPath found! They meet at node: " + intersection);
    }
}

