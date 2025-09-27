package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Function {
    protected int i, k, a, b;
    protected double min;
    protected List<Double> points;

    protected Function(){
        i = 0;
        k = 0;
        a = 5;
        b = 8;
        points = new ArrayList<>();
    }

    public double f(double x){
        ++k;
        return Math.pow(Math.pow(x, 2) - a, 2) / b - 1;
    }
    public int getI(){
        return i;
    }
    public int getK(){
        return k;
    }
    public double getMin(){
        return min;
    }
    public void printStats(){
        System.out.println("iteracijų kiekis: " + i + '\n'
            + "funkcijų kvietimai: " + k + '\n'
            + "minimumo taškas: [" + min + ';' + f(min) + ']'
        );
    }


    public void addPoint(double x) {
        points.add(x);
    }

    public void drawFunction(double xMin, double xMax, int width, int height, String name) {
        JFrame frame = new JFrame(name);
        frame.setSize(width, height);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Background
                g2.setColor(Color.WHITE);
                g2.fillRect(0, 0, width, height);

                // --- coordinate mapping ---
                double yMin = -10, yMax = 10; // vertical range (can auto-scale later)
                double xScale = width / (xMax - xMin) ;
                double yScale = height / (yMax - yMin);

                // Map function (x,y) -> (px,py)
                java.util.function.Function<Double, Integer> mapX = x -> (int) ((x - xMin) * xScale);
                java.util.function.Function<Double, Integer> mapY = y -> (int) (height - (y - yMin) * yScale) ;

                // Axes
                g2.setColor(Color.BLACK);
                int xAxis = mapY.apply(0.0);
                int yAxis = mapX.apply(0.0);

                if (yMin <= 0 && yMax >= 0) {
                    g2.drawLine(0, xAxis, width, xAxis);  // X axis
                }
                if (xMin <= 0 && xMax >= 0) {
                    g2.drawLine(yAxis, 0, yAxis, height); // Y axis
                }

                // Ticks and numbers
                g2.setFont(new Font("Arial", Font.PLAIN, 12));

                // X ticks
                for (int x = (int) Math.ceil(xMin); x <= (int) xMax; x++) {
                    int px = mapX.apply((double) x);
                    g2.drawLine(px, xAxis - 4, px, xAxis + 4);
                    g2.drawString(Integer.toString(x), px - 6, xAxis + 18);
                }

                // Y ticks
                for (int y = (int) Math.ceil(yMin); y <= (int) yMax; y++) {
                    int py = mapY.apply((double) y);
                    g2.drawLine(yAxis - 4, py, yAxis + 4, py);
                    if (y != 0) {
                        g2.drawString(Integer.toString(y), yAxis + 6, py + 4);
                    }
                }

                // Function curve
                g2.setColor(Color.RED);
                int prevX = 0, prevY = 0;
                boolean first = true;

                for (int px = 0; px < width; px++) {
                    double x = xMin + (px / xScale);
                    double y = f(x);

                    int screenX = mapX.apply(x);
                    int screenY = mapY.apply(y);

                    if (!first) {
                        g2.drawLine(prevX, prevY, screenX, screenY);
                    }
                    prevX = screenX;
                    prevY = screenY;
                    first = false;
                }

                // Mark minimum if set


                // Mark all extra points stored in "points"
                int idx = 1;
                for (double x : points) {
                    drawPoint(g2, mapX, mapY, x, f(x), "", Color.BLUE);
                    idx++;
                }
                drawPoint(g2, mapX, mapY, min, f(min), "min", Color.BLACK);
            }
        };

        frame.add(panel);
        frame.setVisible(true);
    }

    private void drawPoint(Graphics2D g2,
                           java.util.function.Function<Double, Integer> mapX,
                           java.util.function.Function<Double, Integer> mapY,
                           double x, double y, String text, Color color) {
        g2.setColor(color);
        int px = mapX.apply(x);
        int py = mapY.apply(y);
        g2.fillOval(px - 4, py - 4, 8, 8);
        g2.drawString(text, px + 6, py - 6);
    }

}
