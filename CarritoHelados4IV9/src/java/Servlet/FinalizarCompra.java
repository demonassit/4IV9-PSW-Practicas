/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Modelo.DCompra;
import Modelo.MCompra;
import Modelo.MProducto;
import Modelo.MUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author demon
 */
public class FinalizarCompra extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            HttpSession sesionusu = request.getSession();
            Date dia = new Date();
            Vector<DCompra>detallecompra = (Vector<DCompra>)sesionusu.getAttribute("detallecompraproductos");
            Vector<MProducto>stockproducto = (Vector<MProducto>)sesionusu.getAttribute("stockproducto");
            
            MProducto producto = new MProducto();
            double totalpagar = 0;
            
            for(DCompra dc : detallecompra){
                totalpagar+=dc.getSubtotal_dcompra();
            }
            
            MUsuario u = new MUsuario();
            MCompra compra = new MCompra();
            DCompra dcompra = new DCompra();
            
            compra.setFecha_compra(dia.toString());
            compra.setTotal_compra(totalpagar);
            compra.setId_dcompra(dcompra.getId_dcompra());
            compra.setId_formapago(1);
            
            boolean registrarCompra = compra.registrarCompra(compra, detallecompra);
            boolean actualizarStock = producto.actualizarStock(stockproducto);
            
            if(registrarCompra != actualizarStock){
                response.sendRedirect("Mensaje.jsp");
            }else{
                response.sendRedirect("error.jsp");
            }
            
            
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FinalizarCompra.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FinalizarCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
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
