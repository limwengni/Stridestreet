/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import entity.Address;
import entity.Customer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author jmok4
 */
public class processEditCustomerProfile extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    @Resource
    UserTransaction utx;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer == null) {
            response.sendRedirect("login.jsp");
        } else {

            String name = request.getParameter("name").trim();
            String phone = request.getParameter("phone").trim();
            String email = request.getParameter("email").trim();
            String username = request.getParameter("username").trim();
            String password = request.getParameter("password").trim();
            String address = request.getParameter("address").trim();

            List<String> errorMessages = new ArrayList<>();
            errorMessages.addAll(validateInput.isPasswordValid(password));
            errorMessages.addAll(validateInput.isPhoneNumberValid(phone));
            errorMessages.addAll(validateInput.isEmailAddressValid(email));
            errorMessages.addAll(validateInput.isUserNameValid(username));
            errorMessages.addAll(validateInput.isNameValid(name));

            if (!errorMessages.isEmpty()) {
                request.setAttribute("errorMessages", errorMessages);
                RequestDispatcher rd = request.getRequestDispatcher("editCustomerProfile.jsp");
                rd.forward(request, response);
                return;
            }

            try {

                try {
                    utx.begin();
                } catch (NotSupportedException ex) {
                    Logger.getLogger(EditAddress.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SystemException ex) {
                    Logger.getLogger(EditAddress.class.getName()).log(Level.SEVERE, null, ex);
                }

                // Find the Customer entity with the matching customerId in the database
                Customer customerDetails = em.find(Customer.class, customer.getCustomerId());

                // Find the Address entity with the matching addressId in the database
                Address addressDetails = em.find(Address.class, customer.getAddressList().get(0).getAddressId());

                addressDetails.setReceiverName(name);
                addressDetails.setAddressLine1(address);
                addressDetails.setReceiverPhone(phone);
                
                customer.setAddressList(Arrays.asList(addressDetails));

                em.merge(customer);
                em.merge(addressDetails);

// Update the fields of the retrieved entity with the new values
                customerDetails.setCustomerName(name);
                customerDetails.setCustomerPhone(phone);
                customerDetails.setCustomerEmail(email);
                customerDetails.setCustomerUsername(username);
                customerDetails.setCustomerPassword(password);
                customerDetails.setCustomerAddress(address);

// Merge the updated entity back into the database
                em.merge(customerDetails);

                try {
                    utx.commit();
                } catch (RollbackException ex) {
                    Logger.getLogger(EditAddress.class.getName()).log(Level.SEVERE, null, ex);
                } catch (HeuristicMixedException ex) {
                    Logger.getLogger(EditAddress.class.getName()).log(Level.SEVERE, null, ex);
                } catch (HeuristicRollbackException ex) {
                    Logger.getLogger(EditAddress.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SecurityException ex) {
                    Logger.getLogger(EditAddress.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalStateException ex) {
                    Logger.getLogger(EditAddress.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SystemException ex) {
                    Logger.getLogger(EditAddress.class.getName()).log(Level.SEVERE, null, ex);
                }

                session.setAttribute("customer", customerDetails);
            } catch (Exception ex) {
                if (utx != null) {
                    try {
                        utx.rollback();
                    } catch (IllegalStateException | SecurityException | SystemException e) {
                        // log the exception
                    }
                }
                // log the exception
            }
            response.sendRedirect("DisplayCustProfile");
        }

    }
}
