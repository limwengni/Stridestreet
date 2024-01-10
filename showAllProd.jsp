<%-- 
    Document   : showAllProd
    Created on : May 4, 2023, 7:12:21 PM
    Author     : Yang
--%>

<%@page import="java.util.Base64"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="servlet.showAllProd"%>
<%@page import="java.io.Serializable"%>
<%@page import="java.util.List"%>
<%@page import="entity.Product"%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="javax.persistence.PersistenceContext"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html> 
    <head>
        <title>Product Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            .product-shoes {

                margin: 130px;
                margin-top: 0px;
                margin-bottom: 0 ;
                margin-left: 0;
                padding: 10px;
            }
            .product-shoes:hover {
                transform: scale(1.1); /* increase the size by 20% */
                transition: transform 0.3s ease;
            }
            .pagination {
                display: inline-block;
            }

            .g{
                background-color:  #FBD9EC;
                color: black;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
                transition: background-color .3s;
                border: 1px solid #ddd;
                margin-left: 15px;
            }
            .heading {

                position: relative;
                margin-left: 55px;
            }
            .fa {

                font-size: 35px;
                position: absolute;
                top: 0;
                margin-bottom: 0;
                bottom: 0;
                right: 190px;
            }
            .btn-0701{
                border: white hidden;
            }
            .g:hover:not(.active) {
                background-color: #712D35;
            }
            .material-icons:hover{
                color:#712D35;
            }
            td:nth-child(even){
                background-color: white !important;
                color: black !important;
            }
        </style>
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
        <br>
        <div style="max-width: 1200px; overflow-x: auto; margin-left: 60px;">
            <h3 class="heading"><b>
                    Shoes
                </b>

            </h3>

            <table style="width: 100%; table-layout: fixed;">
                <% List<Product> products = (List<Product>) request.getAttribute("products");
                for (int i = 0; i < products.size(); i++) {
                    if (i % 4 == 0) { %>
                <tr>
                    <% }%>
                    <td>
                        <div class="product-shoes">
                            <form action="shoeDetails" method="get">
                                <input type="hidden" name="productId" value="<%= products.get(i).getProductId()%>">
                                <button class="btn-0701"><img src="<%= products.get(i).getProductImage1()%>" width="200" height="200" alt="shoes" style="margin: 10px 5px;"/></button>
                            </form>  
                        </div>
                        <div style="padding-left: 10px;">
                            <b><%=products.get(i).getProductName()%></b><br>
                            <span><%=products.get(i).getCategory()%></span>
                            <br><b>RM<%= String.format("%.2f", products.get(i).getProductPrice())%></b>
                        </div>
                    </td>
                    <% if ((i + 1) % 4 == 0 || i == products.size() - 1) { %>
                </tr>
                <% } %>
                <% }%>
            </table>
        </div>

        <br>
        <div id="footer"></div>
    </body>
</html>
