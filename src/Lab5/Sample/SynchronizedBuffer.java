package Lab5.Sample;

/*
 * СП та ОС. Лабораторна робота №5
 *   Кiлькiсть процесiв: 3.
 *   Пам'ять процесiв: 43 Мб, 24 Мб, 53 Мб.
 *   Доступний об'єм оперативної пам'ятi: 96 Мб.
 *   Кiлькiсть ресурсiв: 2.
 *   Порядок використання ресурсiв: 1,2; 1,2;  2.
 *   Прiоритети процесiв: P1 - m, P2 - h, P3 - l.
 */
public class SynchronizedBuffer {
    private int buffer;
    private boolean bufferInUseCount = false;
    SynchronizedBuffer(int abuf) {
        this.buffer = abuf;
    }
    public synchronized void set(int value) {
        while (bufferInUseCount) {
            try {
                wait();
            }
            catch(InterruptedException exception) {
                exception.printStackTrace();
            }
        }
        bufferInUseCount = true;
        buffer = value;
        bufferInUseCount = false;
        notifyAll();
    }
    public synchronized int get() {
        while (bufferInUseCount) {
            try {
                wait();
            }
            catch(InterruptedException exception) {
                exception.printStackTrace();
            }
        }
        return buffer;
    }
}
