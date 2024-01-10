<%-- 
    Document   : displayProductTable
    Created on : May 3, 2023, 11:21:15 AM
    Author     : jmok4
--%>

<%@page import="entity.Staff"%>
<%@page import="java.util.List"%>
<%@page import="entity.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>display product page</title>
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

            if (session.getAttribute("roleType") == null && session.getAttribute("staff") == null) {
                response.sendRedirect("login.jsp");
            } else {
        %>

        <%
            List<Product> productList = (List) session.getAttribute("productList");
        %>

        <div class="displayStaffTitle-container">
            <h3>PRODUCT</h3>
        </div>

        <div class="displayStaffTable-container">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>NAME</th>
                        <th>CATEGORY</th>
                        <th>QUANTITY</th>
                        <th>PRICE</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Product product : productList) {%>
                    <tr>
                        <td><%= product.getProductId()%></td>
                        <td><%= product.getProductName()%></td>
                        <td><%= product.getCategory()%></td>
                        <td><%= product.getProductQuantity()%></td>
                        <td><%= product.getProductPrice()%></td>
                        <%
                            session = request.getSession();
                            Staff staff = (Staff) session.getAttribute("staff");
                            Object manager = session.getAttribute("roleType");

                            if (manager != null && staff == null) {
                        %>

                        <td><form action="processUpdateProductTable" method="post">
                                <input type="hidden" name="productId" value="<%= product.getProductId()%>">
                                <div class="displayStaff-updateBtn">
                                    <button type="submit">Update</button>
                                </div> 
                            </form>
                        </td>
                        <td>
                            <div class="displayStaff-deleteBtn">
                                <button><a href="processDeleteProductTable?id=<%= product.getProductId()%>" class="rightBtn">Delete</a></button>
                            </div> 
                        </td>

                        <%
            } %>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>

        <div class="displayStaff-backBtn">
            <a href="addProduct.jsp"><button type="button">Add</button></a>
        </div>
        <%}%>
        <div id="footer"></div>

    </body>
</html>
