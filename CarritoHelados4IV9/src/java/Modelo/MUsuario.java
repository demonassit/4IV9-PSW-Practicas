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

public class MUsuario {
    
    /*
    id_usuario, nombre_usuario, appat_usuario
    apmat_usuario, fecha_nacimiento_usuario, tel_usuario
    cel_usuario, dir_usuario, user_usuario, pass_usuario
    privilegio_usuario
    */
    
    private int id_usuario, privilegio_usuario, id_direccion;
    private String nombre_usuario, appat_usuario, apmat_usuario;
    private String fecha_nacimiento_usuario, tel_usuario, cel_usuario;
    private String user_usuario, pass_usuario;
    
    public MUsuario(){
    
    }
    
    //CRUD usuario
    
    //un metodo para verificar el tipo de usuario segun el privilegio
    
    public MUsuario verificarUsuario(String user, String pass) throws ClassNotFoundException{
        MUsuario u = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from MUsuario where user_usuario = ? AND pass_usuario = ?";
            ps = con.prepareStatement(q);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while(rs.next()){
                u = new MUsuario();
                u.setId_usuario(rs.getInt("id_usuario"));
                u.setNombre_usuario(rs.getString("nombre_usuario"));
                u.setAppat_usuario(rs.getString("appat_usuario"));
                u.setApmat_usuario(rs.getString("apmat_usuario"));
                u.setFecha_nacimiento_usuario(rs.getString("fecha_nacimiento_usuario"));
                u.setTel_usuario(rs.getString("tel_usuario"));
                u.setCel_usuario(rs.getString("cel_usuario"));
                u.setId_direccion(rs.getInt("id_direccion"));
                u.setUser_usuario(rs.getString("user_usuario"));
                u.setPass_usuario(rs.getString("pass_usuario"));
                u.setPrivilegio_usuario(rs.getInt("privilegio_usuario"));
                break;
            }
        
        }catch(SQLException sq){
            System.out.println("Error al verificar al usuario");
            System.out.println(sq.getMessage());
            u = null;
        }finally{
            try{
                rs.close();
                ps.close();
                con.close();
            
            }catch(Exception e){
                System.out.println("No encontro la clase");
                System.out.println(e.getMessage());
            
            }
        }
        return u;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getPrivilegio_usuario() {
        return privilegio_usuario;
    }

    public void setPrivilegio_usuario(int privilegio_usuario) {
        this.privilegio_usuario = privilegio_usuario;
    }

    public int getId_direccion() {
        return id_direccion;
    }

    public void setId_direccion(int id_direccion) {
        this.id_direccion = id_direccion;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getAppat_usuario() {
        return appat_usuario;
    }

    public void setAppat_usuario(String appat_usuario) {
        this.appat_usuario = appat_usuario;
    }

    public String getApmat_usuario() {
        return apmat_usuario;
    }

    public void setApmat_usuario(String apmat_usuario) {
        this.apmat_usuario = apmat_usuario;
    }

    public String getFecha_nacimiento_usuario() {
        return fecha_nacimiento_usuario;
    }

    public void setFecha_nacimiento_usuario(String fecha_nacimiento_usuario) {
        this.fecha_nacimiento_usuario = fecha_nacimiento_usuario;
    }

    public String getTel_usuario() {
        return tel_usuario;
    }

    public void setTel_usuario(String tel_usuario) {
        this.tel_usuario = tel_usuario;
    }

    public String getCel_usuario() {
        return cel_usuario;
    }

    public void setCel_usuario(String cel_usuario) {
        this.cel_usuario = cel_usuario;
    }

    public String getUser_usuario() {
        return user_usuario;
    }

    public void setUser_usuario(String user_usuario) {
        this.user_usuario = user_usuario;
    }

    public String getPass_usuario() {
        return pass_usuario;
    }

    public void setPass_usuario(String pass_usuario) {
        this.pass_usuario = pass_usuario;
    }
    
    
    
}
