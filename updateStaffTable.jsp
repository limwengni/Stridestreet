<%-- 
    Document   : updateStaffTable
    Created on : May 1, 2023, 2:57:30 PM
    Author     : jmok4
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>update staff page</title>
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
        <div class="staffProfileTitle-container">
        <h3>Update Staff</h3>
        </div>
        <form action="processConfirmationUpdateStaff" method="post">
        <div class="updateStaffProfile-container row">
        <div class="col">
        <label for="name">NAME:</label>
        <input type="text" id="name" name="name" value="${staff.staffName}" /><br><br>
        <label for="role">ROLE:</label>
        <input type="text" id="role" name="role" value="${staff.staffRole}" /><br><br>
        </div>
        <div class="col">
        <label for="username">USERNAME:</label>
        <input type="text" id="username" name="username" value="${staff.staffUsername}" /><br><br>
        <label for="password">PASSWORD:</label>
        <input type="text" id="password" name="password" value="${staff.staffPassword}" /><br><br>
        </div>
        </div>
        <div class="updateStaffProfileEditBtn">
        <button type="submit">SAVE</button>
        </div>
        </form>
        <%}%>
        <div id="footer"></div>
    </body>
</html>
