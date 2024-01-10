<%-- 
    Document   : editCustomerProfile
    Created on : Apr 23, 2023, 11:57:09 PM
    Author     : xiayuan
--%>

<%@page import="entity.Customer"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>edit customer profile page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Nunito+Sans:400,400i,700,900&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="assets/css/gui.css"/>
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
    <header>
        <div id="header"></div>
    </header>
    <%
            session = request.getSession();
            Customer customer = (Customer) session.getAttribute("customer");
            
            if (session.getAttribute("customer") == null) {
                response.sendRedirect("login.jsp");
            } else {
        %>
    <body>

        <% List<String> errorMessages = (List<String>) request.getAttribute("errorMessages"); %>

        <% if (errorMessages != null && !errorMessages.isEmpty()) { %>
        <div class="alert alert-danger" role="alert">
            <% for (String errorMessage : errorMessages) {%>
            <p><%= errorMessage%></p>
            <% } %>
        </div>
        <% }%>

        <div class="profileTitle-container">
            <h3>PROFILE</h3>
        </div>

        <form action="processEditCustomerProfile" method="post">

            <div class="editProfile-container row">

                <div class="col">
                    <div style="padding-bottom: 5px;">
                        <label for="name">NAME:</label>
                        <input type="text" id="name" name="name" value="<%= customer.getCustomerName() %>"/>
                    </div>

                    <div style="padding-bottom: 5px;">
                        <label for="phone">CONTACT NUMBER:</label>
                        <input type="text" id="phone" name="phone" value="<%= customer.getCustomerPhone() %>"/>
                    </div>

                    <div style="padding-bottom: 5px;">
                        <label for="email">EMAIL ADDRESS:</label>
                        <input type="text" id="email" name="email" value="<%= customer.getCustomerEmail() %>"/>
                    </div>

                    <label for="address" style="display: inline-block; vertical-align: top;">ADDRESS:</label>
                    <input type="text" id="address" name="address" value="<%= customer.getCustomerAddress() %>"/>
                </div>

                <div class="col">
                    <div style="padding-bottom: 5px;">
                        <label for="username">USERNAME:</label>
                        <input type="text" id="username" name="username" value="<%= customer.getCustomerUsername() %>"/>
                    </div>

                    <div style="padding-bottom: 5px;">
                        <label for="password">PASSWORD:</label>
                        <input type="text" id="password" name="password" value="<%= customer.getCustomerPassword() %>"/>
                    </div>
                </div>

            </div>
                    
            <div class="editProfileBtn">
                <button type="button"><a href="customerProfile.jsp" style="color:white;">CANCEL</a></button>
                <button type="submit">SAVE</button>
            </div>
        </form>

        <div class="backBtn">
            <button><a href="customerProfile.jsp"  style="color:white;">BACK</a></button>
        </div>

    </body>
    <%}%>
    <footer>
        <div id="footer"></div>
    </footer>
</html>
