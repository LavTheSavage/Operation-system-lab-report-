import java.util.*;

public class PageReplacementAlgorithms {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter number of frames: ");
            int frames = sc.nextInt();
            System.out.print("Enter number of pages in reference string: ");
            int n = sc.nextInt();
            int[] pages = new int[n];
            System.out.println("Enter page reference string:");
            for (int i = 0; i < n; i++) pages[i] = sc.nextInt();

            while (true) {
                System.out.println("\n--- Page Replacement Menu ---");
                System.out.println("1. FIFO / FCFS");
                System.out.println("2. Optimal");
                System.out.println("3. LRU");
                System.out.println("4. Second Chance");
                System.out.println("5. Clock");
                System.out.println("6. LFU");
                System.out.println("0. Exit");
                System.out.print("Choose an algorithm: ");
                int choice = sc.nextInt();
                if (choice == 0) break;

                switch (choice) {
                    case 1 -> fifo(pages, frames);
                    case 2 -> optimal(pages, frames);
                    case 3 -> lru(pages, frames);
                    case 4 -> secondChance(pages, frames);
                    case 5 -> clockAlgo(pages, frames);
                    case 6 -> lfu(pages, frames);
                    default -> System.out.println("Invalid choice!");
                }
            }
        }
    }

    // ---------------- FIFO / FCFS ----------------
    static void fifo(int[] pages, int frames) {
        Set<Integer> memory = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        int faults = 0;
        System.out.println("\nFIFO/FCFS Page Replacement Sequence:");
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

    // ---------------- Optimal ----------------
    static void optimal(int[] pages, int frames) {
        List<Integer> memory = new ArrayList<>();
        int faults = 0;
        System.out.println("\nOptimal Page Replacement Sequence:");
        for (int i = 0; i < pages.length; i++) {
            int page = pages[i];
            if (!memory.contains(page)) {
                if (memory.size() < frames) {
                    memory.add(page);
                } else {
                    int idxToReplace = -1, farthest = -1;
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
                            idxToReplace = j;
                        }
                    }
                    System.out.print("(Removed " + memory.get(idxToReplace) + ") ");
                    memory.set(idxToReplace, page);
                }
                faults++;
            }
            System.out.print(page + " ");
        }
        System.out.println("\nTotal Page Faults: " + faults);
    }

    // ---------------- LRU ----------------
    static void lru(int[] pages, int frames) {
        Set<Integer> memory = new HashSet<>();
        Map<Integer, Integer> recent = new HashMap<>();
        int time = 0, faults = 0;
        System.out.println("\nLRU Page Replacement Sequence:");
        for (int page : pages) {
            time++;
            if (!memory.contains(page)) {
                if (memory.size() == frames) {
                    int lruPage = Collections.min(recent.entrySet(), Map.Entry.comparingByValue()).getKey();
                    memory.remove(lruPage);
                    recent.remove(lruPage);
                    System.out.print("(Removed " + lruPage + ") ");
                }
                memory.add(page);
                faults++;
            }
            recent.put(page, time);
            System.out.print(page + " ");
        }
        System.out.println("\nTotal Page Faults: " + faults);
    }

    // ---------------- Second Chance ----------------
    static void secondChance(int[] pages, int frames) {
        int[] mem = new int[frames];
        boolean[] refBit = new boolean[frames];
        Arrays.fill(mem, -1);
        int pointer = 0, faults = 0;
        System.out.println("\nSecond Chance Page Replacement Sequence:");
        for (int page : pages) {
            boolean hit = false;
            for (int i = 0; i < frames; i++) {
                if (mem[i] == page) {
                    refBit[i] = true;
                    hit = true;
                    break;
                }
            }
            if (!hit) {
                while (refBit[pointer]) {
                    refBit[pointer] = false;
                    pointer = (pointer + 1) % frames;
                }
                System.out.print("(Removed " + mem[pointer] + ") ");
                mem[pointer] = page;
                refBit[pointer] = false;
                pointer = (pointer + 1) % frames;
                faults++;
            }
            System.out.print(page + " ");
        }
        System.out.println("\nTotal Page Faults: " + faults);
    }

    // ---------------- Clock ----------------
    static void clockAlgo(int[] pages, int frames) {
        int[] mem = new int[frames];
        boolean[] use = new boolean[frames];
        Arrays.fill(mem, -1);
        int pointer = 0, faults = 0;
        System.out.println("\nClock Page Replacement Sequence:");
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
        }
        System.out.println("\nTotal Page Faults: " + faults);
    }

    // ---------------- LFU ----------------
    static void lfu(int[] pages, int frames) {
        Map<Integer, Integer> mem = new LinkedHashMap<>();
        Map<Integer, Integer> freq = new HashMap<>();
        int faults = 0;
        System.out.println("\nLFU Page Replacement Sequence:");
        for (int page : pages) {
            if (mem.containsKey(page)) {
                freq.put(page, freq.get(page) + 1);
            } else {
                if (mem.size() == frames) {
                    int lfuPage = mem.keySet().stream().min(Comparator.comparingInt(freq::get)).get();
                    System.out.print("(Removed " + lfuPage + ") ");
                    mem.remove(lfuPage);
                    freq.remove(lfuPage);
                }
                mem.put(page, 1);
                freq.put(page, 1);
                faults++;
            }
            System.out.print(page + " ");
        }
        System.out.println("\nTotal Page Faults: " + faults);
    }
}
