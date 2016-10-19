package Sample;

public class TResource1 {
    String resourceName;
    boolean inUse = false;

    TResource1(String resourceName) {
        this.resourceName = resourceName;
    }

    synchronized void ResJob(int pID, int pRam) {
        while (inUse) {
            System.out.println(Thread.currentThread().getName() + ". Стан: Блокований.");
            sleep();
        }
        inUse = true;

        if (Lab5.processes[pID] == 2) {
            while (Lab5.FreeRam.get() < pRam) {
                sleep();
            }
            Lab5.processes[pID] = 1;
            Lab5.FreeRam.set((Lab5.FreeRam.get() - pRam));
            System.out.println(Lab5.procNames[pID]
                    + ". Стан: Зупинений (витиснено процесом з вищим прiоритетом!)"
                    + " -> Готовий. Об'єм вiльної пам'ятi: " + (Lab5.FreeRam.get()) + " Мб.");
        }
        System.out.println(Thread.currentThread().getName()
                + ". Стан: Виконується. Використовується: "
                + resourceName + "...");
        sleep();
        inUse = false;
    }

    private void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
