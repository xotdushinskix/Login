package controller;

import dao.CustomerDao;
import factory.Factory;
import table.Customer;
import util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by FromxSoul on 10.06.2016.
 */
@WebServlet("/Registration")
public class Registration extends Forward {
    private Factory factory = Factory.getInstance();
    private CustomerDao customerDao = factory.getCustomerDao();
    private Customer customer;
    private String SUCCESS_REG = "/successRegistration.jsp";
    private String EMPTY_REGISTRATION = "emptyRegistration.jsp";
    private String EMPTY_ANY_FIELD_IN_REGISTRATION = "/anyEmptyFieldInRegistration.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HibernateUtil.getSessionFactory();

        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String eMail = request.getParameter("email_password");
        String password = request.getParameter("password");

        if (firstName.length() == 0 || lastName.length() == 0 || eMail.length() == 0 || password.length() == 0) {
            forward(EMPTY_ANY_FIELD_IN_REGISTRATION, request, response);

        } else {
            try {
                customer = new Customer();
                customer.setFirstName(firstName);
                customer.setLastName(lastName);
                customer.setLoginEmail(eMail);
                customer.setPassword(password);
            } catch (NullPointerException e) {
                e.printStackTrace();
                super.forward(EMPTY_REGISTRATION, request, response);
            }

            try {
                customerDao.addCustomer(customer);
                request.setAttribute("firstName", customer.getFirstName());
                super.forward(SUCCESS_REG, request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
