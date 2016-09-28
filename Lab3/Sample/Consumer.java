package Lab3.Sample;

// Cпоживач
public class Consumer extends Thread {
    private SynchronizedBuffer sharedLocation; // посилання на кiльцевий буфер
    private int el_toread; // кiлькiсть елементiв, якi треба зчитати
    // конструктор
    public Consumer(SynchronizedBuffer shared, int ael_toread) {
        super("Consumer");
        sharedLocation = shared;
        el_toread = ael_toread;
    }
    // зчитати el_toread елементiв i порахувати їх суму
    public void run() {
        int sum = 0;
        for (int count = 1; count <= el_toread; count++) {
            try {
                Thread.sleep((int)(Math.random()*3001));
                sum += sharedLocation.get();
            }
            // якщо сплячий поток перервано, вивести дерево викликiв
            catch(InterruptedException exception) {
                exception.printStackTrace();
            }
        }
        System.err.println(getName()+": "+el_toread+" значень зчитано. Сума: "+sum+".\nзавершення "+getName()+".\n");
    }
}
