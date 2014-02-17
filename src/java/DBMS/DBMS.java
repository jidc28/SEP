package DBMS;

import Clases.*;
import Forms.CreateUserForm;
import Forms.EliminarUserForm;
import Sistemas.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
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
                    "jdbc:postgresql://localhost:5432/evalprof",
                    "langtech",
                    "evalprof");
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

            return i > 0 && j > 0;

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

    public ArrayList[] mostrarUsuarios() {

        ArrayList[] total = new ArrayList[4];
        ArrayList<Usuario> profesores = new ArrayList<Usuario>(0);
        ArrayList<Usuario> decanatos = new ArrayList<Usuario>(0);
        ArrayList<Usuario> departamentos = new ArrayList<Usuario>(0);
        ArrayList<Usuario> coordinaciones = new ArrayList<Usuario>(0);
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * FROM usuario ORDER BY usbid");
            ResultSet rs = ps.executeQuery();
            String tipousuario = null;
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setUsbid(rs.getString("usbid"));
                u.setDepartamento(rs.getString("departamento"));
                tipousuario = rs.getString("tipousuario");
                if (tipousuario.equals("profesor")) {
                    profesores.add(u);
                } else if (tipousuario.equals("decanato")) {
                    decanatos.add(u);
                } else if (tipousuario.equals("departamento")) {
                    departamentos.add(u);
                } else if (tipousuario.equals("coordinacion")) {
                    coordinaciones.add(u);
                }
            }
            total[0] = profesores;
            total[1] = decanatos;
            total[2] = departamentos;
            total[3] = coordinaciones;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return total;
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

    public ArrayList<Decanato> listarDecanatos() {

        ArrayList<Decanato> dcnto = new ArrayList(0);
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * FROM decanato ORDER BY codigo");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Decanato u = new Decanato();
                u.setCodigo(rs.getString("codigo"));
                u.setNombre(rs.getString("nombre"));
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
                coords.add(u);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return coords;
    }

    public ArrayList<Coordinacion> listarCoordinacionesAdscritas(String id_decanato) {

        ArrayList<Coordinacion> coords = new ArrayList<Coordinacion>(0);
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * "
                    + "FROM COORDINACION, se_adscribe "
                    + "WHERE codigo_decanato = ? "
                    + "AND codigo_coordinacion = codigo "
                    + "ORDER BY CODIGO");
            ps.setString(1, id_decanato);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Coordinacion u = new Coordinacion();
                u.setCodigo(rs.getString("codigo"));
                u.setNombre(rs.getString("nombre"));
                coords.add(u);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return coords;
    }

    public ArrayList<Departamento> listarDepartamentos() {

        ArrayList<Departamento> dptos = new ArrayList<Departamento>(0);
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * FROM departamento "
                    + "WHERE condicion = 'activo' "
                    + "ORDER BY codigo");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Departamento u = new Departamento();
                u.setCodigo(rs.getString("codigo"));
                u.setNombre(rs.getString("nombre"));
                dptos.add(u);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dptos;
    }

    public Profesor obtenerInfoProfesor(String usbid) {

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
                u.setEmail_personal(rs.getString("email_personal"));
                u.setNivel(rs.getString("nivel"));
                u.setJubilado(rs.getString("jubilado"));
                u.setLapso_contractual_inicio(rs.getString("lapso_contractual_inicio"));
                u.setLapso_contractual_fin(rs.getString("lapso_contractual_fin"));
            }

            return u;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return u;
    }

    public boolean eliminarProfesor(Profesor p) {
        PreparedStatement ps1, ps2;
        try {

            ps1 = conexion.prepareStatement("DELETE FROM usuario WHERE ( usbid = ? );");
            ps1.setString(1, p.getUsbid());

            ps2 = conexion.prepareStatement("DELETE FROM profesor WHERE ( usbid = ? );");
            ps2.setString(1, p.getUsbid());

            Integer r = ps2.executeUpdate();
            Integer s = ps1.executeUpdate();

            return s > 0 && r > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public boolean actualizarInfoProfesor(Profesor u) {

        PreparedStatement psAgregar1 = null;

        try {
            psAgregar1 = conexion.prepareStatement("UPDATE profesor SET email = ?, email_personal = ?, nivel = ?, jubilado = ?, lapso_contractual_inicio = ?, lapso_contractual_fin = ? WHERE usbid = ? ");
            psAgregar1.setString(1, u.getEmail());
            psAgregar1.setString(2, u.getEmail_personal());
            psAgregar1.setString(3, u.getNivel());
            psAgregar1.setString(4, u.getJubilado());
            psAgregar1.setString(5, u.getLapso_contractual_inicio());
            psAgregar1.setString(6, u.getLapso_contractual_fin());
            psAgregar1.setString(7, u.getUsbid());


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
            psAgregar1 = conexion.prepareStatement("UPDATE profesor SET email_personal = ?, nivel = ?, jubilado = ?, lapso_contractual_inicio = ?, lapso_contractual_fin = ? WHERE usbid = ? ");
            psAgregar1.setString(1, "");
            psAgregar1.setString(2, "");
            psAgregar1.setString(3, "");
            psAgregar1.setString(4, "");
            psAgregar1.setString(5, "");
            psAgregar1.setString(6, u.getUsbid());


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

            ps = conexion.prepareStatement("UPDATE usuario SET tipousuario = ?, departamento = ? WHERE ( usbid = ? )");

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

    public boolean eliminarCoordinacion(Coordinacion u) {

        PreparedStatement psEliminar1 = null;
        try {
            psEliminar1 = conexion.prepareStatement("DELETE FROM coordinacion AS c WHERE (c.codigo = ?);");
            psEliminar1.setString(1, u.getCodigo());

            Integer i = psEliminar1.executeUpdate();

            System.out.println("retorna: " + (i > 0));
            return i > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /*public boolean eliminarDepartamento(Departamento u) {

     PreparedStatement psEliminar1 = null;
     try {
     psEliminar1 = conexion.prepareStatement("DELETE FROM DEPARTAMENTO AS d WHERE (d.codigo = ?);");
     psEliminar1.setString(1, u.getCodigo());

     Integer i = psEliminar1.executeUpdate();

     System.out.println("retorna: " + (i > 0));
     return i > 0;

     } catch (SQLException ex) {
     ex.printStackTrace();
     return false;
     }
     }*/
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

    public boolean adscribirCoordinacion(Coordinacion c, String id_decanato, boolean decanato) {
        PreparedStatement ps1, ps2;

        try {
            ps1 = conexion.prepareStatement("INSERT INTO COORDINACION(codigo, nombre) VALUES (?, ?);");
            ps1.setString(1, c.getCodigo());
            ps1.setString(2, c.getNombre());

            if (!decanato) {
                PreparedStatement ps3;

                ps3 = conexion.prepareStatement("SELECT * "
                        + "FROM DECANATO "
                        + "WHERE nombre = ?;");
                ps3.setString(1, id_decanato);

                ResultSet rs = ps3.executeQuery();
                while (rs.next()) {
                    id_decanato = rs.getString("codigo");
                }
            }

            ps2 = conexion.prepareStatement("INSERT INTO se_adscribe(codigo_coordinacion, codigo_decanato) "
                    + "VALUES (?, ?);");
            ps2.setString(1, c.getCodigo());
            ps2.setString(2, id_decanato);

            Integer i = ps1.executeUpdate();
            Integer j = ps2.executeUpdate();

            return i > 0 && j > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean actualizarNombreDecanato(Decanato u) {
        PreparedStatement ps = null;
        try {

            ps = conexion.prepareStatement("UPDATE decanato SET nombre = ? WHERE ( codigo = ? )");

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

            ps = conexion.prepareStatement("UPDATE COORDINACION SET nombre = ? WHERE ( codigo = ? )");

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCodigo());
            Integer s = ps.executeUpdate();

            return s > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Decanato obtenerNombreDecanato(Decanato u) {

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

    public String obtenerCodigoDecanato(Decanato u) {

        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * FROM decanato WHERE nombre = ?");
            ps.setString(1, u.getNombre());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getString("codigo");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Coordinacion obtenerNombreCoordinacion(Coordinacion u) {

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

    public Departamento obtenerNombreDepartamento(Departamento u) {

        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * FROM departamento WHERE codigo = ?");
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

    public ArrayList<SINAI> listarSINAI(String usbid) {

        ArrayList<SINAI> sinai = new ArrayList<SINAI>(0);
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * FROM SINAI WHERE usbid = ?");
            ps.setString(1, usbid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SINAI u = new SINAI();
                u.setNombre(rs.getString("nombre"));
                u.setFecha_inic(rs.getString("Fecha_inic"));
                u.setFecha_fin(rs.getString("Fecha_fin"));
                sinai.add(u);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sinai;
    }

    public ArrayList<CCT> listarCCT(String usbid) {

        ArrayList<CCT> cct = new ArrayList<CCT>(0);
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * FROM CCT WHERE usbid = ?");
            ps.setString(1, usbid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CCT u = new CCT();
                u.setIdent(rs.getString("ident"));
                u.setTitulo(rs.getString("titulo"));
                u.setFecha_inic(rs.getString("Fecha_inic"));
                u.setFecha_fin(rs.getString("Fecha_fin"));
                u.setCarrera(rs.getString("carrera"));
                u.setTipo(rs.getString("tipo"));
                cct.add(u);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cct;
    }

    public ArrayList<DACE> listarDACE(String usbid, int ano, String trim) {

        ArrayList<DACE> dace = new ArrayList<DACE>(0);
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * FROM DACE WHERE usbid = ? AND ano = ? AND trimestre = ?");
            ps.setString(1, usbid);
            ps.setInt(2, ano);
            ps.setString(3, trim);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DACE u = new DACE();
                u.setCodigo(rs.getString("codigo"));
                u.setNombre(rs.getString("nombre"));
                u.setUno(rs.getInt("uno"));
                u.setDos(rs.getInt("dos"));
                u.setTres(rs.getInt("tres"));
                u.setCuatro(rs.getInt("cuatro"));
                u.setCinco(rs.getInt("cinco"));
                u.setRetirados(rs.getInt("retirados"));
                dace.add(u);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dace;
    }

    public ArrayList<Materia> listarMateriasOfertadas(String id_departamento) {

        ArrayList<Materia> materias = new ArrayList<Materia>(0);
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT codigo, nombre, condicion, estado, creditos "
                    + "FROM oferta, materia "
                    + "WHERE codigo_departamento = ? AND "
                    + "codigo_materia = codigo AND "
                    + "condicion = 'activo' "
                    + "AND solicitud = 'no' "
                    + "ORDER BY estado;");

            ps.setString(1, id_departamento);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Materia m = new Materia();
                m.setCodigo(rs.getString("codigo"));
                m.setNombre(rs.getString("nombre"));
                m.setCondicion(rs.getString("condicion"));
                m.setEstado(rs.getString("estado"));
                m.setCreditos(rs.getString("creditos"));
                materias.add(m);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return materias;
    }

    public ArrayList<Profesor> listarProfesoresAEvaluar(String id_departamento) {

        ArrayList<Profesor> profesores = new ArrayList<Profesor>(0);
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * "
                    + "FROM pertenece, profesor "
                    + "WHERE codigo_departamento = ? "
                    + "AND usbid_profesor = usbid "
                    + "AND NOT EXISTS "
                    + "(SELECT * FROM evaluar"
                    + " WHERE usbid_profesor = usbid) "
                    + "ORDER BY lapso_contractual_inicio;");
            ps.setString(1, id_departamento);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Profesor p = new Profesor();
                p.setUsbid(rs.getString("usbid"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setLapso_contractual_inicio(rs.getString("lapso_contractual_inicio"));
                p.setNivel(rs.getString("nivel"));

                if (cantidadMateriasQueDicta(p.getUsbid(), id_departamento) != 0
                        && cantidadMateriasQueDicta(p.getUsbid(), id_departamento) == cantidadPlanillaLlena(p.getUsbid(), id_departamento)) {
                    profesores.add(p);
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return profesores;
    }

    public ArrayList<Profesor> listarProfesoresDepartamento(String id_departamento) {

        ArrayList<Profesor> profesores = new ArrayList<Profesor>(0);
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * "
                    + "FROM pertenece, profesor "
                    + "WHERE codigo_departamento = ? "
                    + "AND usbid_profesor = usbid "
                    + "ORDER BY usbid;");
            ps.setString(1, id_departamento);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Profesor p = new Profesor();
                p.setUsbid(rs.getString("usbid"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setLapso_contractual_inicio(rs.getString("lapso_contractual_inicio"));
                p.setNivel(rs.getString("nivel"));
                profesores.add(p);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return profesores;
    }

    public ArrayList<Profesor> listarProfesoresActivosDepartamento(String id_departamento) {

        ArrayList<Profesor> profesores = new ArrayList<Profesor>(0);
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * "
                    + "FROM pertenece, profesor "
                    + "WHERE codigo_departamento = ? "
                    + "AND usbid_profesor = usbid "
                    + "AND EXISTS ("
                    + "SELECT * FROM oferta as o, dicta as d "
                    + "WHERE usbid_profesor = usbid "
                    + "AND codigo_departamento = ? "
                    + "AND o.codigo_materia = d.codigo_materia"
                    + ") "
                    + "ORDER BY lapso_contractual_inicio;");
            ps.setString(1, id_departamento);
            ps.setString(2, id_departamento);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Profesor p = new Profesor();
                p.setUsbid(rs.getString("usbid"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setLapso_contractual_inicio(rs.getString("lapso_contractual_inicio"));
                p.setNivel(rs.getString("nivel"));
                profesores.add(p);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return profesores;
    }

    public ArrayList<Profesor> listarProfesoresPorMateriaCoordinacion(String codigo_mat, String codigo_coord) {

        ArrayList<Profesor> profesores = new ArrayList<Profesor>(0);
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT usbid, nombre, apellido "
                    + "FROM dicta, profesor, maneja "
                    + "WHERE codigo_materia = ? "
                    + "AND usbid_profesor = usbid AND codigo_coordinacion = ? "
                    + "ORDER BY codigo_materia;");
            ps.setString(1, codigo_mat);
            ps.setString(2, codigo_coord);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Profesor p = new Profesor();
                p.setUsbid(rs.getString("usbid"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                profesores.add(p);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return profesores;
    }

    public void enviarMemoEvaluarProfesor(String[] id_profesores, String id_departamento) {
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
//        Correo email = new Correo();
        Set<String> coords = new TreeSet<String>();

        try {
            for (int A = 0; A < id_profesores.length; A++) {
                String usbid_prof = id_profesores[A];
                ps = conexion.prepareStatement("SELECT dicta.codigo_materia, usbid_profesor, codigo_coordinacion "
                        + "FROM dicta, maneja "
                        + "WHERE usbid_profesor = ? "
                        + "AND dicta.codigo_materia = maneja.codigo_materia;");
                ps.setString(1, usbid_prof);
                ResultSet rs = ps.executeQuery();


                while (rs.next()) {
                    String materia = rs.getString("codigo_materia");
                    String profesor = rs.getString("usbid_profesor");
                    String coordinacion = rs.getString("codigo_coordinacion");
                    coords.add(coordinacion);

                    ps2 = conexion.prepareStatement("INSERT INTO evaluar VALUES (?,?,?,?);");
                    ps2.setString(1, coordinacion);
                    ps2.setString(2, profesor);
                    ps2.setString(3, materia);
                    ps2.setString(4, id_departamento);
                    ps2.executeUpdate();
                }
            }

//            String[] arregloCoords = coords.toArray(new String[0]);
//
//            email.setAsunto("SEP - Evaluación de Profesores");
//            email.setMensaje("Se ha solicitado la evaluacion de uno o más profesores a través del"
//                    + "\n Sistema de Evaluación de Profesores de la Universidad Simón Bolívar."
//                    + "\n\n Por favor, ingrese al sistema mediante el siguiente link:"
//                    + "\n\n LINK \n\n");
//            System.out.println(arregloCoords.length);
//            for (int i = 0; i < arregloCoords.length; i++) {
//                email.enviarNotificacion(arregloCoords[i] + "@usb.ve");
//                System.out.println(arregloCoords[i] + " " + i);
//
//            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean eliminarMateria(String id_materia) {
        PreparedStatement ps = null;

        try {
            ps = conexion.prepareStatement("UPDATE MATERIA SET condicion = 'desactivado' WHERE (codigo = ?);");
            ps.setString(1, id_materia);

            Integer i = ps.executeUpdate();

            return i > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean modificarMateria(Materia materia) {
        PreparedStatement ps = null;

        try {
            ps = conexion.prepareStatement("UPDATE MATERIA SET codigo = ?, nombre = ?, creditos = ? WHERE ( codigo = ? );");
            ps.setString(1, materia.getCodigo());
            ps.setString(2, materia.getNombre());
            ps.setString(3, materia.getCreditos());
            ps.setString(4, materia.getViejoCodigo());

            Integer i = ps.executeUpdate();

            return i > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Materia obtenerDatosMateria(Materia materia) {
        PreparedStatement ps = null;

        try {
            ps = conexion.prepareStatement("SELECT * FROM MATERIA WHERE codigo = ?;");
            ps.setString(1, materia.getCodigo());

            ResultSet rs = ps.executeQuery();
            Materia m = null;

            while (rs.next()) {
                m = new Materia();
                m.setCodigo(rs.getString("codigo"));
                m.setNombre(rs.getString("nombre"));
                m.setCreditos(rs.getString("creditos"));
                m.setSolicitud(rs.getString("solicitud"));
            }

            return m;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String obtenerDatosDepartamento(String departamento) {
        PreparedStatement ps = null;

        try {
            ps = conexion.prepareStatement("SELECT * FROM departamento WHERE nombre = ?;");
            ps.setString(1, departamento);

            ResultSet rs = ps.executeQuery();
            rs.next();

            return rs.getString("codigo");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean vincularMateriaCoordinacion(String cod_coord, String cod_materia) {
        PreparedStatement ps = null;

        try {
            ps = conexion.prepareStatement("INSERT INTO maneja VALUES (?,?)");
            ps.setString(1, cod_coord);
            ps.setString(2, cod_materia);
            Integer i = ps.executeUpdate();

            return i > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;

    }

    public boolean desvincularMateriaCoordinacion(String cod_coord, String cod_materia) {
        PreparedStatement ps = null;

        try {
            ps = conexion.prepareStatement("DELETE FROM maneja WHERE codigo_coordinacion = ? AND codigo_materia = ?");
            ps.setString(1, cod_coord);
            ps.setString(2, cod_materia);
            Integer i = ps.executeUpdate();

            return i > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;

    }

    public ArrayList<Materia> listarMateriasCoordinacion(String cod_coord) {
        PreparedStatement ps = null;
        ArrayList<Materia> materias = new ArrayList<Materia>(0);

        try {
            ps = conexion.prepareStatement("SELECT DISTINCT codigo, nombre "
                    + "FROM maneja, materia, oferta "
                    + "WHERE maneja.codigo_coordinacion = ? "
                    + "AND maneja.codigo_materia = codigo "
                    + "AND condicion = 'activo' "
                    + "AND estado = 'visible'");
            ps.setString(1, cod_coord);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia m = new Materia();
                m.setCodigo(rs.getString("codigo"));
                m.setNombre(rs.getString("nombre"));
                materias.add(m);
            }
            return materias;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public int contarEvaluacionesPendientes(String id_coordinacion) {

        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT Count(usbid_profesor) FROM evaluar WHERE codigo_coordinacion = ?;");
            ps.setString(1, id_coordinacion);

            ResultSet rs = ps.executeQuery();
            rs.next();
            return Integer.parseInt(rs.getString("count"));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public ArrayList<dicta> listarEvaluacionesPendientes(String id_coordinacion) {

        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ArrayList<dicta> dicta_materia = new ArrayList(0);
        String codigoMateria = "";
        try {
            ps = conexion.prepareStatement("SELECT DISTINCT codigo_materia, count(codigo_materia) FROM evaluar WHERE codigo_coordinacion = ? GROUP BY codigo_materia;");
            ps.setString(1, id_coordinacion);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dicta d = new dicta();
                d.setNumeroMateria(rs.getString("count"));
                codigoMateria = rs.getString("codigo_materia");
                d.setCodigoMateria(codigoMateria);

                System.out.println(codigoMateria + " " + d.getNumeroMateria());

                ps2 = conexion.prepareStatement("SELECT codigo_materia, usbid, nombre, apellido FROM evaluar, profesor WHERE codigo_coordinacion = ? AND usbid = usbid_profesor AND codigo_materia= ?;");
                ps2.setString(1, id_coordinacion);
                ps2.setString(2, codigoMateria);

                ResultSet rs2 = ps2.executeQuery();

                while (rs2.next()) {
                    Profesor p = new Profesor();
                    p.setUsbid(rs2.getString("usbid"));
                    p.setNombre(rs2.getString("nombre"));
                    p.setApellido(rs2.getString("apellido"));
                    d.addProfesor(p);
                }
                d.setPrimerProfesor();
                System.out.println("primer profesor: " + d.getPrimerProfesor().getNombre());
                dicta_materia.add(d);
            }
            return dicta_materia;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean registrarMateria(Materia m, String id_departamento) {

        PreparedStatement ps1;
        PreparedStatement ps2;

        try {
            ps1 = conexion.prepareStatement("INSERT INTO MATERIA(codigo, nombre, creditos) "
                    + "VALUES (?, ?, ?);");
            ps1.setString(1, m.getCodigo());
            ps1.setString(2, m.getNombre());
            ps1.setString(3, m.getCreditos());

            ps2 = conexion.prepareStatement("INSERT INTO OFERTA"
                    + "(codigo_materia, codigo_departamento) "
                    + "VALUES (?, ?);");
            ps2.setString(1, m.getCodigo());
            ps2.setString(2, id_departamento);

            Integer i = ps1.executeUpdate();
            Integer j = ps2.executeUpdate();


            return i > 0 && j > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean actualizarDepartamento(Departamento d) {
        PreparedStatement ps = null;
        try {

            ps = conexion.prepareStatement("UPDATE departamento SET nombre = ? WHERE ( codigo = ? )");

            ps.setString(1, d.getNombre());
            ps.setString(2, d.getCodigo());
            Integer s = ps.executeUpdate();

            return s > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean registrarDepartamento(Departamento d) {
        PreparedStatement ps;

        try {
            ps = conexion.prepareStatement("INSERT INTO departamento VALUES (?,?,?)");
            ps.setString(1, d.getCodigo());
            ps.setString(2, d.getNombre());
            ps.setString(3, "activo");
            Integer i = ps.executeUpdate();

            return i > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;

    }

    public boolean cambiarStatusMateria(Materia m) {

        PreparedStatement ps;
        try {

            ps = conexion.prepareStatement("UPDATE materia SET estado = ? WHERE ( codigo = ? )");

            ps.setString(1, m.getEstado());
            ps.setString(2, m.getCodigo());
            Integer s = ps.executeUpdate();

            return s > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean eliminarDepartamento(Departamento d) {
        PreparedStatement ps;

        try {
            ps = conexion.prepareStatement("UPDATE DEPARTAMENTO SET condicion = 'desactivado' WHERE (codigo = ?);");
            ps.setString(1, d.getCodigo());

            Integer i = ps.executeUpdate();

            return i > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public int contarSolicitudesPendientesDepartamento(String id_departamento) {

        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT Count(codigo_materia) "
                    + "FROM solicita_apertura "
                    + "WHERE codigo_departamento = ?;");
            ps.setString(1, id_departamento);

            ResultSet rs = ps.executeQuery();
            rs.next();
            return Integer.parseInt(rs.getString("count"));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public boolean solicitudRegistrarMateria(Materia m, String id_departamento, String id_coordinacion) {

        PreparedStatement ps1;
        PreparedStatement ps2;

        try {
            ps1 = conexion.prepareStatement("INSERT INTO MATERIA(codigo, nombre, creditos, solicitud) "
                    + "VALUES (?, ?, ?, ?);");
            ps1.setString(1, m.getCodigo());
            ps1.setString(2, m.getNombre());
            ps1.setString(3, m.getCreditos());
            ps1.setString(4, "si");

            ps2 = conexion.prepareStatement("INSERT INTO solicita_apertura"
                    + "(codigo_materia, codigo_coordinacion, codigo_departamento, mensaje) "
                    + "VALUES (?, ?, ?, ?);");
            ps2.setString(1, m.getCodigo());
            ps2.setString(2, id_coordinacion);
            ps2.setString(3, id_departamento);
            ps2.setString(4, m.getMensaje());

            Integer i = ps1.executeUpdate();
            Integer j = ps2.executeUpdate();


            return i > 0 && j > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<Materia> listarMateriasSolicitadasDepartamento(String id_departamento) {

        ArrayList<Materia> materias = new ArrayList<Materia>(0);
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT m.codigo, m.nombre, c.nombre, sa.mensaje "
                    + "FROM solicita_apertura AS sa, materia AS m, coordinacion AS c "
                    + "WHERE sa.codigo_departamento = ? "
                    + "AND sa.codigo_materia = m.codigo "
                    + "AND m.solicitud = 'si' "
                    + "AND sa.codigo_coordinacion = c.codigo "
                    + "ORDER BY sa.codigo_coordinacion;");
            ps.setString(1, id_departamento);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia m = new Materia();
                m.setCodigo(rs.getString(1));
                m.setNombre(rs.getString(2));
                m.setCoordinacion(rs.getString(3));
                m.setMensaje(rs.getString(4));
                materias.add(m);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return materias;
    }

    public Materia obtenerMensaje(String id_departamento) {

        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement("SELECT sa.mensaje, sa.codigo_coordinacion "
                    + "FROM solicita_apertura AS sa, materia AS m, coordinacion AS c "
                    + "WHERE sa.codigo_departamento = ? "
                    + "AND sa.codigo_materia = m.codigo "
                    + "AND m.solicitud = 'si' "
                    + "AND sa.codigo_coordinacion = c.codigo "
                    + "ORDER BY sa.codigo_coordinacion;");
            ps.setString(1, id_departamento);
            ResultSet rs = ps.executeQuery();

            Materia m = new Materia();

            while (rs.next()) {
                m.setMensaje(rs.getString(1));
                m.setCoordinacion(rs.getString(2));
                return m;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean aprobarSolicitudMateria(Materia m, String id_departamento) {

        PreparedStatement ps0, ps1, ps3, ps4, ps5;

        try {
            
            ps0 = conexion.prepareStatement("UPDATE MATERIA SET codigo = ? WHERE codigo = ?;");
            ps0.setString(1, m.getCodigo());
            ps0.setString(2, m.getViejoCodigo());
            
            ps1 = conexion.prepareStatement("UPDATE MATERIA SET solicitud = 'no' WHERE codigo = ?;");
            ps1.setString(1, m.getCodigo());

            ps3 = conexion.prepareStatement("INSERT INTO maneja (codigo_coordinacion, codigo_materia) "
                    + "VALUES (?,?);");
            ps3.setString(1, m.getCoordinacion());
            ps3.setString(2, m.getCodigo());

            ps4 = conexion.prepareStatement("DELETE FROM solicita_apertura WHERE codigo_materia = ?;");
            ps4.setString(1, m.getCodigo());

            ps5 = conexion.prepareStatement("INSERT INTO oferta (codigo_materia, codigo_departamento) "
                    + "VALUES (?,?);");
            ps5.setString(1, m.getCodigo());
            ps5.setString(2, id_departamento);

            Integer i = ps0.executeUpdate();
            Integer j = ps1.executeUpdate();
            Integer k = ps3.executeUpdate();
            Integer l = ps4.executeUpdate();
            Integer n = ps5.executeUpdate();

            return i > 0 && j > 0 && k > 0 && l > 0 && n > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean negarSolicitudMateria(Materia m) {

        PreparedStatement ps;

        try {
            ps = conexion.prepareStatement("DELETE FROM MATERIA WHERE codigo = ?;");
            ps.setString(1, m.getViejoCodigo());

            Integer i = ps.executeUpdate();

            return i > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public ArrayList<rendimientoProf> obtenerRendimientoProfesor(String id_profesor, String id_departamento) {
        PreparedStatement ps;
        ArrayList<rendimientoProf> rendimiento = new ArrayList<rendimientoProf>(0);
        try {
            ps = conexion.prepareStatement("SELECT DISTINCT "
                    + "r.usbid_profesor, trimestre, ano, r.codigo_materia, "
                    + "total_estudiantes, nota_prom, nota1, nota2, "
                    + "nota3, nota4, nota5, retirados, m.nombre "
                    + "FROM rendimiento as r, oferta as o, dicta as d, materia as m "
                    + "WHERE r.usbid_profesor = ? "
                    + "AND o.codigo_departamento = ? "
                    + "AND r.codigo_materia = d.codigo_materia "
                    + "AND d.codigo_materia = o.codigo_materia "
                    + "AND r.usbid_profesor = d.usbid_profesor "
                    + "AND m.codigo = o.codigo_materia;");
            ps.setString(1, id_profesor);
            ps.setString(2, id_departamento);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rendimientoProf r = new rendimientoProf();
                r.setUsbid_profesor(id_profesor);
                r.setTrimestre(rs.getString("trimestre"));
                r.setAno(rs.getInt("ano"));
                r.setCodigo_materia(rs.getString("codigo_materia"));
                r.setNombre_materia(rs.getString("nombre"));
                r.setTotal_estudiantes(rs.getInt("total_estudiantes"));
                r.setNota_prom(rs.getFloat("nota_prom"));
                r.setNota1(rs.getInt("nota1"));
                r.setNota2(rs.getInt("nota2"));
                r.setNota3(rs.getInt("nota3"));
                r.setNota4(rs.getInt("nota4"));
                r.setNota5(rs.getInt("nota5"));
                r.setRetirados(rs.getInt("retirados"));
                r.setUsbid_profesor(id_profesor);
                rendimiento.add(r);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rendimiento;
    }

    public ArrayList<Materia> obtenerSolicitudEvaluacionesProfesor(String id_profesor, String id_departamento) {
        PreparedStatement ps;
        ArrayList<Materia> materia = new ArrayList<Materia>(0);
        try {
            ps = conexion.prepareStatement("SELECT * "
                    + "FROM materia as m, dicta as d, oferta as o "
                    + "WHERE usbid_profesor = ? "
                    + "AND o.codigo_departamento = ? "
                    + "AND o.codigo_materia = d.codigo_materia "
                    + "AND d.planilla_llena = 'N' "
                    + "AND m.codigo = o.codigo_materia;");
            ps.setString(1, id_profesor);
            ps.setString(2, id_departamento);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia m = new Materia();
                m.setCodigo(rs.getString(1));
                m.setNombre(rs.getString(2));
                String periodo = rs.getString("periodo");
                if (periodo.equals("SD")) {
                    m.setPeriodo("Septiembre-Diciembre");
                    m.setPeriodoSD("SD");
                } else if (periodo.equals("EM")) {
                    m.setPeriodo("Enero-Marzo");
                    m.setPeriodoEM("EM");
                } else if (periodo.equals("AJ")) {
                    m.setPeriodo("Abril-Julio");
                    m.setPeriodoAJ("AJ");
                } else if (periodo.equals("V")) {
                    m.setPeriodo("Intensivo");
                    m.setPeriodoV("V");
                }

                materia.add(m);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return materia;
    }

    public boolean agregarRendimientoProfesor(rendimientoProf u, String id_departamento) {

        PreparedStatement ps1, ps2, ps3;

        try {
            ps1 = conexion.prepareStatement("INSERT INTO rendimiento VALUES(?,?,?,?,?,?,?,?,?,?,?,?);");
            ps1.setString(1, u.getUsbid_profesor());
            ps1.setString(2, u.getCodigo_materia());
            ps1.setString(3, u.getTrimestre());
            ps1.setInt(4, u.getAno());
            ps1.setInt(5, u.getTotal_estudiantes());
            ps1.setFloat(6, u.getNota_prom());
            ps1.setInt(7, u.getNota1());
            ps1.setInt(8, u.getNota2());
            ps1.setInt(9, u.getNota3());
            ps1.setInt(10, u.getNota4());
            ps1.setInt(11, u.getNota5());
            ps1.setInt(12, u.getRetirados());

            ps2 = conexion.prepareStatement("UPDATE dicta "
                    + "SET planilla_llena = 'S' "
                    + "WHERE usbid_profesor = ? "
                    + "AND codigo_materia = ?;");
            ps2.setString(1, u.getUsbid_profesor());
            ps2.setString(2, u.getCodigo_materia());

            if (0 == cantidadPlanillaVacia(u.getUsbid_profesor(), null)) {
                ps3 = conexion.prepareStatement("UPDATE pertenece "
                        + "SET evaluado = 'S' "
                        + "WHERE usbid_profesor = ? "
                        + "AND codigo_departamento = ?;");
                ps3.setString(1, u.getUsbid_profesor());
                ps3.setString(2, id_departamento);
                Integer k = ps3.executeUpdate();
            }

            //ps3 = conexion.prepareStatement();
            Integer i = ps1.executeUpdate();
            Integer j = ps2.executeUpdate();

            return i > 0 && j > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public int cantidadPlanillaLlena(String id_profesor, String id_departamento) {

        PreparedStatement ps1;

        try {
            ps1 = conexion.prepareStatement("SELECT count(usbid_profesor) "
                    + "FROM dicta as d, oferta as o "
                    + "WHERE d.usbid_profesor = ? "
                    + "AND o.codigo_departamento = ? "
                    + "AND o.codigo_materia = d.codigo_materia "
                    + "AND d.planilla_llena = 'S';");
            ps1.setString(1, id_profesor);
            ps1.setString(2, id_departamento);

            ResultSet rs = ps1.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
        return -1;
    }

    public int cantidadPlanillaVacia(String id_profesor, String id_departamento) {

        PreparedStatement ps1;

        try {
            ps1 = conexion.prepareStatement("SELECT count(usbid_profesor) "
                    + "FROM dicta as d, oferta as o "
                    + "WHERE d.usbid_profesor = ? "
                    + "AND planilla_llena = 'N' "
                    + "AND d.codigo_materia = o.codigo_materia "
                    + "AND codigo_departamento = ? ");
            ps1.setString(1, id_profesor);
            ps1.setString(2, id_departamento);

            ResultSet rs = ps1.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
        return -1;
    }

    public int cantidadMateriasQueDicta(String id_profesor, String id_departamento) {

        PreparedStatement ps1;

        try {
            ps1 = conexion.prepareStatement("SELECT count(usbid_profesor) "
                    + "FROM dicta as d, oferta as o "
                    + "WHERE d.usbid_profesor = ? "
                    + "AND o.codigo_departamento = ? "
                    + "AND o.codigo_materia = d.codigo_materia;");
            ps1.setString(1, id_profesor);
            ps1.setString(2, id_departamento);

            ResultSet rs = ps1.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
        return -1;
    }

    public rendimientoProf obtenerPlanillaEvaluacionProfesor(String id_profesor, String id_materia) {
        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement("SELECT * "
                    + "FROM rendimiento, materia "
                    + "WHERE usbid_profesor = ? "
                    + "AND codigo_materia = ? "
                    + "AND codigo_materia = codigo;");
            ps.setString(1, id_profesor);
            ps.setString(2, id_materia);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rendimientoProf r = new rendimientoProf();
                r.setCodigo_materia(rs.getString("codigo_materia"));
                r.setTrimestre(rs.getString("trimestre"));
                r.setAno(rs.getInt("ano"));
                r.setTotal_estudiantes(rs.getInt("total_estudiantes"));
                r.setNota1(rs.getInt("nota1"));
                r.setNota2(rs.getInt("nota2"));
                r.setNota3(rs.getInt("nota3"));
                r.setNota4(rs.getInt("nota4"));
                r.setNota5(rs.getInt("nota5"));
                r.setRetirados(rs.getInt("retirados"));
                r.setNombre_materia(rs.getString("nombre"));
                return r;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean modificarRendimientoProfesor(rendimientoProf r, String id_departamento) {
        PreparedStatement ps1, ps2;

        try {

            ps1 = conexion.prepareStatement("DELETE FROM RENDIMIENTO "
                    + "WHERE trimestre = ? "
                    + "AND codigo_materia = ? "
                    + "AND usbid_profesor = ? "
                    + "AND ano = ?;");
            ps1.setString(1, r.getViejoTrimestre());
            ps1.setString(2, r.getCodigo_materia());
            ps1.setString(3, r.getUsbid_profesor());
            ps1.setInt(4, r.getViejoAno());

            Integer i = ps1.executeUpdate();

            boolean agregado = agregarRendimientoProfesor(r, id_departamento);

            return i > 0 && agregado;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public rendimientoProf obtenerEvaluacion(dicta d) {
        PreparedStatement ps;
        rendimientoProf evaluacion = null;
        try {
            ps = conexion.prepareStatement("SELECT sum(total_estudiantes), "
                    + "avg(nota_prom) as np, sum(nota1) as n1, sum(nota2) as n2, "
                    + "sum(nota3) as n3, sum(nota4) as n4, sum(nota5) as n5, "
                    + "sum(retirados) as r "
                    + "FROM rendimiento "
                    + "WHERE codigo_materia = ?;");
            String codigo_materia = d.getCodigoMateria();
            ps.setString(1, codigo_materia);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                evaluacion = new rendimientoProf();
                evaluacion.setCodigo_materia(codigo_materia);
                evaluacion.setTotal_estudiantes(rs.getInt("sum"));
                evaluacion.setNota_prom(rs.getFloat("np"));
                evaluacion.setNota1(rs.getInt("n1"));
                evaluacion.setNota2(rs.getInt("n2"));
                evaluacion.setNota3(rs.getInt("n3"));
                evaluacion.setNota4(rs.getInt("n4"));
                evaluacion.setNota5(rs.getInt("n5"));
                evaluacion.setRetirados(rs.getInt("r"));

            }
            return evaluacion;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> obtenerDepartamentosQueNoEvaluaron(String codigo_materia) {
//        select * from dicta as d, pertenece as p, departamento as dep where d.codigo_materia = 'CI6116' and p.usbid_profesor = d.usbid_profesor and dep.codigo = p.codigo_departamento and d.usbid_profesor not in (select usbid_profesor from rendimiento where codigo_materia = 'CI6116');
        PreparedStatement ps;
        ArrayList<String> departamentos = new ArrayList<String>(0);
        try {
            ps = conexion.prepareStatement("SELECT * "
                    + "FROM dicta as d, pertenece as p, departamento as dep "
                    + "WHERE d.codigo_materia = ? "
                    + "AND p.usbid_profesor = d.usbid_profesor "
                    + "AND dep.codigo = p.codigo_departamento "
                    + "AND d.usbid_profesor NOT IN "
                    + "(SELECT usbid_profesor "
                    + "FROM rendimiento "
                    + "WHERE codigo_materia = ?);");
            ps.setString(1, codigo_materia);
            ps.setString(2, codigo_materia);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String dep = rs.getString("nombre");
                System.out.println("-----> " + dep);
                departamentos.add(dep);
            }

            return departamentos;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return departamentos;
    }

    public int cantidadEvaluados(String codigo_materia, String codigo_coordinacion) {
        PreparedStatement ps;
        int cantidad = 0;
        try {
            ps = conexion.prepareStatement("SELECT count(usbid_profesor) "
                    + "FROM evaluar "
                    + "WHERE codigo_materia = ? "
                    + "AND codigo_coordinacion = ?;");
            ps.setString(1, codigo_materia);
            ps.setString(2, codigo_coordinacion);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cantidad = rs.getInt("count");
            }
            return cantidad;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public int cantidadProfesoresDictanMateria(String codigo_materia) {
        PreparedStatement ps;
        int cantidad = 0;
        try {
            ps = conexion.prepareStatement("SELECT count(usbid_profesor) "
                    + "FROM dicta "
                    + "WHERE codigo_materia = ?;");
            ps.setString(1, codigo_materia);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cantidad = rs.getInt("count");
            }
            return cantidad;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public boolean agregarProfesor(Profesor p, String id_departamento) {
        PreparedStatement ps1, ps2, ps3;

        try {
            ps1 = conexion.prepareStatement("INSERT INTO usuario "
                    + "VALUES (?,?,?)");
            ps1.setString(1, p.getUsbid());
            ps1.setString(2, "profesor");
            ps1.setString(3, p.getUsbid());

            Integer j = ps1.executeUpdate();

            ps2 = conexion.prepareStatement("INSERT INTO profesor "
                    + "(usbid, nombre, apellido, cedula, genero, email) "
                    + "VALUES (?,?,?,?,?,?)");
            ps2.setString(1, p.getUsbid());
            ps2.setString(2, p.getNombre());
            ps2.setString(3, p.getApellido());
            ps2.setString(4, p.getCedula());
            ps2.setString(5, p.getGenero());
            ps2.setString(6, p.getEmail());

            Integer i = ps2.executeUpdate();

            ps3 = conexion.prepareStatement("INSERT INTO pertenece "
                    + "(usbid_profesor, codigo_departamento) "
                    + "VALUES (?,?);");
            ps3.setString(1, p.getUsbid());
            ps3.setString(2, id_departamento);

            Integer k = ps3.executeUpdate();

            return i > 0 && j > 0 && k > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public ArrayList<Materia> consultarMateriasSeleccionadas(String[] materias_seleccionadas) {
        PreparedStatement ps1;
        ArrayList<Materia> materias = new ArrayList<Materia>(0);
        try {

            String lista_materias = "";

            for (int i = 0; i < materias_seleccionadas.length; i++) {
                if (i == materias_seleccionadas.length - 1) {
                    lista_materias = lista_materias + "codigo = '"
                            + materias_seleccionadas[i] + "'";
                } else {
                    lista_materias = lista_materias + "codigo = '"
                            + materias_seleccionadas[i] + "' OR ";
                }
            }

            ps1 = conexion.prepareStatement("SELECT * "
                    + "FROM materia "
                    + "WHERE " + lista_materias + ";");

            ResultSet rs = ps1.executeQuery();

            while (rs.next()) {
                Materia m = new Materia();
                m.setCodigo(rs.getString("codigo"));
                m.setNombre(rs.getString("nombre"));
                materias.add(m);
            }

            return materias;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return materias;
    }

    public boolean agregarDicta(Profesor profesor, String id_materia, String periodo) {
        PreparedStatement ps1;
        try {

            ps1 = conexion.prepareStatement("INSERT INTO dicta "
                    + "(usbid_profesor,codigo_materia,periodo) "
                    + "VALUES (?,?,?)");
            ps1.setString(1, profesor.getUsbid());
            ps1.setString(2, id_materia);
            ps1.setString(3, periodo);

            Integer j = ps1.executeUpdate();

            return j > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}