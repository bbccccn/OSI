package Lab2.ThreadSample;

public class Resource1 {
    public boolean r1 = false;

    synchronized void res1(int P1) {
        try {
            if (r1) wait();
            r1 = true;
            System.out.println(" Ресурс 1 используется процессом " + P1);
            r1 = false;
            notify();
        } catch (InterruptedException e) {
        }
    }
}
