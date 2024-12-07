package ExamQuery;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.Query;

public class ClientDemo1 {

    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Update Department using HQL with positional parameters
            String hql = "UPDATE Department SET name = ?1, location = ?2 WHERE departmentId = ?3";
            Query query = session.createQuery(hql);

            // Set positional parameters
            query.setParameter(1, "Updated Name");
            query.setParameter(2, "Updated Location");
            query.setParameter(3, 1); // Update department with ID = 1

            // Execute Update
            int rowsAffected = query.executeUpdate();
            System.out.println("Rows Affected: " + rowsAffected);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
