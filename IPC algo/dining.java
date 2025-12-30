import java.util.concurrent.Semaphore;

class Philosopher extends Thread {
    int id;
    Semaphore left, right, room;

    Philosopher(int id, Semaphore left, Semaphore right, Semaphore room) {
        this.id = id;
        this.left = left;
        this.right = right;
        this.room = room;
    }

    @Override
    public void run() {
        try {
            think();

            System.out.println("\nPhilosopher " + id + " wants to eat");

            room.acquire();
            System.out.println("Philosopher " + id + " enters dining room");

            left.acquire();
            System.out.println("Philosopher " + id + " picked LEFT chopstick");

            right.acquire();
            System.out.println("Philosopher " + id + " picked RIGHT chopstick");

            eat();

            right.release();
            System.out.println("Philosopher " + id + " released RIGHT chopstick");

            left.release();
            System.out.println("Philosopher " + id + " released LEFT chopstick");

            room.release();
            System.out.println("Philosopher " + id + " leaves dining room");

            System.out.println("Philosopher " + id + " is DONE\n");

        } catch (InterruptedException e) {
            System.out.println(" Philosopher " + id + " was interrupted.");
        }
    }

    void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " is THINKING");
        sleep(1000);
    }

    void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + " is EATING");
        sleep(1000);
    }
}

public class dining {
    public static void main(String[] args) {
        int n = 5;
        Semaphore[] chopsticks = new Semaphore[n];
        Semaphore room = new Semaphore(n - 1); 

        for (int i = 0; i < n; i++) {
            chopsticks[i] = new Semaphore(1);
        }

        for (int i = 0; i < n; i++) {
            new Philosopher(
                i + 1,                          
                chopsticks[i],
                chopsticks[(i + 1) % n],
                room
            ).start();
        }
    }
}
