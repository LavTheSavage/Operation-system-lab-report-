import java.util.Scanner;
public class dininglel {
 public static void main(String[] args) {
 boolean[] chopstick = {true, true, true, true, true};
 try (Scanner sc = new Scanner(System.in)) {
    System.out.print("Enter philosopher number (0-4): ");
     int p = sc.nextInt();
     if (chopstick[p] && chopstick[(p + 1) % 5]) {
     System.out.println("Philosopher " + p + " eats");
     chopstick[p] = false;
     chopstick[(p + 1) % 5] = false;
     } else {
     System.out.println("Philosopher " + p + " is waiting (Deadlock situation)");
     }
}
 }
}
