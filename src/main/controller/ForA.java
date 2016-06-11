package controller;

import dao.CustomerDao;
import factory.Factory;
import table.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by FromxSoul on 10.06.2016.
 */
@WebServlet("/ForA")
public class ForA extends Forward {
    private Factory factory = Factory.getInstance();
    private CustomerDao customerDao = factory.getCustomerDao();
    private String INFO = "/info.jsp";
    private String LOGIN = "/login.jsp";
    private String REGISTRATION = "/registration.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        String forwardString = null;
        if (action.equals("info")) {
            String userLogin = session.getAttribute("userLogin").toString();
            try {
                Customer customer = customerDao.getCustomerByLoginEmail(userLogin);
                request.setAttribute("customer", customer);
                forwardString = INFO;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (action.equals("logout")) {
            session.invalidate();
            forwardString = LOGIN;
        } else if (action.equals("registration")) {
            forwardString = REGISTRATION;
        }
        super.forward(forwardString, request, response);
    }
}
