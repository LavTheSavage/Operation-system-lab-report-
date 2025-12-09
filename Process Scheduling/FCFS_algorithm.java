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
public class FCFS_algorithm {
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
            Arrays.sort(processes, Comparator.comparingInt(p -> p.arrivalTime));
            int currentTime = 0;
            for (Process p : processes) {
                if (currentTime < p.arrivalTime) {
                    currentTime = p.arrivalTime;
                }
                p.completionTime = currentTime + p.burstTime;
                p.turnaroundTime = p.completionTime - p.arrivalTime;
                p.waitingTime = p.turnaroundTime - p.burstTime;
                currentTime = p.completionTime;
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
            Arrays.sort(processes, Comparator.comparingInt(p -> p.pid));
        }
    }
}
