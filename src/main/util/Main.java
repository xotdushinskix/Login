package util;

import dao.CustomerDao;
import factory.Factory;
import table.Customer;

import java.sql.SQLException;

/**
 * Created by FromxSoul on 08.06.2016.
 */
public class Main {

    public static void main (String[] args) throws SQLException {
        HibernateUtil.getSessionFactory();

        Factory factory = Factory.getInstance();
        CustomerDao customerDao = factory.getCustomerDao();

        Customer customer = customerDao.getCustomerByLoginEmail("john_smith@gmail.com");
        System.out.println(customer.getFirstName());

//        Customer customer = new Customer();
//        customer.setFirstName("John");
//        customer.setLastName("Smith");
//        customer.setLoginEmail("john_smith@gmail.com");
//        customer.setPassword("johnsmith");
//        customerDao.addCustomer(customer);
//
//
//        Customer customer1 = new Customer();
//        customer1.setFirstName("Kevin");
//        customer1.setLastName("Dorey");
//        customer1.setLoginEmail("kevin_dorey@gmail.com");
//        customer1.setPassword("kevindorey");
//        customerDao.addCustomer(customer1);
    }

}
