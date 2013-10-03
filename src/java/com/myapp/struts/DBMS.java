package com.myapp.struts;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Langtech
 */
public class DBMS {

    static private Connection conexion;
    

    protected DBMS() {
    }
    static private DBMS instance = null;

    static public DBMS getInstance() {
        if (null == DBMS.instance) {
            DBMS.instance = new DBMS();
        }
        conectar();
        return DBMS.instance;
    }

    public static boolean conectar() {
        try {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/dbjava",
                    "postgres",
                    "POSTGRES");
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /* Esta funcion se utiliza cuando un usuario inicia sesion
     * para verificar que su nombre y contrasena sean correctos
     * 
     */
    public Usuario verificarCas(Usuario u) {

        PreparedStatement psConsultar = null;
        try {

            psConsultar = conexion.prepareStatement("SELECT * FROM usuario"
                    + " WHERE usbid = ? AND contrasena = ?");

            psConsultar.setString(1, u.getUsbid());
            psConsultar.setString(2, u.getContrasena());
            System.out.println(psConsultar.toString());
            ResultSet rs = psConsultar.executeQuery();

            while (rs.next()) {
                u.setUsbid(rs.getString("usbid"));
                u.setTipousuario(rs.getString("tipousuario"));

                return u;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        u.setUsbid(null);

        return u;
    }
    
    public boolean agregarUsuario(CreateUserForm u) {

        PreparedStatement psAgregar1 = null;
        PreparedStatement psAgregar2 = null;

        try {
            psAgregar1 = conexion.prepareStatement("INSERT INTO usuario(usbid, contrasena) VALUES (?, ?);");
            psAgregar1.setString(1, u.getUsbid());
            psAgregar1.setString(2, u.getContrasena1());
            System.out.println(psAgregar1.toString());
            Integer i = psAgregar1.executeUpdate();
            
            psAgregar2 = conexion.prepareStatement("INSERT INTO profesor(usbid,email) VALUES (?, ?);");
            psAgregar2.setString(1, u.getUsbid());
            psAgregar2.setString(2, u.getUsbid() + "@usb.ve");
            Integer j = psAgregar2.executeUpdate();
            
            return i > 0 && j >0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<Usuario> listarUsuarios() {

        ArrayList<Usuario> usrs = new ArrayList<Usuario>(0);
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * FROM usuario ORDER BY usbid");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setUsbid(rs.getString("usbid"));
                u.setTipousuario(rs.getString("tipousuario"));
                u.setDepartamento(rs.getString("departamento"));
                usrs.add(u);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usrs;
    }
    
    public ArrayList<Usuario> listarProfesores() {

        ArrayList<Usuario> usrs = new ArrayList<Usuario>(0);
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * FROM usuario WHERE TIPOUSUARIO = 'profesor' ORDER BY usbid");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setUsbid(rs.getString("usbid"));
                u.setTipousuario(rs.getString("tipousuario"));
                u.setDepartamento(rs.getString("departamento"));
                usrs.add(u);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usrs;
    }
    
    public ArrayList<Carrera> listarCarreras() {

        ArrayList<Carrera> carreras = new ArrayList<Carrera>(0);
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * FROM carrera ORDER BY codigo");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Carrera u = new Carrera();
                u.setCodigo(rs.getString("codigo"));
                u.setNombre(rs.getString("nombre"));
                u.setStatus(rs.getString("status"));
                carreras.add(u);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return carreras;
    }
    
    public ArrayList<Decanato> listarDecanatos() {

        ArrayList<Decanato> dcnto = new ArrayList<Decanato>(0);
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * FROM decanato ORDER BY codigo");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Decanato u = new Decanato();
                u.setCodigo(rs.getString("codigo"));
                u.setNombre(rs.getString("nombre"));
                u.setStatus(rs.getString("status"));
                dcnto.add(u);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dcnto;
    }
    
    public ArrayList<Coordinacion> listarCoordinaciones() {

        ArrayList<Coordinacion> coords = new ArrayList<Coordinacion>(0);
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * FROM COORDINACION ORDER BY CODIGO");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Coordinacion u = new Coordinacion();
                u.setCodigo(rs.getString("codigo"));
                u.setNombre(rs.getString("nombre"));
                u.setStatus(rs.getString("status"));
                coords.add(u);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return coords;
    }
    
    public ArrayList<NucleoUniversitario> listarNucleosUniversitarios() {

        ArrayList<NucleoUniversitario> nucleos = new ArrayList<NucleoUniversitario>(0);
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * FROM NUCLEOUNIV ORDER BY CODIGO");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NucleoUniversitario u = new NucleoUniversitario();
                u.setCodigo(rs.getString("codigo"));
                u.setNombre(rs.getString("nombre"));
                u.setStatus(rs.getString("status"));
                nucleos.add(u);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return nucleos;
    }    
    
    public Profesor obtenerInfoProfesor(String usbid){

        Profesor u = new Profesor();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * FROM profesor WHERE usbid = ?");
            ps.setString(1, usbid);
            
            ResultSet rs = ps.executeQuery();
            
            
            
            while (rs.next()) {
                u.setUsbid(rs.getString("usbid"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setCedula(rs.getString("cedula"));
                u.setGenero(rs.getString("genero"));
                u.setEmail(rs.getString("email"));
                u.setNivel(rs.getString("nivel"));
                u.setJubilado(rs.getString("jubilado"));
                u.setLapso_contractual(rs.getString("lapso_contractual"));
            }

             return u;
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return u;
    }
    
    public boolean eliminarUsuario(EliminarUserForm u) {
            PreparedStatement ps = null;
        try {

            ps = conexion.prepareStatement( "DELETE FROM usuario WHERE ( usbid = ? )");
            
            ps.setString(1, u.getUsbid());
            Integer s = ps.executeUpdate();

            return s > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        
    }
    
    public boolean actualizarInfoProfesor(Profesor u) {

        PreparedStatement psAgregar1 = null;
        PreparedStatement psAgregar2 = null;

        try {
            psAgregar1 = conexion.prepareStatement("UPDATE profesor SET nombre = ?, apellido = ?, cedula = ?, genero = ?, email = ?, nivel = ?, jubilado = ?, lapso_contractual = ? WHERE usbid = ? ");
            psAgregar1.setString(1, u.getNombre());
            psAgregar1.setString(2, u.getApellido());
            psAgregar1.setString(3, u.getCedula());
            psAgregar1.setString(4, u.getGenero());
            psAgregar1.setString(5, u.getEmail());
            psAgregar1.setString(6, u.getNivel());
            psAgregar1.setString(7, u.getJubilado());
            psAgregar1.setString(8, u.getLapso_contractual());
            psAgregar1.setString(9, u.getUsbid());
            
            
            Integer i = psAgregar1.executeUpdate();
            
            return i > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    
    public boolean resetInfoProfesor(Profesor u) {

        PreparedStatement psAgregar1 = null;

        try {
            psAgregar1 = conexion.prepareStatement("UPDATE profesor SET nombre = ?, apellido = ?, cedula = ?, genero = ?, email = ?, nivel = ?, jubilado = ?, lapso_contractual = ? WHERE usbid = ? ");
            psAgregar1.setString(1, "");
            psAgregar1.setString(2, "");
            psAgregar1.setString(3, "");
            psAgregar1.setString(4, "");
            psAgregar1.setString(5, "");
            psAgregar1.setString(6, "");
            psAgregar1.setString(7, "");
            psAgregar1.setString(8, "");
            psAgregar1.setString(9, u.getUsbid());
            
            
            Integer i = psAgregar1.executeUpdate();
            
            return i > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean actualizarRolUsuario(EliminarUserForm u) {
            PreparedStatement ps = null;
        try {

            ps = conexion.prepareStatement( "UPDATE usuario SET tipousuario = ?, departamento = ? WHERE ( usbid = ? )");
            
            ps.setString(1, u.getTipousuario());
            ps.setString(2, u.getDepartamento());
            ps.setString(3, u.getUsbid());
            
            Integer s = ps.executeUpdate();

            return s > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        
    }
    
    public boolean registrarCarrera(Carrera u) {

        PreparedStatement psAgregar1 = null;

        try {
            psAgregar1 = conexion.prepareStatement("INSERT INTO carrera(codigo, nombre) VALUES (?, ?);");
            psAgregar1.setString(1, u.getCodigo());
            psAgregar1.setString(2, u.getNombre());

            Integer i = psAgregar1.executeUpdate();
            
            return i > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean registrarDecanato(Decanato u) {

        PreparedStatement psAgregar1 = null;

        try {
            psAgregar1 = conexion.prepareStatement("INSERT INTO decanato(codigo, nombre) VALUES (?, ?);");
            psAgregar1.setString(1, u.getCodigo());
            psAgregar1.setString(2, u.getNombre());

            Integer i = psAgregar1.executeUpdate();
            
            return i > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean registrarCoordinacion(Coordinacion c) {
        PreparedStatement psAgregar = null;
        
        try {
            psAgregar = conexion.prepareStatement("INSERT INTO COORDINACION(codigo, nombre) VALUES (?, ?);");
            psAgregar.setString(1, c.getCodigo());
            psAgregar.setString(2, c.getNombre());
            
            Integer i = psAgregar.executeUpdate();
            
            return i > 0;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean registrarNucleoUniv(NucleoUniversitario n) {
        PreparedStatement psAgregar = null;
        
        try {
            psAgregar = conexion.prepareStatement("INSERT INTO NUCLEOUNIV(codigo, nombre) VALUES (?, ?);");
            psAgregar.setString(1, n.getCodigo());
            psAgregar.setString(2, n.getNombre());
            
            Integer i = psAgregar.executeUpdate();
            
            return i > 0;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean actualizarStatusCoordinacion(Coordinacion u) {
            PreparedStatement ps = null;
        try {

            ps = conexion.prepareStatement( "UPDATE COORDINACION SET status = ? WHERE ( codigo = ? )");
            
            ps.setString(1, u.getStatus());
            ps.setString(2, u.getCodigo());
            Integer s = ps.executeUpdate();

            return s > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        
    }
 
    
    public boolean actualizarStatusCarrera(Carrera u) {
            PreparedStatement ps = null;
        try {

            ps = conexion.prepareStatement( "UPDATE carrera SET status = ? WHERE ( codigo = ? )");
            
            ps.setString(1, u.getStatus());
            ps.setString(2, u.getCodigo());
            Integer s = ps.executeUpdate();

            return s > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        
    }
    
    public boolean actualizarStatusDecanato(Decanato u) {
            PreparedStatement ps = null;
        try {

            ps = conexion.prepareStatement( "UPDATE decanato SET status = ? WHERE ( codigo = ? )");
            
            ps.setString(1, u.getStatus());
            ps.setString(2, u.getCodigo());
            Integer s = ps.executeUpdate();

            return s > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        
    }
    
    public boolean actualizarStatusNucleoUniversitario(NucleoUniversitario u) {
            PreparedStatement ps = null;
        try {

            ps = conexion.prepareStatement("UPDATE NUCLEOUNIV SET status = ? WHERE ( codigo = ? )");
            
            ps.setString(1, u.getStatus());
            ps.setString(2, u.getCodigo());
            Integer s = ps.executeUpdate();

            return s > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        
    }
    
    public boolean actualizarNombreCarrera(Carrera u) {
            PreparedStatement ps = null;
        try {

            ps = conexion.prepareStatement( "UPDATE carrera SET nombre = ? WHERE ( codigo = ? )");
            
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCodigo());
            Integer s = ps.executeUpdate();

            return s > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        
    }
    
    public boolean actualizarNombreDecanato(Decanato u) {
            PreparedStatement ps = null;
        try {

            ps = conexion.prepareStatement( "UPDATE decanato SET nombre = ? WHERE ( codigo = ? )");
            
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCodigo());
            Integer s = ps.executeUpdate();

            return s > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } 
    }
    
    public boolean actualizarNombreCoordinacion(Coordinacion u) {
            PreparedStatement ps = null;
        try {

            ps = conexion.prepareStatement( "UPDATE COORDINACION SET nombre = ? WHERE ( codigo = ? )");
            
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCodigo());
            Integer s = ps.executeUpdate();

            return s > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } 
    }
    
        public boolean actualizarNombreNucleoUniversitario(NucleoUniversitario u) {
            PreparedStatement ps = null;
        try {

            ps = conexion.prepareStatement( "UPDATE NUCLEOUNIV SET nombre = ? WHERE ( codigo = ? )");
            
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCodigo());
            Integer s = ps.executeUpdate();

            return s > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } 
    }
    
    public Carrera obtenerNombreCarrera(Carrera u){

        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * FROM carrera WHERE codigo = ?");
            ps.setString(1, u.getCodigo());
            
            ResultSet rs = ps.executeQuery();
            
            
            
            while (rs.next()) {
                u.setNombre(rs.getString("nombre"));
            }

             return u;
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return u;
    }
    
    public Decanato obtenerNombreDecanato(Decanato u){

        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * FROM decanato WHERE codigo = ?");
            ps.setString(1, u.getCodigo());
            
            ResultSet rs = ps.executeQuery();
            
            
            
            while (rs.next()) {
                u.setNombre(rs.getString("nombre"));
            }

             return u;
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return u;
    }
    
        public Coordinacion obtenerNombreCoordinacion(Coordinacion u){

        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * FROM COORDINACION WHERE codigo = ?");
            ps.setString(1, u.getCodigo());
            
            ResultSet rs = ps.executeQuery();
            
            
            
            while (rs.next()) {
                u.setNombre(rs.getString("nombre"));
            }

             return u;
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return u;
    }
        
    public NucleoUniversitario obtenerNombreNucleoUniversitario(NucleoUniversitario u){

        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * FROM NUCLEOUNIV WHERE codigo = ?");
            ps.setString(1, u.getCodigo());
            
            ResultSet rs = ps.executeQuery();
            
            
            
            while (rs.next()) {
                u.setNombre(rs.getString("nombre"));
            }

             return u;
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return u;
    }
    
}
