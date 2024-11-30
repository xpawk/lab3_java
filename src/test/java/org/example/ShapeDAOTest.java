package org.example;

import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ShapeDAOTest {

    private ShapeDAO shapeDAO;

    @BeforeAll
    void setup() {
        // Initialize ShapeDAO with the test configuration
        shapeDAO = new ShapeDAO(new Configuration().configure("hibernate-test.cfg.xml"));
    }

    @AfterAll
    void teardown() {
        shapeDAO.close();
    }

    @Test
    void testSaveAndFindById() {
        // Create a new Rectangle and save it
        Rectangle rectangle = new Rectangle(5, 10, new Color(255, 0, 0));
        shapeDAO.save(rectangle);

        // Retrieve the rectangle by ID
        Rectangle retrievedRectangle = shapeDAO.findById(rectangle.getId(), Rectangle.class);

        // Verify the saved entity
        assertNotNull(retrievedRectangle);
        assertEquals(rectangle.getWidth(), retrievedRectangle.getWidth());
        assertEquals(rectangle.getHeight(), retrievedRectangle.getHeight());
    }

    @Test
    void testFindAll() {
        // Create and save multiple rectangles
        shapeDAO.save(new Rectangle(3, 6, new Color(0, 255, 0)));
        shapeDAO.save(new Rectangle(7, 4, new Color(0, 0, 255)));

        // Retrieve all rectangles
        List<Rectangle> rectangles = shapeDAO.findAll(Rectangle.class);

        // Verify the retrieved list
        assertEquals(2, rectangles.size());
    }

    @Test
    void testUpdate() {
        // Create and save a rectangle
        Rectangle rectangle = new Rectangle(2, 4, new Color(255, 255, 0));
        shapeDAO.save(rectangle);

        // Update the rectangle
        rectangle.setWidth(10);
        shapeDAO.update(rectangle);

        // Retrieve the updated rectangle
        Rectangle updatedRectangle = shapeDAO.findById(rectangle.getId(), Rectangle.class);

        // Verify the updated fields
        assertNotNull(updatedRectangle);
        assertEquals(10, updatedRectangle.getWidth());
    }

    @Test
    void testDelete() {
        // Create and save a rectangle
        Rectangle rectangle = new Rectangle(6, 8, new Color(128, 128, 128));
        shapeDAO.save(rectangle);

        // Delete the rectangle
        shapeDAO.delete(rectangle);

        // Attempt to retrieve the deleted rectangle
        Rectangle deletedRectangle = shapeDAO.findById(rectangle.getId(), Rectangle.class);

        // Verify the rectangle is no longer in the database
        assertNull(deletedRectangle);
    }
}
