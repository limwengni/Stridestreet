<%@page import="java.util.List"%>
<%@page import="javax.persistence.TypedQuery"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.PersistenceContext"%>
<%@page import="entity.Address"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Shipping Address</title>
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
        <div id="header"></div>

        <div id="ParentContainer">
            <div id="AddressTxtContainer">
                <div id="AddressHeader">Edit shipping address</div>
            </div>

            <div id="ChildContainer">

                <div id="ShippingContainer">
                    <div class="Address">

                        <form action="EditAddress" method="POST">
                            <input type="hidden" name="action" value="edit">

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
                            
                            <%if (request.getAttribute("error1") != null) {%>
                            <div style="color: red; padding-top: 10px; padding-left: 10px;"><%=request.getAttribute("error1")%></div>
                            <%
                                }
                            %>

                            <%
                                List<Address> addresses = (List<Address>) request.getAttribute("addresses");

                                for (Address address : addresses) {
                            %>

                            <div class="form-row">
                                <input type="text" name="name" class="ReceiverName" value="<%=address.getReceiverName()%>"/>
                            </div>
                            <div class="form-row">
                                <input type="text" name="addressLine1" class="AddressLine1" maxlength="50" value="<%=address.getAddressLine1()%>"/>
                            </div>
                            <div class="form-row">
                                <input type="text" name="addressLine2" class="AddressLine2" maxlength="50" value="<%=address.getAddressLine2()%>"/>
                            </div>
                            <div class="form-row" style="display: inline-block;">
                                <input type="text" name="state" class="State" value="<%=address.getAddressState()%>"/> 
                                <input type="text" name="postcode" class="Postcode" maxlength="5" value="<%=address.getPostcode()%>"/>
                            </div>
                            <div class="form-row">
                                <input type="text" name="phone" class="Phone" value="<%=address.getReceiverPhone()%>"/>
                            </div>

                            <div class="setDefault">
                                <label>
                                    <input type="checkbox" name="defaultAddress" id="defaultAddressCheckbox"> Set as default address
                                </label>
                            </div>

                            <input type="hidden" name="addressId" value="<%=address.getAddressId()%>">

                            <%
                                }
                            %>

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
