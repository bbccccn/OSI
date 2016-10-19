package Sample;

public class Lab5 {
    final static char low = 3;    // low
    final static char medium = 5;    // medium
    final static char high = 7;    // high
    static int[] processes = {0, 0, 0};
    static int[] procMem = {43, 24, 53};
    static int[] procPrior = {medium, high, low};
    static String[] procNames = {"Процес №1", "Процес №2", "Процес №3"};
    static SynchronizedBuffer1 FreeRam;

    public static void main(String[] args) {
        System.err.println("96 Мб. ОЗП для 3-х процесiв");
        System.err.println("1) 43Mb, ресурси (r1,r2), medium");
        System.err.println("2) 24Mb, ресурси (r1,r2), high");
        System.err.println("3) 53Mb, ресурси (r2), low");
        FreeRam = new SynchronizedBuffer1(96);

        TResource1 R1 = new TResource1("Ресурс №1");
        TResource1 R2 = new TResource1("Ресурс №2");
        TResource1 ResList[] = {R1, R2};
        TResource1 ResList2[] = {R2};

        TProc1 proc1 = new TProc1(0, Lab5.procNames[0], Lab5.procMem[0], ResList, medium);
        sleep(1000);
        TProc1 proc3 = new TProc1(2, Lab5.procNames[2], Lab5.procMem[2], ResList2, low);
        sleep(1000);
        TProc1 proc2 = new TProc1(1, Lab5.procNames[1], Lab5.procMem[1], ResList, high);

        try {
            proc1.t.join();
            proc2.t.join();
            proc3.t.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread was interrupted!");
        }
        sleep(500);
        System.err.println("Робота програми завершена!");
    }

    private static void sleep(int timeToSleep) {
        try {
            Thread.sleep(timeToSleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
