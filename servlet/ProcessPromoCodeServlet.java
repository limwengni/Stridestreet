package servlet;

import entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
public class ProcessPromoCodeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String code = request.getParameter("code");

        String defaultCode = "30Discount";
        double discount;
        HttpSession session = request.getSession();
        double subtotal = (Double) session.getAttribute("subtotal");
        String message;

        if (code.equals(defaultCode)) {
            discount = 30;
            double discountedAmount = Math.round(subtotal * (discount / 100.0));
            session.setAttribute("discountedAmount", discountedAmount);
            session.setAttribute("discount", discount);
            session.setAttribute("promoCode", code);
            response.sendRedirect("Checkout?total="+subtotal);

        } else {
            message = "Invalid Code! Please try again!";
            session.setAttribute("message", message);
            discount = 0.0;
            session.setAttribute("discount", discount);
            response.sendRedirect("Checkout?total="+subtotal);
        }

    }
}
