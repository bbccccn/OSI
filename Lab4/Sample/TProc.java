package Lab4.Sample;

public class TProc implements Runnable {
    String procName;
    int procRAM;
    TResource ResList[];
    Thread t;

    public TProc(String procName, int procRAM, TResource ResList[]) {
        this.procRAM = procRAM;
        this.ResList = ResList;
        this.procName = procName;
        t = new Thread(this, this.procName);
        t.start();
    }

    public void run() {
        if (Lab4.FreeRAM < procRAM) {
            System.out.println(procName + ". Статус: Новий->Гoтовий/Зупинений. Розмiр вiльноi памятi: " + Lab4.FreeRAM + " mb" + " а необхiдно " + this.procRAM + "mb.");
            while (Lab4.FreeRAM < procRAM) {
                // Чекати на звiльнення пам'ятi
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(procName + ". Статус: Готовий/Зупинений->Готовий. Розмiр вiльноi памятi: " + (Lab4.FreeRAM - procRAM) + " mb");
        } else {
            System.out.println(procName + ". Статус: Новий->Готовий. Розмiр вiльноi памятi: " + (Lab4.FreeRAM - procRAM) + " mb");
            Lab4.FreeRAM -= procRAM;        // Зайняти пам'ять
        }

        try {
            for (int i = 0; i < ResList.length; i++) {
                ResList[i].ResJob();
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println(procName + ". Статус: Вiдмiнений!");
        }
        System.out.println(procName + ". Статус: Закiнчення.");
        Lab4.FreeRAM += procRAM;
    }
}

