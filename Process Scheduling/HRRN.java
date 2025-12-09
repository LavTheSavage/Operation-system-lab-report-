import java.util.*;
class Process {
    int pid, at, bt, ct, tat, wt;
    boolean done;
    Process(int pid, int at, int bt) {
        this.pid = pid;
        this.at = at;
        this.bt = bt;
    }
}
public class HRRN {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter number of processes: ");
            int n = sc.nextInt();
            Process[] p = new Process[n];
            for (int i = 0; i < n; i++) {
                System.out.print("Enter Arrival & Burst Time for P" + (i + 1) + ": ");
                p[i] = new Process(i + 1, sc.nextInt(), sc.nextInt());
            }
            int time = 0, finished = 0;
            while (finished < n) {
                int idx = -1;
                double maxRR = -1;
                for (int i = 0; i < n; i++) {
                    if (!p[i].done && p[i].at <= time) {
                        int wt = time - p[i].at;
                        double rr = (double) (wt + p[i].bt) / p[i].bt;
                        if (rr > maxRR) {
                            maxRR = rr;
                            idx = i;
                        }
                    }
                }
                if (idx == -1) { time++; continue; }
                Process x = p[idx];
                x.ct = time + x.bt;
                x.tat = x.ct - x.at;
                x.wt = x.tat - x.bt;
                time = x.ct;
                x.done = true;
                finished++;
            }
            System.out.println("\nProcess\tAT\tBT\tCT\tTAT\tWT");
            for (Process x : p)
                System.out.printf("P%d\t%d\t%d\t%d\t%d\t%d\n", x.pid, x.at, x.bt, x.ct, x.tat, x.wt);
            double avgTAT = Arrays.stream(p).mapToInt(x -> x.tat).average().orElse(0);
            double avgWT = Arrays.stream(p).mapToInt(x -> x.wt).average().orElse(0);
            System.out.printf("\nAverage Turnaround Time: %.2f\nAverage Waiting Time: %.2f\n", avgTAT, avgWT);
        }
    }
}
