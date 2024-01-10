<%-- 
    Document   : displayOsStaff
    Created on : May 7, 2023, 4:41:51 PM
    Author     : User
--%>

<%@page import="entity.Staff"%>
<%@page import="entity.OrderStatusLog"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> <head>
       <title>Order Status</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
          <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
          <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
       <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
   
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
        <% 
            if (session.getAttribute("staff") == null) {
                response.sendRedirect("login.jsp");
            } else {
        %>
           <div id="header"></div>
        <table class="table" >
            
            <tr class="bg-danger">
                <th>Order ID</th>
                <th>Order Status</th>
                 <th>Date Modified</th>
                <th>Handled By Staff ID</th>
        
                                <th></th>
            </tr>
        <%
            
            session = request.getSession();
            Staff staff = (Staff) session.getAttribute("staff");
          
          List<OrderStatusLog> osList= (List<OrderStatusLog>) session.getAttribute("osList");
          for( OrderStatusLog os: osList){ 
    
        %>
                 <tr>
                   
                     <td  class="table-danger"><%= os.getOrderId().getOrderId() %></td>
                        <td class="table-danger"><%= os.getOrderStatus() %></td>
                         <td class="table-danger"><%= os.getStaffId().getStaffId() %></td>
                        <td class="table-danger"><%= os.getDateModified() %></td>
                         <td class="table-danger" style="color:white;text-decoration:none;"> <a href="updateOS.jsp?staffid=<%=staff.getStaffId() %>">Update Status</a></td>
                           <tr>
                        <%    }
        %>
        </table>
     
              <%    }
        %>
     
            <br>
             <div id="footer"></div>
    </body>

</html>
