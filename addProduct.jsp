<%-- 
    Document   : addProduct
    Created on : May 3, 2023, 9:59:10 PM
    Author     : jmok4
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>add product page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Nunito+Sans:400,400i,700,900&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="assets/css/gui2.css"/>
        <script>
            $(function () {
                $('#header').load("header.jsp");
            });
            $(function () {
                $('#footer').load("footer.jsp");
            });
        </script>
    </head>
    <body>
        
        <div id="header"></div>
        
       <%
            session = request.getSession();
            
            if (session.getAttribute("roleType") == null && session.getAttribute("staff") == null ) {
                response.sendRedirect("login.jsp");
            } else {
        %>
        
        <div class="addProductTittle">
        <h3>ADD PRODUCT</h3>
        </div>
        
        <div class="addProduct-container">
        <form action="processAddProduct" method="post" >
            
        <label for="name">PRODUCT NAME:</label>
        <input type="text" id="name" name="name"  /><br><br>
        
        <label for="description">PRODUCT DESCRIPTION:</label>
        <input type="text" id="description" name="description"  /><br><br>
        
        <label for="sku">PRODUCT SKU:</label>
        <input type="text" id="sku" name="sku" /><br><br>
        
        <label for="category">PRODUCT CATEGORY:</label>
        <input type="text" id="category" name="category" /><br><br>
        
        <label for="size">PRODUCT SIZE:</label>
        <input type="text" id="size" name="size" /><br><br>
        
        <label for="quantity">PRODUCT QUANTITY:</label>
        <input type="text" id="quantity" name="quantity" /><br><br>
        
        <label for="price">PRODUCT PRICE:</label>
        <input type="text" id="price" name="price" /><br><br>
        
        <label for="img1">PRODUCT IMAGE1:</label>
        <input type="file" id="img1" name="img1" accept="image/*"><br><br>
        
        <label for="img2">PRODUCT IMAGE2:</label>
        <input type="file" id="img2" name="img2" accept="image/*"><br><br>
        
        <label for="img3">PRODUCT IMAGE3:</label>
        <input type="file" id="img3" name="img3" accept="image/*"><br><br>
        
        <label for="img4">PRODUCT IMAGE4:</label>
        <input type="file" id="img4" name="img4" accept="image/*"><br><br>
                                                                  
        <label for="img5">PRODUCT IMAGE5:</label>
        <input type="file" id="img5" name="img5" accept="image/*"> <br><br>
        
        <label for="img6">PRODUCT IMAGE6:</label>
        <input type="file" id="img6" name="img6" accept="image/*"><br><br>
        
        </div>
        
        <div class="addProductBtn">
        <button type="submit">ADD</button>
        </div>
        </form>
        <%}%>
        <div id="footer"></div>
    </body>
</html>
