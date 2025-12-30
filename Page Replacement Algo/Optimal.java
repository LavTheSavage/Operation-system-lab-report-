import java.util.*;

public class Optimal {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter number of frames: ");
            int frames = sc.nextInt();
            
            System.out.print("Enter number of pages: ");
            int n = sc.nextInt();
            
            int[] pages = new int[n];
            System.out.println("Enter page reference string:");
            for (int i = 0; i < n; i++) pages[i] = sc.nextInt();
            
            List<Integer> memory = new ArrayList<>();
            int faults = 0;
            
            System.out.println("\nOptimal Page Replacement:");
            for (int i = 0; i < pages.length; i++) {
                int page = pages[i];
                
                if (!memory.contains(page)) {
                    if (memory.size() < frames) {
                        memory.add(page);
                    } else {
                        int index = -1, farthest = -1;
                        
                        for (int j = 0; j < memory.size(); j++) {
                            int nextUse = Integer.MAX_VALUE;
                            for (int k = i + 1; k < pages.length; k++) {
                                if (memory.get(j) == pages[k]) {
                                    nextUse = k;
                                    break;
                                }
                            }
                            if (nextUse > farthest) {
                                farthest = nextUse;
                                index = j;
                            }
                        }
                        System.out.print("(Removed " + memory.get(index) + ") ");
                        memory.set(index, page);
                    }
                    faults++;
                }
                System.out.print(page + " ");
            }

            System.out.println("\nTotal Page Faults: " + faults);
        }
    }
}
