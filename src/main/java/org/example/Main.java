package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Color color1 = new Color(255.0, 0.0, 0.0);
        Color color2 = new Color(0.0, 255.0, 0.0, 0.5);

        Shape rectangle = new Rectangle(5, 10, color1);
        Shape triangle = new Triangle(6, 8, 5, 5, color2);

        ShapeDescriber describer = new ShapeDescriber();
        System.out.println("Rectangle:");
        describer.describe(rectangle);

        System.out.println("\nTriangle:");
        describer.describe(triangle);


    }
}