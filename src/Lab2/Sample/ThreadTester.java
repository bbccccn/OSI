package Lab2.Sample;
// Разные потоки выводят сообщения на экран в разные моменты времени.

public class ThreadTester {
    public static void main(String args[]) {
        // Создание потоков и присвоение им имен
        PrintThread thread1 = new PrintThread("thread1");
        PrintThread thread2 = new PrintThread("thread2");
        PrintThread thread3 = new PrintThread("thread3");

        System.out.println("Запуск потоков");

        thread1.start();  // запуск потока 1, переход в состояние готовности
        thread2.start();  // запуск потока 2, переход в состояние готовности
        thread3.start();  // запуск потока 3, переход в состояние готовности

        System.out.println("Потоки запущены, конец части main \n");
    }  // конец главной функции
}  //конец тела класса ThreadTester

        /*---------------------------------------------------------------------------------------*/

