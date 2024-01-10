<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Footer</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Link CSS file-->
        <link rel="stylesheet" href="assets\css\gui.css">
    </head>
    <body>
        <div class="footer pt-5 mb-0 pb-0 bg-pink">
            <div class="container">
                <div class="text-center">
                    <div class="footer-logo d-center pb-4">
                        <a href="#"><i class="bi bi-twitter fa-3x text-dark"></i></a>
                        <a href="#"><i class="bi bi-instagram fa-3x text-dark"></i></a>
                        <a href="#"><i class="bi bi-facebook fa-3x text-dark"></i></a>
                    </div>
                    <div class="diamond-line"></div>
                    <div class="lh-1 py-4">
                        <%ServletContext context = getServletContext();%>
                        <h4><%= context.getInitParameter("companyEmail")%></h4><br>
                        <h4><%= context.getInitParameter("companyPhone")%></h4><br>
                    </div>
                    <div class="diamond-line"></div>

                    <div class="footer-a py-4">
                        <a href="about-us.html">
                            <h3 class="fw-bold">About</h3>
                        </a>
                        <a href="contact-us.html">
                            <h3 class="fw-bold">Contact</h3>
                        </a>
                        <a href="blog.html">
                            <h3 class="fw-bold">Blog</h3>
                        </a>
                    </div>
                    <p class="blockquote-footer mb-0"><%= context.getInitParameter("copyrightNotice")%></p>
                </div>
            </div>
        </div>
    </body>
</html>
