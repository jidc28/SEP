package DBMS;

import Clases.*;
import Forms.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public boolean agregarUsuario(String usbid, String tipousuario) {

        PreparedStatement ps;

        try {
            ps = conexion.prepareStatement("INSERT INTO usuario "
                    + "VALUES (?, ?, ?);");
            ps.setString(1, usbid);
            ps.setString(2, tipousuario);
            ps.setString(3, usbid);

            Integer i = ps.executeUpdate();

            return i > 0;

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

    public ArrayList<Coordinacion> listarCoordinacionesAdscritas(String id_decanato, String opcion) {

        ArrayList<Coordinacion> coords = new ArrayList<Coordinacion>(0);
        PreparedStatement ps, ps1;
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
                String codigo_coordinacion = rs.getString("codigo");
                u.setCodigo(codigo_coordinacion);
                u.setNombre(rs.getString("nombre"));
                if (opcion != null) {
                    ps1 = conexion.prepareStatement("SELECT count(codigo_coordinacion) "
                            + "FROM evaluar as e "
                            + "WHERE e.codigo_coordinacion = ? "
                            + "AND e.codigo_coordinacion IN ("
                            + "SELECT codigo_coordinacion as ec "
                            + "FROM evaluacion as ec "
                            + "WHERE ec.usbid_profesor = e.usbid_profesor "
                            + ");");

                    ps1.setString(1, codigo_coordinacion);

                    ResultSet rs1 = ps1.executeQuery();

                    rs1.next();
                    u.setEvaluaciones(rs1.getString("count"));
                }
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

    public boolean eliminarUsuario(String id_usuario) {
        PreparedStatement ps;
        try {

            ps = conexion.prepareStatement("DELETE FROM usuario WHERE ( usbid = ? );");
            ps.setString(1, id_usuario);

            Integer s = ps.executeUpdate();

            return s > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean eliminarProfesor(Profesor p) {
        PreparedStatement ps;
        try {

            ps = conexion.prepareStatement("DELETE FROM profesor WHERE ( usbid = ? );");
            ps.setString(1, p.getUsbid());

            Integer r = ps.executeUpdate();
            boolean usuario_eliminado = eliminarUsuario(p.getUsbid());

            return usuario_eliminado && r > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean actualizarInfoProfesor(Profesor u) {

        PreparedStatement psAgregar1 = null;

        try {
            psAgregar1 = conexion.prepareStatement("UPDATE profesor "
                    + "SET email = ?, email_personal = ?, nivel = ?, "
                    + "jubilado = ?, lapso_contractual_inicio = ?, "
                    + "lapso_contractual_fin = ? "
                    + "WHERE usbid = ? ");
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

    public boolean modificarProfesor(Profesor p) {

        PreparedStatement ps0, ps1 = null;

        try {
            ps0 = conexion.prepareStatement("UPDATE profesor "
                    + "SET usbid = ?, cedula = ?, nombre = ?, apellido = ?,"
                    + "genero = ?, email = ?, nivel = ? "
                    + "WHERE usbid = ?;");
            ps0.setString(1, p.getUsbid());
            ps0.setString(2, p.getCedula());
            ps0.setString(3, p.getNombre());
            ps0.setString(4, p.getApellido());
            ps0.setString(5, p.getGenero());
            ps0.setString(6, p.getEmail());
            ps0.setString(7, p.getNivel());
            ps0.setString(8, p.getUsbidViejo());

            ps1 = conexion.prepareStatement("UPDATE usuario "
                    + "SET usbid = ?, contrasena = ? "
                    + "WHERE usbid = ?;");
            ps1.setString(1, p.getUsbid());
            ps1.setString(2, p.getUsbid());
            ps1.setString(3, p.getUsbidViejo());

            Integer j = ps1.executeUpdate();
            Integer i = ps0.executeUpdate();

            return i > 0 && j > 0;

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

            ps = conexion.prepareStatement("UPDATE usuario "
                    + "SET tipousuario = ?, departamento = ? "
                    + "WHERE ( usbid = ? )");

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
            psEliminar1 = conexion.prepareStatement("DELETE FROM coordinacion AS c "
                    + "WHERE (c.codigo = ?);");
            psEliminar1.setString(1, u.getCodigo());

            Integer i = psEliminar1.executeUpdate();
            boolean usuario_eliminado = eliminarUsuario(u.getCodigo());

            return i > 0 && usuario_eliminado;

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

        PreparedStatement ps;

        try {
            ps = conexion.prepareStatement("INSERT INTO decanato(codigo, nombre) "
                    + "VALUES (?, ?);");
            ps.setString(1, u.getCodigo());
            ps.setString(2, u.getNombre());

            Integer i = ps.executeUpdate();
            boolean usuario_agregado = agregarUsuario(u.getCodigo(), "decanato");

            return i > 0 && usuario_agregado;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean eliminarDecanato(Decanato decanato) {

        PreparedStatement ps0;

        try {

            ps0 = conexion.prepareStatement("DELETE FROM DECANATO "
                    + "WHERE codigo = ?;");
            ps0.setString(1, decanato.getCodigo());

            Integer i = ps0.executeUpdate();
            boolean usuario_eliminado = eliminarUsuario(decanato.getCodigo());

            return i > 0 && usuario_eliminado;

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
            ps1 = conexion.prepareStatement("INSERT INTO COORDINACION(codigo, nombre) "
                    + "VALUES (?, ?);");
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

            boolean usuario_agregado = agregarUsuario(c.getCodigo(), "coordinacion");

            return i > 0 && j > 0 && usuario_agregado;

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

    public ArrayList<Profesor> listarProfesoresAEvaluarDepartamento(String id_departamento) {

        ArrayList<Profesor> profesores = new ArrayList<Profesor>(0);
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT DISTINCT usbid, nombre, apellido "
                    + "FROM profesor, evaluacion as ec, pertenece as p "
                    + "WHERE codigo_departamento = ? "
                    + "AND p.usbid_profesor = usbid "
                    + "AND ec.usbid_profesor = usbid "
                    + "AND revisado_departamento = 'no';");
            ps.setString(1, id_departamento);
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

    public ArrayList<Profesor> listarProfesoresAEvaluar(String id_departamento) {

        ArrayList<Profesor> profesores = new ArrayList<Profesor>(0);
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * "
                    + "FROM pertenece, profesor "
                    + "WHERE codigo_departamento = ? "
                    + "AND usbid_profesor = usbid "
                    + "AND NOT EXISTS "
                    + "(SELECT * FROM evaluar "
                    + "WHERE usbid_profesor = usbid) "
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

    public ArrayList<Profesor> listarProfesoresEvaluadosDepartamento(String id_departamento) {

        ArrayList<Profesor> profesores = new ArrayList<Profesor>(0);
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT DISTINCT usbid, nombre, apellido "
                    + "FROM pertenece AS pe, profesor AS pr, evaluado AS ev "
                    + "WHERE pe.codigo_departamento = ? "
                    + "AND pe.usbid_profesor = usbid "
                    + "AND pe.usbid_profesor = ev.usbid_profesor "
                    + "ORDER BY usbid;");
            ps.setString(1, id_departamento);
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

    public int contarEvaluacionesPendientesCoordinacion(String id_coordinacion) {

        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement("SELECT count(usbid_profesor) "
                    + "FROM evaluar "
                    + "WHERE codigo_coordinacion = ? "
                    + "AND usbid_profesor NOT IN ("
                    + "SELECT usbid_profesor "
                    + "FROM evaluacion "
                    + "WHERE codigo_coordinacion = ?"
                    + ");");
            ps.setString(1, id_coordinacion);
            ps.setString(2, id_coordinacion);

            ResultSet rs = ps.executeQuery();
            rs.next();
            return Integer.parseInt(rs.getString("count"));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int contarEvaluacionesPendientesDepartamento(String id_departamento, String profesor) {

        PreparedStatement ps;
        try {

            if (profesor == null) {
                ps = conexion.prepareStatement("SELECT usbid_profesor, count(usbid_profesor) "
                        + "FROM evaluar e "
                        + "WHERE e.codigo_departamento = ? "
                        + "AND e.usbid_profesor IN ("
                        + "SELECT ec.usbid_profesor "
                        + "FROM evaluacion as ec "
                        + "WHERE ec.revisado_departamento <> 'si'"
                        + ")"
                        + "GROUP BY e.usbid_profesor;");
                ps.setString(1, id_departamento);
            } else {
                ps = conexion.prepareStatement("SELECT usbid_profesor, count(usbid_profesor) "
                        + "FROM evaluar e "
                        + "WHERE e.codigo_departamento = ? "
                        + "AND e.usbid_profesor NOT IN ("
                        + "SELECT ec.usbid_profesor "
                        + "FROM evaluacion as ec "
                        + "WHERE ec.revisado_departamento = 'si'"
                        + ")"
                        + "AND usbid_profesor = ? "
                        + "GROUP BY usbid_profesor;");
                ps.setString(1, id_departamento);
                ps.setString(2, profesor);
            }

            ResultSet rs = ps.executeQuery();

            int pendiente = 0;

            while (rs.next()) {
                int prof_evaluado = Integer.parseInt(rs.getString("count"));

                if (profesor == null) {
                    String usbid_profesor = rs.getString("usbid_profesor");
                    int por_evaluar = contarEvaluacionesDepartamento(id_departamento, usbid_profesor);

                    if (por_evaluar == prof_evaluado) {
                        pendiente++;
                    }
                } else {
                    return prof_evaluado;
                }
            }

            return pendiente;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int contarEvaluacionesDepartamento(String id_departamento, String usbid_profesor) {

        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT count(usbid_profesor) "
                    + "FROM evaluar "
                    + "WHERE codigo_departamento = ? "
                    + "AND usbid_profesor = ?;");
            ps.setString(1, id_departamento);
            ps.setString(2, usbid_profesor);

            ResultSet rs = ps.executeQuery();
            rs.next();
            return Integer.parseInt(rs.getString("count"));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public ArrayList<dicta> listarEvaluacionesPendientes(Coordinacion coordinacion,
            String usbid_profesor) {

        PreparedStatement ps, ps2;
        ArrayList<dicta> dicta_materia = new ArrayList(0);
        String codigoMateria;
        String id_coordinacion = coordinacion.getCodigo();
        try {
            ps = conexion.prepareStatement("SELECT DISTINCT codigo_materia, nombre "
                    + "FROM evaluar as e, materia as m "
                    + "WHERE e.codigo_coordinacion = ? "
                    + "AND e.usbid_profesor = ? "
                    + "AND m.codigo = e.codigo_materia "
                    + "AND e.usbid_profesor NOT IN ("
                    + "SELECT ec.usbid_profesor "
                    + "FROM evaluacion as ec "
                    + "WHERE revisado_decanato = 'si' "
                    + ");");
            ps.setString(1, id_coordinacion);
            ps.setString(2, usbid_profesor);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dicta d = new dicta();
                codigoMateria = rs.getString("codigo_materia");
                d.setCodigoMateria(codigoMateria);
                d.setOpcion(rs.getString("nombre"));

                ps2 = conexion.prepareStatement("SELECT codigo_materia, usbid, "
                        + "nombre, apellido "
                        + "FROM evaluar, profesor "
                        + "WHERE codigo_coordinacion = ? "
                        + "AND usbid = usbid_profesor "
                        + "AND codigo_materia = ?;");
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
                dicta_materia.add(d);
            }
            return dicta_materia;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<dicta> listarEvaluacionesPendientes(String id_coordinacion, String usbid_profesor) {

        PreparedStatement ps, ps2;
        ArrayList<dicta> dicta_materia = new ArrayList(0);
        String codigoMateria;
        try {
            ps = conexion.prepareStatement("SELECT DISTINCT codigo_materia, nombre "
                    + "FROM evaluar as e, materia as m "
                    + "WHERE e.codigo_coordinacion = ? "
                    + "AND e.comentado_coordinacion = 'no' "
                    + "AND codigo_materia = codigo "
                    + "AND e.usbid_profesor = ? "
                    + "AND NOT EXISTS ("
                    + "SELECT * "
                    + "FROM evaluacion "
                    + "WHERE codigo_coordinacion = ? "
                    + "AND usbid_profesor = e.usbid_profesor"
                    + ");");
            ps.setString(1, id_coordinacion);
            ps.setString(2, usbid_profesor);
            ps.setString(3, id_coordinacion);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dicta d = new dicta();
                codigoMateria = rs.getString("codigo_materia");
                d.setCodigoMateria(codigoMateria);
                d.setOpcion(rs.getString("nombre"));

                ps2 = conexion.prepareStatement("SELECT codigo_materia, usbid, "
                        + "nombre, apellido "
                        + "FROM evaluar, profesor "
                        + "WHERE codigo_coordinacion = ? "
                        + "AND usbid = usbid_profesor "
                        + "AND codigo_materia = ? "
                        + "AND comentado_coordinacion = 'no';");
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
                dicta_materia.add(d);
            }
            return dicta_materia;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<rendimientoProf> listarEvaluacionesEnviadasCoordinacion(String id_coordinacion,
            int ano, String trimestre) {

        PreparedStatement ps, ps2;
        ArrayList<rendimientoProf> rendimientos = new ArrayList(0);

        try {
            ps = conexion.prepareStatement("SELECT codigo, recomendado, "
                    + "observaciones, nombre, usbid_profesor, ano, trimestre "
                    + "FROM evaluado, materia "
                    + "WHERE codigo_coordinacion = ? "
                    + "AND ano = ? "
                    + "AND trimestre = ? "
                    + "AND codigo_materia = codigo;");
            ps.setString(1, id_coordinacion);
            ps.setInt(2, ano);
            ps.setString(3, trimestre);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                rendimientoProf rendimiento = new rendimientoProf();
                rendimiento.setCodigo_materia(rs.getString("codigo"));
                rendimiento.setRecomendado(rs.getString("recomendado"));
                rendimiento.setObservaciones_c(rs.getString("observaciones"));
                rendimiento.setNombre_materia(rs.getString("nombre"));
                rendimiento.setUsbid_profesor(rs.getString("usbid_profesor"));
                rendimiento.setAno(rs.getInt("ano"));
                rendimiento.setTrimestre(rs.getString("trimestre"));

                rendimientos.add(rendimiento);
            }

            return rendimientos;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<rendimientoProf> listarEvaluacionesEnviadasDepartamento(String id_departamento,
            int ano, String trimestre) {

        PreparedStatement ps, ps2;
        ArrayList<rendimientoProf> rendimientos = new ArrayList(0);

        try {
            ps = conexion.prepareStatement("SELECT codigo, recomendado, "
                    + "observaciones, nombre, usbid_profesor, ano, trimestre "
                    + "FROM evaluado, materia "
                    + "WHERE codigo_departamento = ? "
                    + "AND ano = ? "
                    + "AND trimestre = ? "
                    + "AND codigo_materia = codigo;");
            ps.setString(1, id_departamento);
            ps.setInt(2, ano);
            ps.setString(3, trimestre);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                rendimientoProf rendimiento = new rendimientoProf();
                rendimiento.setCodigo_materia(rs.getString("codigo"));
                rendimiento.setRecomendado(rs.getString("recomendado"));
                rendimiento.setObservaciones_c(rs.getString("observaciones"));
                rendimiento.setNombre_materia(rs.getString("nombre"));
                rendimiento.setUsbid_profesor(rs.getString("usbid_profesor"));
                rendimiento.setAno(rs.getInt("ano"));
                rendimiento.setTrimestre(rs.getString("trimestre"));

                rendimientos.add(rendimiento);
            }

            return rendimientos;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public rendimientoProf listarEvaluacionesEnviadasMateria(
            String id_coordinacion, int ano, String trimestre) {

        PreparedStatement ps, ps2;
        rendimientoProf rendimiento = null;

        try {
            ps = conexion.prepareStatement("SELECT recomendado, "
                    + "observaciones, usbid_profesor, ano, trimestre "
                    + "FROM evaluado "
                    + "WHERE codigo_coordinacion = ? "
                    + "AND ano = ? "
                    + "AND trimestre = ?;");
            ps.setString(1, id_coordinacion);
            ps.setInt(2, ano);
            ps.setString(3, trimestre);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                rendimiento = new rendimientoProf();
                rendimiento.setRecomendado(rs.getString("recomendado"));
                rendimiento.setObservaciones_c(rs.getString("observaciones"));
                rendimiento.setUsbid_profesor(rs.getString("usbid_profesor"));
                rendimiento.setAno(rs.getInt("ano"));
                rendimiento.setTrimestre(rs.getString("trimestre"));
            }

            return rendimiento;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public rendimientoProf listarEvaluacionesCoordinacion(String id_coordinacion, int ano, String trimestre, String codigo_materia, String usbid_profesor) {

        PreparedStatement ps, ps2;
        rendimientoProf rendimiento = null;

        try {
            ps = conexion.prepareStatement("SELECT m.codigo, r.ano, r.trimestre, "
                    + "e.observaciones_coordinacion, m.nombre, e.usbid_profesor "
                    + "FROM evaluar as e, materia as m, rendimiento as r "
                    + "WHERE e.codigo_coordinacion = ? "
                    + "AND r.ano = ? "
                    + "AND r.trimestre = ? "
                    + "AND e.codigo_materia = codigo "
                    + "AND e.codigo_materia = r.codigo_materia "
                    + "AND r.usbid_profesor = e.usbid_profesor "
                    + "AND r.usbid_profesor = ? "
                    + "AND e.codigo_materia = ? "
                    + "AND e.comentado_coordinacion = 'si';");
            ps.setString(1, id_coordinacion);
            ps.setInt(2, ano);
            ps.setString(3, trimestre);
            ps.setString(4, usbid_profesor);
            ps.setString(5, codigo_materia);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                rendimiento = new rendimientoProf();
                rendimiento.setCodigo_materia(rs.getString("codigo"));
                rendimiento.setObservaciones_c(rs.getString("observaciones_coordinacion"));
                rendimiento.setNombre_materia(rs.getString("nombre"));
                rendimiento.setUsbid_profesor(rs.getString("usbid_profesor"));
                rendimiento.setAno(rs.getInt("ano"));
                rendimiento.setTrimestre(rs.getString("trimestre"));
            }

            return rendimiento;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public rendimientoProf listarEvaluacionesGeneralCoordinacion(
            String id_coordinacion, String usbid_profesor) {

        PreparedStatement ps, ps2;
        rendimientoProf rendimiento = null;

        try {
            ps = conexion.prepareStatement("SELECT * "
                    + "FROM evaluacion "
                    + "WHERE usbid_profesor = ? "
                    + "AND codigo_coordinacion = ?;");
            ps.setString(1, usbid_profesor);
            ps.setString(2, id_coordinacion);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                rendimiento = new rendimientoProf();
                rendimiento.setObservaciones_c(rs.getString("observaciones_coordinacion"));
                rendimiento.setUsbid_profesor(rs.getString("usbid_profesor"));
            }

            return rendimiento;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public rendimientoProf listarEvaluacionesEnviadasCoordinacion(
            String id_coordinacion, int ano, String trimestre, String usbid_profesor) {

        PreparedStatement ps, ps2;
        rendimientoProf rendimiento = null;

        try {
            ps = conexion.prepareStatement("SELECT recomendado, observaciones, "
                    + "usbid_profesor, ano, trimestre "
                    + "FROM evaluado as e "
                    + "WHERE codigo_coordinacion = ? "
                    + "AND ano = ? "
                    + "AND trimestre = ? "
                    + "AND usbid_profesor = e.usbid_profesor "
                    + "AND usbid_profesor = ?;");
            ps.setString(1, id_coordinacion);
            ps.setInt(2, ano);
            ps.setString(3, trimestre);
            ps.setString(4, usbid_profesor);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                rendimiento = new rendimientoProf();
                rendimiento.setRecomendado(rs.getString("recomendado"));
                rendimiento.setObservaciones_c(rs.getString("observaciones"));
                rendimiento.setUsbid_profesor(rs.getString("usbid_profesor"));
                rendimiento.setAno(rs.getInt("ano"));
                rendimiento.setTrimestre(rs.getString("trimestre"));
            }

            return rendimiento;

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
            ps = conexion.prepareStatement("INSERT INTO departamento "
                    + "VALUES (?,?,?)");
            ps.setString(1, d.getCodigo());
            ps.setString(2, d.getNombre());
            ps.setString(3, "activo");

            Integer i = ps.executeUpdate();
            boolean usuario_agregado = agregarUsuario(d.getCodigo(), "departamento");

            return i > 0 && usuario_agregado;

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
            ps = conexion.prepareStatement("UPDATE DEPARTAMENTO "
                    + "SET condicion = 'desactivado' "
                    + "WHERE (codigo = ?);");
            ps.setString(1, d.getCodigo());

            Integer i = ps.executeUpdate();
            boolean usuario_eliminado = eliminarUsuario(d.getCodigo());

            return i > 0 && usuario_eliminado;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public int contarSolicitudesPendientesDepartamento(String id_departamento) {

        PreparedStatement ps;
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

    public int contarEvaluacionesPendientesDecanato(String id_decanato) {

        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement("SELECT count(usbid_profesor) "
                    + "FROM evaluar as ev, se_adscribe as se "
                    + "WHERE ev.codigo_coordinacion = se.codigo_coordinacion "
                    + "AND se.codigo_decanato = ? "
                    + "AND ev.usbid_profesor IN ("
                    + "SELECT ec.usbid_profesor "
                    + "FROM evaluacion as ec "
                    + "WHERE ec.usbid_profesor = ev.usbid_profesor "
                    + "AND revisado_decanato <> 'si'"
                    + ");");
            ps.setString(1, id_decanato);

            ResultSet rs = ps.executeQuery();
            rs.next();
            return Integer.parseInt(rs.getString("count"));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public boolean evaluacionIniciadaProfesor(String usbid_profesor) {

        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement("SELECT count(usbid_profesor) "
                    + "FROM evaluar "
                    + "WHERE usbid_profesor = ?;");
            ps.setString(1, usbid_profesor);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("count") != 0) {
                    return true;
                }
            }
            return false;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
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

            ps0 = conexion.prepareStatement("UPDATE MATERIA "
                    + "SET codigo = ?, creditos = ?, nombre = ?"
                    + "WHERE codigo = ?;");
            ps0.setString(1, m.getCodigo());
            ps0.setString(2, m.getCreditos());
            ps0.setString(3, m.getNombre());
            ps0.setString(4, m.getViejoCodigo());

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

    public ArrayList<rendimientoProf> obtenerPlanillasLlenas(String id_profesor, String id_departamento) {
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
                    + "AND m.codigo = o.codigo_materia "
                    + "AND r.evaluado = 'no';");
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
                    + "AND m.codigo = o.codigo_materia "
                    + "AND m.condicion = 'activo';");
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
                    + "FROM dicta as d, oferta as o, materia as m "
                    + "WHERE d.usbid_profesor = ? "
                    + "AND o.codigo_departamento = ? "
                    + "AND o.codigo_materia = d.codigo_materia "
                    + "AND o.codigo_materia = m.codigo "
                    + "AND m.condicion = 'activo' "
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
                    + "FROM dicta as d, oferta as o, materia as m "
                    + "WHERE d.usbid_profesor = ? "
                    + "AND o.codigo_departamento = ? "
                    + "AND o.codigo_materia = d.codigo_materia "
                    + "AND m.codigo = o.codigo_materia "
                    + "AND m.condicion = 'activo';");
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

    public rendimientoProf obtenerPlanillaEvaluacionProfesor(String id_profesor,
            String id_materia, int ano, String trimestre) {
        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement("SELECT * "
                    + "FROM rendimiento, materia "
                    + "WHERE usbid_profesor = ? "
                    + "AND codigo_materia = ? "
                    + "AND codigo_materia = codigo "
                    + "AND evaluado = 'no' "
                    + "AND trimestre = ? "
                    + "AND ano = ?;");
            ps.setString(1, id_profesor);
            ps.setString(2, id_materia);
            ps.setString(3, trimestre);
            ps.setInt(4, ano);

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

    public rendimientoProf obtenerEvaluacionGeneral(dicta d) {
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

    public rendimientoProf obtenerEvaluacion(dicta d) {
        PreparedStatement ps;
        rendimientoProf evaluacion = new rendimientoProf();
        try {
            ps = conexion.prepareStatement("SELECT total_estudiantes as t, "
                    + "nota_prom as np, nota1 as n1, nota2 as n2, "
                    + "nota3 as n3, nota4 as n4, nota5 as n5, "
                    + "retirados as r, ano, trimestre "
                    + "FROM rendimiento "
                    + "WHERE codigo_materia = ? "
                    + "AND usbid_profesor = ?;");

            String codigo_materia = d.getCodigoMateria();
            ps.setString(1, codigo_materia);
            ps.setString(2, d.getUsbidProfesor());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                evaluacion.setCodigo_materia(codigo_materia);
                evaluacion.setTotal_estudiantes(rs.getInt("t"));
                evaluacion.setNota_prom(rs.getFloat("np"));
                evaluacion.setNota1(rs.getInt("n1"));
                evaluacion.setNota2(rs.getInt("n2"));
                evaluacion.setNota3(rs.getInt("n3"));
                evaluacion.setNota4(rs.getInt("n4"));
                evaluacion.setNota5(rs.getInt("n5"));
                evaluacion.setRetirados(rs.getInt("r"));
                evaluacion.setAno(rs.getInt("ano"));
                evaluacion.setTrimestre(rs.getString("trimestre"));

            }
            return evaluacion;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public rendimientoProf obtenerEvaluacion(String usbid_profesor) {
        PreparedStatement ps;
        rendimientoProf evaluacion = new rendimientoProf();
        try {
            ps = conexion.prepareStatement("SELECT total_estudiantes as t, "
                    + "nota_prom as np, nota1 as n1, nota2 as n2, "
                    + "nota3 as n3, nota4 as n4, nota5 as n5, "
                    + "retirados as r, ano, trimestre "
                    + "FROM rendimiento "
                    + "WHERE usbid_profesor = ? "
                    + "AND evaluado = 'no';");

            ps.setString(1, usbid_profesor);

            ResultSet rs = ps.executeQuery();

            int n_1 = 0;
            int n_2 = 0;
            int n_3 = 0;
            int n_4 = 0;
            int n_5 = 0;
            int n_retirados = 0;
            int n_total = 0;

            while (rs.next()) {
                n_1 += rs.getInt("n1");
                n_2 += rs.getInt("n2");
                n_3 += rs.getInt("n3");
                n_4 += rs.getInt("n4");
                n_5 += rs.getInt("n5");
                n_retirados += rs.getInt("r");
                n_total += rs.getInt("t");

                evaluacion.setNota_prom(rs.getFloat("np"));
                evaluacion.setAno(rs.getInt("ano"));
                evaluacion.setTrimestre(rs.getString("trimestre"));
            }

            evaluacion.setTotal_estudiantes(n_total);
            evaluacion.setRetirados(n_retirados);
            evaluacion.setNota1(n_1);
            evaluacion.setNota2(n_2);
            evaluacion.setNota3(n_3);
            evaluacion.setNota4(n_4);
            evaluacion.setNota5(n_5);

            return evaluacion;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public rendimientoProf obtenerEvaluacionPDF(String usbid_profesor) {
        PreparedStatement ps;
        rendimientoProf evaluacion = new rendimientoProf();
        try {
            ps = conexion.prepareStatement("SELECT total_estudiantes as t, "
                    + "nota_prom as np, nota1 as n1, nota2 as n2, "
                    + "nota3 as n3, nota4 as n4, nota5 as n5, "
                    + "retirados as r, ano, trimestre "
                    + "FROM rendimiento "
                    + "WHERE usbid_profesor = ? "
                    + "AND evaluado = 'si';");

            ps.setString(1, usbid_profesor);

            ResultSet rs = ps.executeQuery();

            int n_1 = 0;
            int n_2 = 0;
            int n_3 = 0;
            int n_4 = 0;
            int n_5 = 0;
            int n_retirados = 0;
            int n_total = 0;

            while (rs.next()) {
                n_1 += rs.getInt("n1");
                n_2 += rs.getInt("n2");
                n_3 += rs.getInt("n3");
                n_4 += rs.getInt("n4");
                n_5 += rs.getInt("n5");
                n_retirados += rs.getInt("r");
                n_total += rs.getInt("t");

                evaluacion.setNota_prom(rs.getFloat("np"));
                evaluacion.setAno(rs.getInt("ano"));
                evaluacion.setTrimestre(rs.getString("trimestre"));
            }

            evaluacion.setTotal_estudiantes(n_total);
            evaluacion.setRetirados(n_retirados);
            evaluacion.setNota1(n_1);
            evaluacion.setNota2(n_2);
            evaluacion.setNota3(n_3);
            evaluacion.setNota4(n_4);
            evaluacion.setNota5(n_5);

            return evaluacion;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public rendimientoProf obtenerEvaluacion(String codigo_materia,
            String usbid_profesor, int ano, String trimestre) {
        PreparedStatement ps;
        rendimientoProf evaluacion = new rendimientoProf();
        try {
            ps = conexion.prepareStatement("SELECT total_estudiantes as t, "
                    + "nota_prom as np, nota1 as n1, nota2 as n2, "
                    + "nota3 as n3, nota4 as n4, nota5 as n5, "
                    + "retirados as r, ano, trimestre "
                    + "FROM rendimiento "
                    + "WHERE codigo_materia = ? "
                    + "AND usbid_profesor = ? "
                    + "AND ano = ? "
                    + "AND trimestre = ?;");

            ps.setString(1, codigo_materia);
            ps.setString(2, usbid_profesor);
            ps.setInt(3, ano);
            ps.setString(4, trimestre);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                evaluacion.setCodigo_materia(codigo_materia);
                evaluacion.setTotal_estudiantes(rs.getInt("t"));
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

    public rendimientoProf obtenerEvaluacion(
            String usbid_profesor, int ano, String trimestre) {
        PreparedStatement ps;
        rendimientoProf evaluacion = new rendimientoProf();
        try {
            ps = conexion.prepareStatement("SELECT sum(total_estudiantes) as t, "
                    + "avg(nota_prom) as np, sum(nota1) as n1, sum(nota2) as n2, "
                    + "sum(nota3) as n3, sum(nota4) as n4, sum(nota5) as n5, "
                    + "sum(retirados) as r "
                    + "FROM rendimiento "
                    + "WHERE usbid_profesor = ? "
                    + "AND ano = ? "
                    + "AND trimestre = ?;");

            ps.setString(1, usbid_profesor);
            ps.setInt(2, ano);
            ps.setString(3, trimestre);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                evaluacion.setTotal_estudiantes(rs.getInt("t"));
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

    public rendimientoProf obtenerEvaluaciones(dicta d) {
        PreparedStatement ps;
        rendimientoProf evaluacion = new rendimientoProf();
        try {
            ps = conexion.prepareStatement("SELECT sum(total_estudiantes) as t, "
                    + "sum(nota_prom) as np, sum(nota1) as n1, sum(nota2) as n2, "
                    + "sum(nota3) as n3, sum(nota4) as n4, sum(nota5) as n5, "
                    + "sum(retirados) as r "
                    + "FROM rendimiento "
                    + "WHERE codigo_materia = ?;");

            String codigo_materia = d.getCodigoMateria();
            ps.setString(1, codigo_materia);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                evaluacion.setCodigo_materia(codigo_materia);
                evaluacion.setTotal_estudiantes(rs.getInt("t"));
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

    public rendimientoProf obtenerEvaluaciones(String codigo_materia, int ano,
            String trimestre) {
        PreparedStatement ps;
        rendimientoProf evaluacion = new rendimientoProf();
        try {
            ps = conexion.prepareStatement("SELECT sum(total_estudiantes) as t, "
                    + "sum(nota_prom) as np, sum(nota1) as n1, sum(nota2) as n2, "
                    + "sum(nota3) as n3, sum(nota4) as n4, sum(nota5) as n5, "
                    + "sum(retirados) as r "
                    + "FROM rendimiento "
                    + "WHERE codigo_materia = ? "
                    + "AND ano = ? "
                    + "AND trimestre = ?;");

            ps.setString(1, codigo_materia);
            ps.setInt(2, ano);
            ps.setString(3, trimestre);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                evaluacion.setCodigo_materia(codigo_materia);
                evaluacion.setTotal_estudiantes(rs.getInt("t"));
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

    public rendimientoProf obtenerEvaluacionEnviada(String usbid_profesor,
            int ano, String trimestre) {

        PreparedStatement ps;
        rendimientoProf evaluacion = null;
        try {
            ps = conexion.prepareStatement("SELECT sum(total_estudiantes), "
                    + "avg(nota_prom) as np, sum(nota1) as n1, sum(nota2) as n2, "
                    + "sum(nota3) as n3, sum(nota4) as n4, sum(nota5) as n5, "
                    + "sum(retirados) as r "
                    + "FROM rendimiento "
                    + "WHERE trimestre = ? "
                    + "AND ano = ? "
                    + "AND usbid_profesor = ?;");

            ps.setString(1, trimestre);
            ps.setInt(2, ano);
            ps.setString(3, usbid_profesor);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                evaluacion = new rendimientoProf();
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
                    + "(usbid, nombre, apellido, cedula, genero, email, nivel) "
                    + "VALUES (?,?,?,?,?,?,?)");
            ps2.setString(1, p.getUsbid());
            ps2.setString(2, p.getNombre());
            ps2.setString(3, p.getApellido());
            ps2.setString(4, p.getCedula());
            ps2.setString(5, p.getGenero());
            ps2.setString(6, p.getEmail());
            ps2.setString(7, p.getNivel());

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

    public boolean pathArchivos(String usbid, String path) {
        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement("INSERT INTO directorios "
                    + "(usbid_profesor,path) VALUES (?,?)");
            ps.setString(1, usbid);
            ps.setString(2, path);

            Integer j = ps.executeUpdate();

            return j > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean agregarEspecificacionesArchivo(String usuario, String trimestre,
            int ano, String nombre, String descripcion) {
        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement("INSERT INTO archivos "
                    + "(usbid_profesor,trimestre,ano,nombre,descripcion) "
                    + "VALUES (?,?,?,?,?)");
            ps.setString(1, usuario);
            ps.setString(2, trimestre);
            ps.setInt(3, ano);
            ps.setString(4, nombre);
            ps.setString(5, descripcion);

            Integer j = ps.executeUpdate();

            return j > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public ArrayList<String> listarDirectoriosProfesor(String usbid) {
        PreparedStatement ps;
        ArrayList<String> archivos = new ArrayList<String>(0);
        try {
            ps = conexion.prepareStatement("SELECT path FROM directorios "
                    + "WHERE usbid_profesor = (?)");
            ps.setString(1, usbid);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                archivos.add(rs.getString("path"));
            }

            return archivos;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<Archivo> listarArchivosProfesor(Archivo archivo) {
        PreparedStatement ps;
        ArrayList<Archivo> archivos = new ArrayList<Archivo>(0);
        try {
            ps = conexion.prepareStatement("SELECT * "
                    + "FROM archivos "
                    + "WHERE usbid_profesor = ? "
                    + "AND trimestre = ? "
                    + "AND ano = ?;");
            ps.setString(1, archivo.getUsbidProfesor());
            ps.setString(2, archivo.getTrimestre());
            ps.setInt(3, archivo.getAno());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Archivo archivo_tmp = new Archivo();
                archivo_tmp.setUsbidProfesor(rs.getString("usbid_profesor"));
                archivo_tmp.setTrimestre(rs.getString("trimestre"));
                archivo_tmp.setNombre(rs.getString("nombre"));
                archivo_tmp.setDescripcion(rs.getString("descripcion"));
                archivo_tmp.setAno(rs.getInt("ano"));
                archivos.add(archivo_tmp);
            }

            return archivos;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> listarAnosArchivos(String usbid_profesor) {
        PreparedStatement ps;
        ArrayList<String> archivos = new ArrayList<String>(0);
        try {
            ps = conexion.prepareStatement("SELECT DISTINCT ano "
                    + "FROM archivos "
                    + "WHERE usbid_profesor = ?"
                    + "ORDER BY ano;");
            ps.setString(1, usbid_profesor);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                archivos.add(rs.getString("ano"));
            }

            return archivos;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<Materia> consultarMateriasAsignadas(String usbid_profesor) {

        PreparedStatement ps1, ps2;
        ArrayList<Materia> materias = new ArrayList<Materia>(0);

        try {

            ps1 = conexion.prepareStatement("SELECT DISTINCT codigo, nombre "
                    + "FROM materia, dicta "
                    + "WHERE usbid_profesor = ? "
                    + "AND codigo = codigo_materia "
                    + "ORDER BY codigo;");
            ps1.setString(1, usbid_profesor);

            ResultSet rs1 = ps1.executeQuery();

            while (rs1.next()) {

                Materia m = new Materia();
                m.setCodigo(rs1.getString("codigo"));
                m.setNombre(rs1.getString("nombre"));

                ps2 = conexion.prepareStatement("SELECT periodo "
                        + "FROM dicta "
                        + "WHERE codigo_materia = ? "
                        + "AND usbid_profesor = ?");
                ps2.setString(1, m.getCodigo());
                ps2.setString(2, usbid_profesor);

                ResultSet rs2 = ps2.executeQuery();

                while (rs2.next()) {
                    String periodo = rs2.getString("periodo");
                    if (periodo.equals("SD")) {
                        m.setPeriodoSD("SD");
                    } else if (periodo.equals("EM")) {
                        m.setPeriodoEM("EM");
                    } else if (periodo.equals("AJ")) {
                        m.setPeriodoAJ("AJ");
                    } else if (periodo.equals("V")) {
                        m.setPeriodoV("V");
                    }
                }

                materias.add(m);
            }

            return materias;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean eliminarPeriodos(String id_profesor, String codigo_materia) {
        PreparedStatement ps1, ps2;
        try {

            ps1 = conexion.prepareStatement("DELETE FROM dicta "
                    + "WHERE usbid_profesor = ? "
                    + "AND codigo_materia = ?;");
            ps1.setString(1, id_profesor);
            ps1.setString(2, codigo_materia);

            Integer i = ps1.executeUpdate();

            return i > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public ArrayList<Materia> listarMateriasDictadasTrimestrePDF(
            String id_profesor, int ano, String trimestre) {

        ArrayList<Materia> materias = new ArrayList<Materia>(0);
        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement("SELECT nombre, codigo "
                    + "FROM rendimiento as r, materia as m "
                    + "WHERE r.usbid_profesor = ? "
                    + "AND r.codigo_materia = m.codigo "
                    + "AND r.ano = ? "
                    + "AND r.trimestre = ?;");

            ps.setString(1, id_profesor);
            ps.setInt(2, ano);
            ps.setString(3, trimestre);
            
            System.out.println(ps.toString());
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Materia m = new Materia();
                m.setCodigo(rs.getString("codigo"));
                m.setNombre(rs.getString("nombre"));
                materias.add(m);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return materias;
    }

    public ArrayList<Materia> listarMateriasNoDictadas(String id_departamento, String id_profesor) {

        ArrayList<Materia> materias = new ArrayList<Materia>(0);
        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement("SELECT * "
                    + "FROM oferta as o, materia "
                    + "WHERE o.codigo_departamento = ? AND "
                    + "o.codigo_materia = codigo AND "
                    + "condicion = 'activo' "
                    + "AND solicitud = 'no' "
                    + "and o.codigo_materia NOT IN "
                    + "(SELECT d.codigo_materia "
                    + "FROM dicta as d "
                    + "WHERE usbid_profesor = ?);");

            ps.setString(1, id_departamento);
            ps.setString(2, id_profesor);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Materia m = new Materia();
                m.setCodigo(rs.getString("codigo"));
                m.setNombre(rs.getString("nombre"));
                m.setCreditos(rs.getString("creditos"));
                materias.add(m);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return materias;
    }

    public boolean evaluarCoordinacion(rendimientoProf rendimiento, String id_coordinacion) {

        PreparedStatement ps0;

        try {

            ps0 = conexion.prepareStatement("INSERT INTO evaluacion "
                    + "VALUES (?,?,?,?);");

            ps0.setString(1, id_coordinacion);
            ps0.setString(2, rendimiento.getUsbid_profesor());
            ps0.setString(3, rendimiento.getRecomendado());
            ps0.setString(4, rendimiento.getObservaciones_c());

            Integer i = ps0.executeUpdate();

            return i > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean comentarCoordinacion(rendimientoProf rendimiento,
            String id_coordinacion) {

        PreparedStatement ps0;

        try {

            ps0 = conexion.prepareStatement("UPDATE evaluar "
                    + "SET comentado_coordinacion = 'si', "
                    + "observaciones_coordinacion = ? "
                    + "WHERE usbid_profesor = ? "
                    + "AND codigo_materia = ? "
                    + "AND codigo_coordinacion = ?;");
            ps0.setString(1, rendimiento.getObservaciones_c());
            ps0.setString(2, rendimiento.getUsbid_profesor());
            ps0.setString(3, rendimiento.getCodigo_materia());
            ps0.setString(4, id_coordinacion);

            Integer i = ps0.executeUpdate();

            return i > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean evaluarDepartamento(rendimientoProf rendimiento, String id_departamento) {

        PreparedStatement ps0;

        try {

            ps0 = conexion.prepareStatement("UPDATE evaluar "
                    + "SET revisado_departamento = 'si', "
                    + "recomendado_departamento = ?, "
                    + "observaciones_departamento = ? "
                    + "WHERE usbid_profesor = ? "
                    + "AND codigo_materia = ? "
                    + "AND codigo_departamento = ?;");
            ps0.setString(1, rendimiento.getRecomendado());
            ps0.setString(2, rendimiento.getObservaciones_c());
            ps0.setString(3, rendimiento.getUsbid_profesor());
            ps0.setString(4, rendimiento.getCodigo_materia());
            ps0.setString(5, id_departamento);

            Integer i = ps0.executeUpdate();

            return i > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean revisadoPorDecanato(rendimientoProf rendimiento, String id_departamento) {

        PreparedStatement ps0;

        try {

            ps0 = conexion.prepareStatement("SELECT revisado_decanato "
                    + "FROM evaluacion "
                    + "WHERE usbid_profesor = ?;");
            ps0.setString(1, rendimiento.getUsbid_profesor());

            ResultSet rs = ps0.executeQuery();

            while (rs.next()) {
                String consulta = rs.getString("revisado_decanato");
                if (consulta.equals("no")) {
                    return false;
                }
            }

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public boolean revisadoPorDepartamento(rendimientoProf rendimiento, String id_coordinacion) {

        PreparedStatement ps0;

        try {

            ps0 = conexion.prepareStatement("SELECT revisado_departamento "
                    + "FROM evaluacion "
                    + "WHERE usbid_profesor = ? "
                    + "AND codigo_coordinacion = ?;");
            ps0.setString(1, rendimiento.getUsbid_profesor());
            ps0.setString(2, id_coordinacion);

            ResultSet rs = ps0.executeQuery();

            while (rs.next()) {
                String consulta = rs.getString("revisado_departamento");
                if (consulta.equals("no")) {
                    return false;
                }
            }

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public boolean evaluarRendimiento(String usbid, String materia, int ano, String trimestre) {

        PreparedStatement ps0;

        try {

            ps0 = conexion.prepareStatement("UPDATE rendimiento "
                    + "SET evaluado = 'si' "
                    + "WHERE usbid_profesor = ? "
                    + "AND codigo_materia = ? "
                    + "AND trimestre = ? "
                    + "AND ano = ?;");
            ps0.setString(1, usbid);
            ps0.setString(2, materia);
            ps0.setString(3, trimestre);
            ps0.setInt(4, ano);

            Integer i = ps0.executeUpdate();

            return i > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public int[] obtenerAnoYTrimestre() {

        int[] ano_y_trimestre = new int[2];

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        /* Se extrae el ano y el trimestre actual */
        String fecha = dateFormat.format(date).toString();
        String fecha_ano = fecha.substring(0, 4);
        ano_y_trimestre[0] = Integer.parseInt(fecha_ano);
        ano_y_trimestre[1] = Integer.parseInt(fecha.substring(5, 7));

        return ano_y_trimestre;
    }

    public String obtenerTrimestre(int fecha_mes) {

        if (1 <= fecha_mes && fecha_mes <= 3) {
            /* El trimestre es ENERO-MARZO */
            return "EM";
        } else if (4 <= fecha_mes && fecha_mes <= 7) {
            /* El trimestre es ABRIL-JULIO */
            return "AJ";
        } else if (9 <= fecha_mes && fecha_mes <= 12) {
            /* El trimestre es SEPTIEMBRE-DICIEMBRE */
            return "SD";
        } else if (fecha_mes == 8) {
            /* Período INTENSIVO */
            return "V";
        }
        return null;
    }

    public void evaluar(rendimientoProf rendimiento, String id_departamento, String id_coordinacion) {

        PreparedStatement ps0, ps1;

        try {

            String usbid = rendimiento.getUsbid_profesor();
            String materia = rendimiento.getCodigo_materia();

            int[] ano_y_trimestre = obtenerAnoYTrimestre();
            String trimestre = obtenerTrimestre(ano_y_trimestre[1]);

            if (id_coordinacion == null) {
                ps0 = conexion.prepareStatement("SELECT DISTINCT codigo_coordinacion, "
                        + "trimestre, ano, recomendado_coordinacion, "
                        + "ev.observaciones_coordinacion "
                        + "FROM evaluacion AS ev, rendimiento AS r "
                        + "WHERE ev.usbid_profesor = ? "
                        + "AND ev.usbid_profesor = r.usbid_profesor "
                        + "AND r.evaluado = 'no';");
                ps0.setString(1, usbid);
            } else {
                ps0 = conexion.prepareStatement("SELECT DISTINCT codigo_coordinacion, "
                        + "trimestre, ano, recomendado_coordinacion, "
                        + "ev.observaciones_coordinacion "
                        + "FROM evaluacion AS ev, rendimiento AS r "
                        + "WHERE ev.usbid_profesor = ? "
                        + "AND ev.usbid_profesor = r.usbid_profesor "
                        + "AND ev.codigo_coordinacion = ? "
                        + "AND r.evaluado = 'no';");
                ps0.setString(1, usbid);
                ps0.setString(2, id_coordinacion);
            }

            System.out.println(ps0.toString());

            ResultSet rs = ps0.executeQuery();

            while (rs.next()) {
                int ano = rs.getInt(3);
                String trimestre_rendimiento = rs.getString(2);

                evaluarRendimiento(usbid, materia, ano, trimestre_rendimiento);

                ps1 = conexion.prepareStatement("INSERT into evaluado "
                        + "VALUES (?,?,?,?,?,?);");
                ps1.setString(1, rs.getString(1));
                ps1.setString(2, usbid);
                ps1.setInt(3, ano_y_trimestre[0]);
                ps1.setString(4, trimestre);
                ps1.setString(5, rs.getString(4));
                ps1.setString(6, rs.getString(5));

                ps1.executeUpdate();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean borrarEvaluadosDepartamento(rendimientoProf rendimiento, String id_departamento) {


        PreparedStatement ps0, ps1;

        try {

            ps0 = conexion.prepareStatement("DELETE FROM evaluar "
                    + "WHERE usbid_profesor = ? "
                    + "AND codigo_departamento = ?;");
            ps0.setString(1, rendimiento.getUsbid_profesor());
            ps0.setString(2, id_departamento);

            ps1 = conexion.prepareStatement("DELETE FROM evaluacion "
                    + "WHERE usbid_profesor = ?;");
            ps1.setString(1, rendimiento.getUsbid_profesor());

            Integer i = ps0.executeUpdate();
            Integer j = ps1.executeUpdate();
            return i > 0 && j > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean borrarEvaluadosDecanato(rendimientoProf rendimiento, String id_coordinacion) {


        PreparedStatement ps0, ps1;

        try {

            ps0 = conexion.prepareStatement("DELETE FROM evaluar "
                    + "WHERE usbid_profesor = ? "
                    + "AND codigo_coordinacion = ?;");
            ps0.setString(1, rendimiento.getUsbid_profesor());
            ps0.setString(2, id_coordinacion);

            ps1 = conexion.prepareStatement("DELETE FROM evaluacion "
                    + "WHERE usbid_profesor = ? "
                    + "AND codigo_coordinacion = ?;");
            ps1.setString(1, rendimiento.getUsbid_profesor());
            ps1.setString(2, id_coordinacion);

            Integer i = ps0.executeUpdate();
            Integer j = ps1.executeUpdate();
            return i > 0 && j > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean revisadoDepartamento(rendimientoProf rendimiento, String id_departamento) {

        PreparedStatement ps0;

        try {

            /* Revisar si la evaluacion fue previamente revisada por el Decanato
             * correspondiente */
            if (revisadoPorDecanato(rendimiento, id_departamento)) {
                /* Si ya fue revisada completamente, guardar como evaluado */
                evaluar(rendimiento, id_departamento, null);
                /* Si ya fue revisada completamente, borrar de la tabla
                 * de profesores por evaluar */
                borrarEvaluadosDepartamento(rendimiento, id_departamento);
                return true;
            } else {
                /* Si la evaluacion no fue revisada por el decanato colocar
                 * que fue revisada por el departamento correspondiente */
                ps0 = conexion.prepareStatement("UPDATE evaluacion "
                        + "SET revisado_departamento = 'si' "
                        + "WHERE usbid_profesor = ?;");
                ps0.setString(1, rendimiento.getUsbid_profesor());

                Integer i = ps0.executeUpdate();

                return i > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean revisadoDecanato(rendimientoProf rendimiento, String id_coordinacion) {

        PreparedStatement ps0;

        try {

            /* Revisar si la evaluacion fue previamente revisada por el Departamento
             * correspondiente */
            if (revisadoPorDepartamento(rendimiento, id_coordinacion)) {
                /* Si ya fue revisada completamente, guardar como evaluado */
                evaluar(rendimiento, null, id_coordinacion);
                /* Si ya fue revisada completamente, borrar de la tabla
                 * de profesores por evaluar */
                borrarEvaluadosDecanato(rendimiento, id_coordinacion);
                return true;
            } else {
                /* Si la evaluacion no fue revisada por el decanato colocar
                 * que fue revisada por el departamento correspondiente */
                ps0 = conexion.prepareStatement("UPDATE evaluacion "
                        + "SET revisado_decanato = 'si' "
                        + "WHERE usbid_profesor = ? "
                        + "AND codigo_coordinacion = ?;");
                ps0.setString(1, rendimiento.getUsbid_profesor());
                ps0.setString(2, id_coordinacion);

                Integer i = ps0.executeUpdate();

                return i > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<Profesor> listarProfesoresEvaluadosCoordinacion(String id_coordinacion) {

        ArrayList<Profesor> profesores = new ArrayList<Profesor>(0);
        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement("SELECT DISTINCT p.usbid, p.nombre, "
                    + "p.apellido "
                    + "FROM maneja as m, dicta as d, profesor as p, evaluado as e "
                    + "WHERE m.codigo_materia = d.codigo_materia "
                    + "AND p.usbid = e.usbid_profesor "
                    + "AND m.codigo_coordinacion = ? "
                    + "ORDER BY p.usbid;");

            ps.setString(1, id_coordinacion);

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

    public ArrayList<Profesor> listarProfesoresPorEvaluarCoordinacion(
            String id_coordinacion) {

        ArrayList<Profesor> profesores = new ArrayList<Profesor>(0);
        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement("SELECT DISTINCT p.usbid, p.nombre, "
                    + "p.apellido "
                    + "FROM maneja as m, dicta as d, profesor as p, evaluar as e "
                    + "WHERE m.codigo_materia = d.codigo_materia "
                    + "AND p.usbid = e.usbid_profesor "
                    + "AND m.codigo_coordinacion = ? "
                    + "AND e.usbid_profesor NOT IN ("
                    + "SELECT ec.usbid_profesor "
                    + "FROM evaluacion as ec "
                    + "WHERE ec.codigo_coordinacion = ? "
                    + ")"
                    + "ORDER BY p.usbid;");

            ps.setString(1, id_coordinacion);
            ps.setString(2, id_coordinacion);

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

    public ArrayList<Profesor> listarProfesoresPorEvaluarDecanato(
            String id_coordinacion) {

        ArrayList<Profesor> profesores = new ArrayList<Profesor>(0);
        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement("SELECT * "
                    + "FROM profesor, evaluacion as ec "
                    + "WHERE ec.usbid_profesor = usbid "
                    + "AND ec.codigo_coordinacion = ? "
                    + "AND ec.revisado_decanato = 'no';");
            ps.setString(1, id_coordinacion);

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

    public ArrayList<rendimientoProf> listarAnoEvaluacionesEnviadasCoordinacion(
            String id_coordinacion, String usbid_profesor) {

        PreparedStatement ps;
        ArrayList<rendimientoProf> rendimiento = new ArrayList(0);
        try {
            ps = conexion.prepareStatement("SELECT DISTINCT ano, trimestre "
                    + "FROM evaluado "
                    + "WHERE codigo_coordinacion = ? "
                    + "AND usbid_profesor = ? "
                    + "ORDER BY ano;");
            ps.setString(1, id_coordinacion);
            ps.setString(2, usbid_profesor);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                rendimientoProf r = new rendimientoProf();
                r.setAno(rs.getInt("ano"));
                String periodo = rs.getString("trimestre");
                if (periodo.equals("SD")) {
                    r.setTrimestre("Septiembre-Diciembre");
                } else if (periodo.equals("EM")) {
                    r.setTrimestre("Ener-Marzo");
                } else if (periodo.equals("AJ")) {
                    r.setTrimestre("Abril-Julio");
                } else if (periodo.equals("V")) {
                    r.setTrimestre("Intensivo");
                }
                rendimiento.add(r);
            }

            return rendimiento;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<rendimientoProf> listarAnoEvaluacionesEnviadasDepartamento(
            String id_departamento, String usbid_profesor) {

        PreparedStatement ps;
        ArrayList<rendimientoProf> rendimiento = new ArrayList(0);
        try {
            ps = conexion.prepareStatement("SELECT DISTINCT ano, trimestre "
                    + "FROM pertenece AS pe, profesor AS pr, evaluado AS ev "
                    + "WHERE pe.codigo_departamento = ? "
                    + "AND pr.usbid = ? "
                    + "AND pe.usbid_profesor = usbid "
                    + "AND pe.usbid_profesor = ev.usbid_profesor "
                    + "ORDER BY ano;");
            ps.setString(1, id_departamento);
            ps.setString(2, usbid_profesor);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                rendimientoProf r = new rendimientoProf();
                r.setAno(rs.getInt("ano"));
                String periodo = rs.getString("trimestre");
                if (periodo.equals("SD")) {
                    r.setTrimestre("Septiembre-Diciembre");
                } else if (periodo.equals("EM")) {
                    r.setTrimestre("Ener-Marzo");
                } else if (periodo.equals("AJ")) {
                    r.setTrimestre("Abril-Julio");
                } else if (periodo.equals("V")) {
                    r.setTrimestre("Intensivo");
                }
                rendimiento.add(r);
            }

            return rendimiento;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public InformacionProfesorCoord resumenInformacionProfesor(
            String usbid_profesor) {

        PreparedStatement ps;
        InformacionProfesorCoord informacion = new InformacionProfesorCoord();
        try {
            ps = conexion.prepareStatement("SELECT * "
                    + "FROM informacion_profesor_coordinacion "
                    + "WHERE usbid_profesor = ?;");
            ps.setString(1, usbid_profesor);
            System.out.println(ps.toString());
            ResultSet rs = ps.executeQuery();

            int tt = 0, tj = 0, pc = 0, plt = 0, plj = 0;
            String ca = "no";

            while (rs.next()) {
                informacion.setUsbidProfesor(usbid_profesor);
                if (rs.getString("consejo_asesor").equals("si")) {
                    ca = "si";
                }
                tt += rs.getInt("tesis_tutoria");
                tj += rs.getInt("tesis_jurado");
                pc += rs.getInt("pasantia_corta");
                plt += rs.getInt("pasantia_larga_tutor");
                plj += rs.getInt("pasantia_larga_jurado");
            }

            informacion.setTesisTutoria(tt);
            informacion.setTesisJurado(tj);
            informacion.setPasantiaCorta(pc);
            informacion.setPasantiaLargaTutor(plt);
            informacion.setPasantiaLargaJurado(plj);
            informacion.setConsejoAsesor(ca);

            return informacion;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return informacion;
    }

    public InformacionProfesorCoord listarInformacionProfesorCoordinacion(
            String id_coordinacion, String usbid_profesor) {

        PreparedStatement ps;
        InformacionProfesorCoord informacion = null;
        try {
            ps = conexion.prepareStatement("SELECT * "
                    + "FROM informacion_profesor_coordinacion "
                    + "WHERE codigo_coordinacion = ? "
                    + "AND usbid_profesor = ?;");
            ps.setString(1, id_coordinacion);
            ps.setString(2, usbid_profesor);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                informacion = new InformacionProfesorCoord();
                informacion.setCodigoCoordinacion(id_coordinacion);
                informacion.setUsbidProfesor(usbid_profesor);
                if (rs.getString("consejo_asesor") == null) {
                    informacion.setConsejoAsesor("no");
                } else {
                    informacion.setConsejoAsesor(rs.getString("consejo_asesor"));
                }
                informacion.setTesisTutoria(rs.getInt("tesis_tutoria"));
                informacion.setTesisJurado(rs.getInt("tesis_jurado"));
                informacion.setPasantiaCorta(rs.getInt("pasantia_corta"));
                informacion.setPasantiaLargaTutor(rs.getInt("pasantia_larga_tutor"));
                informacion.setPasantiaLargaJurado(rs.getInt("pasantia_larga_jurado"));
            }

            return informacion;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /* Lista los archivos subidos por un profesor en base a una lista 
     * de archivos que contiene los anos y trimestres que deben
     * ser consultados */
    public ArrayList<Archivo> listarArchivosProfesor(
            String usbid_profesor, Archivo[] archivos_considerados) {

        PreparedStatement ps;
        ArrayList<Archivo> archivos = new ArrayList<Archivo>();
        try {

            ps = conexion.prepareStatement("SELECT * "
                    + "FROM archivos "
                    + "WHERE usbid_profesor = ? "
                    + "AND ano = ? "
                    + "AND trimestre = ?");

            for (int i = 0; i < archivos_considerados.length; i++) {

                int ano = archivos_considerados[i].getAno();
                String trimestre = archivos_considerados[i].getTrimestre();

                /* Se hace la consulta en base a los anos y trimestres 
                 * determinados*/
                ps.setString(1, usbid_profesor);
                ps.setInt(2, ano);
                ps.setString(3, trimestre);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Archivo archivo = new Archivo();

                    archivo.setDescripcion(rs.getString("descripcion"));
                    archivo.setNombre(rs.getString("nombre"));
                    archivo.setUsbidProfesor(usbid_profesor);
                    archivo.setTrimestre(trimestre);
                    archivo.setAno(ano);

                    archivos.add(archivo);
                }
            }

            return archivos;

        } catch (SQLException ex) {            
            ex.printStackTrace();
        }
        return null;
    }

    public boolean crearInformacionProfesorCoordinacion(String id_coordinacion, String usbid_profesor,
            InformacionProfesorCoord informacion) {

        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement("INSERT INTO informacion_profesor_coordinacion "
                    + "VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1, id_coordinacion);
            ps.setString(2, usbid_profesor);
            ps.setString(3, informacion.getConsejoAsesor());
            ps.setInt(4, informacion.getTesisTutoria());
            ps.setInt(5, informacion.getTesisJurado());
            ps.setInt(6, informacion.getPasantiaCorta());
            ps.setInt(7, informacion.getPasantiaLargaTutor());
            ps.setInt(8, informacion.getPasantiaLargaJurado());

            Integer j = ps.executeUpdate();

            return j > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean actualizarInformacionProfesorCoordinacion(String id_coordinacion, String usbid_profesor,
            InformacionProfesorCoord informacion) {

        PreparedStatement ps0;
        try {
            ps0 = conexion.prepareStatement("UPDATE informacion_profesor_coordinacion "
                    + "SET consejo_asesor = ?, "
                    + "tesis_tutoria = ?, "
                    + "tesis_jurado = ?, "
                    + "pasantia_corta = ?, "
                    + "pasantia_larga_tutor = ?, "
                    + "pasantia_larga_jurado = ? "
                    + "WHERE usbid_profesor = ? "
                    + "AND codigo_coordinacion = ?;");
            ps0.setString(1, informacion.getConsejoAsesor());
            ps0.setInt(2, informacion.getTesisTutoria());
            ps0.setInt(3, informacion.getTesisJurado());
            ps0.setInt(4, informacion.getPasantiaCorta());
            ps0.setInt(5, informacion.getPasantiaLargaTutor());
            ps0.setInt(6, informacion.getPasantiaLargaJurado());
            ps0.setString(7, usbid_profesor);
            ps0.setString(8, id_coordinacion);

            Integer i = ps0.executeUpdate();

            return i > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public ArrayList<dicta> listarEvaluadosPorCoordinacion(
            String id_departamento, String id_profesor) {

        PreparedStatement ps, ps2;
        ArrayList<dicta> dicta_materia = new ArrayList(0);
        String codigo_materia;
        String usbid_profesor;
        try {
            ps = conexion.prepareStatement("SELECT DISTINCT e.codigo_materia, "
                    + "e.usbid_profesor, m.nombre "
                    + "FROM evaluar as e, materia as m "
                    + "WHERE e.codigo_departamento = ? "
                    + "AND m.codigo = e.codigo_materia "
                    + "AND e.usbid_profesor NOT IN ("
                    + "SELECT ec.usbid_profesor "
                    + "FROM evaluacion as ec "
                    + "WHERE ec.revisado_departamento = 'si' "
                    + "AND ec.usbid_profesor = e.usbid_profesor "
                    + ")"
                    + "AND usbid_profesor = ? "
                    + "ORDER BY usbid_profesor;");
            ps.setString(1, id_departamento);
            ps.setString(2, id_profesor);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                usbid_profesor = rs.getString("usbid_profesor");

                int pendientes = contarEvaluacionesPendientesDepartamento(id_departamento, usbid_profesor);
                int total = contarEvaluacionesDepartamento(id_departamento, usbid_profesor);

                dicta d = new dicta();
                codigo_materia = rs.getString("codigo_materia");
                d.setCodigoMateria(codigo_materia);
                d.setOpcion(rs.getString("nombre"));

                ps2 = conexion.prepareStatement("SELECT DISTINCT codigo_materia, "
                        + "usbid, nombre, apellido "
                        + "FROM evaluar, profesor "
                        + "WHERE codigo_departamento = ? "
                        + "AND usbid = usbid_profesor "
                        + "AND usbid = ?"
                        + "AND codigo_materia = ?;");
                ps2.setString(1, id_departamento);
                ps2.setString(2, usbid_profesor);
                ps2.setString(3, codigo_materia);

                ResultSet rs2 = ps2.executeQuery();

                while (rs2.next()) {
                    Profesor p = new Profesor();
                    p.setUsbid(rs2.getString("usbid"));
                    p.setNombre(rs2.getString("nombre"));
                    p.setApellido(rs2.getString("apellido"));
                    d.addProfesor(p);
                }
                d.setPrimerProfesor();
                dicta_materia.add(d);
            }
            return dicta_materia;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<dicta> listarEvaluadosPorCoordinacion(String id_departamento) {

        PreparedStatement ps, ps2;
        ArrayList<dicta> dicta_materia = new ArrayList(0);
        String codigo_materia;
        String usbid_profesor;
        try {
            ps = conexion.prepareStatement("SELECT DISTINCT codigo_materia, "
                    + "usbid_profesor "
                    + "FROM evaluar "
                    + "WHERE codigo_departamento = ? "
                    + "AND evaluado_coordinacion = 'si' "
                    + "AND revisado_departamento = 'no' "
                    + "ORDER BY usbid_profesor;");
            ps.setString(1, id_departamento);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                usbid_profesor = rs.getString("usbid_profesor");

                int pendientes = contarEvaluacionesPendientesDepartamento(id_departamento, usbid_profesor);
                int total = contarEvaluacionesDepartamento(id_departamento, usbid_profesor);

                if (pendientes == total) {

                    dicta d = new dicta();
                    codigo_materia = rs.getString("codigo_materia");
                    d.setCodigoMateria(codigo_materia);

                    ps2 = conexion.prepareStatement("SELECT DISTINCT codigo_materia, "
                            + "usbid, nombre, apellido "
                            + "FROM evaluar, profesor "
                            + "WHERE codigo_departamento = ? "
                            + "AND usbid = usbid_profesor "
                            + "AND usbid = ?"
                            + "AND codigo_materia = ? "
                            + "AND evaluado_coordinacion = 'si' "
                            + "AND revisado_departamento = 'no';");
                    ps2.setString(1, id_departamento);
                    ps2.setString(2, usbid_profesor);
                    ps2.setString(3, codigo_materia);

                    ResultSet rs2 = ps2.executeQuery();

                    while (rs2.next()) {
                        Profesor p = new Profesor();
                        p.setUsbid(rs2.getString("usbid"));
                        p.setNombre(rs2.getString("nombre"));
                        p.setApellido(rs2.getString("apellido"));
                        d.addProfesor(p);
                    }
                    d.setPrimerProfesor();
                    dicta_materia.add(d);
                }
            }
            return dicta_materia;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<rendimientoProf> obtenerEvaluacionCoordinaciones(
            String id_departamento, String usbid_profesor, String codigo_materia) {

        PreparedStatement ps;
        ArrayList<rendimientoProf> informacion = new ArrayList<rendimientoProf>(0);
        try {
            ps = conexion.prepareStatement("SELECT codigo, nombre, "
                    + "observaciones_coordinacion "
                    + "FROM evaluar, coordinacion "
                    + "WHERE codigo_departamento = ? "
                    + "AND usbid_profesor = ? "
                    + "AND codigo_materia = ? "
                    + "AND codigo = codigo_coordinacion "
                    + "AND comentado_coordinacion = 'si';");
            ps.setString(1, id_departamento);
            ps.setString(2, usbid_profesor);
            ps.setString(3, codigo_materia);

            System.out.println(ps.toString());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                rendimientoProf rendimiento = new rendimientoProf();
                String nombre_coordinacion = rs.getString("nombre");
                String codigo_coordinacion = rs.getString("codigo");
                /* Uso estos setters porque de verdad necesito la informacion 
                 * y no quiero embasurar mas la clase */
                rendimiento.setObservaciones_d(nombre_coordinacion);
                rendimiento.setCodigo_materia(codigo_coordinacion);
                rendimiento.setObservaciones_c(rs.getString("observaciones_coordinacion"));
                informacion.add(rendimiento);
            }

            return informacion;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<rendimientoProf> obtenerEvaluacionCoordinaciones(
            String id_departamento, String usbid_profesor) {

        PreparedStatement ps;
        ArrayList<rendimientoProf> informacion = new ArrayList<rendimientoProf>(0);
        try {
            ps = conexion.prepareStatement("SELECT codigo, nombre, "
                    + "recomendado_coordinacion, observaciones_coordinacion "
                    + "FROM evaluacion, coordinacion "
                    + "WHERE usbid_profesor = ? "
                    + "AND codigo = codigo_coordinacion;");
            ps.setString(1, usbid_profesor);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                rendimientoProf rendimiento = new rendimientoProf();
                String nombre_coordinacion = rs.getString("nombre");
                String codigo_coordinacion = rs.getString("codigo");
                /* Uso estos setters porque de verdad necesito la informacion 
                 * y no quiero embasurar mas la clase */
                rendimiento.setObservaciones_d(nombre_coordinacion);
                rendimiento.setCodigo_materia(codigo_coordinacion);
                rendimiento.setObservaciones_c(rs.getString("observaciones_coordinacion"));
                rendimiento.setRecomendado(rs.getString("recomendado_coordinacion"));
                informacion.add(rendimiento);
            }

            return informacion;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<rendimientoProf> obtenerEvaluacionesEnviadasCoordinaciones(
            String id_departamento, String usbid_profesor, int ano, String trimestre) {

        PreparedStatement ps;
        ArrayList<rendimientoProf> informacion = new ArrayList<rendimientoProf>(0);
        try {
            ps = conexion.prepareStatement("SELECT DISTINCT codigo, c.nombre, "
                    + "recomendado, observaciones "
                    + "FROM evaluado as ev, coordinacion as c, profesor as pr, "
                    + "pertenece as p "
                    + "WHERE p.codigo_departamento = ? "
                    + "AND usbid = p.usbid_profesor "
                    + "AND ev.usbid_profesor = usbid "
                    + "AND ev.usbid_profesor = ? "
                    + "AND codigo = codigo_coordinacion "
                    + "AND ev.ano = ? "
                    + "AND ev.trimestre = ?;");
            ps.setString(1, id_departamento);
            ps.setString(2, usbid_profesor);
            ps.setInt(3, ano);
            ps.setString(4, trimestre);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                rendimientoProf rendimiento = new rendimientoProf();
                String nombre_coordinacion = rs.getString("nombre");
                String codigo_coordinacion = rs.getString("codigo");
                /* Uso estos setters porque de verdad necesito la informacion 
                 * y no quiero embasurar mas la clase */
                rendimiento.setObservaciones_d(nombre_coordinacion);
                rendimiento.setCodigo_materia(codigo_coordinacion);
                rendimiento.setObservaciones_c(rs.getString("observaciones"));
                rendimiento.setRecomendado(rs.getString("recomendado"));
                informacion.add(rendimiento);
            }

            return informacion;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}