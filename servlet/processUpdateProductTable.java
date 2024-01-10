/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import entity.Product;
import java.io.IOException;
import java.sql.Blob;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jmok4
 */
public class processUpdateProductTable extends HttpServlet {

    @PersistenceContext EntityManager em; 
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        Product product = em.find(Product.class, productId);
        
        
        HttpSession session = request.getSession();
        session.setAttribute("product",product);
        response.sendRedirect("processDisplayProductTable");
        
    }

    
}
