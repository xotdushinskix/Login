package dao;

import table.Customer;

import java.sql.SQLException;

/**
 * Created by FromxSoul on 08.06.2016.
 */
public interface CustomerDao {

    void addCustomer(Customer customer) throws SQLException;
    Customer getCustomerById(int customerId) throws SQLException;
    Customer getCustomerByLoginEmail(String loginEmail) throws SQLException;

}
