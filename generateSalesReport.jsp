<%-- 
    Document   : generateSalesReport
    Created on : May 3, 2023, 7:51:33 PM
    Author     : xiayuan
--%>

<%@page import="entity.Product"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>generate sales report page</title>
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
            
            if (session.getAttribute("Manager") == null) {
                response.sendRedirect("login.jsp");
            } else {
    %>
        
    <% List<Product> top5 = (List<Product>) session.getAttribute("top5"); %>
    <% List<Product> bottom5 = (List<Product>) session.getAttribute("bottom5"); %>
    
    <div class="generateSalesReportTittleHeading-container">
    <h3>SALES REPORT</h3>
    </div>
    
    <div class="generateSalesReportTittle-container">
    <h5>TOP 5 SOLD PRODUCT</h5>
    </div>
    
    <div class="generateSalesReport-container">
    <table class="table table-bordered">
    <thead>
        <tr>
        <th>PRODUCT ID</th>
        <th>PRODUCT NAME</th>
        <th>PRODUCT SOLD</th>
        </tr>
    </thead>
    <tbody>
        <% for (Product product : top5) { %>
        <tr>
            <td><%= product.getProductId() %></td>
            <td><%= product.getProductName() %></td>
            <td><%= product.getProductSold() %></td>
        </tr>
        <% } %>
    </tbody>
    </table>
    </div>
        
    
    <div class="generateSalesReportTittle-container">
    <h5>LEAST 5 SOLD PRODUCT</h5>
    </div>
    <div class="generateSalesReport-container">
    <table class="table table-bordered">
    <thead>
        <tr>
        <th>PRODUCT ID</th>
        <th>PRODUCT NAME</th>
        <th>PRODUCT SOLD</th>
        </tr>
    </thead>
    <tbody>
        <% for (Product productBottom5 : bottom5) { %>
        <tr>
            <td><%= productBottom5.getProductId() %></td>
            <td><%= productBottom5.getProductName() %></td>
            <td><%= productBottom5.getProductSold() %></td>
        </tr>
        <% } %>
    </tbody>
    </table> 
    </div>
    <div class="backBtn">
            <button type="button" onclick="history.back()">BACK</button>
    </div>
    <%}%>
    <div id="footer"></div>
    </body>
</html>
