package servlet;

import entity.Cart;
import entity.CartItem;
import entity.Customer;
import entity.OrderDetail;
import entity.Orders;
import entity.Product;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

public class ProcessCheckoutServlet extends HttpServlet {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction utx;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

//        int custId;
        if (customer == null) {
            response.sendRedirect("login.jsp");
        } else {

            String selectedPaymentMethod = request.getParameter("selectedOption");

            if (selectedPaymentMethod == null || selectedPaymentMethod.isEmpty()) {
                String errorMessage = "Please select your payment method.";
                session.setAttribute("errorMessage", errorMessage);
                response.sendRedirect("Checkout?total=" + request.getParameter("subtotal"));
//                RequestDispatcher dispatcher = request.getRequestDispatcher("Checkout");
//                dispatcher.forward(request, response);
            }

            String totalAmount = request.getParameter("totalAmount");
            request.setAttribute("totalAmount", totalAmount);

            String cardNumber = request.getParameter("card-number-input").trim();
            String expiryDate = request.getParameter("expiry-date-input");
            String cvv = request.getParameter("cvv-input");
            String name = request.getParameter("name-input").trim();

            if ("Credit/DebitCard".equals(selectedPaymentMethod)) {

                String[] emptyMessages = new String[10];
                String cardType = null;

                emptyMessages[0] = "Please fill up the cvv field";
                emptyMessages[1] = "Please fill up the name field";
                emptyMessages[2] = "Please fill up the card number field";
                emptyMessages[3] = "Please fill up the card expiry date field";

                if (cvv == null || cvv.equals("")) {
                    session.setAttribute("emptyMessage0", emptyMessages[0]);
                }
                if (name == null || name.equals("")) {
                    session.setAttribute("emptyMessage1", emptyMessages[1]);
                }

                if (cardNumber.matches("^4[0-9]{12}(?:[0-9]{3})?$")) {
                    cardType = "Visa";
                } else if (cardNumber.matches("^5[1-5][0-9]{14}$")) {
                    cardType = "MasterCard";
                }

                boolean cardValid = cardValidation(cardNumber);
                boolean dateValid = expiryDateValidation(expiryDate);

                String cardMessage = "";
                String dateMessage = "";

                if (cardValid != true) {
                    if (cardNumber.equals("")) {
                        session.setAttribute("emptyMessage2", emptyMessages[2]);
                    } else {
                        cardMessage = "Card number is invalid! Please use another card!";
                        session.setAttribute("cardMessage", cardMessage);
                    }
                } else {
                    cardMessage = null;
                    session.setAttribute("cardMessage", cardMessage);
                }

                if (dateValid != true) {
                    if (expiryDate.equals("")) {
                        session.setAttribute("emptyMessage3", emptyMessages[3]);
                    } else {
                        dateMessage = "Card is expired! Please use another card!";
                        session.setAttribute("dateMessage", dateMessage);
                    }
                } else {
                    dateMessage = null;
                    session.setAttribute("dateMessage", dateMessage);
                }

                if (cardValid) {
                    // card validation successful
                    session.setAttribute("paymentType", "credit/debitcard");
                } else {
                    // card validation failed
                    session.setAttribute("paymentType", "cash");
                }

                if (cardMessage != null || dateMessage != null) {
                    // forward the request back to the checkout page
                    response.sendRedirect("Checkout?total=" + request.getParameter("subtotal"));
//                    RequestDispatcher dispatcher = request.getRequestDispatcher("Checkout");
//                    dispatcher.forward(request, response);
                } else {
                    try {
                        Orders order1 = storeOrder(customer);
                        storeOrderDetail(order1, customer, selectedPaymentMethod);
                        updateProduct(order1);
                        Query query = em.createNativeQuery("SELECT MAX(order_id) FROM orders");
                        int orderId = (Integer) query.getSingleResult();
                        request.setAttribute("orderId", orderId);
                        // Store the subtotal value in the session
                        session.setAttribute("subtotal", 0.0);
                        double newSubtotal;
                        newSubtotal = (Double) session.getAttribute("subtotal");

                        Customer customer1 = em.find(Customer.class, customer.getCustomerId());
                        TypedQuery<Cart> query1 = em.createQuery("SELECT c FROM Cart c WHERE c.customerId = :customerId", Cart.class);
                        query1.setParameter("customerId", customer1);
                        Cart cart = query1.getSingleResult();
                        List<CartItem> cartItems = cart.getCartItemList();

                        try {
                            utx.begin();
                        } catch (NotSupportedException ex) {
                            Logger.getLogger(EditAddress.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SystemException ex) {
                            Logger.getLogger(EditAddress.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        for (CartItem cartItem : cartItems) {
                            em.detach(cartItem);
                            cartItem = em.merge(cartItem);
                            em.remove(cartItem);
                        }

                        // Clear the cart items list
                        cart.setCartItemList(new ArrayList<CartItem>());
                        cart.setSubtotal(0.0);
                        em.merge(cart);

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

// Close the entity manager
                        em.close();

                    } catch (Exception ex) {
                        Logger.getLogger(ProcessCheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    request.setAttribute("paymentType", "credit/debit card");
                    request.setAttribute("cardType", cardType);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("paymentSuccess.jsp");
                    dispatcher.forward(request, response);
                }
            } else if ("Cash".equals(selectedPaymentMethod)) {
                try {
                    Orders order1 = storeOrder(customer);
                    storeOrderDetail(order1, customer, selectedPaymentMethod);
                    updateProduct(order1);
                    Query query = em.createNativeQuery("SELECT MAX(order_id) FROM orders");
                    int orderId = (Integer) query.getSingleResult();
                    request.setAttribute("orderId", orderId);
                    double newSubtotal;
                    newSubtotal = (Double) session.getAttribute("subtotal");

                    Customer customer1 = em.find(Customer.class, customer.getCustomerId());
                    TypedQuery<Cart> query1 = em.createQuery("SELECT c FROM Cart c WHERE c.customerId = :customerId", Cart.class);
                    query1.setParameter("customerId", customer1);
                    Cart cart = query1.getSingleResult();
                    List<CartItem> cartItems = cart.getCartItemList();

                    try {
                        utx.begin();
                    } catch (NotSupportedException ex) {
                        Logger.getLogger(EditAddress.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SystemException ex) {
                        Logger.getLogger(EditAddress.class.getName()).log(Level.SEVERE, null, ex);
                    }

//                    for (CartItem cartItem : cartItems) {
//                        Product prodId = cartItem.getProductId();
//                        
//                        Product product = em.find(Product.class, prodId);
//                        int quantity = cartItem.getQuantity();
//                        
//                        int newQuantity= product.getProductQuantity() - quantity;
//                        product.setProductQuantity(newQuantity);
//                        product.setProductSold(quantity);
//                        em.merge(product);
//                    }
                    for (CartItem cartItem : cartItems) {

                        em.detach(cartItem);
                        cartItem = em.merge(cartItem);
                        em.remove(cartItem);
                    }

                    // Clear the cart items list
                    cart.setCartItemList(new ArrayList<CartItem>());
                    cart.setSubtotal(0.0);
                    em.merge(cart); // save the changes to the cart entity

//                    Cart newCart = new Cart();
//                    newCart.setCustomerId(customer1);
//                    newCart.setSubtotal(0.0);
//                    em.persist(newCart);
//
//                    em.detach(cart);
//                    cart = em.merge(cart);
//                    em.remove(cart);
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

// Close the entity manager
                    em.close();
                } catch (Exception ex) {
                    Logger.getLogger(ProcessCheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("paymentType", "cash");
                session.setAttribute("subtotal", 0.0);
                RequestDispatcher dispatcher = request.getRequestDispatcher("paymentSuccess.jsp");
                dispatcher.forward(request, response);
            }

        }
    }

    // Return true if the card number is valid
    private static boolean cardValidation(String cardNumber) {

        // Check if credit card number is in a valid format using regex(13-16 numbers)(Master/Visa)
        //5425233430109903 04/26 123
        //4263982640269299 08/26 123
        if (cardNumber == null || !cardNumber.matches("^(4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14})$")) {
            return false;
        }

        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    private static boolean expiryDateValidation(String expiryDate) {
        if (expiryDate == null) {
            return false; // The expiry date is not valid
        }

        // Set the date format to match the format of the expiry date
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");

        // Parse the expiry date string to a Date object
        Date date;
        try {
            date = sdf.parse(expiryDate);
        } catch (ParseException e) {
            return false; // The expiry date is not in a valid format
        }

        // Get the current date
        Date currentDate = new Date();

        // Check if the expiry date is in the future
        if (date.after(currentDate)) {
            return true; // The expiry date is valid
        } else {
            return false; // The expiry date is expired or the same as the current date
        }
    }

    private Orders storeOrder(Customer custId) throws Exception {
        Orders newOrder = new Orders();

        newOrder.setCustomerId(custId);

        utx.begin();
        em.persist(newOrder);
        utx.commit();

        return newOrder;
    }

    private void storeOrderDetail(Orders orderId, Customer customer, String selectedPaymentMethod) throws Exception {
        OrderDetail newDetail = new OrderDetail();

        LocalDate today = LocalDate.now();
        LocalDate deliveryDate = today.plusDays(7);
        ZoneId zoneId = ZoneId.systemDefault();
        Date date = Date.from(deliveryDate.atStartOfDay(zoneId).toInstant());

        Customer customer1 = em.find(Customer.class, customer.getCustomerId());
        TypedQuery<Cart> query1 = em.createQuery("SELECT c FROM Cart c WHERE c.customerId = :customerId", Cart.class);
        query1.setParameter("customerId", customer1);
        Cart cart = query1.getSingleResult();
        List<CartItem> cartItems = cart.getCartItemList();
        Product product;

        utx.begin();

        for (CartItem cartItem : cartItems) {
            product = em.find(Product.class, cartItem.getProductId().getProductId());
            newDetail.setOrderId(orderId);
            newDetail.setDeliveryDate(date);
            newDetail.setOrderStatus("Preparing");
            newDetail.setProductId(cartItem.getProductId());
            
            // Calculate total quantity of products ordered for all sizes
            int totalQuantityOrdered = 0;
            for (CartItem ci : cartItems) {
                if (ci.getProductId() == cartItem.getProductId()) {
                    totalQuantityOrdered += ci.getQuantity();
                } else {
                    totalQuantityOrdered = ci.getQuantity();
                }
            }
            
            newDetail.setProductQuantity(totalQuantityOrdered);
            newDetail.setPaymentMethod(selectedPaymentMethod);

            em.persist(newDetail);
        }

        utx.commit();
    }

    private void updateProduct(Orders orderId) throws Exception {
        TypedQuery<OrderDetail> query = em.createQuery("SELECT o FROM OrderDetail o WHERE o.orderId = :orderId", OrderDetail.class);
        query.setParameter("orderId", orderId);
        List<OrderDetail> orderDetails = query.getResultList();
        Product product;

        utx.begin();

        for (OrderDetail orderDetail : orderDetails) {
            product = em.find(Product.class, orderDetail.getProductId().getProductId());
            int quantitySold = orderDetail.getProductQuantity();

            // Calculate total quantity of products ordered for all sizes
            int totalQuantityOrdered = 0;
            for (OrderDetail od : orderDetails) {
                if (od.getProductId() == orderDetail.getProductId()) {
                    totalQuantityOrdered += od.getProductQuantity();
                } else {
                    totalQuantityOrdered = od.getProductQuantity();
                }
            }

            // Update product quantity and quantity sold
            int quantityLeft = product.getProductQuantity() - totalQuantityOrdered;
            int newQuantitySold = product.getProductSold() + quantitySold;
            product.setProductQuantity(quantityLeft);
            product.setProductSold(newQuantitySold);

            em.persist(product);
        }

        utx.commit();

    }

}
