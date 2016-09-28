package Lab3.Sample;

// Bиробник
public class Producer extends Thread {
    private SynchronizedBuffer sharedLocation; // посилання на кiльцевий буфер
    private int valuesToWrite; // кiлькiсть елементiв, якi треба записати
    // конструктор
    public Producer(SynchronizedBuffer shared, int ael_towrite) {
        super("Producer");
        sharedLocation = shared;
        valuesToWrite = ael_towrite;
    }
    // записати valuesToWrite елементiв у буфер
    public void run() {
        for (int count = 1; count <= valuesToWrite; count++) {
            try {
                Thread.sleep((int)(Math.random()*3001));
                sharedLocation.set(count);
            }
            // якщо сплячий поток перервано, вивести дерево викликiв
            catch(InterruptedException exception) {
                exception.printStackTrace();
            }
        }
        System.err.println(getName()+" закiнчив виробництво. Значень вироблено: "+ valuesToWrite +"\nЗавершення "+getName()+".\n");
    }
}
