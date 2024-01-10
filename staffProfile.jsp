<%-- 
    Document   : staffProfile
    Created on : May 2, 2023, 6:13:32 PM
    Author     : jmok4
--%>

<%@page import="entity.Staff"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Staff profile page</title>
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
            Staff staff = (Staff) session.getAttribute("staff");

            if (session.getAttribute("staff") == null) {
                response.sendRedirect("login.jsp");
            } else {
        %>

        <div class="staffProfileTitle-container">
            <h3>STAFF PROFILE</h3>
        </div>
        
        <div class="staffProfile-container row">
            <div class="col">
                <label for="name">NAME:</label>
                <input type="text" id="name" name="name" value="${staff.staffName}" readonly/><br><br>
                <label for="role">ROLE:</label>
                <input type="text" id="role" name="role" value="${staff.staffRole}" readonly/><br><br>
            </div>

            <div class="col">
                <label for="username">USERNAME:</label>
                <input type="text" id="username" name="username" value="${staff.staffUsername}" readonly/><br><br>
                <label for="password">PASSWORD:</label>
                <input type="text" id="password" name="password" value="${staff.staffPassword}" readonly/><br><br>
            </div>
        </div>

        <div class="staffProfileEditBtn">
            <form action="editStaffProfile.jsp">
                <button type="submit">EDIT</button>
            </form>
        </div>

        <%
            }
        %>

        <div id="footer"></div>
    </body>
</html>
