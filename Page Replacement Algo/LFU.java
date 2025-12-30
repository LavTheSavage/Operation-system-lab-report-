import java.util.*;

public class LFU {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter number of frames: ");
            int frames = sc.nextInt();
            
            System.out.print("Enter number of pages: ");
            int n = sc.nextInt();
            
            int[] pages = new int[n];
            System.out.println("Enter page reference string:");
            for (int i = 0; i < n; i++) pages[i] = sc.nextInt();
            
            Map<Integer, Integer> memory = new LinkedHashMap<>();
            Map<Integer, Integer> freq = new HashMap<>();
            int faults = 0;
            
            System.out.println("\nLFU Page Replacement:");
            for (int page : pages) {
                if (memory.containsKey(page)) {
                    freq.put(page, freq.get(page) + 1);
                } else {
                    if (memory.size() == frames) {
                        int lfu = memory.keySet()
                                .stream()
                                .min(Comparator.comparingInt(freq::get))
                                .get();
                        System.out.print("(Removed " + lfu + ") ");
                        memory.remove(lfu);
                        freq.remove(lfu);
                    }
                    memory.put(page, page);
                    freq.put(page, 1);
                    faults++;
                }
                System.out.print(page + " ");
            }

            System.out.println("\nTotal Page Faults: " + faults);
        }
    }
}
