import java.util.*;

public class FIFO {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter number of frames: ");
            int frames = sc.nextInt();
            
            System.out.print("Enter number of pages: ");
            int n = sc.nextInt();
            
            int[] pages = new int[n];
            System.out.println("Enter page reference string:");
            for (int i = 0; i < n; i++) pages[i] = sc.nextInt();
            
            Set<Integer> memory = new HashSet<>();
            Queue<Integer> queue = new LinkedList<>();
            int faults = 0;
            
            System.out.println("\nFIFO Page Replacement:");
            for (int page : pages) {
                if (!memory.contains(page)) {
                    if (memory.size() == frames) {
                        int removed = queue.poll();
                        memory.remove(removed);
                        System.out.print("(Removed " + removed + ") ");
                    }
                    memory.add(page);
                    queue.add(page);
                    faults++;
                }
                System.out.print(page + " ");
            }

            System.out.println("\nTotal Page Faults: " + faults);
        }
    }
}
