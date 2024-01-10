/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import entity.Customer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

/**
 *
 * @author jmok4
 */
public class processConfirmationUpdateCustomer extends HttpServlet {
    
    @PersistenceContext EntityManager em; 
    @Resource UserTransaction utx;
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        
        List<String> errorMessages = new ArrayList<>();
        errorMessages.addAll(validateInput.isPasswordValid(password));
        errorMessages.addAll(validateInput.isPhoneNumberValid(phone));
        errorMessages.addAll(validateInput.isEmailAddressValid(email));
        errorMessages.addAll(validateInput.isUserNameValid(username));
        errorMessages.addAll(validateInput.isNameValid(name));

        if (!errorMessages.isEmpty()) {
            request.setAttribute("errorMessages", errorMessages);
            RequestDispatcher rd = request.getRequestDispatcher("updateCustomerTable.jsp");
            rd.forward(request, response);
            return;
        }
        
        try{
            
            Customer customerDetails = em.find(Customer.class, customer.getCustomerId());
            customerDetails.setCustomerName(name);
            customerDetails.setCustomerEmail(email);
            customerDetails.setCustomerPhone(phone);
            customerDetails.setCustomerUsername(username);
            customerDetails.setCustomerPassword(password);
            customerDetails.setCustomerAddress(address);
        
            utx.begin();
            em.merge(customerDetails);
            utx.commit();
        
            response.sendRedirect("processDisplayCustomer");
        }catch(Exception e){
            // Handle other exceptions
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred");
        }
       
    }

     

}
