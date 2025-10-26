import java.util.*;

public class Alpha_Beta_Pruning {

    static class Node 
    {
        int index;              
        List<Node> children;   
        Integer value;         

        Node(int index) 
        {
            this.index = index;
            children = new ArrayList<>();
            value = null;
        }
    }

    public static int alphaBeta(Node node, boolean isMaximizing, int alpha, int beta) {
      
        if (node.value != null) 
        {
            System.out.println("Visiting leaf node " + node.index + " with value " + node.value);
            return node.value;
        }

        if (isMaximizing) 
        {
            int best = Integer.MIN_VALUE;
            System.out.println("Visiting Max node at index " + node.index);
            for (Node child : node.children) 
            {
                int val = alphaBeta(child, false, alpha, beta);
                best = Math.max(best, val);
                alpha = Math.max(alpha, best);
                if (beta <= alpha) 
                {
                    System.out.println("Pruning children of Max node " + node.index);
                    break; 
                }
            }
            return best;
        } 
        else 
        {
            int best = Integer.MAX_VALUE;
            System.out.println("Visiting Min node at index " + node.index);

            for (Node child : node.children) 
            {
                int val = alphaBeta(child, true, alpha, beta);
                best = Math.min(best, val);
                beta = Math.min(beta, best);
                if (beta <= alpha) 
                {
                    System.out.println("Pruning children of Min node " + node.index);
                    break; 
                }
            }
            return best;
        }
    }
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter total number of nodes : ");
        int totalNodes = sc.nextInt();

        Node[] nodes = new Node[totalNodes];
        for (int i = 0; i < totalNodes; i++) 
        {
            nodes[i] = new Node(i);
        }

        System.out.println("Enter number of children for each node :");
        for (int i = 0; i < totalNodes; i++) 
        {
            System.out.print("Node " + i + ": ");
            int numChildren = sc.nextInt();
            if (numChildren == -1) 
            {
                System.out.print("Enter leaf value for node " + i + " : ");
                nodes[i].value = sc.nextInt();
            } 
            else 
            {
                System.out.println("Enter indices of children for node " + i + " :");
                for (int j = 0; j < numChildren; j++) 
                {
                    int childIndex = sc.nextInt();
                    nodes[i].children.add(nodes[childIndex]);
                }
            }
        }

        int optimalValue = alphaBeta(nodes[0], true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println("\nOptimal value for Maximizer : " + optimalValue);
        sc.close();
    }
}
