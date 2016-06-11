package daoImpl;

import dao.CustomerDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import table.Customer;
import util.HibernateUtil;

import java.sql.SQLException;

/**
 * Created by FromxSoul on 08.06.2016.
 */
public class CustomerDaoImpl implements CustomerDao {
    public void addCustomer(Customer customer) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(customer);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
    }



    public Customer getCustomerById(int customerId) throws SQLException {
        Session session = null;
        Customer customer = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            customer = session.get(Customer.class, customerId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return customer;
    }



    public Customer getCustomerByLoginEmail(String loginEmail) throws SQLException {
        Session session = null;
        Customer customer = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Customer.class)
                    .add(Restrictions.like("loginEmail", loginEmail));
            customer = (Customer) criteria.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return customer;
    }
}
