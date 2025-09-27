package org.example;

public class Niutono extends Function{
    private double x0;

    public Niutono(double x0){
        super();
        this.x0 = x0;
        points.add(x0);
        min = findMin(x0);
    }
    private double df(double x){
        ++k;
        return (Math.pow(x, 3) - x * 5) / 2;
    }
    private double ddf(double x){
        ++k;
        return 3 * Math.pow(x, 2) / 2 - 2.5;
    }
    private double findMin(double x){
        double xn = x - df(x) / ddf(x);
        ++i;
        if(Math.abs(x - xn) < 0.0001){
            return x;
        }
        points.add(xn);
        return findMin(xn);
    }


}
