package Lab3.Sample;

public class Lab3 {
    public static void main(String [] args) {
        // Показати заголовки стовпцiв виведення
        StringBuffer columnHeads = new StringBuffer("Дiя\t\t\t\t");
        columnHeads.append("Буфер\tKiлькiсть зайнятих елементiв\n");
        System.err.println(columnHeads);
        // Створити кiльцевий буфер, що використовується потоками
        SynchronizedBuffer sharedLocation = new SynchronizedBuffer(3); //розмiр 3 елементи
        sharedLocation.displayState("Початковий стан\t\t\t"); // усi елементи порожнi
        // створити об'єкти-Bи      робники i об'єкти-Cпоживачi
        Producer Producer_1 = new Producer(sharedLocation, 4); // записує 4 елементи
        Producer_1.setName("Виробник_1");
        Producer Producer_2 = new Producer(sharedLocation, 4);// записує 4 елементи
        Producer_2.setName("Виробник_2");
        Producer Producer_3 = new Producer(sharedLocation, 4);// записує 4 елементи
        Producer_3.setName("Виробник_3");

        Consumer Consumer_1 = new Consumer(sharedLocation, 6);// читає 6 елементiв
        Consumer_1.setName("Споживач_1");
        Consumer Consumer_2 = new Consumer(sharedLocation, 6);// читає 6 елементiв
        Consumer_2.setName("Споживач_2");
        // запуск потокiв
        Producer_1.start();
        Producer_2.start();
        Producer_3.start();
        Consumer_1.start();
        Consumer_2.start();
    }
}

