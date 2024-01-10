<%-- 
    Document   : addCustomer
    Created on : May 6, 2023, 9:12:00 PM
    Author     : jmok4
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>add customer page</title>
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
        
        <div class="addCustomerTittle">
        <h3>ADD CUSTOMER</h3>
        </div>
        
        <form action="processAddCustomer" method="post">
            
        <div class="addCustomer-container">
        <label for="name">NAME:</label>
        <input type="text" id="name" name="name"  required/><br><br>
        <label for="phone">CONTACT NUMBER:</label>
        <input type="text" id="phone" name="phone"  required/><br><br>
        <label for="email">EMAIL ADDRESS:</label>
        <input type="text" id="email" name="email" required/><br><br>
        <label for="address">ADDRESS:</label>
        <input type="text" id="address" name="address" required/><br><br>
        <label for="username">USERNAME:</label>
        <input type="text" id="username" name="username" required/><br><br>
        <label for="password">PASSWORD:</label>
        <input type="text" id="password" name="password" required/><br><br>
        </div>
        
        <div class="addCustomerBtn">
        <button type="submit">ADD</button>
        </div>
            
        </form>
        
        <%}%>
        <div id="footer"></div>
    </body>
</html>
