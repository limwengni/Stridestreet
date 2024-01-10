
package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FooterServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();

        String companyName = context.getInitParameter("companyName");
        String companyEmail = context.getInitParameter("companyEmail");
        String companyPhone = context.getInitParameter("companyPhone");
        String copyrightNotice = context.getInitParameter("copyrightNotice");

        request.setAttribute("companyName", companyName);
        request.setAttribute("companyEmail", companyEmail);
        request.setAttribute("companyPhone", companyPhone);
        request.setAttribute("copyrightNotice", copyrightNotice);

        RequestDispatcher dispatcher = request.getRequestDispatcher("footer.jsp");
        dispatcher.forward(request, response);
    }

}
