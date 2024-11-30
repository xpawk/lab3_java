package org.example;

import jakarta.persistence.Embeddable;

@Embeddable
public class Color {
    private double R;
    private double G;
    private double B;
    private double A;

    public Color() {
        // Default constructor for Hibernate
    }

    public Color(double R, double G, double B, double A) {
        this.R = R;
        this.G = G;
        this.B = B;
        this.A = A;
    }

    public Color(double R, double G, double B) {
        this(R, G, B, 1.0); // Default alpha to 1.0
    }

    // Getters and setters
    public double getR() {
        return R;
    }

    public void setR(double r) {
        R = r;
    }

    public double getG() {
        return G;
    }

    public void setG(double g) {
        G = g;
    }

    public double getB() {
        return B;
    }

    public void setB(double b) {
        B = b;
    }

    public double getA() {
        return A;
    }

    public void setA(double a) {
        A = a;
    }
}
