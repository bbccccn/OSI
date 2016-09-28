package Lab2.ThreadSample;

public class Resource2 {
    public boolean r2 = false;

    synchronized void res2(int P2) {
        try {
            if (r2) wait();
            r2 = true;
            System.out.println(" Ресурс 2 используется процессом " + P2);
            r2 = false;
            notify();
        } catch (InterruptedException e) {
        }
    }
}
