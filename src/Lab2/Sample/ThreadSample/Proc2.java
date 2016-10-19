package Lab2.Sample.ThreadSample;

public class Proc2 implements Runnable {
    Resource1 q1;
    Resource2 q2;

    public Proc2(Resource1 q1, Resource2 q2) {
        this.q1 = q1;
        this.q2 = q2;
        new Thread(this).start();
    }

    public void run() {
        try {
            for (int j = 1; j <= 5; j++) {
                q1.res1(2);
                q2.res2(2);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
        }
    }
}
