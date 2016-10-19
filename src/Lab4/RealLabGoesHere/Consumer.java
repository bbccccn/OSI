package Lab4.RealLabGoesHere;

import java.util.List;

public class Consumer implements Runnable{
    private List<Resource> producersToUse;
    RAMWrapper ramWrapper;
    Integer neededRAM;
    Thread  t;

    public Consumer(String processName, List<Resource> producersToUse, RAMWrapper RAM, Integer neededRAM) {
        this.producersToUse = producersToUse;
        this.ramWrapper = RAM;
        this.neededRAM = neededRAM;
        t = new Thread(this, processName);
//        t.start();
    }

    public void run() {
        try {
            if (ramWrapper.RAM < neededRAM) {
                System.out.println("Для процессу " + Thread.currentThread().getName() + " недостатньо пам'яті.\n"
                        + "Памяті потрібно: " + neededRAM + "mb; Пам'яті вільно:" + (ramWrapper.RAM) + "mb."
                        + "\n Статус: Заблокований");
                while (ramWrapper.RAM  < neededRAM) {
                    holdOn();
                }
            }
            ramWrapper.RAM  -= neededRAM;

            for (Resource res : producersToUse) {
                res.ResJob();
                Thread.sleep(100);
            }

            ramWrapper.RAM  += neededRAM;
            System.out.println(Thread.currentThread().getName() + ". Статус: Закiнчення.");
        } catch (InterruptedException e) {}
    }

    private void holdOn() {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
