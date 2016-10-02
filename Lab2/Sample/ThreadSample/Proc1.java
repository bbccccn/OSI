package Lab2.Sample.ThreadSample;

public class Proc1 implements Runnable {
    Resource1 q1;
    Resource2 q2;

    public Proc1(Resource1 q1, Resource2 q2) {
        this.q1 = q1;
        this.q2 = q2;
        new Thread(this).start();
    }

    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                q1.res1(1);
                q2.res2(1);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
        }
    }
}

