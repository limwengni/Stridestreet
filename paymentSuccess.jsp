<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Payment Success</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
        <link rel="stylesheet" href="assets/css/paymentSuccess.css"/>
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
            if (session.getAttribute("customer") == null) {
                response.sendRedirect("login.jsp");
            } else {
        %>

        <div>
            <div id="content">
                <div class="notify_successbox">
                    <div class="check"><i class="fa fa-check" aria-hidden="true"></i></div>
                    <div id="PaymentTxt">Payment Successful!</div>
                </div>

                <div id="Box">
                    <hr class="divider">

                    <%
                        Object selectedPaymentMethodobj = request.getAttribute("paymentType");
                        Object selectedCardTypeobj = request.getAttribute("cardType");
                        Object orderId = request.getAttribute("orderId");

                        String selectedPaymentMethod = (String) selectedPaymentMethodobj;
                        String selectedCardType = (String) selectedCardTypeobj;
                        Object totalAmountobj = request.getAttribute("totalAmount");
                        String totalAmount = (String) totalAmountobj;

                        String paymentTxt;

                        if (selectedPaymentMethod == "credit/debit card") {
                            paymentTxt = selectedPaymentMethod + "(" + selectedCardType + ")";
                        } else {
                            paymentTxt = selectedPaymentMethod;
                        }

                        LocalDate today = LocalDate.now();
                        LocalDate deliveryDate = today.plusDays(7);

                    %>

                    <div id="TransactionBox">
                        <div class="LeftText">transaction id</div>
                        <div class="RightText"><%= orderId%></div>
                        <div class="LeftText">payment method</div>
                        <div class="RightText"><%= paymentTxt%></div>
                        <div class="LeftText">transaction status</div>
                        <div class="RightText">success</div>
                        <div class="LeftText">amount</div>
                        <div class="RightText">RM<%= totalAmount%></div>
                    </div>

                </div>

                <div style="text-align: center; font-size: 20px; padding-bottom: 10px;">
                    Thank you for buying!<br> We have received your order and your order is expected to arrive on <%= deliveryDate%>.
                </div>
                <div class="checkStatusBtn">
                    <button type="button">
                        <a href="order-statusC.jsp?transactionNumber=${orderId}">Check Order Status</a>
                    </button>
                </div>
            </div>
            <div id="ReturnDiv">
                <a href="#" id="ReturnBtn">return to home</a>
            </div>
        </div>

        <div id="footer"></div>

        <%
            }
        %>
    </body>
</html>
