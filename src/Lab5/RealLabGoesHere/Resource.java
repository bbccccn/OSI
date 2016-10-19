package RealLabGoesHere;

public class Resource {
    String resourceName;
    boolean inUse = false;
    Buffer buffer;

    public Resource(String resourceName, Buffer buffer) {
        this.resourceName = resourceName;
        this.buffer = buffer;
    }

    synchronized void ResJob(int pID, int pRam) {
        while (inUse) {
            System.out.println(Thread.currentThread().getName() + ". Стан: Блокований.");
            sleep();
        }
        inUse = true;

        if (buffer.getProcesses().get(pID).getpID() == 2) {
            while (buffer.getRAM() < pRam) {
                sleep();
            }
            buffer.getProcesses().get(pID).setpID(1);
            buffer.substractRam(pRam);
            System.out.println(buffer.getProcesses().get(pID)
                    + ". Стан: Зупинений (витиснено процесом з вищим прiоритетом!)"
                    + " -> Готовий. Об'єм вiльної пам'ятi: " + (buffer.getRAM()) + " Мб.");
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
