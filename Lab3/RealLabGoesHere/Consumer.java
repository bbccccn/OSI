package Lab3.RealLabGoesHere;

public class Consumer extends Thread {
    private VaultBuffer vaultBuffer;
    private int elementsToRead;

    public Consumer(VaultBuffer shared, int elementsToRead) {
        super("Consumer");
        vaultBuffer = shared;
        this.elementsToRead = elementsToRead;
    }

    public void run() {
        int sum = 0;
        for (int count = 1; count <= elementsToRead; count++) {
            try {
                Thread.sleep((int) (Math.random() * 3001));
                sum += vaultBuffer.get();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
        System.err.println(getName() + ": " + elementsToRead + " значень зчитано. Сума: " + sum + ".\nзавершення " + getName() + ".\n");
    }
}
