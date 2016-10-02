package Lab3.Sample;

// Cпоживач
public class Consumer extends Thread {
    private SynchronizedBuffer sharedLocation;
    private int elementsToRead;

    public Consumer(SynchronizedBuffer shared, int elementsToRead) {
        super("Consumer");
        sharedLocation = shared;
        this.elementsToRead = elementsToRead;
    }

    public void run() {
        int sum = 0;
        for (int count = 1; count <= elementsToRead; count++) {
            try {
                Thread.sleep((int) (Math.random() * 3001));
                sum += sharedLocation.get();
            }
            catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
        System.err.println(getName() + ": " + elementsToRead + " значень зчитано. Сума: " + sum + ".\nзавершення " + getName() + ".\n");
    }
}
