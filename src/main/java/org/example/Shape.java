package org.example;

public abstract class Shape {
    private Color color;

    public Shape(Color color) {
        this.color = color;
    }

    public String getColorDescription() {
        return String.format("Red: %.0f, Green: %.0f, Blue: %.0f, Alpha: %.0f", color.R(), color.G(), color.B(), color.A());
    }
    public void print() {
        System.out.println("Shape");
    }

    public abstract double getArea();
    public abstract double getPerimeter();
}