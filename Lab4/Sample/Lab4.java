package Lab4.Sample;

public class Lab4 {
    static int FreeRAM = 32;
    // Списки номерiв ресурсiв, що використовуються кожним процесом
    static int Proc1ResNos[] = {1, 3, 4};
    static int Proc2ResNos[] = {1, 2, 3};

    public static void main(String[] args) {
        TResource R1 = new TResource("Ресурс №1");
        TResource R2 = new TResource("Ресурс №2");
        TResource R3 = new TResource("Ресурс №3");
        TResource R4 = new TResource("Ресурс №4");
        TResource ResList1[] = {R1, R3, R4};
        TResource ResList2[] = {R1, R2, R3};
        System.out.println("Загальна кiлькiсть памятi рiвна " + FreeRAM + " mb.");
        TProc proc1 = new TProc("Процес #1", 10, ResList1);
        System.out.println("Для запуску процесу #1 необхiдно 10  mb iз " + (FreeRAM));
        TProc proc2 = new TProc("Процес #2", 25, ResList2);
        System.out.println("Для запуску процесу #2 необхiдно 25  mb iз " + (FreeRAM - 10));
        // очiкування завершення роботи усiх потокiв
        try {
            proc1.t.join();
            proc2.t.join();
        } catch (InterruptedException e) {
            System.out.println("Виконання основного потоку було перервано!");
        }
    }
}