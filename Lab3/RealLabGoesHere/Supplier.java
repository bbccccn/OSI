package Lab3.RealLabGoesHere;

import Lab3.Sample.SynchronizedBuffer;

// Bиробник
public class Supplier extends Thread {
    private VaultBuffer sharedLocation; // посилання на кiльцевий буфер
    private int valuesToWrite; // кiлькiсть елементiв, якi треба записати

    public Supplier(VaultBuffer shared, int valuesToWrite) {
        super("Producer");
        sharedLocation = shared;
        this.valuesToWrite = valuesToWrite;
    }

    public void run() {
        for (int count = 1; count <= valuesToWrite; count++) {
            try {
                Thread.sleep((int) (Math.random() * 3001));
                sharedLocation.set(count);
            }
            // якщо сплячий поток перервано, вивести дерево викликiв
            catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
        System.err.println(getName() + " закiнчив виробництво. Значень вироблено: " + valuesToWrite + "\nЗавершення " + getName() + ".\n");
    }
}