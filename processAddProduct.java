/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import entity.Product;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

/**
 *
 * @author jmok4
 */
public class processAddProduct extends HttpServlet {
    
    @PersistenceContext EntityManager em;
    @Resource
    UserTransaction utx;
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String sku = request.getParameter("sku");
        String category = request.getParameter("category");
        String size = request.getParameter("size");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int sold = 0;
        double price = Double.parseDouble(request.getParameter("price"));
        String image1 = request.getParameter("img1");
        String image2 = request.getParameter("img2");
        String image3 = request.getParameter("img3");
        String image4 = request.getParameter("img4");
        String image5 = request.getParameter("img5");
        String image6 = request.getParameter("img6");
        
    // Open a database conn
        try{
            
            Product product = new Product();
            product.setProductName(name);
            product.setProductDesc(description);
            product.setSku(sku);
            product.setCategory(category);
            product.setProductSize(size);
            product.setProductQuantity(quantity);
            product.setProductSold(sold);
            product.setProductPrice(price);
            product.setProductImage1(image1);
            product.setProductImage2(image2);
            product.setProductImage3(image3);
            product.setProductImage4(image4);
            product.setProductImage5(image5);
            product.setProductImage6(image6);
           
            
            utx.begin();
            em.persist(product);
            utx.commit();
            
            response.sendRedirect("processDisplayProductTable");
        }catch(Exception ex){
            Logger.getLogger(processAddProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
