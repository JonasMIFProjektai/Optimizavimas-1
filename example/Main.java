package org.example;

public class Main {
    public static void main(String[] args) {
        int l = 0;
        int r = 10;
        int x0 = 5;
        System.out.println("\nIntervalo dalijimo pusiau metodas:");
        IntervaloDalijimasPusiau i = new IntervaloDalijimasPusiau(l, r);
        i.printStats();
        System.out.println("\nAuksinio pjūvio metodas:");
        AuksinioPjuvio a = new AuksinioPjuvio(l, r);
        a.printStats();
        System.out.println("\nNiutono metodas:");
        Niutono n = new Niutono(x0);
        n.printStats();
        i.drawFunction(0, 10, 500, 400, "Interavlo dalijimo pusiau metodas");
        a.drawFunction(0, 10, 500, 400, "Auksinio pjūvio metodas");
        n.drawFunction(0, 10, 500, 400, "Niutono metodas");
    }
}