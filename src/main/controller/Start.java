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
@SuppressWarnings("Since15")
@WebServlet("/Start")
public class Start extends Forward {
    private Factory factory = Factory.getInstance();
    private CustomerDao customerDao = factory.getCustomerDao();
    private Customer customer;

    private String INVALID_LOGIN = "/invalidLogin.jsp";
    private String OK_PAGE = "/Ok.jsp";
    private String INVALID_PASSWORD = "/InvalidPassword.jsp";
    private String EMPTY_LOGIN = "/emptyLogin.jsp";
    private String EMPTY_PASSWORD = "/emptyPassword.jsp";
    private String EMPTY_ALL = "/loginEmptyAllFields.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HibernateUtil.getSessionFactory();

        String userLogin = request.getParameter("login_l");
        String userPassword = request.getParameter("password_p");


        if (userLogin.length() == 0 & userPassword.length() == 0) {
            super.forward(EMPTY_ALL, request, response);
            return;
        }



        if (userLogin.length() == 0) {
            super.forward(EMPTY_LOGIN, request, response);
        } else {
            if (userPassword.length() == 0) {
                super.forward(EMPTY_PASSWORD, request, response);

            } else {
                try {
                    customer = customerDao.getCustomerByLoginEmail(userLogin);
                    customer.getLoginEmail();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    super.forward(INVALID_LOGIN, request, response);
                }

                try {
                    String customerPassword = customer.getPassword();
                    if (customerPassword.equals(userPassword)) {
                        HttpSession session = request.getSession();
                        session.setAttribute("userLogin", userLogin);

                        userLogin = session.getAttribute("userLogin").toString();
                        customer = customerDao.getCustomerByLoginEmail(userLogin);
                        request.setAttribute("customer", customer);
                        super.forward(OK_PAGE, request, response);

                    } else {
                        super.forward(INVALID_PASSWORD, request, response);
                    }

                } catch (NullPointerException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
