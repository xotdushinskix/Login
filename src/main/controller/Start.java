package controller;

import dao.CustomerDao;
import factory.Factory;
import table.Customer;
import util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by FromxSoul on 09.06.2016.
 */
@WebServlet("/Start")
public class Start extends Forward {
    private Factory factory = Factory.getInstance();
    private CustomerDao customerDao = factory.getCustomerDao();
    private Customer customer;

    private String ABSENT_CUSTOMER = "/CustomerAbsent.jsp";
    private String OK_PAGE = "/Ok.jsp";
    private String INVALID_PASSWORD = "/InvalidPassword.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HibernateUtil.getSessionFactory();

        String userLogin = request.getParameter("login_l");
        String userPassword = request.getParameter("password_p");

        try {
            customer = customerDao.getCustomerByLoginEmail(userLogin);
            String customerPassword = customer.getPassword();
            if (customerPassword.equals(userPassword)) {
                HttpSession session = request.getSession();
                session.setAttribute("userLogin", userLogin);

                userLogin = session.getAttribute("userLogin").toString();
                Customer customer = customerDao.getCustomerByLoginEmail(userLogin);
                request.setAttribute("customer", customer);

                super.forward(OK_PAGE, request, response);
            } else {
                super.forward(INVALID_PASSWORD, request, response);
            }
        } catch (SQLException e) {
            super.forward(ABSENT_CUSTOMER, request, response);
            e.printStackTrace();
        }

    }
}
