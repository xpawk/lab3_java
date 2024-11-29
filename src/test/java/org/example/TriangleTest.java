package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    @Test
    void testTriangleArea() {
        Triangle triangle = new Triangle(6, 8, 5, 5, new Color(0, 255, 0));
        assertEquals(24, triangle.getArea(), "Area should be 24");
    }

    @Test
    void testTrianglePerimeter() {
        Triangle triangle = new Triangle(6, 8, 5, 5, new Color(0, 255, 0));
        assertEquals(16, triangle.getPerimeter(), "Perimeter should be 16");
    }
}
