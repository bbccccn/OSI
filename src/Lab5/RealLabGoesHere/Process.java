package RealLabGoesHere;

import java.util.List;

public class Process implements Runnable {
    private int pID;
    private String procName;
    private int procRAM;
    private List<Resource> ResList;
    private char priority;
    private Thread t;
    private Buffer buffer;

    Process(int pID, String procName, int procRAM, char priority, Buffer buffer) {
        this.pID = pID;
        this.procName = procName;
        this.procRAM = procRAM;
        this.priority = priority;
        this.buffer = buffer;
        this.ResList = buffer.getResources();
        t = new Thread(this, procName);
        t.start();
    }

    public void run() {
        boolean isResourcesDispensed = false;
        if (Lab5.FreeRam.get() < procRAM) {
            for (int i = 0; i < Lab5.processes.length; ++i) {
                if (isDispensingPossible(i)) {
                    Lab5.processes[i] = 2;
                    Lab5.FreeRam.set((Lab5.FreeRam.get() + Lab5.procMem[i]));
                    isResourcesDispensed = true;
                    System.out.println(Lab5.procNames[i]
                            + ". Стан: Зупинений (витиснено процесом: "
                            + this.procName
                            + ") Об'єм вiльної пам'ятi: "
                            + Lab5.FreeRam.get() + " Мб.");
                    System.out.println(procName
                            + ". Стан: Новий -> Готовий. Об'єм вiльної пам'ятi: "
                            + (Lab5.FreeRam.get() - procRAM) + " Мб.");
                    break;
                }
            }
            if (!isResourcesDispensed) {
                System.out.println(procName
                        + ". Стан: Новий -> Готовий/Призупинений. Об'єм вiльної пам'ятi: "
                        + Lab5.FreeRam.get() + " Мб.");
                sleepUntilRamFree();
                System.out.println(procName
                        + ". Стан: Готовий/Призупинений -> Готовий. Об'єм вiльної пам'ятi: "
                        + (Lab5.FreeRam.get() - procRAM) + " Мб.");
            }
        } else {
            System.out.println(procName
                    + ". Стан: Новий -> Готовий. Об'єм вiльної пам'ятi: "
                    + (Lab5.FreeRam.get() - procRAM) + " Мб.");
        }
        Lab5.FreeRam.set(Lab5.FreeRam.get() - procRAM);
        Lab5.processes[this.pID] = 1;

        doJob();

        Lab5.FreeRam.set(Lab5.FreeRam.get() + procRAM);
        System.out.println(procName + ". Завершення.Об'єм вiльної пам'ятi: " + Lab5.FreeRam.get() + " Мб.");
    }

    private void sleepUntilRamFree() {
        while (Lab5.FreeRam.get() < procRAM) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void doJob() {
        try {
            for (int i = 0; i < ResList.size(); i++) {
                ResList.get(i).ResJob(this.pID, this.procRAM);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.err.println(procName + ". Виконання перерване!");
        }
    }

    private boolean isDispensingPossible(int i) {
        return (Lab5.processes[i] == 1)
                && (Lab5.procPrior[i] < this.priority)
                && (Lab5.procMem[i] + Lab5.FreeRam.get() >= procRAM);
    }

    public int getpID() {
        return pID;
    }

    public void setpID(int pID) {
        this.pID = pID;
    }

    public String getProcName() {
        return procName;
    }

    public void setProcName(String procName) {
        this.procName = procName;
    }

    public int getProcRAM() {
        return procRAM;
    }

    public void setProcRAM(int procRAM) {
        this.procRAM = procRAM;
    }

    public char getPriority() {
        return priority;
    }

    public void setPriority(char priority) {
        this.priority = priority;
    }

    public Thread getT() {
        return t;
    }

    public void setT(Thread t) {
        this.t = t;
    }
}