package Lab4.RealLabGoesHere;

public class Resource {
    String resName;
    boolean inUse = false;


    Resource(String resName) {
        this.resName = resName;
    }

    synchronized void ResJob() {
        try {
            if (inUse) {
                System.out.println(Thread.currentThread().getName() + ". Статус: Заблококований.");
                wait();
            }
            inUse = true;

            System.out.println(Thread.currentThread().getName() + ". Статус: Запущений. Використовується: " + resName + "...");
            Thread.sleep(300);

            inUse = false;
            notify();
        } catch (InterruptedException e) {
        }
    }
}
