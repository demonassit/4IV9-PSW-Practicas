<%-- 
    Document   : editar
    Created on : 30/04/2021, 07:22:52 PM
    Author     : demon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" language="java" import="java.sql.*, java.util.*, java.text.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualizar Datos</title>
    </head>
    <body>
        <h1>Tabla de Actualización de Datos</h1>
        <form method="post" name="formularioactualizar"  action="actualizar.jsp">
            <table border="2" >
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
                            String q = "select id_usu, nom_usu, ciu_usu, tel_usu from registro "
                                    + "where id_usu="+id;
                            
                            set = con.createStatement();
                            rs = set.executeQuery(q);
                            while(rs.next()){
                            %>
                    <tr>
                        <td>ID</td>
                        <td> <input type="hidden" name="id2" value="<%=rs.getInt("id_usu")%>" > </td>
                    </tr>
                    <tr>
                        <td>Nombre:</td>
                        <td> <input type="text" name="nombre2" value="<%=rs.getString("nom_usu")%>" > </td>
                    </tr>
                    <tr>
                        <td>Ciudad:</td>
                        <td> <input type="text" name="ciudad2" value="<%=rs.getString("ciu_usu")%>" > </td>
                    </tr>
                    <tr>
                        <td>Telefono:</td>
                        <td> <input type="text" name="tel2" value="<%=rs.getString("tel_usu")%>" > </td>
                    </tr>        
                            
                            
                            <%
                            
                            }
                            rs.close();
                            set.close();
                        
                        }catch(SQLException ed){
                            System.out.println("Error al consultar los datos");
                            System.out.println(ed.getMessage());
                            %>
                <tr>
                    <td>ID</td>
                    <td> <input type="hidden" name="id2" value="" > </td>
                </tr>
                <tr>
                    <td>Nombre:</td>
                    <td> <input type="text" name="nombre2" value="" > </td>
                </tr>
                <tr>
                    <td>Ciudad:</td>
                    <td> <input type="text" name="ciudad2" value="" > </td>
                </tr>
                <tr>
                    <td>Telefono:</td>
                    <td> <input type="text" name="tel2" value="" > </td>
                </tr>        
                            
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
                
                
            </table>
                    <input type="submit" value="Actualizar Datos" >
                    <input type="reset" value="Borrar Datos" >
            
        </form>
                    <br>
                    <a href="index.html" >Regresar al Menú Principal</a>
                    <br>
                    <a href="registrar.jsp" >Registrar Nuevo Usuario</a>
    </body>
</html>
