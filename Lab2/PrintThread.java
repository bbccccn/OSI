package Lab2;

// Класс  PrintThread  контролирует работу потоков
public class PrintThread extends Thread {
    private int sleepTime;

    // Присвоение имени потоку путем вызова конструктора суперкласса
    public PrintThread(String name) {
        super(name);

        // Выбор произвольного (случайного) времени сна процесса (от 0 до 5 сек)
        sleepTime = (int) (Math.random() * 5001);
    }  // завершение конструктора  PrintThread

    // Следующий поток запускает метода  run
    public void run() {
        // Перевод потока в спящий режим на время sleepTime
        try {
            System.out.println(getName() + " уснет на " +
                    sleepTime + " миллисекунд");
            Thread.sleep(sleepTime);
        }  // завершение функции  try
        // Если сон потока прерван, распечатать стек
        catch (InterruptedException exception) {
            exception.printStackTrace();
        }  // завершение функции  catch
        // Вывод имени потока
        System.out.println(getName() + " вышел из сна");
    }  // Завершение метода  run

}  // Завершение класса  PrintThread
