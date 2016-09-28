package Lab3.Sample;

// Kiльцевий буфер
public class SynchronizedBuffer {
    private int maxSize; //максимальний розмiр
    private int[] buffer; // масив елементiв
    private int bufferCurrentCount; //поточний розмiр
    private int readPosition; //позицiя для читання
    private int writePosition; //позицiя для запису
    // конструктор
    public SynchronizedBuffer(int maxSize) {
        this.maxSize = maxSize;
        buffer = new int[this.maxSize];
        bufferCurrentCount = 0;
        readPosition = 0;
        writePosition = 0;
    }
    // записати елемент
    public synchronized void set(int value) {
        // Iм'я поточного потоку
        String name = Thread.currentThread().getName();
        // якщо буфер повнiстю заповнений - чекати i вивести повiдомлення
        while (bufferCurrentCount == maxSize) {
            try {
                System.out.println(name+" робить спробу писати.");
                displayState("Буфер повний. "+name+" чекає.\t");
                wait();
            }
            // якщо чекаючий поток перервано, вивести дерево викликiв
            catch(InterruptedException exception) {
                exception.printStackTrace();
            }
        }
        // якщо є вiльнi мiсця - записати елемент
        buffer[writePosition] = value;
        bufferCurrentCount++;
        displayState(name+" writes "+Integer.toString(buffer[writePosition])+"\t\t");
        if (writePosition == maxSize -1)
            writePosition = 0;
        else
            writePosition++;
        // повiдомити усi чекаючi потоки про можливiсть продовжити роботу
        if (bufferCurrentCount == 1)
            notifyAll();
    }
    // зчитати елемент
    public synchronized int get() {
        // iм'я поточного потоку
        String name = Thread.currentThread().getName();
        // якщо буфер порожнiй - чекати i вивести повiдомлення
        while(bufferCurrentCount == 0) {
            try {
                System.out.println(name+" робить спробу читати." );
                displayState("Буфер порожнiй. "+name+" чекає.\t");
                wait();
            }
            // якщо чекаючий поток перервано, вивести дерево викликiв
            catch(InterruptedException exception) {
                exception.printStackTrace();
            }
        }
        // якщо буфер не порожнiй - зчитати елемент
        int temp = buffer[readPosition];
        buffer[readPosition] = 0; //тепер елемент порожнiй
        bufferCurrentCount--;
        displayState(name+" зчитує "+Integer.toString(temp)+"\t\t");
        if (readPosition == maxSize -1)
            readPosition = 0;
        else
            readPosition++;
        // повiдомити усi чекаючi потоки про можливiсть продовжити роботу
        if (bufferCurrentCount == maxSize -1)
            notifyAll();
        return temp;
    }
    // вивести поточнi елементи i їх кiлькiсть
    public void displayState(String operation) {
        StringBuffer outputLine = new StringBuffer(operation);
        for (int j = 0; j < maxSize; j++)
            outputLine.append(Integer.toString(this.buffer[j]) + " ");
        outputLine.append("\t"+ bufferCurrentCount +"\n");
        System.out.println(outputLine);
    }
}
