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

public class MProducto {
    
    /*
    id_producto, id_tipohelado, id_promocion
    id_cantidad, id_tamano, id_presentacion, precio_producto
    stock_producto
    */
    
    private int id_producto, id_tipohelado, id_promocion, id_cantidad;
    private int id_tamano, id_presentacion, stock_producto;
    private double precio_producto;
    
    public MProducto(){
    
    }
    
    //Crud
    
    //lista de todos los productos
    
    public Vector<MProducto> listaProductos() throws ClassNotFoundException{
        Vector<MProducto> listaproductos = new Vector<MProducto>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from MProducto";
            //querry donde se obtenga a traves de join o con vista
            ps = con.prepareStatement(q);
            rs = ps.executeQuery();
            while(rs.next()){
                MProducto producto = new MProducto();
                producto.setId_producto(rs.getInt("id_producto"));
                producto.setId_tipohelado(rs.getInt("id_tipohelado"));
                //ctipohelado.setSabor_tipohelado(rs.getString("sabor_tipohelado"))
                producto.setId_promocion(rs.getInt("id_promocion"));
                producto.setId_cantidad(rs.getInt("id_cantidad"));
                producto.setId_tamano(rs.getInt("id_tamano"));
                producto.setId_presentacion(rs.getInt("id_presentacion"));
                producto.setStock_producto(rs.getInt("stock_producto"));
                producto.setPrecio_producto(rs.getDouble("precio_producto"));
                listaproductos.add(producto);
            }
            
        }catch(SQLException sq){
            System.out.println("Error al consultar los productos");
            System.out.println(sq.getMessage());
            listaproductos = null;
        
        }finally{
            try{
                rs.close();
                ps.close();
                con.close();
            
            }catch(Exception e){
                System.out.println("Error no encuentra la clase");
                System.out.println(e.getMessage());
            }
        }
        return listaproductos;
    }
    
    
    public MProducto buscarProducto(int codigoproducto) throws ClassNotFoundException{
        MProducto producto = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from MProducto where id_producto = ?";
            //querry donde se obtenga a traves de join o con vista
            ps = con.prepareStatement(q);
            ps.setInt(1, codigoproducto);
            rs = ps.executeQuery();
            while(rs.next()){
                producto = new MProducto();
                producto.setId_producto(rs.getInt("id_producto"));
                producto.setId_tipohelado(rs.getInt("id_tipohelado"));
                //ctipohelado.setSabor_tipohelado(rs.getString("sabor_tipohelado"))
                producto.setId_promocion(rs.getInt("id_promocion"));
                producto.setId_cantidad(rs.getInt("id_cantidad"));
                producto.setId_tamano(rs.getInt("id_tamano"));
                producto.setId_presentacion(rs.getInt("id_presentacion"));
                producto.setStock_producto(rs.getInt("stock_producto"));
                producto.setPrecio_producto(rs.getDouble("precio_producto"));
                
            }
            
        }catch(SQLException sq){
            System.out.println("Error al buscar el producto");
            System.out.println(sq.getMessage());
            producto = null;
        
        }finally{
            try{
                rs.close();
                ps.close();
                con.close();
            
            }catch(Exception e){
                System.out.println("Error no encuentra la clase");
                System.out.println(e.getMessage());
            }
        }
        return producto;
    }

    //metodo que se encargue de actualizar de forma automatica el stock al haber realizado la compra
    public boolean actualizarStock(Vector<MProducto> vectorproducto) throws ClassNotFoundException{
        boolean actualizo = false;
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = Conexion.getConexion();
            for(MProducto producto : vectorproducto){
                String q = "update MProducto SET stock_producto where id_producto = ?";
                ps = con.prepareStatement(q);
                ps.setInt(1, producto.getStock_producto());
                ps.setInt(2, producto.getId_producto());
                
                if(ps.executeUpdate() == 1){
                    actualizo = true;
                }else{
                    actualizo = false;
                    break;
                }
            }
        
        }catch(SQLException sq){
            System.out.println("Error al actualizar el stock de los productos ");
            System.out.println(sq.getMessage());
            actualizo = false;
        
        }finally{
            try{
                ps.close();
                con.close();
            
            }catch(Exception e){
                System.out.println("Error no encuentra la clase");
                System.out.println(e.getMessage());
                
            }
        }
        return actualizo;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_tipohelado() {
        return id_tipohelado;
    }

    public void setId_tipohelado(int id_tipohelado) {
        this.id_tipohelado = id_tipohelado;
    }

    public int getId_promocion() {
        return id_promocion;
    }

    public void setId_promocion(int id_promocion) {
        this.id_promocion = id_promocion;
    }

    public int getId_cantidad() {
        return id_cantidad;
    }

    public void setId_cantidad(int id_cantidad) {
        this.id_cantidad = id_cantidad;
    }

    public int getId_tamano() {
        return id_tamano;
    }

    public void setId_tamano(int id_tamano) {
        this.id_tamano = id_tamano;
    }

    public int getId_presentacion() {
        return id_presentacion;
    }

    public void setId_presentacion(int id_presentacion) {
        this.id_presentacion = id_presentacion;
    }

    public int getStock_producto() {
        return stock_producto;
    }

    public void setStock_producto(int stock_producto) {
        this.stock_producto = stock_producto;
    }

    public double getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(double precio_producto) {
        this.precio_producto = precio_producto;
    }
    
    
    
}
