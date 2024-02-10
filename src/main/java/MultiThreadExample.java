public class MultiThreadExample {

    public static void main(String[] args) {
        // Creating the first thread for even numbers
        Thread evenThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 10; i += 2) {
                    System.out.println("Even: " + i);
                    try {
                        Thread.sleep(100); // Pause to ensure the output is mixed between the threads
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("Thread was interrupted, Failed to complete operation");
                    }
                }
            }
        });

        // Creating the second thread for odd numbers
        Thread oddThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i += 2) {
                    System.out.println("Odd: " + i);
                    try {
                        Thread.sleep(100); // Pause to ensure the output is mixed between the threads
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("Thread was interrupted, Failed to complete operation");
                    }
                }
            }
        });

        // Starting both threads
        evenThread.start();
        oddThread.start();

        // Waiting for both threads to finish
        try {
            evenThread.join();
            oddThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread was interrupted.");
        }

        System.out.println("Finished executing both threads.");
    }
}
