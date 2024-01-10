<%-- 
    Document   : retrive-img1
    Created on : May 4, 2023, 7:15:43 PM
    Author     : Yang
--%>

<%@page import="javax.sql.rowset.serial.SerialBlob"%>
<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="java.io.ObjectOutputStream"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.io.Serializable"%>
<%@page import="entity.Product"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
// Retrieve the ID parameter from the request
            int id = Integer.parseInt(request.getParameter("id"));

// Retrieve the image data from the database
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Gui_AssPU"); // The persistence unit name
            EntityManager em = emf.createEntityManager();
            Product product = em.find(Product.class, id);

            if (product != null && product.getProductImage1() != null) {

                Serializable serial = product.getProductImage1();

// Convert the Serializable object to a byte array
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(serial);
                byte[] bytes = baos.toByteArray();

// Create a new Blob instance from the byte array
                Blob blob = new SerialBlob(bytes);
                InputStream inputStream = blob.getBinaryStream();
                response.setContentType("image/jpeg");
                OutputStream outputStream = response.getOutputStream();
                byte[] buffer = new byte[4024];
                int bytesRead = 0;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.flush();
                outputStream.close();
                inputStream.close();
            }
            em.close();
            emf.close();
        %>
         </body>
</html>