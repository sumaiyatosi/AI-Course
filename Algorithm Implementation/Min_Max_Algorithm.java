import java.util.Scanner;

public class Min_Max_Algorithm {

    public static int minimax(int node, int depth, boolean isMaximizing, int[][] tree, int[] leafValues, int leafStartIndex) {

        if (node >= leafStartIndex) 
        {
            int value = leafValues[node - leafStartIndex];
            System.out.println("Visiting leaf node " + node + " with value " + value);
            return value;
        }

        if (isMaximizing) 
        {
            int best = Integer.MIN_VALUE;
            System.out.println("Visiting Max node at index " + node);
            for (int child : tree[node]) {
                int val = minimax(child, depth - 1, false, tree, leafValues, leafStartIndex);
                best = Math.max(best, val);
            }
            return best;
        } 
        else 
        {
            int best = Integer.MAX_VALUE;
            System.out.println("Visiting Min node at index " + node);
            for (int child : tree[node]) 
            {
                int val = minimax(child, depth - 1, true, tree, leafValues, leafStartIndex);
                best = Math.min(best, val);
            }
            return best;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of levels : ");
        int levels = sc.nextInt();

        System.out.print("Enter number of leaf nodes : ");
        int leafNodes = sc.nextInt();

        int totalNodes = (int)Math.pow(2, levels + 1) - 1; 
        int leafStartIndex = totalNodes - leafNodes;

        int[][] tree = new int[totalNodes][];
        int[] leafValues = new int[leafNodes];

        System.out.println("Enter leaf node values :");
        for (int i = 0; i < leafNodes; i++) 
        {
            leafValues[i] = sc.nextInt();
        }

        for (int i = 0; i < leafStartIndex; i++) 
        {
            tree[i] = new int[]{2*i + 1, 2*i + 2};
        }

        int optimalValue = minimax(0, levels, true, tree, leafValues, leafStartIndex);
        System.out.println("\nOptimal value for Maximizer : " + optimalValue);
        sc.close();
    }
}
