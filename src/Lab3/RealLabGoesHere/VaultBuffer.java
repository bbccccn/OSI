package Lab3.RealLabGoesHere;

import java.util.ArrayList;
import java.util.List;

public class VaultBuffer {
    private int size;
    private List<Integer> buffer;

    public VaultBuffer(int size) {
        this.size = size;
        buffer = new ArrayList<>(size);
    }

    public synchronized void set(int value) {
        String name = Thread.currentThread().getName();

        while (buffer.size() == size) {
            try {
                System.out.println(name + " робить спробу писати.");
                displayState("Буфер повний. " + name + " чекає.\t");
                wait();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

        buffer.add(value);
        displayState(name + " writes " + Integer.toString(buffer.size() - 1) + "\t\t");

        if (buffer.size() == 1)
            notifyAll();
    }

    // зчитати елемент
    public synchronized int get() {
        String name = Thread.currentThread().getName();

        while (buffer.size() == 0) {
            try {
                System.out.println(name + " робить спробу читати.");
                displayState("Буфер порожнiй. " + name + " чекає.\t");
                wait();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

        int readPosition = buffer.size() - 1;
        int temp = buffer.get(readPosition);
        buffer.remove(readPosition); //тепер елемент порожнiй
        displayState(name + " зчитує " + Integer.toString(temp) + "\t\t");
        if (buffer.size() == size - 1)
            notifyAll();
        return temp;
    }

    public void displayState(String operation) {
        StringBuffer outputLine = new StringBuffer(operation);
        for (Integer value :
                buffer) {
            outputLine.append(Integer.toString(value) + " ");
        }
        outputLine.append("\t" + buffer.size() + "\n");
        System.out.println(outputLine);
    }

}
