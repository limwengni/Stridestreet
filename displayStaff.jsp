<%-- 
    Document   : displayStaff
    Created on : Apr 23, 2023, 11:59:31 PM
    Author     : xiayuan
--%>

<%@page import="entity.Staff"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>display staff page</title>
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

    <body>
        <div id="header"></div>

        <%
            List<Staff> staffList = (List) session.getAttribute("staffList");

            session = request.getSession();

            if (session.getAttribute("roleType") == null) {
                response.sendRedirect("login.jsp");
            } else {
        %>

        <div class="displayStaffTitle-container">
            <h3>EMPLOYEE</h3>
        </div>

        <div class="displayStaffTable-container">

            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>NAME</th>
                        <th>ROLE</th>
                        <th>USERNAME</th>
                        <th>PASSWORD</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Staff staff : staffList) {%>
                    <tr>
                        <td><%= staff.getStaffId()%></td>
                        <td><%= staff.getStaffName()%></td>
                        <td><%= staff.getStaffRole()%></td>
                        <td><%= staff.getStaffUsername()%></td>
                        <td><%= staff.getStaffPassword()%></td>
                        <td><form action="processUpdateStaffTable" method="post">
                                <input type="hidden" name="staffId" value="<%= staff.getStaffId()%>">
                                <div class="displayStaff-updateBtn">
                                    <button type="submit">Update</button>
                                </div<
                            </form>
                        </td>
                        <td>
                            <div class="displayStaff-deleteBtn">
                                <button><a href="processDeleteStaffTable?id=<%= staff.getStaffId()%>" class="rightBtn">Delete</a></button>
                            </div>
                        </td>
                    </tr>
                    <% }%>
                    <% }%>
                </tbody>
            </table>

        </div>

        <div class="displayStaff-backBtn">
            <a href="registerStaff.jsp"><button type="button">Add</button></a>
        </div>

        <div id="footer"></div>
    </body>
</html>
