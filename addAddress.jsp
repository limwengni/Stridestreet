<%@page import="entity.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Shipping Address</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
        <link rel="stylesheet" href="assets\css\addressForm.css" />
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
            }
        %>

        <div id="header"></div>

        <div id="ParentContainer">
            <div id="AddressTxtContainer">
                <div id="AddressHeader">Add shipping address</div>
            </div>

            <div id="ChildContainer">

                <div id="ShippingContainer">
                    <div class="Address">
                        <!-- session.getAttribute("customerId") -->
                        <form action="AddAddress" method="POST">
                            <input type="hidden" name="action" value="add">

                            <%if (request.getAttribute("emptyMessage0") != null) {%>
                            <div style="color: red; padding-top: 10px; padding-left: 10px;"><%=request.getAttribute("emptyMessage0")%></div>
                            <%
                                }
                            %>
                            <%if (request.getAttribute("emptyMessage1") != null) {%>
                            <div style="color: red; padding-top: 10px; padding-left: 10px;"><%=request.getAttribute("emptyMessage1")%></div>
                            <%
                                }
                            %>
                            <%if (request.getAttribute("emptyMessage2") != null) {%>
                            <div style="color: red; padding-top: 10px; padding-left: 10px;"><%=request.getAttribute("emptyMessage2")%></div>
                            <%
                                }
                            %>
                            <%if (request.getAttribute("emptyMessage3") != null) {%>
                            <div style="color: red; padding-top: 10px; padding-left: 10px;"><%=request.getAttribute("emptyMessage3")%></div>
                            <%
                                }
                            %>
                            <%if (request.getAttribute("emptyMessage4") != null) {%>
                            <div style="color: red; padding-top: 10px; padding-left: 10px;"><%=request.getAttribute("emptyMessage4")%></div>
                            <%
                                }
                            %>
                            <%if (request.getAttribute("emptyMessage5") != null) {%>
                            <div style="color: red; padding-top: 10px; padding-left: 10px;"><%=request.getAttribute("emptyMessage5")%></div>
                            <%
                                }
                            %>

                            <%if (request.getAttribute("errorMessage") != null) {%>
                            <div style="color: red; padding-top: 10px; padding-left: 10px;"><%=request.getAttribute("errorMessage")%></div>
                            <%
                                }
                            %>

                            <div class="form-row">
                                <input type="text" name="name" class="ReceiverName"/>
                            </div>
                            <div class="form-row">
                                <input type="text" name="addressLine1" class="AddressLine1" maxlength="50"/>
                            </div>
                            <div class="form-row">
                                <input type="text" name="addressLine2" class="AddressLine2" maxlength="50"/>
                            </div>
                            <div class="form-row" style="display: inline-block;">
                                <input type="text" name="state" class="State"/> 
                                <input type="text" name="postcode" class="Postcode" maxlength="5"/>
                            </div>
                            <div class="form-row">
                                <input type="text" name="phone" class="Phone"/>
                            </div>

                            <div class="setDefault">
                                <label>
                                    <input type="checkbox" name="defaultAddress" id="defaultAddressCheckbox"> Set as default address
                                </label>
                            </div>

                            <div class="AddressBtn">
                                <button type="submit" id="addressBtn">done</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>

        <a href="AddressList">
            <button id="Back">back</button>
        </a>

        <div id="footer"></div>
    </body>
</html>
