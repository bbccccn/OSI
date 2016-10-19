package Sample;

public class SynchronizedBuffer1 {
    private int buffer;
    private boolean bufferInUseCount = false;

    SynchronizedBuffer1(int buffer) {
        this.buffer = buffer;
    }

    public synchronized void set(int value) {
        while (bufferInUseCount) {
            waiting();
        }
        bufferInUseCount = true;
        buffer = value;
        bufferInUseCount = false;
        notifyAll();
    }

    public synchronized int get() {
        while (bufferInUseCount) {
            waiting();
        }
        return buffer;
    }

    private void waiting() {
        try {
            wait();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
