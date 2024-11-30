package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Initialize ShapeDAO
        ShapeDAO shapeDAO = new ShapeDAO();

        // Create sample shapes
        Color color1 = new Color(255.0, 0.0, 0.0, 1.0);
        Color color2 = new Color(0.0, 255.0, 0.0, 0.5);

        Shape rectangle = new Rectangle(5, 10, color1);
        Shape triangle = new Triangle(6, 8, 5, 5, color2);

        // Save shapes to the database
        shapeDAO.save(rectangle);
        shapeDAO.save(triangle);

        // Retrieve and describe all rectangles
        System.out.println("All Rectangles:");
        List<Rectangle> rectangles = shapeDAO.findAll(Rectangle.class);
        for (Rectangle rect : rectangles) {
            ShapeDescriber describer = new ShapeDescriber();
            describer.describe(rect);
        }

        // Retrieve and describe all triangles
        System.out.println("\nAll Triangles:");
        List<Triangle> triangles = shapeDAO.findAll(Triangle.class);
        for (Triangle tri : triangles) {
            ShapeDescriber describer = new ShapeDescriber();
            describer.describe(tri);
        }

        // Close the DAO
        shapeDAO.close();
    }
}
