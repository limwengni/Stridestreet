/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import entity.Address;
import entity.Customer;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddressList extends HttpServlet {

    // Get the EntityManager
    @PersistenceContext(unitName = "StrideStreetPU")
    EntityManager em = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        // Create a mock customer object(delete later)
//        Customer customer = new Customer();
//        customer.setCustomerId(1);
//// Set the customer object as an attribute in the session
//        HttpSession session = request.getSession();
//        session.setAttribute("customer", customer);
        Customer customer = (Customer) request.getSession().getAttribute("customer");

        if (customer == null) {
            response.sendRedirect("login.jsp");
        } else {

            // execute a JPA query to retrieve a list of Address entities
            TypedQuery<Address> query = em.createQuery("SELECT a FROM Address a WHERE a.defaultAddress = 1 AND a.customerId = :customerId", Address.class);
            query.setParameter("customerId", customer);
            List<Address> defaultAddresses = query.getResultList();

            // set the list of addresses as an attribute on the request object
            request.setAttribute("defaultAddresses", defaultAddresses);

            TypedQuery<Address> query2 = em.createQuery("SELECT a FROM Address a WHERE a.defaultAddress = 0 AND a.customerId = :customerId", Address.class);
            query2.setParameter("customerId", customer);
            List<Address> notDefaultAddresses = query2.getResultList();

            // set the list of addresses as an attribute on the request object
            request.setAttribute("notDefaultAddresses", notDefaultAddresses);

            // forward the request to a JSP for display
            RequestDispatcher dispatcher = request.getRequestDispatcher("addressList.jsp");
            dispatcher.forward(request, response);

            // close the EntityManager
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

    }
}
