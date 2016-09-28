package Lab2.ThreadSample;

public class comm1 {
    public static void main(String args[]) {
        Resource1 q1 = new Resource1();
        Resource2 q2 = new Resource2();
        new Proc1(q1, q2);
        new Proc2(q1, q2);
        new Proc1(q1, q2);
    }
}
