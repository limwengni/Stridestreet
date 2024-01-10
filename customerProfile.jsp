<%-- 
    Document   : customerProfile
    Created on : Apr 23, 2023, 11:55:22 PM
    Author     : xiayuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>customer profile page</title>
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
    <header>
        <div id="header"></div>
    </header>
    <body>

        <%
            session = request.getSession();

            if (session.getAttribute("customer") == null) {
                response.sendRedirect("login.jsp");
            } else {
        %>

        <div class="profileTitle-container">
            <h3>PROFILE</h3>
        </div>

        <div class="displayProfile-container row">

            <div class="col">
                <label for="name">NAME:</label>
                <input type="text" id="name" name="name" value="${customer.customerName}" readonly/><br><br>
                <label for="phone">CONTACT NUMBER:</label>
                <input type="text" id="phone" name="phone" value="${customer.customerPhone}" readonly/><br><br>
                <label for="email">EMAIL ADDRESS:</label>
                <input type="text" id="email" name="email" value="${customer.customerEmail}" readonly/><br><br>
                <label for="address">ADDRESS:</label>
                <textarea style="width: 200px; height:50px; border: none; font-weight: bold;" readonly>${customer.customerAddress}</textarea><br><br>
            </div>

            <div class="col">
                <label for="username">USERNAME:</label>
                <input type="text" id="username" name="username" value="${customer.customerUsername}" readonly/><br><br>
                <label for="password">PASSWORD:</label>
                <input type="password" id="password" name="password" value="${customer.customerPassword}" readonly/><br><br>
            </div>

        </div>

        <div class="customerProfileEditBtn">
            <form action="editCustomerProfile.jsp">
                <button type="submit">EDIT</button>
            </form>
        </div>


<!--        <div class="backBtn">
            <button type="button" onclick="history.back()">BACK</button>
        </div>-->

        <%}%>

    </body>
    <footer>
        <div id="footer"></div>
    </footer>
</html>

