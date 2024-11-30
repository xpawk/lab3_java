package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ShapeDAO {
    private final SessionFactory sessionFactory;

    public ShapeDAO() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public void save(Shape shape) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(shape);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void update(Shape shape) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(shape);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Delete a Shape entity from the database.
     *
     * @param shape the Shape entity to delete
     */
    public void delete(Shape shape) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(shape);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Retrieve a Shape entity by its ID.
     *
     * @param id    the ID of the Shape entity
     * @param clazz the class of the Shape (Rectangle.class, Triangle.class, etc.)
     * @return the Shape entity, or null if not found
     */
    public <T extends Shape> T findById(Long id, Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(clazz, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieve all Shape entities of a specific type from the database.
     *
     * @param clazz the class of the Shape (Rectangle.class, Triangle.class, etc.)
     * @return a list of Shape entities
     */
    public <T extends Shape> List<T> findAll(Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM " + clazz.getSimpleName();
            return session.createQuery(hql, clazz).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Close the session factory when done.
     */
    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
