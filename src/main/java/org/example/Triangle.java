package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "triangles")
public class Triangle extends Shape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "base", nullable = false)
    private double base;

    @Column(name = "height", nullable = false)
    private double height;

    @Column(name = "side_a", nullable = false)
    private double sideA;

    @Column(name = "side_b", nullable = false)
    private double sideB;

    public Triangle() {
        // Default constructor for Hibernate
    }

    public Triangle(double base, double height, double sideA, double sideB, Color color) {
        super(color);
        this.base = base;
        this.height = height;
        this.sideA = sideA;
        this.sideB = sideB;
    }

    @Override
    public double getArea() {
        return (base * height) / 2;
    }

    @Override
    public double getPerimeter() {
        return base + sideA + sideB;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getSideA() {
        return sideA;
    }

    public void setSideA(double sideA) {
        this.sideA = sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public void setSideB(double sideB) {
        this.sideB = sideB;
    }
}
