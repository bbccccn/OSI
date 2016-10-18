package Lab5.Sample;

public class Lab5 {
    // Костанти прiоритетiв
    final static char l = 3;	// low
    final static char m = 5;	// medium
    final static char h = 7;	// high
    //private static int FreeRAM = 96; // Кiлькiсть доступної опер. пам'ятi
    static int[] processes = {0, 0, 0};
    static int[] procMem = {43, 24, 53};
    static int[] procPrior = {m, h, l};
    static String[] procNames = {"Процес №1", "Процес №2", "Процес №3"};
    static SynchronizedBuffer FreeRam;
    // Списки номерiв ресурсiв, що використовуються кожним процесом
    int Proc1ResNos[] = {1, 2};
    int Proc2ResNos[] = {1, 2};
    int Proc3ResNos[] = {2};

    public static void main(String[] args) {
        System.err.println("96 Мб. ОЗП для 3-х процесiв");
        System.err.println("1) 43Mb, ресурси (r1,r2), m");
        System.err.println("2) 24Mb, ресурси (r1,r2), h");
        System.err.println("3) 53Mb, ресурси (r2), l");
        Lab5.FreeRam = new SynchronizedBuffer(96);

        TResource R1 = new TResource("Ресурс №1");
        TResource R2 = new TResource("Ресурс №2");
        TResource ResList[] = {R1, R2};
        TResource ResList3[] = {R2};

        TProc proc1 = new TProc(0, Lab5.procNames[0], Lab5.procMem[0], ResList, m);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TProc proc3 = new TProc(2, Lab5.procNames[2], Lab5.procMem[2], ResList3, l);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TProc proc2 = new TProc(1, Lab5.procNames[1], Lab5.procMem[1], ResList, h);

        try {
            proc1.t.join();
            proc2.t.join();
            proc3.t.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread was interrupted!");
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("Робота програми завершена!");
    }
}

