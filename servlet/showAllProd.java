/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import entity.Customer;
import entity.Product;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class showAllProd extends HttpServlet {

    // Get the EntityManager
    @PersistenceContext
    EntityManager em;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // execute a JPA query to retrieve a list of product entities

        TypedQuery<Product> query = em.createNamedQuery("Product.findAll", Product.class);
        List<Product> products = query.getResultList();

        request.setAttribute("products", products);

        // forward the request to a JSP for display
        RequestDispatcher dispatcher = request.getRequestDispatcher("showAllProd.jsp");
        dispatcher.forward(request, response);

        // close the EntityManager
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
}
//}
