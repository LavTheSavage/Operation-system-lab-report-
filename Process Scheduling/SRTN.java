import java.util.*;
class Process {
    int pid, at, bt, rt, ct, tat, wt;
    Process(int pid, int at, int bt) {
        this.pid = pid;
        this.at = at;
        this.bt = bt;
        this.rt = bt;
    }
}
public class SRTN {
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
                int idx = -1, minRT = Integer.MAX_VALUE;
                for (int i = 0; i < n; i++) {
                    if (p[i].at <= time && p[i].rt > 0 && p[i].rt < minRT) {
                        minRT = p[i].rt;
                        idx = i;
                    }
                }
                if (idx == -1) { time++; continue; }
                p[idx].rt--;
                time++;
                if (p[idx].rt == 0) {
                    p[idx].ct = time;
                    p[idx].tat = p[idx].ct - p[idx].at;
                    p[idx].wt = p[idx].tat - p[idx].bt;
                    finished++;
                }
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
