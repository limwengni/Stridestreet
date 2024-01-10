package servlet;


import entity.OrderStatusLog;
import entity.Orders;

import java.io.IOException;
import java.time.LocalDate;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import javax.persistence.Query;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.transaction.UserTransaction;



public class orderstatus extends HttpServlet {
	 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   int orderId1 = Integer.parseInt(request.getParameter("order-id1"));

	 	Orders lo = new Orders();
                lo.setOrderId(orderId1);
          EntityManagerFactory emf = Persistence.createEntityManagerFactory("StrideStreetPU");
    EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT l FROM OrderStatusLog l WHERE l.orderId = :orderId");
		query.setParameter("orderId", lo);
		List<OrderStatusLog> logs = query.getResultList();
          
			OrderStatusLog latestLog = logs.get(0);
		String orderStatus = latestLog.getOrderStatus();
                LocalDate today = LocalDate.now();
                        LocalDate deliveryDate = today.plusDays(7);
		emf.close();
		request.setAttribute("orderId", orderId1);
		request.setAttribute("orderStatus", orderStatus);
                request.setAttribute("deliveryDate", "Estimated Delivery : <i>" +deliveryDate+"</i>");
		request.getRequestDispatcher("order-statusC.jsp").forward(request, response);
               
		
	}
	
}
