<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Cart"%>
<%@page import="java.util.List"%>
<%@page import="entity.CartItem"%>
<%@page import="entity.Customer"%>
<%@page import="javax.persistence.PersistenceContext"%>
<%@page import="javax.persistence.EntityManager"%>

<!DOCTYPE html>
<html lang="en">

    <head>


        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
        <!-- jQuery -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick-theme.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.css" />

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.1/mdb.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />

        <script>
            $(function () {
                $('#header').load("header.jsp");
            });
            $(function () {
                $('#footer').load("footer.jsp");
            });
        </script>
        <!-- Link CSS file-->
        <link rel="stylesheet" href="shoe.css">

        <title>Cart</title>
    </head>

    <div id="header"></div>

    <div class="container py-4">
        <h2 class="py-3"><i>CART</i></h2>
        <div class="text-left">
            <div class="row cart">


                <%! @PersistenceContext
                    EntityManager entityManager;
                    EntityManagerFactory emf = Persistence.createEntityManagerFactory("StrideStreetPU");
                %>
                <%
                    entityManager = emf.createEntityManager();
                    DecimalFormat decimalFormat = new DecimalFormat("0.00");

                    Customer customer1 = new Customer();
                    Customer cust = (Customer) request.getSession().getAttribute("customer");
                    customer1.setCustomerId(cust.getCustomerId());
                    Customer customer2 = entityManager.find(Customer.class, customer1.getCustomerId());

                    List<Cart> carts = customer2.getCartList();
                    List<CartItem> cartItems = new ArrayList<CartItem>();
                    if (carts != null && !carts.isEmpty()) {
                        for (Cart cart : carts) {
                            List<CartItem> items = entityManager.createQuery("SELECT c FROM CartItem c WHERE c.cartId = :cartId", CartItem.class)
                                    .setParameter("cartId", cart)
                                    .getResultList();
                            cartItems.addAll(items);
                        }
                    }

                    request.setAttribute("cartItems", cartItems);
                %>
                <% if (cartItems == null || cartItems.isEmpty()) { %>
                <p>Your cart is empty.</p>
                <% } else { %>
                <% for (CartItem cartItem : cartItems) { %>
                <%double price = cartItem.getProductId().getProductPrice();

                    String prices = decimalFormat.format(price);%>
                <div class="col-4" id="cart-img">
                    <img class="img-thumbnail" src="kyrie4.jpg" alt="">
                </div>
                <div class="col-8" id="cart-details">
                    <div class="list d-flex justify-content-between">
                        <%= "<h3>" + cartItem.getProductId().getProductName() + "</h3>"%>
                        <%= "<h3>RM" + prices + "</h3>"%>
                    </div>
                    <%= "<h4>" + cartItem.getProductId().getCategory() + "</h4>"%>
                    <div class="row">
                        <h4 class="col dropdown">Size :&nbsp;<%= cartItem.getSize()%></h4>

                        <h4 class="col dropdown">Quantity :&nbsp;<%=cartItem.getQuantity()%></h4>
                        <form action="deleteItemProcess" method="post"><button style="background-color: #ff0000;
                                                                               border: none;
                                                                               color: #fff;
                                                                               padding: 10px 20px;
                                                                               text-align: center;
                                                                               text-decoration: none;
                                                                               display: inline-block;
                                                                               font-size: 16px;
                                                                               border-radius: 5px;" type="submit" name="cartItemId" value="<%= cartItem.getCartItemId()%>">Remove</button></form>
                    </div>
                    <hr class="my-5">
                </div>

                <% } %>
                <% } %>

            </div>


        </div>
        <hr class="my-5">

    </div>

    <hr class="my-5">

</div>
<div class="subtotal">
    <div class="d-flex justify-content-between">
        <h3 class="text-pink"><i>SUBTOTAL</i> </h3>
        <%
            double subtotal = 0.0;
            if (customer2.getCartList() != null) {
                for (Cart cart : customer2.getCartList()) {

                    subtotal += cart.getSubtotal();

                }
            }
            String total = decimalFormat.format(subtotal);
            request.setAttribute("total", total);%>
        <h3>RM<%= request.getAttribute("total")%></h3>
    </div>
    <p>*shipping charges,taxes and discount codes are calculated at the time of accounting.</p>
</div>




</div>

</div>
<div id="buyNowDiv">
    <%
        if (subtotal == 0) {
    %>
    <button id="butNowBtn" style="background-color: #4f4f4f;" disabled><b>BUY NOW</b></button>
    <%
    } else {
    %>
    <a href="Checkout?total=<%=total%>" id="butNowBtn"><b>BUY NOW</b></a>
    <%
        }
    %>
</div>
<!-- footer -->
<div id="footer"></div>




<!-- jQuery -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.js"></script>

<!-- Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" defer></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.1/mdb.min.js"></script>

<!-- vue js script -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.7.14/vue.js" defer></script>

<!-- link JS file -->
<script src="assets\js\index.js" defer></script>
</body>

</html>