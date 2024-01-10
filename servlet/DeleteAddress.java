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
 * Servlet implementation class DeleteAddress
 */
public class DeleteAddress extends HttpServlet {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction utx;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAddress() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer == null) {
            response.sendRedirect("login.jsp");
        } else {
            int addressId = Integer.parseInt(request.getParameter("id"));
            int custId = customer.getCustomerId();

            Customer customer1 = em.find(Customer.class, custId);
            Address address = em.find(Address.class, addressId);

            if (addressId == customer1.getAddressList().get(0).getAddressId()) {
                String error = "You are not allowed to delete your main address!";
                request.setAttribute("error", error);
                RequestDispatcher dispatcher = request.getRequestDispatcher("AddressList");
                dispatcher.forward(request, response);
            }

            try {
                utx.begin();
            } catch (NotSupportedException ex) {
                Logger.getLogger(DeleteAddress.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(DeleteAddress.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (address.getDefaultAddress() == 1) { //if default, need to choose new address as default
                Address resultList = em.createQuery("SELECT a FROM Address a WHERE a.customerId = :customerId AND a.defaultAddress = 0", Address.class)
                            .setParameter("customerId", customer1).getResultList().get(0);
                
                if (resultList != null) {
                    resultList.setDefaultAddress(1);
                    em.merge(resultList);
                } else {
                    String error1 = "Please have at least one address in your account!";
                    request.setAttribute("error1", error1);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("AddressList");
                    dispatcher.forward(request, response);
                }
            }

            em.detach(address); // Detach the entity from the persistence context
            address = em.merge(address); // Merge the detached entity back into the persistence context
            em.remove(address); // Call remove on the merged entity

            try {
                utx.commit();
            } catch (RollbackException ex) {
                Logger.getLogger(DeleteAddress.class.getName()).log(Level.SEVERE, null, ex);
            } catch (HeuristicMixedException ex) {
                Logger.getLogger(DeleteAddress.class.getName()).log(Level.SEVERE, null, ex);
            } catch (HeuristicRollbackException ex) {
                Logger.getLogger(DeleteAddress.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(DeleteAddress.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalStateException ex) {
                Logger.getLogger(DeleteAddress.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(DeleteAddress.class.getName()).log(Level.SEVERE, null, ex);
            }

            response.sendRedirect("AddressList");
        }
    }
}
