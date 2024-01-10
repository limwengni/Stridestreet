package servlet;

import entity.Address;
import entity.Customer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * Servlet implementation class EditAddress
 */
public class EditAddress extends HttpServlet {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction utx;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditAddress() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int addressId = Integer.parseInt(request.getParameter("addressId"));

        Address address = em.find(Address.class, addressId);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        
        int custId = customer.getCustomerId();
        
        Customer customer1 = em.find(Customer.class, custId);

        if (customer == null) {
            response.sendRedirect("login.jsp");
        } else {

            String name = request.getParameter("name").trim();
            String addressLine1 = request.getParameter("addressLine1").trim();
            String addressLine2 = request.getParameter("addressLine2").trim();
            String state = request.getParameter("state").trim();
            String postcode = request.getParameter("postcode");
            String phone = request.getParameter("phone");
            String defaultAdd = request.getParameter("defaultAddress");

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
            if (addressLine1 == null || addressLine1.equals("")) {
                request.setAttribute("emptyMessage1", emptyMessages[1]);
            }
            if (addressLine2 == null || addressLine2.equals("")) {
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

                if (addressId != customer1.getAddressList().get(0).getAddressId()) {

                    RequestDispatcher dispatcher = request.getRequestDispatcher("addAddress.jsp");
                    dispatcher.forward(request, response);
                }

            }

            address.setReceiverName(name);
            address.setAddressLine1(addressLine1);
            address.setAddressLine2(addressLine2);
            address.setAddressState(state);
            address.setPostcode(postcode);
            address.setReceiverPhone(phone);

            // Find the Customer entity with the matching customerId in the database
            Customer customerDetails = em.find(Customer.class, customer1.getCustomerId());

            // Find the Address entity with the matching addressId in the database
            Address addressDetails = em.find(Address.class, customer1.getAddressList().get(0).getAddressId());

            if (defaultAdd == null) {
                if (address.getDefaultAddress() == 0) {
                    address.setDefaultAddress(0);
                } else {
                    if (addressDetails.equals(address)) {
                        addressDetails.setDefaultAddress(1);
                    } else {
                        address.setDefaultAddress(0);
                        addressDetails.setDefaultAddress(1);
                    }
                }
            } else {
                if (address.getDefaultAddress() == 0) {
                    address.setDefaultAddress(1);
                    addressDetails.setDefaultAddress(0);
                }
                if (addressDetails.equals(address)) {
                    addressDetails.setDefaultAddress(1);
                } else {
                    address.setDefaultAddress(1);
                }
                // Find the original default address
                List<Address> resultList = em.createQuery("SELECT a FROM Address a WHERE a.customerId = :customerId AND a.defaultAddress = 1", Address.class).setParameter("customerId", customer1).getResultList();
                if (!resultList.isEmpty()) {
                    // Set its isDefault field to false
                    Address originalDefaultAddress = resultList.get(0);

                    originalDefaultAddress.setDefaultAddress(0);
                    try {
                        utx.begin();
                    } catch (NotSupportedException ex) {
                        Logger.getLogger(EditAddress.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SystemException ex) {
                        Logger.getLogger(EditAddress.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    em.merge(originalDefaultAddress);
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
                }
            }

            try {
                utx.begin();
            } catch (NotSupportedException ex) {
                Logger.getLogger(EditAddress.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(EditAddress.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (addressDetails.equals(address)) {
                addressDetails.setReceiverName(name);
                addressDetails.setAddressLine1(addressLine1);
                addressDetails.setAddressLine2(addressLine2);
                addressDetails.setAddressState(state);
                addressDetails.setPostcode(postcode);
                addressDetails.setReceiverPhone(phone);

//                addressDetails.setReceiverName(name);
//                addressDetails.setAddressLine1(addressLine1);
//                addressDetails.setReceiverPhone(phone);
                em.merge(addressDetails);

                customerDetails.setCustomerName(name);
                customerDetails.setCustomerPhone(phone);
                customerDetails.setCustomerAddress(addressLine1);

// Merge the updated entity back into the database
                em.merge(customerDetails);

            } else {
                em.merge(addressDetails);

                em.merge(address);
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
