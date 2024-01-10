/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import entity.Product;
import java.io.IOException;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

/**
 *
 * @author jmok4
 */
public class processConfirmationUpdateProductTable extends HttpServlet {
    
    @PersistenceContext EntityManager em; 
    @Resource UserTransaction utx;
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Product product = (Product) session.getAttribute("product");
       
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String sku = request.getParameter("sku");
        String category = request.getParameter("category");
        String size = request.getParameter("size");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));
        String image1 = request.getParameter("img1");
        String image2 = request.getParameter("img2");
        String image3 = request.getParameter("img3");
        String image4 = request.getParameter("img4");
        String image5 = request.getParameter("img5");
        String image6 = request.getParameter("img6");
     
        try{
            
            Product productDetails = em.find(Product.class, product.getProductId());
            productDetails.setProductName(name);
            productDetails.setProductDesc(description);
            productDetails.setSku(sku);
            productDetails.setCategory(category);
            productDetails.setProductSize(size);
            productDetails.setProductQuantity(quantity);
            productDetails.setProductPrice(price);
            productDetails.setProductImage1(image1);
            productDetails.setProductImage2(image2);
            productDetails.setProductImage3(image3);
            productDetails.setProductImage4(image4);
            productDetails.setProductImage5(image5);
            productDetails.setProductImage6(image6);
            
            utx.begin();
            em.merge(productDetails);
            utx.commit();
        
            response.sendRedirect("processDisplayProductTable");
        }catch(Exception e){
            // Handle other exceptions
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred");
        }
       
    }

     

}
