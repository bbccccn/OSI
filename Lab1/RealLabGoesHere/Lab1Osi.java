package Lab1.RealLabGoesHere;

import java.util.Scanner;

public class Lab1Osi {
    public static void main(String[] args) {
        MemoryAccessTester MAT = new MemoryAccessTester();
        scanVars(MAT);
        System.out.println(MAT.getAccessTimeMessage());
    }

    private static void scanVars(MemoryAccessTester MAT){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your memory parameters : ");
        System.out.println("t1 = ");
        MAT.setT1(getNextDoubleValue(sc));
        System.out.println("t2 = ");
        MAT.setT2(getNextDoubleValue(sc));
        System.out.println("t3 = ");
        MAT.setT3(getNextDoubleValue(sc));
        System.out.println("P1 = ");
        MAT.setP1(getNextDoubleValue(sc));
        System.out.println("P2 = ");
        MAT.setP2(getNextDoubleValue(sc));
    }

    private static double getNextDoubleValue(Scanner sc) {
        return Double.parseDouble(sc.nextLine());
    }
}
