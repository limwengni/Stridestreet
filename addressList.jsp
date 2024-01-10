<%@page import="entity.Customer"%>
<%@page import="entity.Address"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Select Shipping Address</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
        <link rel="stylesheet" href="assets\css\addressList.css" />
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
            if (session.getAttribute("customer") == null) {
                response.sendRedirect("login.jsp");
            } else {
        %>

        <div id="header"></div>

        <div id="ParentContainer">
            <div id="AddressTxtContainer">
                <div id="AddressHeader">my addresses</div>
            </div>

            <div id="ChildContainer">

                <div id="ShippingContainer">

                    <%if (request.getAttribute("error") != null) {%>
                    <div style="color: red; padding-bottom: 10px; padding-left: 10px; text-align: center;"><%=request.getAttribute("error")%></div>
                    <%
                        }
                    %>

                    <%if (request.getAttribute("error1") != null) {%>
                    <div style="color: red; padding-bottom: 10px; padding-left: 10px; text-align: center;"><%=request.getAttribute("error1")%></div>
                    <%
                        }
                    %>

                    <%
                        Customer customer = (Customer) request.getSession().getAttribute("customer");

                        //get default address
                        List<Address> defaultAddress = (List<Address>) request.getAttribute("defaultAddresses");
                        for (Address defaultAdd : defaultAddress) {
                    %>
                    <div class="Address">
                        <div style="flex: 1;">
                            <div class="ReceiverName"><%= defaultAdd.getReceiverName()%></div>
                            <div class="ReceiverAdd"><%= defaultAdd.getAddressLine1()%> <%= defaultAdd.getAddressLine2()%></div>
                            <div class="ReceiverNo"><%= defaultAdd.getAddressState()%></div>
                            <div class="ReceiverNo"><%= defaultAdd.getReceiverPhone()%></div>
                            <label for="default" style="border: 2px solid #FF3F57; color: #FF3F57; border-width: 3px;">
                                <span style="padding: 15px 10px; font-weight: bold; text-transform: uppercase; font-size: 18px;">Default</span>
                            </label>
                        </div>
                        <div style="display: flex; flex-direction: column;">
                            <a href="GetAddressInfoForEdit?id=<%= defaultAdd.getAddressId()%>" class="rightBtn">Edit</a>
                            <a href="DeleteAddress?id=<%= defaultAdd.getAddressId()%>" class="rightBtn">Delete</a>
                        </div>
                    </div>

                    <hr class="divider">
                    <%
                        }
                    %>

                    <%
                        //get not default address
                        List<Address> notDefaultAddresses = (List<Address>) request.getAttribute("notDefaultAddresses");
                        for (Address notDefaultAdd : notDefaultAddresses) {
                    %>
                    <div class="Address">
                        <div style="flex: 1;">
                            <div class="ReceiverName"><%= notDefaultAdd.getReceiverName()%></div>
                            <div class="ReceiverAdd"><%= notDefaultAdd.getAddressLine1()%> <%= notDefaultAdd.getAddressLine2()%></div>
                            <div class="ReceiverNo"><%= notDefaultAdd.getAddressState()%></div>
                            <div class="ReceiverNo"><%= notDefaultAdd.getReceiverPhone()%></div>
                        </div>
                        <div style="display: flex; flex-direction: column;">
                            <a href="GetAddressInfoForEdit?id=<%= notDefaultAdd.getAddressId()%>" class="rightBtn">Edit</a>
                            <a href="DeleteAddress?id=<%= notDefaultAdd.getAddressId()%>" class="rightBtn">Delete</a>
                        </div>
                    </div>

                    <hr class="divider">
                    <%
                        }
                    %>
                </div>
            </div>

            <div class="AddAddress">
                <a href="addAddress.jsp?id=<%=customer.getCustomerId()%>">
                    <button id="addAddress">add address</button>
                </a>
            </div>

        </div>

        <%
            if (session.getAttribute("subtotal") != null) {
                Double subtotal = (Double) session.getAttribute("subtotal");
                if (subtotal != 0 && session.getAttribute("subtotal") != null) {%>
        <a href="Checkout?total=<%=session.getAttribute("subtotal")%>"><button type="button" id="Back">back</button></a>
        <% }
            }%>

        <div id="footer"></div>

        <%}%>

    </body>
</html>
