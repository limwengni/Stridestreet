<%-- 
    Document   : displayCustomer
    Created on : May 2, 2023, 7:51:12 PM
    Author     : jmok4
--%>

<%@page import="entity.Customer"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>display customer page</title>
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
            List<Customer> customerList = (List) session.getAttribute("customerList");

            session = request.getSession();

            if (session.getAttribute("roleType") == null) {
                response.sendRedirect("login.jsp");
            } else {
        %>

        <div class="displayStaffTitle-container">
            <h3>CUSTOMER</h3>
        </div>

        <div class="displayStaffTable-container">

            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>NAME</th>
                        <th>EMAIL</th>
                        <th>USERNAME</th>
                        <th>PASSWORD</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Customer customer : customerList) {%>
                    <tr>
                        <td><%= customer.getCustomerId()%></td>
                        <td><%= customer.getCustomerName()%></td>
                        <td><%= customer.getCustomerEmail()%></td>
                        <td><%= customer.getCustomerUsername()%></td>
                        <td><%= customer.getCustomerPassword()%></td>
                        <td>
                            <form action="processUpdateCustomerTable" method="POST">
                                <input type="hidden" name="customerId" value="<%= customer.getCustomerId()%>">
                                <div class="displayStaff-updateBtn">
                                    <button type="submit">Update</button>
                                </div>
                            </form>
                        </td>
                        <td>
                            <div class="displayStaff-deleteBtn">
                                <button><a href="processDeleteCustomerTable?id=<%= customer.getCustomerId()%>" class="rightBtn">Delete</a></button>
                            </div> 
                        </td>

                    </tr>
                    <% }%>
                </tbody>
            </table>

        </div>

        <div class="displayStaff-backBtn">
            <a href="addCustomer.jsp"><button type="button">Add</button></a>
        </div>

        <%
            }
        %>

        <div id="footer"></div>
    </body>
</html>
