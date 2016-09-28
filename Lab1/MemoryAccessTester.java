package Lab1;

public class MemoryAccessTester {
    private double t1;
    private double t2;
    private double t3;
    private double P1;
    private double P2;

    public MemoryAccessTester() {}

    public MemoryAccessTester(double t1, double t2, double t3, double p1, double p2) {
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = msToNs(t3);
        P1 = p1;
        P2 = p2;
    }

    private double msToNs(double value) {
        return value / 1000000;
    }

    public double getAccessTime() {
        return P1 * t1 + (1 - P1) * P2 * t2 + (1 - P1 - P2 + P1 * P2) * t3;
    }

    public String getAccessTimeMessage() {
        return "Asserted access time is " + getAccessTime() + " ns";
    }

    public void setT1(double t1) {
        this.t1 = t1;
    }

    public void setT2(double t2) {
        this.t2 = t2;
    }

    public void setT3(double t3) {
        this.t3 = t3;
    }

    public void setP1(double p1) {
        P1 = p1;
    }

    public void setP2(double p2) {
        P2 = p2;
    }
}
