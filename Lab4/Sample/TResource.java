package Lab4.Sample;

/*
 * СП та ОС. Лабораторна робота №4.
 * Доступний об'єм оперативної пам'ятi: 32 Мб.
 * Процесiв - 2 (ОП: 10 Мб, 25 Мб), Ресурсiв – 4.
 * № ресурсiв, що використовуються кожним процесом
 * {1, 3, 4}; {1, 2, 3};
 */
public class TResource {
    String resName;
    boolean inUse = false;

    TResource(String resName) {
        this.resName = resName;
    }

    synchronized void ResJob() {
        try {
            if (inUse) {
                System.out.println(Thread.currentThread().getName() + ". Статус: Заблококований.");
                wait();
            }
            inUse = true;

            System.out.println(Thread.currentThread().getName() + ". Статус: Запущений. Використовується: " + resName + "...");
            Thread.sleep(300);

            inUse = false;
            notify();
        } catch (InterruptedException e) {
            System.out.println("Помилка переривання в " + resName + "!");
        }
    }
}
