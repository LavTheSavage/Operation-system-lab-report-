import java.util.*;
public class BankersAlgorithm {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter number of processes: ");
            int n = sc.nextInt();
            System.out.print("Enter number of resources: ");
            int m = sc.nextInt();
            int[][] max = new int[n][m];
            int[][] allocation = new int[n][m];
            int[][] need = new int[n][m];
            int[] available = new int[m];
            System.out.println("Enter Max matrix:");
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                    max[i][j] = sc.nextInt();
            System.out.println("Enter Allocation matrix:");
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                    allocation[i][j] = sc.nextInt();
            System.out.println("Enter Available vector:");
            for (int j = 0; j < m; j++)
                available[j] = sc.nextInt();  
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                    need[i][j] = max[i][j] - allocation[i][j];
            boolean[] finish = new boolean[n];
            int[] safeSeq = new int[n];
            int count = 0;
            int[] work = Arrays.copyOf(available, m);
            while (count < n) {
                boolean found = false;
                for (int i = 0; i < n; i++) {
                    if (!finish[i]) {
                        boolean possible = true;
                        for (int j = 0; j < m; j++) {
                            if (need[i][j] > work[j]) {
                                possible = false;
                                break;
                            }
                        }
                        if (possible) {
                            for (int j = 0; j < m; j++)
                                work[j] += allocation[i][j];
                            safeSeq[count++] = i;
                            finish[i] = true;
                            found = true;
                            break;
                        }
                    }
                }
                if (!found) break;
            }
            boolean safe = count == n;
            if (safe) {
                System.out.println("System is in a safe state.");
                System.out.print("Safe sequence: ");
                for (int i = 0; i < n; i++)
                    System.out.print("P" + safeSeq[i] + (i == n - 1 ? "" : " -> "));
            } else {
                System.out.println("System is NOT in a safe state. Deadlock may occur.");
            }
        }
    }
}
