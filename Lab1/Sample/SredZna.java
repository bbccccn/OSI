package Lab1.Sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SredZna {
    public static void main(String args[]) {  // опис методу
        double a = 5, b = 20, SredZ;            // опис змiнних
        String line = null;        //обьявляем переменные
        try {
            BufferedReader is = new BufferedReader(    //обьявляем
                    new InputStreamReader(System.in));    //буффер ввода
            System.out.println("Введiть значення для а = ");
            line = is.readLine();        //читаем строку
            //a = Integer.parseInt(line);	//берём с нее переменную(указываем тип Integer)
            a = Double.parseDouble(line);//берём с нее переменную(указываем тип Double)
            System.out.println("Введiть значення для b = ");
            line = is.readLine();        //читаем строку
            b = Double.parseDouble(line);//берём с нее переменную(указываем тип Double)

        } catch (NumberFormatException ex) {        //"ловим" ошибки ввода
            System.err.println("Not a valid number: " + line);    //"ловим" ошибки ввода
        } catch (IOException e) {                //"ловим" ошибки ввода
            System.err.println("Unexpected IO ERROR: " + e);//"ловим" ошибки ввода
        }
        SredZ = (a + b) / 2;
        System.out.println("(a+b)/2 = " + SredZ);
    }
}
