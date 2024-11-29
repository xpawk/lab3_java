package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {
    @Test
    void testRectangleArea() {
        Rectangle rectangle = new Rectangle(5, 10, new Color(255, 0, 0));
        assertEquals(50, rectangle.getArea(), "Area should be 50");
    }

    @Test
    void testRectanglePerimeter() {
        Rectangle rectangle = new Rectangle(5, 10, new Color(255, 0, 0));
        assertEquals(30, rectangle.getPerimeter(), "Perimeter should be 30");
    }
}
