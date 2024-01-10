package servlet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import entity.OrderStatusLog;
import entity.Orders;
import entity.Staff;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author User
 */

public class UpdateOrderStatusServlet extends HttpServlet {
@PersistenceContext EntityManager em;
@Resource UserTransaction utx;
  private static final long serialVersionUID = 1L;

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Get the parameters from the request
    int staffId = Integer.parseInt(request.getParameter("staffId"));
    int orderId = Integer.parseInt(request.getParameter("orderId"));
    String orderStatus = request.getParameter("status");

    // Check if an order status log already exists for the specified order ID
Orders lo = new Orders();
                lo.setOrderId(orderId);
  	Query query = em.createQuery("SELECT l FROM OrderStatusLog l WHERE l.orderId = :orderId");
		query.setParameter("orderId", lo);
		List<OrderStatusLog> log = query.getResultList();
    if (log == null) {
        // Create a new order status log
      
    } else {
        // Update the existing order status log
       
   OrderStatusLog log1 = new   OrderStatusLog();
   log1 = log.get(0);
        System.out.println(log1.getOrderStatus());
        Staff s = new Staff();
        s.setStaffId(staffId);
        log1.setStaffId(s);
        log1.setOrderStatus(orderStatus);
         try {
            utx.begin();
        } catch (NotSupportedException ex) {
            Logger.getLogger(UpdateOrderStatusServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(UpdateOrderStatusServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        em.merge(log1);
        try {
            utx.commit();
        } catch (RollbackException ex) {
            Logger.getLogger(UpdateOrderStatusServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(UpdateOrderStatusServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(UpdateOrderStatusServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(UpdateOrderStatusServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(UpdateOrderStatusServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(UpdateOrderStatusServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


  

    // Redirect back to the order status page with a success message
    request.setAttribute("updateSuccess", "Update Succesful");
		request.getRequestDispatcher("updateOS.jsp").forward(request, response);
}

}
