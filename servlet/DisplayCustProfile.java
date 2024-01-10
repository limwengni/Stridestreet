/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
public class DisplayCustProfile extends HttpServlet {

    // Get the EntityManager
    @PersistenceContext
    EntityManager em;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Customer customer = (Customer) request.getSession().getAttribute("customer");

        if (customer == null) {
            response.sendRedirect("login.jsp");
        } else {
            List<Customer> customerDetails = em.createNamedQuery("Customer.findByCustomerId", Customer.class).setParameter("customerId", customer.getCustomerId()).getResultList();
            // set the list of addresses as an attribute on the request object
            request.setAttribute("customerDetails", customerDetails);

            // forward the request to a JSP for display
            RequestDispatcher dispatcher = request.getRequestDispatcher("customerProfile.jsp");
            dispatcher.forward(request, response);
            
            // forward the request to a JSP for display
            RequestDispatcher dispatcher2 = request.getRequestDispatcher("editCustomerProfile.jsp");
            dispatcher2.forward(request, response);

            // close the EntityManager
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

    }

}
