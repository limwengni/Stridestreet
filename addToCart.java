/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import entity.Cart;
import entity.CartItem;
import entity.Customer;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author Yang
 */
public class addToCart extends HttpServlet {

    @PersistenceContext
    private EntityManager entityManager;
    @Resource
    UserTransaction utx;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet addToCart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addToCart at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String size = request.getParameter("selected-size");
        HttpSession session = request.getSession();
        int productId = Integer.parseInt(request.getParameter("productId"));
        Customer customer1 = new Customer();
        Customer cust = (Customer) request.getSession().getAttribute("customer");
        if (cust == null) {
            response.sendRedirect("login.jsp");
        } else {
            customer1.setCustomerId(cust.getCustomerId());
            List<Product> productList = (List<Product>) session.getAttribute("product");
            Customer customer = entityManager.find(Customer.class, customer1.getCustomerId());
            List<Cart> cartList = customer.getCartList();

            try {
                Cart cart;
                Product product = getProductFromModel(productId);
                cart = getCartFromModel(customer);
                addProductToCart(product, 1, cart, size);
                updateCartSubtotal(cart);

            } catch (NotSupportedException ex) {
                Logger.getLogger(addToCart.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(addToCart.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RollbackException ex) {
                Logger.getLogger(addToCart.class.getName()).log(Level.SEVERE, null, ex);
            } catch (HeuristicMixedException ex) {
                Logger.getLogger(addToCart.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(addToCart.class.getName()).log(Level.SEVERE, null, ex);
            } catch (HeuristicRollbackException ex) {
                Logger.getLogger(addToCart.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Get the product from the model
            // Redirect to the shopping cart page
// Forward to the JSP page
            request.setAttribute("productid", productId);
            RequestDispatcher dispatcher = request.getRequestDispatcher("shoeDetails");
            dispatcher.forward(request, response);

        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private Cart getCartFromModel(Customer customerId) throws NotSupportedException, SystemException, RollbackException, HeuristicMixedException, SecurityException, HeuristicRollbackException {
        // Check if the customer already has a cart

        Customer customer = entityManager.find(Customer.class, customerId.getCustomerId());
        TypedQuery<Cart> cartQuery = entityManager.createQuery(
                "SELECT c FROM Cart c WHERE c.customerId = :customerId", Cart.class);
        cartQuery.setParameter("customerId", customerId);

        try {
            Cart cart = cartQuery.getSingleResult();
            cart.setCartItemList(getCartItemsFromModel(cart));
            return cart;
        } catch (NoResultException e) {
            // If the customer does not have a cart, create one
            utx.begin();
            Cart cart = new Cart();
            cart.setCustomerId(customer);
            entityManager.persist(cart);
            entityManager.flush();
            utx.commit();
            cart.setCartItemList(new ArrayList<>());
            return cart;
        }
    }

    private List<CartItem> getCartItemsFromModel(Cart cartId) {
        TypedQuery<CartItem> cartItemQuery = entityManager.createQuery(
                "SELECT ci FROM CartItem ci WHERE ci.cartId = :cartId", CartItem.class);
        cartItemQuery.setParameter("cartId", cartId);
        return cartItemQuery.getResultList();
    }

    private Product getProductFromModel(int productId) {
        return entityManager.find(Product.class, productId);
    }

    private void addProductToCart(Product product, int quantity, Cart cart, String size) throws NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
        List<CartItem> cartItems = cart.getCartItemList();
        CartItem existingCartItem = null;

        // Check if a cart item with the same product ID and size already exists
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProductId().getProductId() == product.getProductId() && cartItem.getSize().equals(size)) {
                existingCartItem = cartItem;
                break;
            }
        }

        utx.begin();

        if (existingCartItem != null) {
            // Update the quantity of the existing cart item
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
            entityManager.merge(existingCartItem);
        } else {
            // Create a new cart item with the given size
            CartItem cartItem = new CartItem();
            Product prod = entityManager.find(Product.class, product.getProductId());
            Cart c = entityManager.find(Cart.class, cart.getCartId());
            cartItem.setCartId(c);
            cartItem.setProductId(prod);
            cartItem.setQuantity(quantity);
            cartItem.setSize(size);
            entityManager.persist(cartItem);
            cartItems.add(cartItem);
        }

        utx.commit();
    }

    private void updateCartSubtotal(Cart cart) throws IllegalStateException, SecurityException, SystemException {
        double subtotal = 0.0;
        for (CartItem cartItem : cart.getCartItemList()) {
            Product product = getProductFromModel(cartItem.getProductId().getProductId());
            subtotal += cartItem.getQuantity() * product.getProductPrice();
        }
        cart.setSubtotal(subtotal);

        try {
            utx.begin();
            entityManager.merge(cart);
            utx.commit();
        } catch (Exception e) {
            if (utx != null) {
                utx.rollback();
            }
            e.printStackTrace();
        }
    }

}
