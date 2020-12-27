class Counter {
    private int x;
    private int count;

    public synchronized int getCount() {
        return count;
    }

    public synchronized void run() {
        while (x < 999999) {
            x++;
            if ((x % 10) + (x / 10) % 10 + (x / 100) % 10 == (x / 1000)
                    % 10 + (x / 10000) % 10 + (x / 100000) % 10) {
                System.out.println(x);
                count++;
            }
        }
    }
}

public class Lucky {
    static int count = 0;
    static Counter counter;

    static class LuckyThread extends Thread {
        public LuckyThread(Counter c) {
            counter = c;
        }

        @Override
        public void run() {
            counter.run();
            count = counter.getCount();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread t1 = new LuckyThread(counter);
        Thread t2 = new LuckyThread(counter);
        Thread t3 = new LuckyThread(counter);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + count);
    }
}