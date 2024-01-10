<%-- 
    Document   : order-statusC
    Created on : May 4, 2023, 6:32:09 PM
    Author     : User
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script>
            $(function () {
                $('#header').load("header.jsp");
            });
            $(function () {
                $('#footer').load("footer.jsp");
            });
        </script>
    </head>
    <body >
 <div id="header"></div>
 <%  String hold =null;
   int oid = 0;
 if (request.getParameter("transactionNumber") != null){
      hold = request.getParameter("transactionNumber");
 oid = Integer.parseInt(hold);
     }
     %>
     <div style="align-content: center;">
     <h2><i>Check Order Status</i></h2>

	
     <form method="GET" action="orderstatus">
		<label for="orderId">Order ID: <%=oid %>${orderId} </label>
                <br><br>
                <input type="hidden" name="order-id1" value="<%=oid %>">
		<input type="submit" value="Check Status">
	</form>
	
	<c:if test="${not empty orderStatus}">
		<h3>Status for Order ID: ${orderId}</h3>
		<p class="badge bg-danger text-wrap" style="width: 10rem; height:2rem; ">${orderStatus}</p>
                <h3>${deliveryDate}</h3>
	</c:if>
        <c:if test="${empty orderStatus}">
    <p>${error}</p>
</c:if>
     </div>
 <div id="footer"></div>
    </body>
</html>

