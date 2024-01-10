<%--
Document : signUp
Created on : May 1, 2023, 11:20:33 PM
Author : jmok4
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sign Up Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Nunito+Sans:400,400i,700,900&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="assets/css/.css"/>
        <link rel="stylesheet" href="assets/css/gui2.css"/>
        <script>
            $(function () {
                $('#header').load("header.jsp");
            });
            $(function () {
                $('#footer').load("footer.jsp");
            });
        </script>
        <script>
            function confirmSignUp() {
                var confirmed = confirm("Are you sure the information you entered is correct?");
                if (confirmed) {
        // Submit the form
                    document.getElementById("signup-form").submit();
                }
            }
            $(function () {
                $('#signup-btn').click(function (event) {
                    event.preventDefault();
                    confirmSignUp();
                });
            });
        </script>
    </head>
    <body>
        <div id="header"></div>

        <% List<String> errorMessages = (List<String>) request.getAttribute("errorMessages"); %>

        <% if (errorMessages != null && !errorMessages.isEmpty()) { %>
        <div class="alert alert-danger" role="alert">
            <% for (String errorMessage : errorMessages) {%>
            <p><%= errorMessage%></p>
            <% } %>
        </div>
        <% }%>

        <div class="signUpTitle-container">
            <h3>SIGN UP</h3>
        </div>

        <form id="signup-form" action="processSignUp" method="post">

            <div class="signUp-container row">

                <div class="col">
                    <label for="name">NAME:</label>
                    <input type="text" id="name" name="name" required/><br><br>
                    <label for="phone">CONTACT NUMBER:</label>
                    <input type="text" id="phone" name="phone" required/><br><br>
                    <label for="email">EMAIL ADDRESS:</label>
                    <input type="text" id="email" name="email" required/><br><br>
                    <label for="address">ADDRESS:</label>
                    <input type="text" id="address" name="address" required/><br><br>
                </div>
                <div class="col">
                    <label for="username">USERNAME:</label>
                    <input type="text" id="username" name="username" required/><br><br>
                    <label for="password">PASSWORD:</label>
                    <input type="text" id="password" name="password" required/><br><br>
                    <label for="cpassword">CONFIRM PASSWORD:</label>
                    <input type="text" id="cpassword" name="cpassword" required/><br><br>
                </div>

            </div>

            <div class="signUpBtn">
                <button id="signup-btn" type="submit">SIGN UP</button>
            </div>

        </form>

        <div id="footer"></div>
    </body>
</html>