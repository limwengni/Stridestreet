package servlet;

import entity.Address;
import entity.Customer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetAddressInfoForEdit extends HttpServlet {

    // Get the EntityManager
    @PersistenceContext
    EntityManager em = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer == null) {
            response.sendRedirect("login.jsp");
        } else {

            try {

                // Get the query string from the request URL
                String queryString = request.getQueryString();

                // Split the query string into individual parameters
                String[] queryParams = queryString.split("&");

                // Loop through the parameters to find the 'id' parameter
                int addressId = 0;
                for (String param : queryParams) {
                    String[] keyValue = param.split("=");
                    if (keyValue[0].equals("id")) {
                        addressId = Integer.parseInt(keyValue[1]);
                        break;
                    }
                }

                Address address = em.find(Address.class, addressId);

                List<Address> addresses = new ArrayList<>();
                addresses.add(address);
                request.setAttribute("addresses", addresses);

                // forward the request to a JSP for display
                RequestDispatcher dispatcher = request.getRequestDispatcher("editAddress.jsp");
                dispatcher.forward(request, response);

            } finally {
                // close the EntityManager
                if (em != null && em.isOpen()) {
                    em.close();
                }
            }
        }

    }
}
