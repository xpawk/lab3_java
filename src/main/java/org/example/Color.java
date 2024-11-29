package org.example;

public record Color(double R, double G, double B, double A) {
    public Color(double R, double G, double B) {
        this(R, G, B, 0.0);
    }
}
