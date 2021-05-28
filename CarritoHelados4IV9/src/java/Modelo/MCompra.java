/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author demon
 */
import Control.Conexion;
import java.sql.*;
import java.util.Vector;

public class MCompra {
    /*
    id_compra, fecha_compra, total_compra
    id_dcompra, id_formapago
    
    */
    private int id_compra, id_dcompra, id_formapago;
    private String fecha_compra;
    private double total_compra;
    
    public MCompra(){
    
    }
    
    /*
    para poder realizar de forma automatica el registro de la compra
    y el detalle de la compra debo de saber la ultima compra que se a registrado
    para que se desencadene al detalle
    */
    
    private int ultimocodigoInserto(Connection con){
        int codigo = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String q = "select MAX(id_compra) as Codigo from MCompra";
            ps = con.prepareStatement(q);
            rs = ps.executeQuery();
            while(rs.next()){
                codigo = rs.getInt("Codigo");
                break;
            }
        
        }catch(SQLException sq){
            System.out.println("Error al obtener la ultima compra");
            System.out.println(sq.getMessage());
        }finally{
            try{
                rs.close();
                ps.close();
            
            }catch(Exception e){
                System.out.println("Error no encuentra la clase");
                System.out.println(e.getMessage());
            }
        }
        return codigo;
    }
    
    public boolean registrarCompra(MCompra compra, Vector<DCompra> listaproductos) throws ClassNotFoundException{
        boolean registro = false;
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = Conexion.getConexion();
            String q = "insert into MCompra("
                    + "fecha_compra, total_compra, id_dcompra, id_formapago) "
                    + "values(?, ?, ?, ?)";
            ps = con.prepareStatement(q);
            ps.setString(1, compra.getFecha_compra());
            ps.setDouble(2, compra.getTotal_compra());
            ps.setInt(3, compra.getId_dcompra());
            ps.setInt(4, compra.getId_formapago());
            
            if(ps.executeUpdate() == 1){
                int codigo = this.ultimocodigoInserto(con);
                registro = this.registrarDCompra(codigo, listaproductos, con);
            }else{
                registro = false;
            }
        
        }catch(SQLException sq){
            System.out.println("Error al registrar la compra");
            System.out.println(sq.getMessage());
        }finally{
            try{
                
                ps.close();
                con.close();
            
            }catch(Exception e){
                System.out.println("Error no encuentra la clase");
                System.out.println(e.getMessage());
            }
        }
        return registro;
    }
    
    private boolean registrarDCompra(int codigo, Vector<DCompra> listaproductos, Connection con) {
        boolean registro = false;       
        PreparedStatement ps = null;
        try{
            for(DCompra detallecompra : listaproductos){
                String q = "insert into DCompra "
                    + "values(?, ?, ?, ?, ?)";
                ps = con.prepareStatement(q);
                
                ps.setInt(1, codigo);
                ps.setInt(2, detallecompra.getId_producto());
                ps.setInt(3, detallecompra.getCantidad_dcompra());
                ps.setDouble(4, detallecompra.getSubtotal_dcompra());
                ps.setInt(5, detallecompra.getId_ecompra());
                
                if(ps.executeUpdate() == 1){
                    registro = true;
                    }else{
                    registro = false;
                    break;
                }
            }
                  
        }catch(SQLException sq){
            System.out.println("Error al registrar la el dcompra");
            System.out.println(sq.getMessage());
        }finally{
            try{
                
                ps.close();
                con.close();
            
            }catch(Exception e){
                System.out.println("Error no encuentra la clase");
                System.out.println(e.getMessage());
            }
        }
        return registro;
    }
    

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public int getId_dcompra() {
        return id_dcompra;
    }

    public void setId_dcompra(int id_dcompra) {
        this.id_dcompra = id_dcompra;
    }

    public int getId_formapago() {
        return id_formapago;
    }

    public void setId_formapago(int id_formapago) {
        this.id_formapago = id_formapago;
    }

    public String getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(String fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public double getTotal_compra() {
        return total_compra;
    }

    public void setTotal_compra(double total_compra) {
        this.total_compra = total_compra;
    }


    
    
}
