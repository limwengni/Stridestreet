/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import entity.Customer;
import entity.Role;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

/**
 *
 * @author jmok4
 */
public class processAddCustomer extends HttpServlet {
    
    @PersistenceContext EntityManager em;
    @Resource UserTransaction utx;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        
        String roleId = "1";
        Role role = em.find(Role.class, roleId);
        
        List<String> errorMessages = new ArrayList<String>();
        
        Query query = em.createQuery("SELECT c FROM Customer c WHERE c.customerUsername = :username");
        query.setParameter("username", username);
        List<Customer> customers = query.getResultList();
        if (!customers.isEmpty()) {
            errorMessages.add("Username is already exist in our database change another one");
        }
        
        if (!errorMessages.isEmpty()) {
            request.setAttribute("errorMessages", errorMessages);
            RequestDispatcher rd = request.getRequestDispatcher("addCustomer.jsp");
            rd.forward(request, response);
            return;
        }
        
        try{
        Customer customer = new Customer();
        customer.setCustomerName(name);
        customer.setCustomerEmail(email);
        customer.setCustomerPassword(password);
        customer.setCustomerAddress(address);
        customer.setCustomerPhone(phone);
        customer.setCustomerUsername(username);
        customer.setRoleId(role);
        
        utx.begin();
        em.persist(customer);
        utx.commit();
        
        response.sendRedirect("processDisplayCustomer");
        
        }catch(Exception e){
            // Handle other exceptions
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred");
        }
        
    }

   

}
