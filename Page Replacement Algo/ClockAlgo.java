import java.util.*;

public class ClockAlgo {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter number of frames: ");
            int frames = sc.nextInt();
            System.out.print("Enter number of pages: ");
            int n = sc.nextInt();
            int[] pages = new int[n];
            System.out.println("Enter page reference string:");
            for (int i = 0; i < n; i++) pages[i] = sc.nextInt();
            int[] mem = new int[frames];
            boolean[] use = new boolean[frames];
            Arrays.fill(mem, -1);
            int pointer = 0, faults = 0;
            System.out.println("\nClock Page Replacement:");
            for (int page : pages) {
                boolean hit = false;
                
                for (int i = 0; i < frames; i++) {
                    if (mem[i] == page) {
                        use[i] = true;
                        hit = true;
                        break;
                    }
                }
                
                if (!hit) {
                    while (use[pointer]) {
                        use[pointer] = false;
                        pointer = (pointer + 1) % frames;
                    }
                    System.out.print("(Removed " + mem[pointer] + ") ");
                    mem[pointer] = page;
                    use[pointer] = true;
                    pointer = (pointer + 1) % frames;
                    faults++;
                }
                System.out.print(page + " ");
            }   System.out.println("\nTotal Page Faults: " + faults);
        }
    }
}
