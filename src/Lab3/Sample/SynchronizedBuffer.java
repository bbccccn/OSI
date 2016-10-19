package Lab3.Sample;

// Kiльцевий буфер
public class SynchronizedBuffer {
    private int maxSize;
    private int[] buffer;
    private int bufferCurrentCount;
    private int readPosition;
    private int writePosition;

    public SynchronizedBuffer(int maxSize) {
        this.maxSize = maxSize;
        buffer = new int[this.maxSize];
        bufferCurrentCount = 0;
        readPosition = 0;
        writePosition = 0;
    }

    public synchronized void set(int value) {
        String name = Thread.currentThread().getName();

        while (bufferCurrentCount == maxSize) {
            try {
                System.out.println(name + " робить спробу писати.");
                displayState("Буфер повний. " + name + " чекає.\t");
                wait();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
        // якщо є вiльнi мiсця - записати елемент
        buffer[writePosition] = value;
        bufferCurrentCount++;
        displayState(name + " writes " + Integer.toString(buffer[writePosition]) + "\t\t");
        if (writePosition == maxSize - 1)
            writePosition = 0;
        else
            writePosition++;
        // повiдомити усi чекаючi потоки про можливiсть продовжити роботу
        if (bufferCurrentCount == 1)
            notifyAll();
    }

    // зчитати елемент
    public synchronized int get() {
        String name = Thread.currentThread().getName();

        while (bufferCurrentCount == 0) {
            try {
                System.out.println(name + " робить спробу читати.");
                displayState("Буфер порожнiй. " + name + " чекає.\t");
                wait();
            }
            catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

        int temp = buffer[readPosition];
        buffer[readPosition] = 0; //тепер елемент порожнiй
        bufferCurrentCount--;
        displayState(name + " зчитує " + Integer.toString(temp) + "\t\t");
        if (readPosition == maxSize - 1)
            readPosition = 0;
        else
            readPosition++;
        if (bufferCurrentCount == maxSize - 1)
            notifyAll();
        return temp;
    }

    public void displayState(String operation) {
        StringBuffer outputLine = new StringBuffer(operation);
        for (int j = 0; j < maxSize; j++)
            outputLine.append(Integer.toString(this.buffer[j]) + " ");
        outputLine.append("\t" + bufferCurrentCount + "\n");
        System.out.println(outputLine);
    }
}
