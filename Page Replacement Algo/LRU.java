import java.util.*;

public class LRU {
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
            Map<Integer, Integer> recent = new HashMap<>();
            int time = 0, faults = 0;
            System.out.println("\nLRU Page Replacement:");
            for (int page : pages) {
                time++;
                if (!memory.contains(page)) {
                    if (memory.size() == frames) {
                        int lru = Collections.min(recent.entrySet(),
                                Map.Entry.comparingByValue()).getKey();
                        memory.remove(lru);
                        recent.remove(lru);
                        System.out.print("(Removed " + lru + ") ");
                    }
                    memory.add(page);
                    faults++;
                }
                recent.put(page, time);
                System.out.print(page + " ");
            }   System.out.println("\nTotal Page Faults: " + faults);
        }
    }
}
