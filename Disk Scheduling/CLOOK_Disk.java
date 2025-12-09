import java.util.*;
public class CLOOK_Disk {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter number of requests: ");
            int n = sc.nextInt();
            int[] req = new int[n];
            System.out.println("Enter request queue:");
            for (int i = 0; i < n; i++) req[i] = sc.nextInt();
            System.out.print("Enter initial head position: ");
            int head = sc.nextInt();
            Arrays.sort(req);
            List<Integer> left = new ArrayList<>(), right = new ArrayList<>();
            for (int x : req) {
                if (x < head) left.add(x);
                else right.add(x);
            }
            int total = 0;
            System.out.print("\nSeek Sequence: " + head);
            for (int x : right) {
                total += Math.abs(x - head);
                head = x;
                System.out.print(" -> " + head);
            }
            if (!left.isEmpty()) {
                total += Math.abs(head - left.get(left.size() - 1));
                head = left.get(left.size() - 1);
            }
            Collections.reverse(left);
            for (int x : left) {
                total += Math.abs(x - head);
                head = x;
                System.out.print(" -> " + head);
            }
            System.out.println("\nTotal Head Movement: " + total);
            System.out.printf("Average Seek Time: %.2f\n", (double) total / n);
        }
    }
}
