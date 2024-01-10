/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import entity.Product;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Search extends HttpServlet {

    // Get the EntityManager
    @PersistenceContext
    EntityManager em;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // execute a JPA query to retrieve a list of product entities
        
        String productName = request.getParameter("searchitem");

        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.productName like :productName", Product.class);
        query.setParameter("productName", "%" + productName + "%");
        List<Product> results = query.getResultList();
        request.setAttribute("searchResults", results);

        // forward the request to a JSP for display
        RequestDispatcher dispatcher = request.getRequestDispatcher("showResult.jsp");
        dispatcher.forward(request, response);

        // close the EntityManager
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
}
//}
