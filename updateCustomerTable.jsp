<%-- 
    Document   : updateCustomerTable
    Created on : May 2, 2023, 9:19:08 PM
    Author     : jmok4
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>update customer profile page</title>
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
            
            if (session.getAttribute("roleType") == null) {
                response.sendRedirect("login.jsp");
            } else {
        %>
        
        <% List<String> errorMessages = (List<String>) request.getAttribute("errorMessages"); %>

        <% if (errorMessages != null && !errorMessages.isEmpty()) { %>
            <div class="alert alert-danger" role="alert">
                <% for (String errorMessage : errorMessages) { %>
                    <p><%= errorMessage %></p>
                <% } %>
            </div>
        <% } %>
        
        <div class="updateCustomerTableTittle">
            <h3>CUSTOMER DETAILS</h3>
        </div>
        <form action="processConfirmationUpdateCustomer" method="post">
            
            <div class="updateCustomerTable-container row">
                
                <div class="col">
                    <label for="name">NAME:</label>
                    <input type="text" id="name" name="name" value="${customer.customerName}" required/><br><br>
                    <label for="phone">CONTACT NUMBER:</label>
                    <input type="text" id="phone" name="phone" value="${customer.customerPhone}" required/><br><br>
                    <label for="email">EMAIL ADDRESS:</label>
                    <input type="text" id="email" name="email" value="${customer.customerEmail}" required/><br><br>
                </div>
                
                <div class="col">
                    <label for="username">USERNAME:</label>
                    <input type="text" id="username" name="username" value="${customer.customerUsername}" required/><br><br>
                    <label for="password">PASSWORD:</label>
                    <input type="text" id="password" name="password" value="${customer.customerPassword}" required/><br><br>
                    <label for="address">ADDRESS:</label>
                    <input type="text" id="address" name="address" value="${customer.customerAddress}" required/><br><br>
                </div>
                    
            </div>
            
            <div class="updateCustomerTableBtn">
                <button type="submit">SAVE</button>
            </div>
        </form>
        <%}%>
        <div id="footer"></div>
    </body>
</html>
