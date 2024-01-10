<%@page import="entity.CartItem"%>
<%@page import="entity.Cart"%>
<%@page import="java.util.List"%>
<%@page import="entity.Staff"%>
<%@page import="entity.Customer"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.PersistenceContext"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- jQuery -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick-theme.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.css" />

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.1/mdb.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />


        <!-- Link CSS file-->
        <link rel="stylesheet" href="assets\css\gui.css">

        <title>Header</title>
    </head>

    <body>
        <div class="header">
            <nav class="navbar navbar-expand-lg navbar-light bg-pink">
                <!-- Container wrapper -->
                <div class="container-fluid">
                    <a class="navbar-brand col mx-3 my-1" href="index.html"><img src="assets/images/logo-left.png" alt="logo"></a>
                    <!-- Toggle button -->
                    <button class="navbar-toggler px-0" type="button" data-mdb-toggle="collapse"
                            data-mdb-target="#navbarExample6" aria-controls="navbarExample6" aria-expanded="false"
                            aria-label="Toggle navigation">
                        <i class="fas fa-bars"></i>
                    </button>

                    <!-- Collapsible wrapper -->
                    <div class="collapse navbar-collapse sideNav" id="navbarExample6">
                        <!-- Left links -->
                        <ul class="navbar-nav me-auto ps-lg-0" style="padding-left: 0.15rem">

                            <!-- Navbar dropdown -->
                            <!-- men -->
                            <li class="nav-item  dropdown position-static ">
                                <a class="nav-link fw-bold" href="#" id="navbarDropdown" role="button"
                                   data-mdb-toggle="dropdown" aria-expanded="false">
                                    MEN
                                </a>
                                <!-- Dropdown menu -->
                                <div class="dropdown-menu w-100" aria-labelledby="navbarDropdown" style="
                                     border-top-left-radius: 0;
                                     border-top-right-radius: 0;
                                     ">
                                    <div class="container">
                                        <div class="row my-4">
                                            <div class="col-md-4 mb-4 mb-md-0">
                                                <strong class="text-uppercase h5 d-block my-2 pb-2 border-bottom">
                                                    Featured
                                                </strong>
                                                <a href="" class="text-dark">
                                                    <h6>New Releases</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Bestsellers</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Member Exclusive</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Last Sizez Acailable</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Sale</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Runing Shoe Finder</h6>
                                                </a>
                                            </div>
                                            <div class="col-md-4 mb-4 mb-md-0">
                                                <strong class="text-uppercase h5 d-block my-2 pb-2 border-bottom">
                                                    Shoes
                                                </strong>
                                                <a href="" class="text-dark">
                                                    <h6>All Shoes</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Lifestyle</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Runnning</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Gym and Traning</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Sandals and Slides</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Basketball</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Football</h6>
                                                </a>
                                            </div>
                                            <div class="col-md-4">

                                                <img class="img-fluid" src="assets/images/mega-man.jpg" alt="Menu image">
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>

                            <!-- woman -->
                            <li class="nav-item  dropdown position-static ">
                                <a class="nav-link fw-bold" href="#" id="navbarDropdown" role="button"
                                   data-mdb-toggle="dropdown" aria-expanded="false">
                                    WOMEN
                                </a>
                                <!-- Dropdown menu -->
                                <div class="dropdown-menu w-100" aria-labelledby="navbarDropdown" style="
                                     border-top-left-radius: 0;
                                     border-top-right-radius: 0;
                                     ">
                                    <div class="container">
                                        <div class="row my-4">
                                            <div class="col-md-4 mb-4 mb-md-0">
                                                <strong class="text-uppercase h5 d-block my-2 pb-2 border-bottom">
                                                    Featured
                                                </strong>
                                                <a href="" class="text-dark">
                                                    <h6>New Releases</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Bestsellers</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Member Exclusive</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Last Sizez Acailable</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Sale</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Runing Shoe Finder</h6>
                                                </a>
                                            </div>
                                            <div class="col-md-4 mb-4 mb-md-0">
                                                <strong class="text-uppercase h5 d-block my-2 pb-2 border-bottom">
                                                    Shoes
                                                </strong>
                                                <a href="" class="text-dark">
                                                    <h6>All Shoes</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Lifestyle</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Runnning</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Gym and Traning</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Sandals and Slides</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Basketball</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Football</h6>
                                                </a>
                                            </div>
                                            <div class="col-md-4">

                                                <img class="img-fluid" src="assets/images/mega-woman.jpg" alt="Menu image2">
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>

                            <!-- Kids -->
                            <li class="nav-item fw-bold dropdown position-static ">
                                <a class="nav-link " href="#" id="navbarDropdown" role="button" data-mdb-toggle="dropdown"
                                   aria-expanded="false">
                                    KIDS
                                </a>
                                <!-- Dropdown menu -->
                                <div class="dropdown-menu w-100" aria-labelledby="navbarDropdown" style="
                                     border-top-left-radius: 0;
                                     border-top-right-radius: 0;
                                     ">
                                    <div class="container">
                                        <div class="row my-4">
                                            <div class="col-md-4 mb-4 mb-md-0">
                                                <strong class="text-uppercase h5 d-block my-2 pb-2 border-bottom">
                                                    Featured
                                                </strong>
                                                <a href="" class="text-dark">
                                                    <h6>New Releases</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Bestsellers</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Member Exclusive</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Last Sizez Acailable</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Sale</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Runing Shoe Finder</h6>
                                                </a>
                                            </div>
                                            <div class="col-md-4 mb-4 mb-md-0">
                                                <strong class="text-uppercase h5 d-block my-2 pb-2 border-bottom">
                                                    Shoes
                                                </strong>
                                                <a href="" class="text-dark">
                                                    <h6>All Shoes</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Lifestyle</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Runnning</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Gym and Traning</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Sandals and Slides</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Basketball</h6>
                                                </a>
                                                <a href="" class="text-dark">
                                                    <h6>Football</h6>
                                                </a>
                                            </div>
                                            <div class="col-md-4">

                                                <img class="img-fluid" src="assets/images/mega-kid.jpg" alt="Menu image3">
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>

                        </ul>
                        <!-- Left links -->
                    </div>
                    <!-- Collapsible wrapper -->

                    <div class="d-flex" onkeypress="if (event.keyCode === 13) {
                                document.myForm.submit();
                            }">
                        <form name="search" method="GET" action="Search">
                            <input class="form-control me-sm-2" type="search" name="searchitem" placeholder="Search" style="height: 50px;">
                        </form>
                        <div class="dropdown dropstart">
                            <button type="button" class="btn shadow-none px-3 m-2 my-sm-0" data-bs-toggle="dropdown">
                                <i class="bi bi-person-circle fa-2x"></i>
                            </button>
                            <%!
                                @PersistenceContext
                                EntityManager em;
                            %>
                            <%
                                session = request.getSession();
                                Customer customer = (Customer) session.getAttribute("customer");
                                Staff staff = (Staff) session.getAttribute("staff");
                                Object manager = session.getAttribute("roleType");
                            %>
                            <ul class="dropdown-menu">
                                <% if (customer != null) {%>
                                <li><a class="dropdown-item" href="DisplayCustProfile"><%= customer.getCustomerEmail()%></a></li>
                                <li><hr class="dropdown-divider"></hr></li>
                                <li><a class="dropdown-item" href="AddressList">My Addresses</a></li>
                                <li><hr class="dropdown-divider"></hr></li>
                                <li><a class="dropdown-item" href="logout.jsp">Log Out</a></li>
                                    <% } else if (staff != null) { %>
                                <li><a class="dropdown-item" href="staffProfile.jsp">Staff Profile</a></li>
                                <li><hr class="dropdown-divider"></hr></li>
                                <li><a class="dropdown-item" href="staffProfile.jsp">Update Order Status</a></li>
                                <li><hr class="dropdown-divider"></hr></li>
                                <li><a class="dropdown-item" href="processDisplayProductTable">Display Product Table</a></li>
                                <li><hr class="dropdown-divider"></hr></li>
                                <li><a class="dropdown-item" href="logout.jsp">Log Out</a></li>
                                    <% } else if (manager != null) { %>
                                <li><a class="dropdown-item" href="processDisplayCustomer">Display Customer Table</a></li>
                                <li><hr class="dropdown-divider"></hr></li>
                                <li><a class="dropdown-item" href="staffProfile.jsp">Update Order Status</a></li>
                                <li><hr class="dropdown-divider"></hr></li>
                                <li><a class="dropdown-item" href="processDisplayStaff">Display Staff Table</a></li>
                                <li><hr class="dropdown-divider"></hr></li>
                                <li><a class="dropdown-item" href="processDisplayProductTable">Display Product Table</a></li>
                                <li><hr class="dropdown-divider"></hr></li>
                                <li><a class="dropdown-item" href="logout.jsp">Log Out</a></li>
                                    <% } else { %>
                                <li><a class="dropdown-item" href="login.jsp">Login / SignUp</a></li>
                                    <% }%>
                            </ul>
                        </div>
                        <% if (customer != null) {%>
                        <button class="btn shadow-none px-3 m-2 my-sm-0" type="button">
                            <a href="cart.jsp" style="text-decoration: none; color: #514e50; position: relative;">
                                <i class="bi bi-bag fa-2x"></i>
                            </a>
                        </button>
                        <% } %>

                    </div>
                    <!-- SEARCH -->
                </div>
                <!-- Container wrapper -->
            </nav>
        </div>

        <!-- jQuery -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.js"></script>

        <!-- Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" defer></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.1/mdb.min.js"></script>

        <!-- vue js script -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.7.14/vue.js" defer></script>

        <!-- link JS file -->
        <script src="assets\js\index.js" defer></script>
    </body>

</html>