<%@page import="entity.Staff"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head>
       <title>Order Status</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
          <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
          <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
      
        <script>
            $(function () {
                $('#header').load("header.jsp");
            });
            $(function () {
                $('#footer').load("footer.html");
            });
        </script>
    </head>
    <body >
 <div id="header"></div>
 <%
 String staffid = request.getParameter("staffid");
   if (session.getAttribute("staff") == null) {
                response.sendRedirect("login.jsp");
            } else {
 %>
 <h2><i>Update Order Status</i></h2>
	
	<form method="post" action="UpdateOrderStatusServlet">
		<label for="orderId">Order ID:</label>
		<input type="number" id="orderId" name="orderId" required>
		<br>
                <input type="hidden" name="staffId" value="<%= staffid %>">
		<label for="status">Status:</label>
		<select id="status" name="status" required>
			<option value="">-- Select Status --</option>
			<option value="packaging">Packaging</option>
			<option value="shipping">Shipping</option>
			<option value="delivery">Delivery</option>
		</select>
		<br>
	
		<input type="submit" value="Update Status">
	</form>
	
	<c:if test="${not empty updateSuccess}">
            <p> ${updateSuccess} </p>
	</c:if>
    <a href="processDisplayOsStaff" style="color:darksalmon;"><i>Display order status list table</i></a>
 <div id="footer"></div>
 <% }%>
    </body>
</html>

