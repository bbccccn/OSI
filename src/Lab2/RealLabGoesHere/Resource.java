package Lab2.RealLabGoesHere;

public class Resource {
    private String name;
    private boolean inUse = false;

    Resource(String name) {
        this.name = name;
    }

    synchronized public void use(String threadName) throws InterruptedException {
        if (inUse) wait();
        inUse = true;
        System.out.println("Resource " + this.name + " in use by thread named " + threadName);
        inUse = false;
        notify();
    }
}
