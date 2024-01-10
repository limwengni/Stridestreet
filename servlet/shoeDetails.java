/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Yang
 */
public class shoeDetails extends HttpServlet {

    @PersistenceContext
    EntityManager em;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String html = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "    <style>\n"
                + "        .shoePic{\n"
                + "            margin-left: 22%;\n"
                + "            \n"
                + "        }\n"
                + "        .shoePic img{\n"
                + "            width: 20%;\n"
                + "        }\n"
                + "        .shoeDtls{\n"
                + "            float:right;\n"
                + "            margin-right: 20%;\n"
                + "            margin-top: 5%;\n"
                + "        }\n"
                + "\n"
                + "        .select {\n"
                + "      display: flex;\n"
                + "      flex-wrap: wrap;\n"
                + "    }\n"
                + "    .option {\n"
                + "      flex-basis: 30%;\n"
                + "      flex-grow: 0;\n"
                + "      text-align: center;\n"
                + "      margin: 5px;\n"
                + "      padding: 10px 15px;\n"
                + "      border: 3px solid #ECEEF0;\n"
                + "      width: 15px;\n"
                + "      border-top-left-radius: 25px;\n"
                + "      border-top-right-radius: 25px;\n"
                + "      border-bottom-left-radius: 25px;\n"
                + "      border-bottom-right-radius: 25px;\n"
                + "      background-color: #fff;\n"
                + "      cursor: pointer;\n"
                + "    }\n"
                + "    .option:hover {\n"
                + "      background-color: #712D35;\n"
                + "      color: #fff;\n"
                + "    }\n"
                + "    .option.selected {\n"
                + "      background-color: #712D35;\n"
                + "      color: #fff;\n"
                + "    }\n"
                + "    .button1{\n"
                + "        \n"
                + "        text-align: center;\n"
                + "    }\n"
                + "    .button2{\n"
                + "        margin-top: 15px;\n"
                + "      border: 3px solid #ECEEF0;\n"
                + "      width: 60%;\n"
                + "      font-size: 120%;\n"
                + "      border-top-left-radius: 25px;\n"
                + "      border-top-right-radius: 25px;\n"
                + "      border-bottom-left-radius: 25px;\n"
                + "      border-bottom-right-radius: 25px;\n"
                + "      background-color: #fff;\n"
                + "    }\n"
                + "    </style>\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "\n"
                + "    <!-- jQuery -->\n"
                + "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js\"></script>\n"
                + "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick-theme.min.css\" />\n"
                + "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.css\" />\n"
                + "\n"
                + "    <!-- Bootstrap CSS -->\n"
                + "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\n"
                + "    <link href=\"https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.1/mdb.min.css\" rel=\"stylesheet\" />\n"
                + "    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css\">\n"
                + "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css\" />\n"
                + "\n"
                + "\n"
                + "    <!-- Link CSS file-->\n"
                + "    <link rel=\"stylesheet\" href=\"shoe.css\">\n"
                + "\n"
                + "    <title>Shoe Details</title>\n"
                + "<script>\n"
                + "            $(function () {\n"
                + "                $('#header').load(\"header.jsp\");\n"
                + "            });\n"
                + "            $(function () {\n"
                + "                $('#footer').load(\"footer.jsp\");\n"
                + "            });\n"
                + "        </script>"
                + "    \n"
                + "</head>\n"
                + "\n"
                + "<div id=\"header\"></div>"
                + "<body>\n"
                + "    <div class=\"shoeDtls\">\n";
        out.println(html);

        int productId = Integer.parseInt(request.getParameter("productId"));

        Query query = em.createNamedQuery("Product.findByProductId").setParameter("productId", productId);
        List<Product> productList = query.getResultList();

        out.println("<p>" + productList.get(0).getProductName() + "</p>");
        out.println("<p style'font-size:15px;margin-top:-20px;'>" + productList.get(0).getCategory() + "</p>");
        out.println("<p style='color:grey ;font-size:15px;margin-top:-20px'>Quantity :" + productList.get(0).getProductQuantity() + "</p>");
        out.println("<p><b>RM" + String.format("%.2f", productList.get(0).getProductPrice()) + "</b></p>");

        out.println("<p>Select Size&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; Select Guide</p>");
        out.println("<form action='addToCart' method='get'>");
        out.println("<div class='select'>");

        String[] sizeArray = productList.get(0).getProductSize().split(",");
        for (String size : sizeArray) {
            out.println("<div class='option' data-value='" + size + "'>" + size + "</div>");
        }

        out.println("</div>"
                + "<input type='hidden'  name='productId' value='" + productId + "'>"
                + "<input type='hidden' id='selected-size-input' name='selected-size' value=''>"
                + "<script>"
                + "const options = document.querySelectorAll('.option');"
                + "const selectedSizeInput = document.getElementById('selected-size-input');"
                + "options.forEach(option => {"
                + " option.addEventListener('click', () => {"
                + "  options.forEach(option => option.classList.remove('selected'));"
                + " option.classList.add('selected');"
                + "selectedSizeInput.value = option.dataset.value;"
                + "});"
                + "});"
                + "</script>");

        String html2 = "\n"
                + "     \n"
                + "            \n"
                + "        \n"
                + "        <div class=\"button1\">\n"
                + "        <button style=\"background-color: #4f4f4f;color: #fff;cursor: not-allowed; \" type=\"button\" class=\"button2\" \"disabled\">OUT OF STOCK</button>\n"
                + "        <button style=\"background-color: #FBD9EC \" class=\"button2\">FAVOURITE</button>\n"
                + "        \n"
                + "        </div>\n"
                + "        \n"
                + "   </div>\n"
                + "</form>\n"
                + "    <div class=\"shoePic\">\n"
                + "        <a><img src=\"" + productList.get(0).getProductImage1() + "\" alt=\"shoe\"></a>\n"
                + "         <a><img src=\"" + productList.get(0).getProductImage2() + "\" alt=\"shoe\"></a>\n"
                + "        <br>\n"
                + "        <a><img src=\"" + productList.get(0).getProductImage3() + "\" alt=\"shoe\"></a>\n"
                + "        <a><img src=\"" + productList.get(0).getProductImage4() + "\" alt=\"shoe\"></a>\n"
                + "        <br>\n"
                + "         <a><img src=\"" + productList.get(0).getProductImage5() + "\" alt=\"shoe\"></a>\n"
                + "        <a><img src=\"" + productList.get(0).getProductImage6() + "\" alt=\"shoe\"></a>\n"
                + "    </div>\n"
                + "    \n"
                + "    \n"
                + "</body>\n"
                + "<div id=\"footer\"></div>"
                + "\n"
                + "\n"
                + "\n"
                + "    <!-- jQuery -->\n"
                + "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js\"></script>\n"
                + "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.js\"></script>\n"
                + "\n"
                + "    <!-- Bootstrap Bundle with Popper -->\n"
                + "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js\" defer></script>\n"
                + "    <script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.1/mdb.min.js\"></script>\n"
                + "\n"
                + "    <!-- vue js script -->\n"
                + "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/vue/2.7.14/vue.js\" defer></script>\n"
                + "\n"
                + "    <!-- link JS file -->\n"
                + "    <script src=\"assets\\js\\index.js\" defer></script>\n"
                + "\n"
                + "\n"
                + "</html>";

        String html3 = "\n"
                + "     \n"
                + "            \n"
                + "        \n"
                + "        <div class=\"button1\">\n"
                + "        <button style=\"background-color: #712D35;color: #fff \" type=\"submit\" class=\"button2\">ADD TO CART</button>\n"
                + "        <button style=\"background-color: #FBD9EC \" class=\"button2\">FAVOURITE</button>\n"
                + "        \n"
                + "        </div>\n"
                + "        \n"
                + "   </div>\n"
                + "</form>\n"
                + "    <div class=\"shoePic\">\n"
                + "        <a><img src=\"" + productList.get(0).getProductImage1() + "\" alt=\"shoe\"></a>\n"
                + "         <a><img src=\"" + productList.get(0).getProductImage2() + "\" alt=\"shoe\"></a>\n"
                + "        <br>\n"
                + "        <a><img src=\"" + productList.get(0).getProductImage3() + "\" alt=\"shoe\"></a>\n"
                + "        <a><img src=\"" + productList.get(0).getProductImage4() + "\" alt=\"shoe\"></a>\n"
                + "        <br>\n"
                + "         <a><img src=\"" + productList.get(0).getProductImage5() + "\" alt=\"shoe\"></a>\n"
                + "        <a><img src=\"" + productList.get(0).getProductImage6() + "\" alt=\"shoe\"></a>\n"
                + "    </div>\n"
                + "    \n"
                + "    \n"
                + "</body>\n"
                + "<div id=\"footer\"></div>"
                + "\n"
                + "\n"
                + "\n"
                + "    <!-- jQuery -->\n"
                + "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js\"></script>\n"
                + "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.js\"></script>\n"
                + "\n"
                + "    <!-- Bootstrap Bundle with Popper -->\n"
                + "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js\" defer></script>\n"
                + "    <script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.1/mdb.min.js\"></script>\n"
                + "\n"
                + "    <!-- vue js script -->\n"
                + "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/vue/2.7.14/vue.js\" defer></script>\n"
                + "\n"
                + "    <!-- link JS file -->\n"
                + "    <script src=\"assets\\js\\index.js\" defer></script>\n"
                + "\n"
                + "\n"
                + "</html>";

        if (productList.get(0).getProductQuantity() == 0) {
            out.println(html2);
        } else {
            out.println(html3);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
