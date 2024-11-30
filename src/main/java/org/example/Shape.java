package org.example;

import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Shape {
    @Embedded
    private Color color;

    public Shape() {
        // Default constructor for Hibernate
    }

    public Shape(Color color) {
        this.color = color;
    }

    public String getColorDescription() {
        return String.format("Red: %.0f, Green: %.0f, Blue: %.0f, Alpha: %.0f",
                color.getR(), color.getG(), color.getB(), color.getA());
    }

    public abstract double getArea();

    public abstract double getPerimeter();

    // Getters and setters
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
