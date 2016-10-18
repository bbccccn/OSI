package Lab5.Sample;

//ресурс
public class TResource {
    String resName;
    boolean inUse = false;
    // конструктор
    TResource(String resName) {
        this.resName = resName;
    }
    synchronized void ResJob(int pID, int pRam) {
        while (inUse) {
            System.out.println(Thread.currentThread().getName()+". Стан: Блокований.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }	// Очiкування завершення роботи iнших процесiв з даним ресурсом
        }
        inUse = true; // Захоплення ресурсу поточним процесом
        // емуляцiя роботи з ресурсом
        if (Lab5.processes[pID] == 2) {
            while (Lab5.FreeRam.get() < pRam) {
                // Чекати на звiльнення пам'ятi
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Lab5.processes[pID] = 1;
            Lab5.FreeRam.set((Lab5.FreeRam.get() - pRam));
            System.out.println(Lab5.procNames[pID]+". Стан: Зупинений (витиснено процесом з вищим прiоритетом!) -> Готовий. Об'єм вiльної пам'ятi: "+(Lab5.FreeRam.get())+" Мб.");
        }
        System.out.println(Thread.currentThread().getName() + ". Стан: Виконується. Використовується: "+ resName+"..."/*+i*/);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //	}
        inUse = false;	// Звiльнення ресурсу
    }
}
