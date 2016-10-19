package Lab3.RealLabGoesHere;

import java.util.List;

import static java.util.Arrays.asList;

public class Lab3OSI {
    //18. C:4 | P:4 | 1,3,4; 1,2,3; 2,4; 4,2
    public static void main(String[] args) {
        VaultBuffer vaultBuffer = new VaultBuffer(4);
        List<Supplier> suppliers = asList(new Supplier(vaultBuffer, 3), new Supplier(vaultBuffer, 2), new Supplier(vaultBuffer, 2), new Supplier(vaultBuffer, 3));
        for (int i = 0; i < suppliers.size(); i++) {
            suppliers.get(i).setName("Supplier " + (i + 1));
        }
        List<Consumer> consumers = asList(new Consumer(vaultBuffer, 3), new Consumer(vaultBuffer, 3), new Consumer(vaultBuffer, 2), new Consumer(vaultBuffer, 2));
        for (int i = 0; i < consumers.size(); i++) {
            consumers.get(i).setName("Consumer " + (i + 1));
        }

        for (Supplier supplier :
                suppliers) {
            supplier.start();
        }
        for (Consumer consumer :
                consumers) {
            consumer.start();
        }
    }
}
