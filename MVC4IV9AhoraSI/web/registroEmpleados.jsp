<%-- 
    Document   : registroEmpleados
    Created on : 14/05/2021, 06:19:23 PM
    Author     : demon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Registro de Empleado Exitoso</h1>
        <br>
        <%
            HttpSession sesionCliente = request.getSession();
            
            /*
            Si yo tuviera que consultar de la bd
            
            VerificarUsuario(String user, String pass)
            
            Un objeto empleado
            */
                                     //parametro //e.nombre()  valor  
            sesionCliente.setAttribute("Usuario", "Maria");
            
            //invalidate
            %>
        <a href="index.html" >Regresar al Menu Principal</a>
        <br>
        Bienvenido: <% out.println(sesionCliente.getAttribute("Usuario")); %>
    </body>
</html>
