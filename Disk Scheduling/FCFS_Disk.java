import java.util.*;

public class FCFS_Disk {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter number of requests: ");
            int n = sc.nextInt();
            int[] req = new int[n];
            System.out.println("Enter request queue:");
            for (int i = 0; i < n; i++) req[i] = sc.nextInt();
            System.out.print("Enter initial head position: ");
            int head = sc.nextInt();

            int total = 0;
            System.out.print("\nSeek Sequence: " + head);
            for (int i = 0; i < n; i++) {
                total += Math.abs(req[i] - head);
                head = req[i];
                System.out.print(" -> " + head);
            }

            System.out.println("\nTotal Head Movement: " + total);
            System.out.printf("Average Seek Time: %.2f\n", (double) total / n);
        }
    }
}
