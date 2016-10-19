package Lab4.RealLabGoesHere;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

public class Lab4OSI {
    //18.#ofProcesses: 4 |memoryPerProcess: 11, 24, 25, 44
    // |summaryRAM: 64 |#ofProcesses: 4 |#ofResources: 1,3,4; 1,2,3; 2,4; 4,2
    public static void main(String[] args) {
        RAMWrapper RAM = new RAMWrapper(64);
        List<Resource> resources = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            resources.add(new Resource("Resournce #" + i));
        }

        List<Consumer> consumers = asList(
                new Consumer("Consumer #1", asList(resources.get(0), resources.get(2), resources.get(3)), RAM, 11),
                new Consumer("Consumer #2", asList(resources.get(0), resources.get(1), resources.get(2)), RAM, 24),
                new Consumer("Consumer #3", asList(resources.get(1), resources.get(3)), RAM, 25),
                new Consumer("Consumer #4", asList(resources.get(3), resources.get(1)), RAM, 44)
        );

        Collections.shuffle(consumers);

        for (Consumer consumer : consumers) {
            consumer.t.start();
        }

    }


}
