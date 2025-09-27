package org.example;

import java.util.ArrayList;
import java.util.List;

public class IntervaloDalijimasPusiau extends Function{
    private double l, r;

    public IntervaloDalijimasPusiau(double l, double r){
        super();

        this.r = r;
        this.l = l;
        points.add(l);
        points.add(r);
        min = findMin(l, r);
    }
    private double findMin(double l, double r){
        double xm, x1, x2;
        double L = r - l;
        if(L < 0.0001){
            return l;
        }
        ++i;
        xm = (l+r) / 2;
        x1 = l + L / 4;
        x2 = r - L / 4;
        double fxm = f(xm);

        if(f(x1) < fxm){
            points.add(xm);
            return findMin(l, xm);
        }
        if(f(x2) < fxm){
            points.add(xm);
            return findMin(xm, r);
        }
        points.add(x1);
        points.add(x2);
        return findMin(x1, x2);
    }



}
