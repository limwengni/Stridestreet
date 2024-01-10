package servlet;

import entity.Address;
import entity.Customer;
import java.io.IOException;
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
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

public class AddAddress extends HttpServlet {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction utx;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        int custId = customer.getCustomerId();

        Customer customer1 = em.find(Customer.class, custId);

        if (customer == null) {
            response.sendRedirect("login.jsp");
        } else {
            // Retrieve form data
            String name = request.getParameter("name").trim();
            String addressL1 = request.getParameter("addressLine1").trim();
            String addressL2 = request.getParameter("addressLine2").trim();
            String state = request.getParameter("state").trim();
            String postcode = request.getParameter("postcode");
            String phone = request.getParameter("phone");
            String defaultAddress = request.getParameter("defaultAddress");

            String[] emptyMessages = new String[10];

            emptyMessages[0] = "Please fill up the name field";
            emptyMessages[1] = "Please fill up the address line 1 field";
            emptyMessages[2] = "Please fill up the address line 2 field";
            emptyMessages[3] = "Please fill up the state field";
            emptyMessages[4] = "Please fill up the postcode field";
            emptyMessages[5] = "Please fill up the phone field";

            if (name == null || name.equals("")) {
                request.setAttribute("emptyMessage0", emptyMessages[0]);
            }
            if (addressL1 == null || addressL1.equals("")) {
                request.setAttribute("emptyMessage1", emptyMessages[1]);
            }
            if (addressL2 == null || addressL2.equals("")) {
                request.setAttribute("emptyMessage2", emptyMessages[2]);
            }
            if (state == null || state.equals("")) {
                request.setAttribute("emptyMessage3", emptyMessages[3]);
            }
            if (postcode == null || postcode.equals("")) {
                request.setAttribute("emptyMessage4", emptyMessages[4]);
            }
            if (phone == null || phone.equals("")) {
                request.setAttribute("emptyMessage5", emptyMessages[5]);
            }

            if (request.getAttribute("emptyMessage0") != null
                    || request.getAttribute("emptyMessage1") != null
                    || request.getAttribute("emptyMessage2") != null
                    || request.getAttribute("emptyMessage3") != null
                    || request.getAttribute("emptyMessage4") != null
                    || request.getAttribute("emptyMessage5") != null) {

                RequestDispatcher dispatcher = request.getRequestDispatcher("addAddress.jsp");
                dispatcher.forward(request, response);
                response.sendRedirect("AddressList");
            }

//// Create a mock customer object
//        Customer customer = new Customer();
//        customer.setCustomerId(1);
//// Set the customer object as an attribute in the session
//        HttpSession session = request.getSession();
//        session.setAttribute("customer", customer);
            try {
                utx.begin();
            } catch (NotSupportedException ex) {
                Logger.getLogger(EditAddress.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(EditAddress.class.getName()).log(Level.SEVERE, null, ex);
            }

            int defAdd;
            Address newAddress = null;

            if (defaultAddress == null) {
                defAdd = 0;

//                newAddress = new Address(name, addressL1, addressL2, state, postcode, phone, customer1, defAdd);
                newAddress = new Address();
                newAddress.setReceiverName(name);
                newAddress.setAddressLine1(addressL1);
                newAddress.setAddressLine2(addressL2);
                newAddress.setAddressState(state);
                newAddress.setPostcode(postcode);
                newAddress.setReceiverPhone(phone);
                newAddress.setCustomerId(customer1);
                newAddress.setDefaultAddress(defAdd);
                em.persist(newAddress);
            } else {
                defAdd = 1;
                // Find the original default address
                List<Address> resultList = em.createQuery("SELECT a FROM Address a WHERE a.customerId = :customerId AND a.defaultAddress = 1", Address.class).setParameter("customerId", customer1).getResultList();
                if (!resultList.isEmpty()) {
                    // Set its isDefault field to false
                    Address originalDefaultAddress = resultList.get(0);

                    originalDefaultAddress.setDefaultAddress(0);
                    em.merge(originalDefaultAddress);

                }
//                newAddress = new Address(name, addressL1, addressL2, state, postcode, phone, customer1, defAdd);
                newAddress = new Address();
                newAddress.setReceiverName(name);
                newAddress.setAddressLine1(addressL1);
                newAddress.setAddressLine2(addressL2);
                newAddress.setAddressState(state);
                newAddress.setPostcode(postcode);
                newAddress.setReceiverPhone(phone);
                newAddress.setCustomerId(customer1);
                newAddress.setDefaultAddress(defAdd);
                em.persist(newAddress);
            }

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

            response.sendRedirect("AddressList");

        }
    }
}
