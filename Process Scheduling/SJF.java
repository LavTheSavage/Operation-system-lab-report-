import java.util.*;
class Process {
    int pid;
    int arrivalTime;
    int burstTime;
    int completionTime;
    int turnaroundTime;
    int waitingTime;
    Process(int pid, int arrivalTime, int burstTime) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }
}
public class SJF {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter number of processes: ");
            int n = sc.nextInt();
            Process[] processes = new Process[n];
            for (int i = 0; i < n; i++) {
                System.out.print("Enter Arrival Time for P" + (i + 1) + ": ");
                int at = sc.nextInt();
                System.out.print("Enter Burst Time for P" + (i + 1) + ": ");
                int bt = sc.nextInt();
                processes[i] = new Process(i + 1, at, bt);
            }
            boolean[] completed = new boolean[n];
            int currentTime = 0;
            int completedCount = 0;
            while (completedCount < n) {
                int idx = -1;
                int minBT = Integer.MAX_VALUE;
                for (int i = 0; i < n; i++) {
                    if (!completed[i] && processes[i].arrivalTime <= currentTime) {
                        if (processes[i].burstTime < minBT) {
                            minBT = processes[i].burstTime;
                            idx = i;
                        } else if (processes[i].burstTime == minBT) {
                            if (processes[i].arrivalTime < processes[idx].arrivalTime) {
                                idx = i;
                            }
                        }
                    }
                }
                if (idx == -1) {
                    currentTime++;
                } else {
                    Process p = processes[idx];
                    p.completionTime = currentTime + p.burstTime;
                    p.turnaroundTime = p.completionTime - p.arrivalTime;
                    p.waitingTime = p.turnaroundTime - p.burstTime;
                    currentTime = p.completionTime;
                    completed[idx] = true;
                    completedCount++;
                }
            }
            Arrays.sort(processes, Comparator.comparingInt(p -> p.pid));
            System.out.println("\nProcess\tAT\tBT\tCT\tTAT\tWT");
            for (Process p : processes) {
                System.out.println("P" + p.pid + "\t" + p.arrivalTime + "\t" + p.burstTime + "\t" +
                        p.completionTime + "\t" + p.turnaroundTime + "\t" + p.waitingTime);
            }
            double avgTAT = Arrays.stream(processes).mapToInt(p -> p.turnaroundTime).average().orElse(0);
            double avgWT = Arrays.stream(processes).mapToInt(p -> p.waitingTime).average().orElse(0);
            System.out.println("\nAverage Turnaround Time: " + avgTAT);
            System.out.println("Average Waiting Time: " + avgWT);
        }
    }
}
