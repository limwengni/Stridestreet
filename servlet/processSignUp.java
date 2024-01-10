/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import entity.Address;
import entity.Customer;
import entity.Role;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
import javax.validation.ConstraintViolation;

/**
 *
 * @author jmok4
 */
public class processSignUp extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    @Resource
    UserTransaction utx;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmpassword = request.getParameter("cpassword");

        String roleId = "1";
        Role role = em.find(Role.class, roleId);

        List<String> errorMessages = new ArrayList<>();
        errorMessages.addAll(validateInput.isPasswordValid(password));
        errorMessages.addAll(validateInput.isPhoneNumberValid(phone));
        errorMessages.addAll(validateInput.isEmailAddressValid(email));
        errorMessages.addAll(validateInput.isUserNameValid(username));
        errorMessages.addAll(validateInput.isNameValid(name));

        Query query = em.createQuery("SELECT c FROM Customer c WHERE c.customerUsername = :username");
        query.setParameter("username", username);
        List<Customer> customers = query.getResultList();
        if (!customers.isEmpty()) {
            errorMessages.add("Username is already exist in our database change another one");
        }

        if (!password.equals(confirmpassword)) {
            errorMessages.add("Password and Confirm Password is not same");
        }

        if (!errorMessages.isEmpty()) {
            request.setAttribute("errorMessages", errorMessages);
            RequestDispatcher rd = request.getRequestDispatcher("signUp.jsp");
            rd.forward(request, response);
            return;
        }

        Customer customer = new Customer();
        customer.setCustomerName(name);
        customer.setCustomerEmail(email);
        customer.setCustomerPassword(password);
        customer.setCustomerAddress(address);
        customer.setCustomerPhone(phone);
        customer.setCustomerUsername(username);
        customer.setRoleId(role);
        customer.setAddressList(new ArrayList<Address>()); // Initialize the Address list

        // Create a new Address object with the user's input and set it to the customer
//        Address newAddress = new Address(name, address, "", "", "", phone, customer, 1);
        Address newAddress = new Address();
        newAddress.setReceiverName(name);
        newAddress.setAddressLine1(address);
        newAddress.setAddressLine2("");
        newAddress.setAddressState("");
        newAddress.setPostcode("");
        newAddress.setReceiverPhone(phone);
        newAddress.setCustomerId(customer);
        newAddress.setDefaultAddress(1);
        customer.getAddressList().add(newAddress);

        try {
            utx.begin();
        } catch (NotSupportedException ex) {
            Logger.getLogger(processSignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(processSignUp.class.getName()).log(Level.SEVERE, null, ex);
        }

        em.persist(customer);

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

        response.sendRedirect("login.jsp");

    }

}
