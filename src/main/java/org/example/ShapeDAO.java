package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.Metadata;

import java.util.List;

public class ShapeDAO {
    private final SessionFactory sessionFactory;

    // Default constructor for production usage
    public ShapeDAO() {
        this("hibernate.cfg.xml");
    }

    // Constructor for custom configuration (e.g., in tests)
    public ShapeDAO(String configFileName) {
        this.sessionFactory = buildSessionFactory(configFileName);
    }

    // Constructor for dependency injection (e.g., in tests)
    public ShapeDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Method to build SessionFactory
    private SessionFactory buildSessionFactory(String configFileName) {
        try {
            // Create the ServiceRegistry from hibernate.cfg.xml
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                    .configure(configFileName)
                    .build();

            // Create MetadataSources and register entity classes
            MetadataSources metadataSources = new MetadataSources(standardRegistry);
            metadataSources.addAnnotatedClass(Rectangle.class);
            metadataSources.addAnnotatedClass(Triangle.class);
            metadataSources.addAnnotatedClass(Color.class);
            metadataSources.addAnnotatedClass(Shape.class);

            // Build the Metadata
            Metadata metadata = metadataSources.getMetadataBuilder().build();

            // Build the SessionFactory
            return metadata.getSessionFactoryBuilder().build();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to build SessionFactory", e);
        }
    }

    // CRUD methods (ensure proper session and transaction management)

    public void save(Shape shape) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(shape);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void update(Shape shape) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(shape);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void delete(Shape shape) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(shape);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public <T extends Shape> T findById(Long id, Class<T> clazz) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.get(clazz, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public <T extends Shape> List<T> findAll(Class<T> clazz) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String hql = "FROM " + clazz.getSimpleName();
            return session.createQuery(hql, clazz).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
