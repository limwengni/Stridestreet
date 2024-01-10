<%@page import="java.util.List"%>
<%@page import="entity.Address"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Checkout</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
        <link rel="stylesheet" href="assets\css\checkout.css" />
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

            <div id="CheckOutTxtContainer">
                <div id="CheckOutTxt">checkout</div>
            </div>

            <div id="ChildContainer">
                <div id="ShippingContainer">
                    <div class="HeaderTxt">shipping address</div>
                    <div id="AddressTxt">
                        <%
                            //get default address
                            List<Address> defaultAddress = (List<Address>) request.getAttribute("defaultAddress1");
                            for (Address defaultAdd : defaultAddress) {
                        %>
                        <div id="ReceiverName"><%= defaultAdd.getReceiverName()%></div>
                        <div id="ReceiverAdd"><%= defaultAdd.getAddressLine1()%> <%= defaultAdd.getAddressLine2()%></div>
                        <div id="ReceiverNo"><%= defaultAdd.getReceiverPhone()%></div>
                    </div>
                    <div id="AddAddress">
                        <span style="flex: 1;">Add New Shipping Address</span>
                        <a href="AddressList" class="PlusBtn"><i class="bi bi-plus"></i></a>
                    </div>

                </div>

                <%
                    }
                %>

                <hr class="divider">

                <!-- Calculations -->
                <%
                    String totalStr = request.getParameter("total");
                    double subtotal1 = Double.parseDouble(totalStr);

                    // Store the subtotal value in the session
                    session.setAttribute("subtotal", subtotal1);

                    // Retrieve the subtotal value from the session
                    double subtotal = (Double) session.getAttribute("subtotal");

                    System.out.println(session.getAttribute("subtotal"));

                    double discount = 0.0;

                    if (session.getAttribute("discount") != null) {
                        discount = (Double) session.getAttribute("discount");
                    } else {
                        discount = 0;
                    }

                    double discountedAmount = 0;

                    if (discount != 0) {
                        discountedAmount = (Double) session.getAttribute("discountedAmount");
                    }

                    double shippingFee = 0.00;
                    if (subtotal < 200) {
                        shippingFee = 25.00;
                    } else {
                        shippingFee = 0.00;
                    }

                    double totalAmount = subtotal + shippingFee - discountedAmount;

                %>

                <!-- Calculations -->

                <!-- display the text in a format -->
                <%                    String subtotalText = "RM" + String.format("%.2f", subtotal);

                    String shippingFeeText = null;
                    if (shippingFee == 0.00) {
                        shippingFeeText = "RM0.00";
                    } else {
                        shippingFeeText = "RM" + String.format("%.2f", shippingFee);
                    }

                    String discountText = null;
                    if (discount == 0) {
                        discountText = "RM0.00";
                    } else {
                        discountText = "-RM" + String.format("%.2f", discountedAmount);
                    }

                    String totalText = "RM" + String.format("%.2f", totalAmount);

                    String discountMessage = null;
                    if (session.getAttribute("promoCode") == null) {
                        discountMessage = "discount";
                    } else {
                        discountMessage = "discount" + "(" + session.getAttribute("promoCode") + ")";
                    }
                %>
                <!-- display the text in a format -->

                <div id="PaymentContainer">
                    <div class="HeaderTxt">payment method</div>
                    <div id="DropdownMenu">
                        <div class="dropdownC">
                            <div class="dropdownC-selected">
                                <div data-value="Select">Select Payment Method</div>
                                <i class="fa fa-chevron-down"></i>
                            </div>
                            <div class="dropdownC-options" id="paymentOptions">
                                <div class="dropdownC-option" data-value="Credit/DebitCard" onclick="setSelectedValue(this);">
                                    <input type="radio" id="paymentOption" name="paymentOption" value="Credit/DebitCard" ${paymentType == 'credit/debitcard' ? 'checked' : ''}>
                                    <label for="paymentTypeCard">Credit/DebitCard</label>
                                </div>
                                <div class="dropdownC-option" data-value="Cash" onclick="setSelectedValue(this);">
                                    <input type="radio" id="paymentOption" name="paymentOption" value="Cash" ${paymentType == 'cash' ? 'checked' : ''}>
                                    <label for="paymentType">Cash</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <%if (session.getAttribute("errorMessage") != null) {%>
                <div style="color: red; padding-top: 10px; padding-left: 10px;"><%=session.getAttribute("errorMessage")%></div>
                <%
                    }
                %>

                <%if (session.getAttribute("emptyMessage0") != null) {%>
                <div style="color: red; padding-top: 10px; padding-left: 10px;"><%=session.getAttribute("emptyMessage0")%></div>
                <%
                    }
                %>

                <%if (session.getAttribute("emptyMessage1") != null) {%>
                <div style="color: red; padding-top: 10px; padding-left: 10px;"><%=session.getAttribute("emptyMessage1")%></div>
                <%
                    }
                %>

                <%if (session.getAttribute("emptyMessage2") != null) {%>
                <div style="color: red; padding-top: 10px; padding-left: 10px;"><%=session.getAttribute("emptyMessage2")%></div>
                <%
                    }
                %>

                <%if (session.getAttribute("emptyMessage3") != null) {%>
                <div style="color: red; padding-top: 10px; padding-left: 10px;"><%=session.getAttribute("emptyMessage3")%></div>
                <%
                    }
                %>

                <%if (session.getAttribute("cardMessage") != null) {%>
                <div style="color: red; padding-top: 10px; padding-left: 10px;"><%=session.getAttribute("cardMessage")%></div>
                <%
                    }
                %>

                <%if (session.getAttribute("dateMessage") != null) {%>
                <div style="color: red; padding-top: 10px; padding-left: 10px;"><%=session.getAttribute("dateMessage")%></div>
                <%
                    }
                %>

                <hr class="divider">

                <div id="EnterCard">

                    <div id="EnterCardForm">
                        <div class="HeaderTxt" style="color: black; padding-bottom: 20px; text-align: center;">Enter Card</div>


                        <form action="ProcessCheckoutServlet" method="GET" id="checkoutForm">
                            <div class="input-container">
                                <input type="text" name="card-number-input" required>
                                <label for="card-number-input">Card Number</label>
                            </div>

                            <div class="input-container">
                                <input type="text" name="expiry-date-input" maxlength = "5" required>
                                <label for="expiry-date-input">Expiry Date (MM/YY)</label>
                            </div>

                            <div class="input-container">
                                <input type="text" name="cvv-input" maxlength = "3" pattern="[0-9]{3}" required>
                                <label for="cvv-input">CVV</label>
                            </div>

                            <div class="input-container">
                                <input type="text" name="name-input" required>
                                <label for="name-input">Name on Card</label>
                            </div>

                            <%
                                String totalAmountTxt = String.format("%.2f", totalAmount);
                                String selectedOption = request.getParameter("paymentType");

                                if (selectedOption != null) {
                                    if ("Credit/DebitCard".equals(selectedOption)) {%>
                            <input type="hidden" name="selectedOption" id="selectedOption" value="Credit/DebitCard">
                            <%    } else if ("Cash".equals(selectedOption)) {%>
                            <input type="hidden" name="selectedOption" id="selectedOption" value="Cash">
                            <%    }
                            } else {%>
                            <input type = "hidden" name = "selectedOption" id = "selectedOption" value =
                                   "">
                            <%      }
                            %>

                            <input type="hidden" name="subtotal" value="<%= subtotal%>" />
                            <input type="hidden" name="totalAmount" value="<%= totalAmountTxt%>" />

                        </form>

                        <!--                        <div style="text-align: center;">
                                                    <button type="submit" id="submit">Submit</button>
                                                </div>-->

                    </div>

                    <hr class="divider">
                </div>

                <div id="PromotionContainer">
                    <form action="ProcessPromoCodeServlet" method="GET">
                        <div class="HeaderTxt">add promotion code</div>
                        <div id="PromoCode">
                            <input type="text" placeholder="Enter Promo Code" name="code" id="PromoField" style="text-align: center; color: grey;">
                            <button type="submit" id="Apply">Apply</button>
                            <%if (session.getAttribute("message") != null) {%>
                            <div style="color: red; padding-top: 10px;"><%=request.getAttribute("message")%></div>
                            <%
                                }
                            %>
                        </div>
                    </form>
                </div>

                <hr class="divider">

                <div id="FinalPayment">

                    <div class="LeftText">subtotal</div>
                    <div class="RightText"><%= subtotalText%></div>

                    <div class="LeftText">shipping fee</div>
                    <div class="RightText"><%= shippingFeeText%></div>

                    <div class="LeftText"><%=discountMessage%></div>
                    <div class="RightText"><%= discountText%></div>

                    <div class="LeftText">total</div>
                    <div class="RightText"><%= totalText%></div>
                </div>
            </div>


        </div>

        <div id="CheckOutBtn">
            <button type = "button" onclick="submitForm()" id="CheckOut">check out</button>
        </div>

        <div id="footer"></div>

        <script>
            function setSelectedValue(option) {
                var selectedOption = option.getAttribute("data-value");
                document.getElementById("selectedOption").value = selectedOption;
            }

            function onPaymentTypeChange() {
                if (document.getElementById("paymentOption").checked) {
                    // radio button is checked
                    document.getElementById("paymentOption").remove();
                }
            }

            $('input[name="paymentOption"]').on('change', function () {
                var selectedValue = $('input[name="paymentOption"]:checked').val();
                $('.dropdownC-selected').attr('data-value', selectedValue);
            });

            const dropdownOptions = document.querySelector('.dropdownC-options');
            const dropdownSelected = document.querySelector('.dropdownC-selected');
            const selectCardsSection = document.getElementById('EnterCard');

            dropdownOptions.addEventListener('click', (event) => {
                const selectedOptionValue = event.target.dataset.value;

                if (selectedOptionValue === 'Credit/DebitCard') {
                    selectCardsSection.style.display = 'block';
                } else {
                    selectCardsSection.style.display = 'none';
                }
            });

            var dropdown = document.querySelector('.dropdownC');
            var selected = dropdown.querySelector('.dropdownC-selected');
            var options = dropdown.querySelector('.dropdownC-options');
            var optionList = dropdown.querySelectorAll('.dropdownC-option');

            selected.addEventListener('click', function () {
                if (options.style.display === 'none') {
                    options.style.display = 'block';
                } else {
                    options.style.display = 'none';
                }
            });

            optionList.forEach(function (option) {
                option.addEventListener('click', function () {
                    selected.innerHTML = option.innerHTML + '<i class="fa fa-chevron-down"></i>';
                    options.style.display = 'none';
                    optionList.forEach(function (otherOption) {
                        otherOption.classList.remove('selected');
                    });
                    option.classList.add('selected');
                });
            });

            function submitForm() {
                document.getElementById("checkoutForm").submit();
            }

        </script>
    </body>

    <%
        }
    %>
</html>

