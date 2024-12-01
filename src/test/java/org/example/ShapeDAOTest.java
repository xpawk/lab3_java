package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ShapeDAOTest {

    private ShapeDAO shapeDAO;
    private SessionFactory sessionFactory;


    @BeforeEach
    void setup() {
        // Initialize Configuration and register entity classes
        Configuration configuration = new Configuration()
                .configure("hibernate-test.cfg.xml")
                .addAnnotatedClass(Rectangle.class)
                .addAnnotatedClass(Triangle.class);

        sessionFactory = configuration.buildSessionFactory();
        shapeDAO = new ShapeDAO(sessionFactory);
    }

    @AfterEach
    void teardown() {
        // Close the session factory
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Test
    void testTableCreation() {
        assertDoesNotThrow(() -> {
            try (Session session = sessionFactory.openSession()) {
                session.doWork(connection -> {
                    DatabaseMetaData metaData = connection.getMetaData();

                    // Adjust table names to uppercase for H2
                    String[] types = {"TABLE"};

                    // Check for 'RECTANGLES' table
                    try (ResultSet rs = metaData.getTables(null, null, "RECTANGLES", types)) {
                        assertTrue(rs.next(), "Table 'RECTANGLES' should exist");
                    }

                    // Check for 'TRIANGLES' table
                    try (ResultSet rs = metaData.getTables(null, null, "TRIANGLES", types)) {
                        assertTrue(rs.next(), "Table 'TRIANGLES' should exist");
                    }
                });
            }
        });
    }


    @Test
    void testSaveAndRetrieveRectangle() {
        // Save a new Rectangle
        Rectangle rectangle = new Rectangle(5, 10, new Color(255, 0, 0));
        shapeDAO.save(rectangle);

        // Retrieve the Rectangle by ID
        Rectangle retrievedRectangle = shapeDAO.findById(rectangle.getId(), Rectangle.class);

        // Assertions
        assertNotNull(retrievedRectangle);
        assertEquals(5, retrievedRectangle.getWidth());
        assertEquals(10, retrievedRectangle.getHeight());
        assertEquals(255, retrievedRectangle.getColor().getR());
    }

    @Test
    void testUpdateRectangle() {
        // Save a new Rectangle
        Rectangle rectangle = new Rectangle(3, 6, new Color(0, 255, 0));
        shapeDAO.save(rectangle);

        // Update the Rectangle
        rectangle.setWidth(7);
        rectangle.setHeight(14);
        shapeDAO.update(rectangle);

        // Retrieve the updated Rectangle
        Rectangle updatedRectangle = shapeDAO.findById(rectangle.getId(), Rectangle.class);

        // Assertions
        assertNotNull(updatedRectangle);
        assertEquals(7, updatedRectangle.getWidth());
        assertEquals(14, updatedRectangle.getHeight());
    }

    @Test
    void testDeleteRectangle() {
        // Save a new Rectangle
        Rectangle rectangle = new Rectangle(4, 8, new Color(0, 0, 255));
        shapeDAO.save(rectangle);

        // Delete the Rectangle
        shapeDAO.delete(rectangle);

        // Try to retrieve the deleted Rectangle
        Rectangle deletedRectangle = shapeDAO.findById(rectangle.getId(), Rectangle.class);

        // Assertions
        assertNull(deletedRectangle);
    }

    @Test
    void testFindAllRectangles() {
        // Save multiple Rectangles
        shapeDAO.save(new Rectangle(2, 4, new Color(255, 255, 0)));
        shapeDAO.save(new Rectangle(5, 5, new Color(128, 128, 128)));

        // Retrieve all Rectangles
        List<Rectangle> rectangles = shapeDAO.findAll(Rectangle.class);

        // Assertions
        assertNotNull(rectangles);
        assertEquals(2, rectangles.size());
    }
}
