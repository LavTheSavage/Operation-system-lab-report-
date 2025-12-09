import java.util.*;

public class WorstFit {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter number of memory blocks: ");
            int m = sc.nextInt();
            int[] blockSize = new int[m];
            System.out.println("Enter block sizes:");
            for (int i = 0; i < m; i++) blockSize[i] = sc.nextInt();

            System.out.print("Enter number of processes: ");
            int n = sc.nextInt();
            int[] processSize = new int[n];
            System.out.println("Enter process sizes:");
            for (int i = 0; i < n; i++) processSize[i] = sc.nextInt();

            int[] allocation = new int[n];
            Arrays.fill(allocation, -1);

            for (int i = 0; i < n; i++) {
                int worstIdx = -1;
                for (int j = 0; j < m; j++) {
                    if (blockSize[j] >= processSize[i]) {
                        if (worstIdx == -1 || blockSize[j] > blockSize[worstIdx])
                            worstIdx = j;
                    }
                }
                if (worstIdx != -1) {
                    allocation[i] = worstIdx;
                    blockSize[worstIdx] -= processSize[i];
                }
            }

            System.out.println("\nProcess No\tProcess Size\tBlock No");
            for (int i = 0; i < n; i++)
                System.out.printf("%d\t\t%d\t\t%s\n", i+1, processSize[i],
                                  (allocation[i] == -1 ? "Not Allocated" : (allocation[i]+1)));
        }
    }
}
