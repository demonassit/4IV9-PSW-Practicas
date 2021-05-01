<%-- 
    Document   : borrar
    Created on : 30/04/2021, 07:14:49 PM
    Author     : demon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" language="java" import="java.sql.*, java.util.*, java.text.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Borrar Registros</title>
    </head>
    <body>
        <%
            Connection con = null;
            Statement set = null;
            ResultSet rs = null;

            String url, userName, password, driver;

            url = "jdbc:mysql://localhost/registro";
            userName = "root";
            password = "n0m3l0";
            driver = "com.mysql.jdbc.Driver";
            
            try{
                Class.forName(driver);
                con = DriverManager.getConnection(url, userName, password);
                try{
                    int id = Integer.parseInt(request.getParameter("id"));
                    String q = "delete from registro where id_usu="+id;
                    
                    set = con.createStatement();
                    
                    int borrar = set.executeUpdate(q);
                    
                    %>
                    
                    <h1>Registro Borrado con Exito</h1>
                    
                    <%     
                    set.close();    
                
                }catch(SQLException ed){
                    System.out.println("Error al eliminar el dato");
                    System.out.println(ed.getMessage());
                     %>
                    
                    <h1>Registro No es posible Borrarlo con Exito, juguito contigo</h1>
                    
                    <% 
                
                }
                con.close();
            
            }catch(Exception e){
                System.out.println("Error al conectar con la bd");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
                %>
                    
                    <h1>Sitio en construcción</h1>
                    
                    <%
            
            }
            %>
        
        <br>
        <a href="index.html" >Regresar al Menú Principal</a>
        <br>
        <a href="registrar.jsp" >Registrar Nuevo Usuario</a>
        
    </body>
</html>
