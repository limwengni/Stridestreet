<%-- 
    Document   : customerProfile
    Created on : Apr 23, 2023, 11:55:22 PM
    Author     : xiayuan
--%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Register Staff page</title>
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
        
    <div class="registerStaffTitle-container">
        <h3>REGISTER STAFF</h3>
    </div>
    
    <form action="processRegisterStaff" method="post">
        
    <div class="registerStaff-container row">
            
        <div class="col">
            <label for="name">NAME:</label>
            <input type="text" id="name" name="name" required/><br><br>
            <label for="role">ROLE:</label>
            <input type="text" id="role" name="role" required/><br><br>
        </div>
                
        <div class="col">
            <label for="username">USERNAME:</label>
            <input type="text" id="username" name="username" required/><br><br>
            <label for="password">PASSWORD:</label>
            <input type="text" id="password" name="password" required/><br><br>
        </div>
                
        </div>
                
        <div class="registerStaffBtn">
                <button type="submit">REGISTER</button>
        </div>
        
    </form>
    <%}%>
    <div id="footer"></div>
                 
    </body>

</html>

