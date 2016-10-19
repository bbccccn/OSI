package Lab2.RealLabGoesHere;

import java.util.List;

import static java.util.Arrays.asList;

public class Lab2Osi {
    public static void main(String[] args) {
        //P: 4 | R: 4  | 1,3,4; 1,2,3; 2,4; 4,2
        List<Resource> resources = asList(new Resource("1"), new Resource("2"), new Resource("3"), new Resource("4"));

        new Process(asList(resources.get(0), resources.get(2), resources.get(3)), "First");
        new Process(asList(resources.get(0), resources.get(1), resources.get(2)), "Second");
        new Process(asList(resources.get(1), resources.get(3)), "Third");
        new Process(asList(resources.get(3), resources.get(1)), "Fours");
    }
}
