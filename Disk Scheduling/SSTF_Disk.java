import java.util.*;

public class SSTF_Disk {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter number of requests: ");
            int n = sc.nextInt();
            int[] req = new int[n];
            System.out.println("Enter request queue:");
            for (int i = 0; i < n; i++) req[i] = sc.nextInt();
            System.out.print("Enter initial head position: ");
            int head = sc.nextInt();

            boolean[] done = new boolean[n];
            int total = 0;
            System.out.print("\nSeek Sequence: " + head);

            for (int c = 0; c < n; c++) {
                int idx = -1, min = Integer.MAX_VALUE;
                for (int i = 0; i < n; i++)
                    if (!done[i] && Math.abs(req[i] - head) < min) {
                        min = Math.abs(req[i] - head);
                        idx = i;
                    }

                total += Math.abs(req[idx] - head);
                head = req[idx];
                done[idx] = true;
                System.out.print(" -> " + head);
            }

            System.out.println("\nTotal Head Movement: " + total);
            System.out.printf("Average Seek Time: %.2f\n", (double) total / n);
        }
    }
}
