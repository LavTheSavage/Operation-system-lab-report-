import java.util.*;
class Process {
    int pid, at, bt, ct, tat, wt, rt;
    Process(int pid, int at, int bt) {
        this.pid = pid;
        this.at = at;
        this.bt = bt;
        this.rt = bt;
    }
}
public class RR {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter number of processes: ");
            int n = sc.nextInt();
            Process[] p = new Process[n];
            for (int i = 0; i < n; i++) {
                System.out.print("Enter Arrival & Burst Time for P" + (i + 1) + ": ");
                p[i] = new Process(i + 1, sc.nextInt(), sc.nextInt());
            }
            System.out.print("Enter Time Quantum: ");
            int q = sc.nextInt();
            Queue<Process> qList = new LinkedList<>();
            int time = 0, finished = 0;
            boolean[] inQueue = new boolean[n];
            while (finished < n) {
                for (int i = 0; i < n; i++)
                    if (!inQueue[i] && p[i].at <= time) {
                        qList.add(p[i]);
                        inQueue[i] = true;
                    }
                if (qList.isEmpty()) { time++; continue; }
                Process x = qList.poll();
                int exec = Math.min(x.rt, q);
                x.rt -= exec;
                time += exec;
                for (int i = 0; i < n; i++)
                    if (!inQueue[i] && p[i].at <= time) {
                        qList.add(p[i]);
                        inQueue[i] = true;
                    }
                if (x.rt == 0) {
                    x.ct = time;
                    x.tat = x.ct - x.at;
                    x.wt = x.tat - x.bt;
                    finished++;
                } else {
                    qList.add(x);
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
