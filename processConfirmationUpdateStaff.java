/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import entity.Staff;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

/**
 *
 * @author jmok4
 */
public class processConfirmationUpdateStaff extends HttpServlet {
    
    @PersistenceContext EntityManager em; 
    @Resource UserTransaction utx;
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Staff staff = (Staff) session.getAttribute("staff");
       
        String name = request.getParameter("name");
        String role = request.getParameter("role");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        List<String> errorMessages = new ArrayList<>();
        errorMessages.addAll(validateInput.isPasswordValid(password));
        

        if (!errorMessages.isEmpty()) {
            request.setAttribute("errorMessages", errorMessages);
            RequestDispatcher rd = request.getRequestDispatcher("updateStaffTable.jsp");
            rd.forward(request, response);
            return;
        }
        
        try{
            
            Staff staffDetails = em.find(Staff.class, staff.getStaffId());
            staffDetails.setStaffName(name);
            staffDetails.setStaffRole(role);
            staffDetails.setStaffUsername(username);
            staffDetails.setStaffPassword(password);
        
            utx.begin();
            em.merge(staffDetails);
            utx.commit();
        
            response.sendRedirect("processDisplayStaff");
        }catch(Exception e){
            // Handle other exceptions
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred");
        }
       
    }

     

}
