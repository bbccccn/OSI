package Lab5.Sample;

//процес
public class TProc implements Runnable {
    int pID;
    String procName;
    int procRAM; //доступна пам'ять
    TResource ResList[]; //список ресурс?в
    char priority;//пр?ор?тет
    Thread t;
    // конструктор
    TProc(int pID, String procName, int procRAM, TResource ResList[], char priority) {
        this.pID = pID;
        this.procName = procName;
        this.procRAM = procRAM;
        this.ResList = ResList;
        this.priority = priority;
        t = new Thread(this, procName);
        t.start();
    }
    public void run() {
        int z = 0;
        //пошук процес?в для вит?снення, якщо не вистачає пам'ят?
        if(Lab5.FreeRam.get() < procRAM) {
            for (int i = 0; i < Lab5.processes.length; ++i) {
                if ((Lab5.processes[i] == 1) && (Lab5.procPrior[i] < this.priority) && (Lab5.procMem[i]+Lab5.FreeRam.get() >= procRAM)) {
                    Lab5.processes[i] = 2;
                    Lab5.FreeRam.set((Lab5.FreeRam.get() + Lab5.procMem[i]));
                    z = 1;
                    System.out.println(Lab5.procNames[i]+". Стан: Зупинений (витиснено процесом: " + this.procName + ") Об'єм вiльної пам'ятi: "+Lab5.FreeRam.get()+" Мб.");
                    System.out.println(procName+". Стан: Новий -> Готовий. Об'єм вiльної пам'ятi: "+(Lab5.FreeRam.get()-procRAM)+" Мб.");
                    break;
                }
            }
            //вит?снити процеси не вдалось
            if (z == 0) {
                System.out.println(procName + ". Стан: Новий -> Готовий/Призупинений. Об'єм вiльної пам'ятi: " + Lab5.FreeRam.get() + " Мб.");
                while(Lab5.FreeRam.get() < procRAM) {
                    // Чекати на звiльнення пам'ятi
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(procName+". Стан: Готовий/Призупинений -> Готовий. Об'єм вiльної пам'ятi: "+(Lab5.FreeRam.get()-procRAM)+" Мб.");
            }
        }
        else
            System.out.println(procName+". Стан: Новий -> Готовий. Об'єм вiльної пам'ятi: "+(Lab5.FreeRam.get()-procRAM)+" Мб.");
        // Зайняти пам'ять
        Lab5.FreeRam.set(Lab5.FreeRam.get() - procRAM);
        Lab5.processes[this.pID] = 1;
        try {
            // Використання процесом всiх ресурсiв по списку
            for(int i = 0; i < ResList.length; i++) {
                ResList[i].ResJob(this.pID, this.procRAM);
                Thread.sleep(500); // пауза
            }
        }
        catch (InterruptedException e) {
            System.err.println(procName + ". Виконання перерване!");
        }
        // Звiльнити пам'ять
        Lab5.FreeRam.set(Lab5.FreeRam.get() + procRAM);
        System.out.println(procName + ". Завершення.Об'єм вiльної пам'ятi: "+Lab5.FreeRam.get()+" Мб.");
    }
}
