/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import entity.Role;
import entity.Staff;
import java.io.IOException;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

/**
 *
 * @author jmok4
 */
public class processRegisterStaff extends HttpServlet {
    
    @PersistenceContext EntityManager em; 
    @Resource UserTransaction utx;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String staffrole = request.getParameter("role");
        try{
        String roleId = "2";
        Role role = em.find(Role.class, roleId);
        
        Staff staff = new Staff();
        staff.setStaffName(name);
        staff.setStaffUsername(username);
        staff.setStaffPassword(password);
        staff.setStaffRole(staffrole);
        staff.setRoleId(role);
        
        utx.begin();
        em.persist(staff);
        utx.commit();
        
        response.sendRedirect("processDisplayStaff");
        
        }catch(Exception e){
            // Handle other exceptions
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred");
        }
    }

}
