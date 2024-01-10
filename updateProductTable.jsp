<%-- 
    Document   : updateProductTable
    Created on : May 3, 2023, 11:45:10 AM
    Author     : jmok4
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>update product table page</title>
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
        <%
            session = request.getSession();

            if (session.getAttribute("roleType") == null) {
                response.sendRedirect("login.jsp");
            } else {
        %>
        <div id="header"></div>

        <div class="profileTitle-container">
            <h3>PRODUCT DETAILS</h3>
        </div>

        <form action="processConfirmationUpdateProductTable" method="post">

            <div class="updateCustomerTable-container row">

                <div class="col">

                    <label for="id">PRODUCT ID:</label>
                    <input type="text" id="id" name="id" value="${product.productId}" readonly/><br><br>

                    <label for="name">PRODUCT NAME:</label>
                    <input type="text" id="name" name="name" value="${product.productName}" required/><br><br>

                    <label for="description">PRODUCT DESCRIPTION:</label>
                    <input type="text" id="description" name="description" value="${product.productDesc}" required/><br><br>

                    <label for="sku">PRODUCT SKU:</label>
                    <input type="text" id="sku" name="sku" value="${product.sku}" required/><br><br>

                    <label for="category">PRODUCT CATEGORY:</label>
                    <input type="text" id="category" name="category" value="${product.category}" required/><br><br>

                    <label for="size">PRODUCT SIZE:</label>
                    <input type="text" id="size" name="size" value="${product.productSize}" required/><br><br>

                    <label for="quantity">PRODUCT QUANTITY:</label>
                    <input type="text" id="quantity" name="quantity" value="${product.productQuantity}" required/><br><br>
                </div>

                <div class="col">

                    <label for="price">PRODUCT PRICE:</label>
                    <input type="text" id="price" name="price" value="${product.productPrice}" required/><br><br>

                    <label for="img1">PRODUCT IMAGE1:</label>
                    <img src="${product.productImage1}" height="100" width="100"/>
                    <input type="file" id="img1" name="img1" accept="image/*"><br><br>

                    <label for="img2">PRODUCT IMAGE2:</label>
                    <img src="${product.productImage2}" height="100" width="100"/>
                    <input type="file" id="img2" name="img2" accept="image/*"><br><br>

                    <label for="img3">PRODUCT IMAGE3:</label>
                    <img src="${product.productImage3}" height="100" width="100"/>
                    <input type="file" id="img3" name="img3" accept="image/*"><br><br>

                    <label for="img4">PRODUCT IMAGE4:</label>
                    <img src="${product.productImage4}" height="100" width="100"/>
                    <input type="file" id="img4" name="img4" accept="image/*"><br><br>

                    <label for="img5">PRODUCT IMAGE5:</label>
                    <img src="${product.productImage5}" height="100" width="100"/>
                    <input type="file" id="img5" name="img5" accept="image/*"> <br><br>

                    <label for="img6">PRODUCT IMAGE6:</label>
                    <img src="${product.productImage6}" height="100" width="100"/>
                    <input type="file" id="img6" name="img6" accept="image/*"><br><br>
                </div>
            </div>

            <div class="updateCustomerTableBtn">
                <button type="submit">SAVE</button>
            </div>

        </form>

        <%
            }
        %>


        <div id="footer"></div>
    </body>
</html>
