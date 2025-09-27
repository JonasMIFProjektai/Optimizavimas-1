package org.example;

public class AuksinioPjuvio extends Function{
    private double l, r;

    public AuksinioPjuvio(double l, double r){
        super();

        this.r = r;
        this.l = l;
        points.add(l);
        points.add(r);
        min = findMin(l, r);
    }
    private double t = (Math.sqrt(5) - 1) / 2;
    private double findMin(double l, double r){
        double L = r - l;
        if(L < 0.0001){
            return l;
        }
        ++i;
        double x1, x2;
        x1 = r - t * L;
        x2 = l + t * L;
        if(f(x1) > f(x2)){
            points.add(x1);
            return findMin(x1, r);
        }else{
            points.add(x2);
            return findMin(l, x2);
        }
    }
}
