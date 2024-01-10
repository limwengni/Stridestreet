/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import entity.Customer;
import entity.Staff;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jmok4
 */
public class processLogin extends HttpServlet {
    
    @PersistenceContext EntityManager em; 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String Manager = "Manager";
       
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Authenticate the user is a dedault manager ornot.If not proceed to the next
        if (username.equals("Manager") && password.equals("Manager")) {
            HttpSession session = request.getSession();
            session.setAttribute("manager",Manager);
            session.setAttribute("roleType",Manager);
            response.sendRedirect("processDisplayStaff");
            return;
        }
        
        // Authenticate the user is a customer ornot.If not proceed to the next
        Query query = em.createQuery("SELECT c FROM Customer c WHERE c.customerUsername = :username AND c.customerPassword = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<Customer> customers = query.getResultList();
        if (!customers.isEmpty()) {
            HttpSession session = request.getSession();
            Customer customer = (Customer) query.getSingleResult();
            String roleType = customer.getRoleId().getRoleType();
            session.setAttribute("customer",customer);
            session.setAttribute("roleType",roleType);
            response.sendRedirect("customerProfile.jsp");
            return;
        }
        
        // Authenticate the user is a staff ornot.If not proceed to the next
        query = em.createQuery("SELECT s FROM Staff s WHERE s.staffUsername = :username AND s.staffPassword = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<Staff> staffs = query.getResultList();
        if (!staffs.isEmpty()) {
            HttpSession session = request.getSession();
//            session.setAttribute("staffs",staffs);
            Staff staff = (Staff) query.getSingleResult();
            String roleType = staff.getRoleId().getRoleType();
            session.setAttribute("staff",staff);
            session.setAttribute("roleType",roleType);
            response.sendRedirect("staffProfile.jsp");
            return;
        }
        
        // Authenticate the user if the username, password or not record in database
        response.sendRedirect("login.jsp?error=1");
    }
    
    
}
