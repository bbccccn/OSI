package Lab2.RealLabGoesHere;

import java.util.List;

public class Process implements Runnable {
    private List<Resource> resources;
    private String name;

    public Process(List<Resource> resources, String name) {
        this.resources = resources;
        this.name = name;
        new Thread(this).start();
    }

    public void run() {
        for (Resource resource: resources) {
            try {
                resource.use(this.name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
