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

    /**
     * verificarCas
     *
     * Este método es el que se encarga de auntenticar un usuario, es decir
     * revisa que pertenece a la base de datos y luego revisa que la contraseña
     * coincida.
     *
     * @param u: usuario que se quiere autenticar en el sistema.
     * @return Usuario
     *
     */
    public Usuario verificarCas(Usuario u) {

        PreparedStatement psConsultar = null;
        try {

            psConsultar = conexion.prepareStatement("SELECT * "
                    + "FROM usuario"
                    + " WHERE usbid = ? "
                    + "AND contrasena = ?");

            psConsultar.setString(1, u.getUsbid());
            psConsultar.setString(2, u.getContrasena());
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

    /**
     * agregarUsuario
     *
     * Agrega un usuario a la base de datos.
     *
     * @param u: usuario que se quiere agregar al sistema.
     * @return boolean que indica si el usuario ha sido agregado exitosamente
     */
    public boolean agregarUsuario(CreateUserForm u) {

        PreparedStatement psAgregar1, psAgregar2;

        try {
            psAgregar1 = conexion.prepareStatement("INSERT "
                    + "INTO usuario(usbid, contrasena) "
                    + "VALUES (?, ?);");
            psAgregar1.setString(1, u.getUsbid());
            psAgregar1.setString(2, u.getContrasena1());
            Integer i = psAgregar1.executeUpdate();

            psAgregar2 = conexion.prepareStatement("INSERT "
                    + "INTO profesor(usbid,email) "
                    + "VALUES (?, ?);");
            psAgregar2.setString(1, u.getUsbid());
            psAgregar2.setString(2, u.getUsbid() + "@usb.ve");
            Integer j = psAgregar2.executeUpdate();

            return i > 0 && j > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * agregarUsuario
     *
     * Agrega un usuario a la base de datos.
     *
     * @param usbid: identificador del usuario
     * @param tipousuario: tipo del usuario
     * @return boolean que indica si el usuario ha sido agregado exitosamente/
     */
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

    /**
     * listarProfesores
     *
     * Consulta profesores presentes en la base de datos.
     *
     * @return listado de profesores presentes en el sistema.
     */
    public ArrayList<Usuario> listarProfesores() {

        ArrayList<Usuario> usrs = new ArrayList<Usuario>(0);
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * "
                    + "FROM USUARIO "
                    + "WHERE tipousuario = 'profesor' "
                    + "ORDER BY usbid");
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

    /**
     * listarDecanatos
     *
     * Consulta todos los decanatos del sistema
     *
     * @return listado de decanatos.
     */
    public ArrayList<Decanato> listarDecanatos() {

        ArrayList<Decanato> dcnto = new ArrayList(0);
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * "
                    + "FROM DECANATO "
                    + "ORDER BY codigo");
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

    /**
     * listarCoordinaciones
     *
     * Consulta las coordinaciones registradas en el sistema
     *
     * @return listado de coordinaciones
     */
    public ArrayList<Coordinacion> listarCoordinaciones() {

        ArrayList<Coordinacion> coords = new ArrayList<Coordinacion>(0);
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * "
                    + "FROM COORDINACION "
                    + "ORDER BY codigo");
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

    /**
     * listarCoordinacionesAdscritas
     *
     * Consulta de las coordinaciones adscritas a un determinado departamento
     * con sus respectivas evaluaciones pendientes (dado el caso)
     *
     * @param id_decanato: identificador del decanato
     * @param opcion: parámetro que define si se deben contar las instancias de
     * evaluaciones que se deben revisar en el sistema.
     * @return listado de coordinaciones
     */
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

    /**
     * listarDepartamentos
     *
     * Consulta el listado de departamentos activos en el sistema.
     *
     * @return listado de departamentos
     */
    public ArrayList<Departamento> listarDepartamentos() {

        ArrayList<Departamento> dptos = new ArrayList<Departamento>(0);
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * "
                    + "FROM DEPARTAMENTO "
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

    /**
     * obtenerInfoProfesor
     *
     * Obtener la información completa de determinado profesor.
     *
     * @param usbid: identificador del profesor
     * @return todos los datos del profesor
     */
    public Profesor obtenerInfoProfesor(String usbid) {

        Profesor u = new Profesor();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * "
                    + "FROM PROFESOR "
                    + "WHERE usbid = ?;");
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

    /**
     * eliminarUsuario
     *
     * Eliminar un usuario del sistema.
     *
     * @param id_usuario: identificador del usuario que se quiere eliminar
     * @return boolean que determina si el usuario fue o no eliminado
     */
    public boolean eliminarUsuario(String id_usuario) {
        PreparedStatement ps;
        try {

            ps = conexion.prepareStatement("DELETE "
                    + "FROM USUARIO "
                    + "WHERE usbid = ?;");
            ps.setString(1, id_usuario);

            Integer s = ps.executeUpdate();

            return s > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * eliminarProfesor
     *
     * Eliminar un profesor del sistema.
     *
     * @param p: profesor que se quiere eliinar
     * @return boolean que determina si el profesor fue o no eliminado
     */
    public boolean eliminarProfesor(Profesor p) {
        PreparedStatement ps;
        try {

            ps = conexion.prepareStatement("DELETE "
                    + "FROM PROFESOR "
                    + "WHERE usbid = ?;");
            ps.setString(1, p.getUsbid());

            Integer r = ps.executeUpdate();
            boolean usuario_eliminado = eliminarUsuario(p.getUsbid());

            return usuario_eliminado && r > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * actualizarInfoProfesor
     *
     * Dado un profesor actualiza su infomación.
     *
     * @param u: profesor a actualizar
     * @return
     */
    public boolean actualizarInfoProfesor(Profesor u) {

        PreparedStatement psAgregar1 = null;

        try {
            psAgregar1 = conexion.prepareStatement("UPDATE PROFESOR "
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

    /**
     * modificarProfesor
     *
     * @param p: profesor a modificar
     * @return booleano que determina si el profesor fue o no modificado
     */
    public boolean modificarProfesor(Profesor p) {

        PreparedStatement ps0, ps1 = null;

        try {
            ps0 = conexion.prepareStatement("UPDATE PROFESOR "
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

    /**
     * resetInfoProfesor
     *
     * Dado un profesor se elimina la información adicional proporcionada.
     *
     * @param u: identificador del profesor cuya información se quiere eliminar
     * @return booleano que determina si la informacion del profesor fue
     * eliminada
     */
    public boolean resetInfoProfesor(Profesor u) {

        PreparedStatement psAgregar1 = null;

        try {
            psAgregar1 = conexion.prepareStatement("UPDATE PROFESOR "
                    + "SET email_personal = ?, nivel = ?, jubilado = ?, "
                    + "lapso_contractual_inicio = ?, lapso_contractual_fin = ? "
                    + "WHERE usbid = ? ");
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

    /**
     * eliminarCoordinacion
     *
     * Elimina una coordinacion de la base de datos del sistema
     *
     * @param u: coordinacion a eliminar
     * @return booleando que determina si la coordinacion fue o no eliminada.
     */
    public boolean eliminarCoordinacion(Coordinacion u) {

        PreparedStatement psEliminar1 = null;
        try {
            psEliminar1 = conexion.prepareStatement("DELETE "
                    + "FROM COORDINACION "
                    + "WHERE codigo = ?;");
            psEliminar1.setString(1, u.getCodigo());

            Integer i = psEliminar1.executeUpdate();
            boolean usuario_eliminado = eliminarUsuario(u.getCodigo());

            return i > 0 && usuario_eliminado;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * registrarDecanato
     *
     * Registra un decanato al sistema
     *
     * @param u: decanato a registrar
     * @return booleano que determina si el decanato fue registrado.
     */
    public boolean registrarDecanato(Decanato u) {

        PreparedStatement ps;

        try {
            ps = conexion.prepareStatement("INSERT "
                    + "INTO DECANATO(codigo, nombre) "
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

    /**
     * eliminarDecanato
     *
     * Elimina un decanato del sistema
     *
     * @param decanato: decanato a eliminar
     * @return booleano que determina si el decanato fue eliminado.
     */
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

    /**
     * adscribirCoordinacion
     *
     * Adscribir una coordinación a un decanato determinado
     *
     * @param c: coordinacion a adscribir
     * @param id_decanato: decanato al que debe estar adscrita
     * @param decanato: en caso que el administrador sea quien adscriba la
     * coordinacion se debe obtener el identificador del decanato
     * correspondiente.
     * @return booleano que determina si la coordinacion fue adscrita.
     */
    public boolean adscribirCoordinacion(Coordinacion c, String id_decanato,
            boolean decanato) {

        PreparedStatement ps1, ps2;
        try {
            ps1 = conexion.prepareStatement("INSERT "
                    + "INTO COORDINACION(codigo, nombre) "
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

            ps2 = conexion.prepareStatement("INSERT "
                    + "INTO se_adscribe(codigo_coordinacion, codigo_decanato) "
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

    /**
     * actualizarNombreDecanato
     *
     * Modificar el nombre de un decanato determinado
     *
     * @param u: decanato a actualizar
     * @return booleano que determina si el decanato fue actualizado
     */
    public boolean actualizarNombreDecanato(Decanato u) {
        PreparedStatement ps = null;
        try {

            ps = conexion.prepareStatement("UPDATE DECANATO "
                    + "SET nombre = ? "
                    + "WHERE codigo = ?;");

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCodigo());
            Integer s = ps.executeUpdate();

            return s > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * actualizarNombreCoordinacion
     *
     * Modificar el nombre de una coordinacion determinada
     *
     * @param u: coordinacion a actualizar
     * @return booleano que determina si la coordinacion fue o no actualizada
     */
    public boolean actualizarNombreCoordinacion(Coordinacion u) {
        PreparedStatement ps = null;
        try {

            ps = conexion.prepareStatement("UPDATE COORDINACION "
                    + "SET nombre = ? "
                    + "WHERE codigo = ?;");

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCodigo());
            Integer s = ps.executeUpdate();

            return s > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * obtenerNombreDecanato
     *
     * Dado un decanato obtener su nombre.
     *
     * @param u: decanato del que se quiere obtener el nombre
     * @return objeto Decanato con la información correspondiente
     */
    public Decanato obtenerNombreDecanato(Decanato u) {

        PreparedStatement ps;
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

    /**
     * obtenerCodigoDecanato
     *
     * Dado el nombre de un decanato obtener su código.
     *
     * @param u: decanato
     * @return codigo del decanato
     */
    public String obtenerCodigoDecanato(Decanato u) {

        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * "
                    + "FROM DECANATO "
                    + "WHERE nombre = ?;");
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

    /**
     * obtenerNombreCoordinacion
     *
     * Dado un codigo de coordinacion obtener su nombre
     *
     * @param u: coordinacion
     * @return objeto Coordinacion con la informacion correspondiente
     */
    public Coordinacion obtenerNombreCoordinacion(Coordinacion u) {

        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement("SELECT * "
                    + "FROM COORDINACION "
                    + "WHERE codigo = ?");
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

    /**
     * obtenerNombreDepartamento
     *
     * Dado un departamento obtener su nombre
     *
     * @param u: departamento
     * @return objeto Departamento con la información correspondiente.
     */
    public Departamento obtenerNombreDepartamento(Departamento u) {

        PreparedStatement ps;
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

    /**
     * listarMateriasOfertadas
     *
     * Listar las materias ofertadas por un departamento determinado
     *
     * @param id_departamento: departamento del que se quieren listar las
     * materias ofertadas.
     * @return listado de materias.
     */
    public ArrayList<Materia> listarMateriasOfertadas(String id_departamento) {

        ArrayList<Materia> materias = new ArrayList<Materia>(0);
        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement("SELECT codigo, nombre, creditos "
                    + "FROM oferta, MATERIA "
                    + "WHERE codigo_departamento = ? "
                    + "AND codigo_materia = codigo "
                    + "AND solicitud = 'no';");

            ps.setString(1, id_departamento);
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

    /**
     * listarMateriasOfertadas
     *
     * Listar las materias ofertadas por un departamento determinado que no
     * maneja la coordinación que solicita el listado
     *
     * @param id_departamento: departamento del que se quieren listar las
     * materias ofertadas.
     * @return listado de materias.
     */
    public ArrayList<Materia> listarMateriasOfertadas(String id_departamento,
            String id_coordinacion) {

        ArrayList<Materia> materias = new ArrayList<Materia>(0);
        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement("SELECT codigo, nombre, creditos "
                    + "FROM oferta, MATERIA "
                    + "WHERE codigo_departamento = ? "
                    + "AND codigo_materia = codigo "
                    + "AND solicitud = 'no' "
                    + "AND codigo NOT IN ("
                    + "SELECT codigo_materia "
                    + "FROM maneja "
                    + "WHERE codigo_coordinacion = ?"
                    + ");");

            ps.setString(1, id_departamento);
            ps.setString(2, id_coordinacion);
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

    /**
     * listarProfesoresAEvaluarDepartamento
     *
     * Listar los profesores que un determinado departamento deba evaluar.
     *
     * @param id_departamento: identificador del departamento que debe evaluar
     * @return listado de profesores
     */
    public ArrayList<Profesor> listarProfesoresAEvaluarDepartamento(String id_departamento) {

        ArrayList<Profesor> profesores = new ArrayList<Profesor>(0);
        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement("SELECT DISTINCT usbid, nombre, apellido "
                    + "FROM PROFESOR, evaluacion as ec, pertenece as p "
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

    /**
     * listarProfesoresAEvaluar
     *
     * Listar todos aquellos profesores que tengan las planillas
     * correspondientes llenas en su totalidad para proceder a enviar el memo de
     * evaluación.
     *
     * @param id_departamento: identificador del departamento
     * @return
     */
    public ArrayList<Profesor> listarProfesoresAEvaluar(String id_departamento) {

        ArrayList<Profesor> profesores = new ArrayList<Profesor>(0);
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("SELECT * "
                    + "FROM pertenece, PROFESOR "
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
                        && cantidadMateriasQueDicta(p.getUsbid(), id_departamento)
                        == cantidadPlanillaLlena(p.getUsbid(), id_departamento)) {
                    profesores.add(p);
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return profesores;
    }

    /**
     * listarProfesoresEvaluadosDepartamento
     *
     * Listar profesores evaluados que pertenecen a un determinado departamento.
     *
     * @param id_departamento: departamento del que se obtiene el listado.
     * @return listado de profesores
     */
    public ArrayList<Profesor> listarProfesoresEvaluadosDepartamento(
            String id_departamento) {

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

    /**
     * listarProfesoresDepartamento
     *
     * Listar los profesores de un departamento determinado
     *
     * @param id_departamento: departamento del que se obtiene el listado.
     * @return listado de profesores
     */
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

    /**
     * listarProfesoresActivosDepartamento
     *
     * Listar los profesores activos de un determinado departamento
     *
     * @param id_departamento: departamento del que se obtiene el listado.
     * @return listado de profesores
     */
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

    /**
     * enviarMemoEvaluarProfesor
     *
     * Envía el memo a las coordinaciones que deben evaluar a los profesores,
     * este mecanismo es el que desencadena la evaluación de profesores.
     *
     * @param id_profesores: listado de profesores a evaluar
     * @param id_departamento: departamento que desencadena la acción.
     */
    public void enviarMemoEvaluarProfesor(String[] id_profesores, String id_departamento) {
        PreparedStatement ps, ps2;
        Set<String> coords = new TreeSet<String>();

        try {
            for (int A = 0; A < id_profesores.length; A++) {
                String usbid_prof = id_profesores[A];
                ps = conexion.prepareStatement("SELECT dicta.codigo_materia, "
                        + "usbid_profesor, codigo_coordinacion "
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

                    ps2 = conexion.prepareStatement("INSERT "
                            + "INTO evaluar "
                            + "VALUES (?,?,?,?);");
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

    /**
     * eliminarMateria
     *
     * Desactiva una materia determinada
     *
     * @param id_materia: identificador de la materia a desactivar.
     * @return booleano que determina si la materia fue o no desactivada.
     */
    public boolean eliminarMateria(String id_materia) {
        PreparedStatement ps;

        try {
            ps = conexion.prepareStatement("DELETE FROM MATERIA "
                    + "WHERE codigo = ?;");
            ps.setString(1, id_materia);

            Integer i = ps.executeUpdate();

            return i > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * modificarMateria
     *
     * Modificar los datos de determinada materia.
     *
     * @param materia: materia a modificar
     * @return booleano que determina si la materia fue o no modificada.
     */
    public boolean modificarMateria(Materia materia) {
        PreparedStatement ps;

        try {
            ps = conexion.prepareStatement("UPDATE MATERIA "
                    + "SET codigo = ?, nombre = ?, creditos = ? "
                    + "WHERE codigo = ?;");
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

    /**
     * obtenerDatosMateria
     *
     * Dada una materia obtener toda su información.
     *
     * @param materia: materia de la cual se quiere obtener la información.
     * @return objeto Materia con la información correspondiente.
     */
    public Materia obtenerDatosMateria(Materia materia) {
        PreparedStatement ps = null;

        try {
            ps = conexion.prepareStatement("SELECT * "
                    + "FROM MATERIA "
                    + "WHERE codigo = ?;");
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

    /**
     * obtenerDatosDepartamento
     *
     * Dado un departartamento obtener toda su información.
     *
     * @param departamento: identificador del departamento del que se quiere
     * obtener la información.
     * @return código del departamento
     */
    public String obtenerDatosDepartamento(String departamento) {
        PreparedStatement ps;

        try {
            ps = conexion.prepareStatement("SELECT * "
                    + "FROM DEPARTAMENTO "
                    + "WHERE nombre = ?;");
            ps.setString(1, departamento);

            ResultSet rs = ps.executeQuery();
            rs.next();

            return rs.getString("codigo");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * vincularMateriaCoordinacion
     *
     * Vincular una materia a una determinada coordinacion.
     *
     * @param cod_coord: codigo de la coordinacion
     * @param cod_materia: codigo de la materia q se quiere vincular
     * @return booleano que determina si la materia fue vinculada.
     */
    public boolean vincularMateriaCoordinacion(String cod_coord,
            String cod_materia) {
        PreparedStatement ps;

        try {
            ps = conexion.prepareStatement("INSERT "
                    + "INTO maneja "
                    + "VALUES (?,?)");
            ps.setString(1, cod_coord);
            ps.setString(2, cod_materia);
            Integer i = ps.executeUpdate();

            return i > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * desvincularMateriaCoordinacion
     *
     * Desvincular una materia a una determinada coordinacion.
     *
     * @param cod_coord: codigo de la coordinacion
     * @param cod_materia: codigo de la materia que se quiere desvincular
     * @return booleno que determina si la materia fue desvinculada
     */
    public boolean desvincularMateriaCoordinacion(String cod_coord, String cod_materia) {
        PreparedStatement ps;

        try {
            ps = conexion.prepareStatement("DELETE "
                    + "FROM maneja "
                    + "WHERE codigo_coordinacion = ? "
                    + "AND codigo_materia = ?");
            ps.setString(1, cod_coord);
            ps.setString(2, cod_materia);
            Integer i = ps.executeUpdate();

            return i > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;

    }

    /**
     * listarMateriasCoordinacion
     *
     * Listar las materia vinculadas a determinada coordinacion.
     *
     * @param cod_coord: identificador de la coordinacion.
     * @return listado de las materias.
     */
    public ArrayList<Materia> listarMateriasCoordinacion(String cod_coord) {
        PreparedStatement ps;
        ArrayList<Materia> materias = new ArrayList<Materia>(0);

        try {
            ps = conexion.prepareStatement("SELECT DISTINCT codigo, nombre "
                    + "FROM maneja, materia, oferta "
                    + "WHERE maneja.codigo_coordinacion = ? "
                    + "AND maneja.codigo_materia = codigo;");
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

    /**
     * contarEvaluacionesPendientesCoordinacion
     *
     * Contar las evaluaciones pendientes.
     *
     * @param id_coordinacion: identificador de la coordinacion
     * @return número de evaluaciones pendientes
     */
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

    /**
     * contarEvaluacionesPendientesDepartamento
     *
     * Contar las evaluaciones pendientes.
     *
     * @param id_departamento: identificador del departamento
     * @return número de evaluaciones pendientes
     */
    public int contarEvaluacionesPendientesDepartamento(String id_departamento,
            String profesor) {

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

    /**
     * contarEvaluacionesDepartamento
     *
     * @param id_departamento
     * @param usbid_profesor
     * @return número de evaluaciones pendientes
     */
    public int contarEvaluacionesDepartamento(String id_departamento, String usbid_profesor) {

        PreparedStatement ps;
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

    /**
     * listarEvaluacionesPendientes
     *
     * Dada una coordinación y un profesor listar las evaluaciones pendientes.
     *
     * @param coordinacion
     * @param usbid_profesor
     * @return listado de rendimientos
     */
    public ArrayList<Dicta> listarEvaluacionesPendientes(Coordinacion coordinacion,
            String usbid_profesor) {

        PreparedStatement ps, ps2;
        ArrayList<Dicta> dicta_materia = new ArrayList(0);
        String codigoMateria;
        String id_coordinacion = coordinacion.getCodigo();
        try {
            ps = conexion.prepareStatement("SELECT DISTINCT e.codigo_materia, "
                    + "nombre, trimestre "
                    + "FROM evaluar as e, materia as m, rendimiento as r "
                    + "WHERE e.codigo_coordinacion = ? "
                    + "AND e.usbid_profesor = r.usbid_profesor "
                    + "AND e.codigo_materia = r.codigo_materia "
                    + "AND e.usbid_profesor = ? "
                    + "AND m.codigo = e.codigo_materia "
                    + "AND r.evaluado = 'no' "
                    + "AND e.usbid_profesor NOT IN ("
                    + "SELECT ec.usbid_profesor "
                    + "FROM evaluacion as ec "
                    + "WHERE revisado_decanato = 'si' "
                    + ");");
            ps.setString(1, id_coordinacion);
            ps.setString(2, usbid_profesor);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Dicta d = new Dicta();
                codigoMateria = rs.getString("codigo_materia");
                d.setCodigoMateria(codigoMateria);
                d.setOpcion(rs.getString("nombre"));
                d.setPeriodo(obtenerTrimestrePorSiglas(rs.getString("trimestre")));

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

    /**
     * listarEvaluacionesPendientes
     *
     * Dada una coordinación y un profesor listar las evaluaciones pendientes.
     *
     * @param id_coordinacion
     * @param usbid_profesor
     * @return listado de rendimientos
     */
    public ArrayList<Dicta> listarEvaluacionesPendientes(String id_coordinacion,
            String usbid_profesor) {

        PreparedStatement ps, ps2;
        ArrayList<Dicta> dicta_materia = new ArrayList(0);
        String codigoMateria;
        try {
            ps = conexion.prepareStatement("SELECT DISTINCT r.codigo_materia, trimestre, nombre "
                    + "FROM evaluar as e, materia as m, rendimiento as r "
                    + "WHERE e.codigo_coordinacion = ? "
                    + "AND r.codigo_materia = e.codigo_materia "
                    + "AND e.codigo_materia = codigo "
                    + "AND e.usbid_profesor = r.usbid_profesor "
                    + "AND e.usbid_profesor = ? "
                    + "AND r.evaluado = 'no' "
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
                Dicta d = new Dicta();
                codigoMateria = rs.getString("codigo_materia");
                d.setCodigoMateria(codigoMateria);
                d.setOpcion(rs.getString("nombre"));

                d.setPeriodo(obtenerTrimestrePorSiglas(rs.getString("trimestre")));

                ps2 = conexion.prepareStatement("SELECT codigo_materia, usbid, "
                        + "nombre, apellido "
                        + "FROM evaluar, profesor "
                        + "WHERE codigo_coordinacion = ? "
                        + "AND usbid = usbid_profesor "
                        + "AND codigo_materia = ? ");
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

    /**
     * listarEvaluacionesEnviadasCoordinacion
     *
     * Listar las evaluaciones enviadas de una coordinacion en determinado año y
     * trimestre.
     *
     * @param id_coordinacion: identificador de la coordinacion
     * @param ano: año de la evaluacion
     * @param trimestre: trimestre de la evaluacion
     * @return listado de evaluaciones
     */
    public ArrayList<Rendimiento> listarEvaluacionesEnviadasCoordinacion(String id_coordinacion,
            int ano, String trimestre) {

        PreparedStatement ps;
        ArrayList<Rendimiento> rendimientos = new ArrayList(0);

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
                Rendimiento rendimiento = new Rendimiento();
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

    /**
     * listarEvaluacionesEnviadasDepartamento
     *
     * Listar las evaluaciones enviadas de un departamento en determinado año y
     * trimestre.
     *
     * @param id_departamento: identificador del departamento
     * @param ano: año de la evaluación
     * @param trimestre: trimestre de la evaluación
     * @return listado de evaluaciones
     */
    public ArrayList<Rendimiento> listarEvaluacionesEnviadasDepartamento(String id_departamento,
            int ano, String trimestre, String usbid_profesor) {

        PreparedStatement ps;
        ArrayList<Rendimiento> rendimientos = new ArrayList(0);

        try {
            ps = conexion.prepareStatement("SELECT DISTINCT m.nombre, "
                    + "m.codigo, r.ano, r.trimestre "
                    + "FROM pertenece as p, evaluado as e, rendimiento as r, "
                    + "materia as m "
                    + "WHERE p.codigo_departamento = ? "
                    + "AND r.usbid_profesor = ? "
                    + "AND p.usbid_profesor = e.usbid_profesor "
                    + "AND r.ano_evaluacion = ?"
                    + "AND r.trimestre_evaluacion = ? "
                    + "AND m.codigo = r.codigo_materia;");
            ps.setString(1, id_departamento);
            ps.setString(2, usbid_profesor);
            ps.setInt(3, ano);
            ps.setString(4, trimestre);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Rendimiento rendimiento = new Rendimiento();
                rendimiento.setCodigo_materia(rs.getString("codigo"));
                rendimiento.setNombre_materia(rs.getString("nombre"));
                rendimiento.setAno(rs.getInt("ano"));
                rendimiento.setTrimestre(
                        obtenerTrimestrePorSiglas(rs.getString("trimestre")));
                rendimientos.add(rendimiento);
            }

            return rendimientos;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * listarEvaluacionesCoordinacion
     *
     * Listado de evaluaciones pendientes de un determinado profesor, materia,
     * año y trimestre
     *
     * @param id_coordinacion: identificador de la coordinacion.
     * @param ano: año de la evaluación
     * @param trimestre: trimestre de la evaluación
     * @param codigo_materia: codigo de la materia que está siendo evaluada
     * @param usbid_profesor: usbid del profesor que está siedo evaluado.
     * @return evaluación de la materia.
     */
    public Rendimiento listarEvaluacionesCoordinacion(String id_coordinacion,
            int ano, String trimestre, String codigo_materia, String usbid_profesor) {

        PreparedStatement ps;
        Rendimiento rendimiento = null;

        try {
            ps = conexion.prepareStatement("SELECT m.codigo, r.ano, r.trimestre, "
                    + "m.nombre, e.usbid_profesor "
                    + "FROM evaluar as e, materia as m, rendimiento as r "
                    + "WHERE e.codigo_coordinacion = ? "
                    + "AND r.ano = ? "
                    + "AND r.trimestre = ? "
                    + "AND e.codigo_materia = codigo "
                    + "AND e.codigo_materia = r.codigo_materia "
                    + "AND r.usbid_profesor = e.usbid_profesor "
                    + "AND r.usbid_profesor = ? "
                    + "AND e.codigo_materia = ? ");
            ps.setString(1, id_coordinacion);
            ps.setInt(2, ano);
            ps.setString(3, trimestre);
            ps.setString(4, usbid_profesor);
            ps.setString(5, codigo_materia);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                rendimiento = new Rendimiento();
                rendimiento.setCodigo_materia(rs.getString("codigo"));
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

    /**
     * listarEvaluacionesGeneralCoordinacion
     *
     * Listado general que incluye las materias que dicto el profesor que está
     * siendo evaluado
     *
     * @param id_coordinacion
     * @param usbid_profesor
     * @return
     */
    public Rendimiento listarEvaluacionesGeneralCoordinacion(
            String id_coordinacion, String usbid_profesor) {

        PreparedStatement ps, ps2;
        Rendimiento rendimiento = null;

        try {

            ps = conexion.prepareStatement("SELECT * "
                    + "FROM evaluacion "
                    + "WHERE usbid_profesor = ? "
                    + "AND codigo_coordinacion = ?;");
            ps.setString(1, usbid_profesor);
            ps.setString(2, id_coordinacion);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                rendimiento = new Rendimiento();
                rendimiento.setObservaciones_c(rs.getString("observaciones_coordinacion"));
                rendimiento.setUsbid_profesor(rs.getString("usbid_profesor"));
                rendimiento.setRecomendado(rs.getString("recomendado_coordinacion"));
            }

            return rendimiento;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * listarEvaluacionesGeneralCoordinacion
     *
     * Listado general que incluye las materias que dicto el profesor que está
     * siendo evaluado
     *
     * @param id_coordinacion
     * @param usbid_profesor
     * @return
     */
    public Rendimiento listarEvaluacionesGeneralCoordinacion(
            String id_coordinacion, String usbid_profesor, int ano, String trimestre) {

        PreparedStatement ps, ps2;
        Rendimiento rendimiento = null;

        try {

            ps = conexion.prepareStatement("SELECT * "
                    + "FROM evaluado "
                    + "WHERE usbid_profesor = ? "
                    + "AND codigo_coordinacion = ? "
                    + "AND ano = ? "
                    + "AND trimestre = ?;");
            ps.setString(1, usbid_profesor);
            ps.setString(2, id_coordinacion);
            ps.setInt(3, ano);
            ps.setString(4, trimestre);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                rendimiento = new Rendimiento();
                rendimiento.setObservaciones_c(rs.getString("observaciones"));
                rendimiento.setUsbid_profesor(rs.getString("usbid_profesor"));
                rendimiento.setRecomendado(rs.getString("recomendado"));
            }

            return rendimiento;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * listarEvaluacionesEnviadasCoordinacion
     *
     * Listado de evaluaciones enviadas (por parte de la coordinación_ de
     * determinado profesor, año y trimestre.
     *
     * @param id_coordinacion: identificador de la coordinacion
     * @param ano: año de la evaluación
     * @param trimestre: trimestre de la evaluación.
     * @param usbid_profesor identificador del profesor.
     * @return listado de evaluaciones
     */
    public ArrayList<Rendimiento> listarEvaluacionesEnviadasCoordinacion(String id_coordinacion,
            int ano, String trimestre, String usbid_profesor) {

        PreparedStatement ps;
        ArrayList<Rendimiento> rendimientos = new ArrayList(0);

        try {
            ps = conexion.prepareStatement("SELECT DISTINCT m.nombre, m.codigo, r.trimestre "
                    + "FROM evaluado as e, materia as m, rendimiento as r "
                    + "WHERE e.codigo_coordinacion = ? "
                    + "AND r.codigo_materia = codigo "
                    + "AND e.usbid_profesor = r.usbid_profesor "
                    + "AND e.usbid_profesor = ? "
                    + "AND r.ano_evaluacion = ? "
                    + "AND r.trimestre_evaluacion = ?;");
            ps.setString(1, id_coordinacion);
            ps.setString(2, usbid_profesor);
            ps.setInt(3, ano);
            ps.setString(4, trimestre);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Rendimiento rendimiento = new Rendimiento();
                rendimiento.setCodigo_materia(rs.getString("codigo"));
                rendimiento.setNombre_materia(rs.getString("nombre"));
                rendimiento.setTrimestre(
                        obtenerTrimestrePorSiglas(rs.getString("trimestre")));

                rendimientos.add(rendimiento);
            }

            return rendimientos;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * registrarMateria
     *
     * Registro de una materia
     *
     * @param m: materia a registrar
     * @param id_departamento: identificador del departamento que va a realizar
     * el registro.
     * @return booleano que determina si la materia fue registrada.
     */
    public boolean registrarMateria(Materia m, String id_departamento) {

        PreparedStatement ps1, ps2;

        try {
            ps1 = conexion.prepareStatement("INSERT "
                    + "INTO MATERIA(codigo, nombre, creditos) "
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

    /**
     * actualizarDepartamento
     *
     * Modificación de la información de un departamento
     *
     * @param d: departamento a ser modificado
     * @return booleano que determina si el departamento fue modificado
     */
    public boolean actualizarDepartamento(Departamento d) {
        PreparedStatement ps;
        try {

            ps = conexion.prepareStatement("UPDATE DEPARTAMENTO "
                    + "SET nombre = ? "
                    + "WHERE codigo = ?;");

            ps.setString(1, d.getNombre());
            ps.setString(2, d.getCodigo());
            Integer s = ps.executeUpdate();

            return s > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * registrarDepartamento
     *
     * Registro de un departamento
     *
     * @param d: departamento a ser registrado
     * @return booleano que determina si el departamento fue registrado.
     */
    public boolean registrarDepartamento(Departamento d) {
        PreparedStatement ps;

        try {
            ps = conexion.prepareStatement("INSERT INTO DEPARTAMENTO "
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

    /**
     * eliminarDepartamento
     *
     * Eliminar un departamento
     *
     * @param d: departamento a eliminar
     * @return booleano que determina si el departamento fue eliminado.
     */
    public boolean eliminarDepartamento(Departamento d) {
        PreparedStatement ps;

        try {
            ps = conexion.prepareStatement("DELETE FROM DEPARTAMENTO "
                    + "WHERE codigo = ?;");
            ps.setString(1, d.getCodigo());

            Integer i = ps.executeUpdate();
            boolean usuario_eliminado = eliminarUsuario(d.getCodigo());

            return i > 0 && usuario_eliminado;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * contarSolicitudesPendientesDepartamento
     *
     * Contar las solicitudes de apertura de una materia de un departamento.
     *
     * @param id_departamento: departamento sobre el cuel se listarán las
     * solicitudes
     * @return número de solicitudes pendientes
     */
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

    /**
     * contarEvaluacionesPendientesDecanato
     *
     * Contas las evaluaciones pendientes por parte del decanato.
     *
     * @param id_decanato: identificador del decanato
     * @return número de evaluaciones pendientes
     */
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

    /**
     * evaluacionIniciadaProfesor
     *
     * Obtener si la evaluación de determinado profesor ya se ha iniciado
     *
     * @param usbid_profesor
     * @return booleano que determina si se ha iniciado la evaluacion.
     */
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

    /**
     * solicitudRegistrarMateria
     *
     * Agregar una solicitud de registro de materia al sistema
     *
     * @param m: materia de la solicitud
     * @param id_departamento: identificador del departamento al que se le hace
     * la solicitud
     * @param id_coordinacion: identificador de la coordinación que realiza la
     * solicitud
     * @return booleano que determina si la solicitud fue almacenada.
     */
    public boolean solicitudRegistrarMateria(Materia m, String id_departamento,
            String id_coordinacion) {

        PreparedStatement ps1, ps2;

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

    /**
     * listarMateriasSolicitadasDepartamento
     *
     * Listar solicitudes de apertura de materias
     *
     * @param id_departamento: identificador del departamento al que está ligada
     * la solicitud
     * @return listado de materias
     */
    public ArrayList<Materia> listarMateriasSolicitadasDepartamento(
            String id_departamento) {

        ArrayList<Materia> materias = new ArrayList<Materia>(0);
        PreparedStatement ps;
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

    /**
     * obtenerMensaje
     *
     * Obtener mensaje de solicitud de apertura de materia
     *
     * @param id_departamento: identificador del departamento que revise las
     * solicitudes
     * @return solicitud de la materia
     */
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

    /**
     * aprobarSolicitudMateria
     *
     * Aprobar solicitud de la materia
     *
     * @param m: solicitud de la materia
     * @param id_departamento: identificador del departamento que aprueba la
     * solicitud
     * @return booleano que determina que la solicitud fue aprobada
     */
    public boolean aprobarSolicitudMateria(Materia m, String id_departamento) {

        PreparedStatement ps0, ps1, ps3, ps4, ps5;

        try {

            ps0 = conexion.prepareStatement("UPDATE MATERIA "
                    + "SET codigo = ?, creditos = ?, nombre = ? "
                    + "WHERE codigo = ?;");
            ps0.setString(1, m.getCodigo());
            ps0.setString(2, m.getCreditos());
            ps0.setString(3, m.getNombre());
            ps0.setString(4, m.getViejoCodigo());

            ps1 = conexion.prepareStatement("UPDATE "
                    + "MATERIA SET solicitud = 'no' "
                    + "WHERE codigo = ?;");
            ps1.setString(1, m.getCodigo());

            ps3 = conexion.prepareStatement("INSERT "
                    + "INTO maneja (codigo_coordinacion, codigo_materia) "
                    + "VALUES (?,?);");
            ps3.setString(1, m.getCoordinacion());
            ps3.setString(2, m.getCodigo());

            ps4 = conexion.prepareStatement("DELETE "
                    + "FROM solicita_apertura "
                    + "WHERE codigo_materia = ?;");
            ps4.setString(1, m.getCodigo());

            ps5 = conexion.prepareStatement("INSERT "
                    + "INTO oferta (codigo_materia, codigo_departamento) "
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

    /**
     * aprobarSolicitudMateria
     *
     * Negar solicitud de la materia
     *
     * @param m: solicitud de la materia
     * @param id_departamento: identificador del departamento que niega la
     * solicitud
     * @return booleano que determina que la solicitud fue negada
     */
    public boolean negarSolicitudMateria(Materia m) {

        PreparedStatement ps;

        try {
            ps = conexion.prepareStatement("DELETE "
                    + "FROM MATERIA "
                    + "WHERE codigo = ?;");
            ps.setString(1, m.getViejoCodigo());

            Integer i = ps.executeUpdate();

            return i > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * obtenerRendimientoProfesor
     *
     * Obtener una planilla de determinado profesor
     *
     * @param id_profesor: identificador del profesor del cual se quiere obtener
     * el rendimiento.
     * @param id_departamento: identificador del departamento de
     * @return listado de planillas
     */
    public ArrayList<Rendimiento> obtenerRendimientoProfesor(String id_profesor,
            String id_departamento) {
        PreparedStatement ps;
        ArrayList<Rendimiento> rendimiento = new ArrayList<Rendimiento>(0);
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
                Rendimiento r = new Rendimiento();
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

    /**
     * obtenerPlanillasLlenas
     *
     * Obtener un listado de planillas llenas de un determinado profesor
     *
     * @param id_profesor: identificador del profesor.
     * @param id_departamento: identificador del departamento
     * @return listado de planillas llenas
     */
    public ArrayList<Rendimiento> obtenerPlanillasLlenas(String id_profesor,
            String id_departamento) {
        PreparedStatement ps;
        ArrayList<Rendimiento> rendimiento = new ArrayList<Rendimiento>(0);
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
                Rendimiento r = new Rendimiento();
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

    /**
     * obtenerPlanillasVacias
     *
     * Obtiene las planillas vacías de un determinado profesor y departamento
     *
     * @param id_profesor: identificador del profesor del cual se quieren
     * obtener las planillas
     * @param id_departamento: identificador del departamento que hace la
     * consulta.
     * @return listado de materias cuya planilla está vacía.
     */
    public ArrayList<Materia> obtenerPlanillasVacias(String id_profesor, String id_departamento) {
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

    /**
     * guardarPlanilla
     *
     * Guarda la planilla de un determinado profesor en la base de datos.
     *
     * @param u: planilla del profesor
     * @param id_departamento: identificador del departamento que se encargó de
     * llenar la planilla.
     * @return booleano que indica si la planilla fue guardada.
     */
    public boolean guardarPlanilla(Rendimiento u, String id_departamento) {

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
                    + "AND codigo_materia = ? "
                    + "AND periodo = ?;");
            ps2.setString(1, u.getUsbid_profesor());
            ps2.setString(2, u.getCodigo_materia());
            ps2.setString(3, u.getTrimestre());

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

    /**
     * cantidadPlanillaLlena
     *
     * Obtener la cantidad de planillas que llenó un departamento de un
     * profesor.
     *
     * @param id_profesor: profesor sobre el cual se quieren saber cuántas
     * planillas llenas hay
     * @param id_departamento: identificador del departamento que llenó las
     * planillas
     * @return número de planillas llenas
     */
    public int cantidadPlanillaLlena(String id_profesor, String id_departamento) {

        PreparedStatement ps1;

        try {
            ps1 = conexion.prepareStatement("SELECT count(usbid_profesor) "
                    + "FROM dicta as d, oferta as o, materia as m "
                    + "WHERE d.usbid_profesor = ? "
                    + "AND o.codigo_departamento = ? "
                    + "AND o.codigo_materia = d.codigo_materia "
                    + "AND o.codigo_materia = m.codigo "
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

    /**
     * cantidadPlanillaVacia
     *
     * Obtener la cantidad de planillas vacías de un profesor.
     *
     * @param id_profesor: profesor sobre el cual se quieren saber cuántas
     * planillas llenas hay
     * @param id_departamento: identificador del departamento que llenó las
     * planillas
     * @return número de planillas vacías
     */
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

    /**
     * cantidadMateriasQueDicta
     *
     * Obtiene la cantidad de materias que Dicta determinado profesor
     *
     * @param id_profesor: profesor del que se quiere obtener la cantidad de
     * materias que Dicta
     * @param id_departamento: identificador del departamento al que pertenece.
     * @return número de materias que Dicta
     */
    public int cantidadMateriasQueDicta(String id_profesor, String id_departamento) {

        PreparedStatement ps1;

        try {
            ps1 = conexion.prepareStatement("SELECT count(usbid_profesor) "
                    + "FROM dicta as d, oferta as o, materia as m "
                    + "WHERE d.usbid_profesor = ? "
                    + "AND o.codigo_departamento = ? "
                    + "AND o.codigo_materia = d.codigo_materia "
                    + "AND m.codigo = o.codigo_materia;");
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

    /**
     * obtenerPlanillaEvaluacionProfesor
     *
     * Obtener los datos de determinada planilla llena de un profesor
     *
     * @param id_profesor: identificador del profesor
     * @param id_materia: materia sobre la cual se lleno la planilla
     * @param ano: año en el cual se dictó la materia
     * @param trimestre: trimestre en el cual se dictó la materia
     * @return planilla de evaluación
     */
    public Rendimiento obtenerPlanillaEvaluacionProfesor(String id_profesor,
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
                Rendimiento r = new Rendimiento();
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

    /**
     * modificarRendimientoProfesor
     *
     * Modificar la planilla previamente llenada de un profesor
     *
     * @param r: planilla
     * @param id_departamento: identificador del departamento que llenó la
     * planilla
     * @return booleano que determina si la planilla fue modificada
     */
    public boolean modificarRendimientoProfesor(Rendimiento r, String id_departamento) {
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

            boolean agregado = guardarPlanilla(r, id_departamento);

            return i > 0 && agregado;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * obtenerEvaluacion
     *
     * Obtener la evaluación de un profesor en un año y trimestre determinados.
     *
     * @param d: relación que contiene el usbid del profesor, la materia, el año
     * y el trimestre
     * @return rendimiento del profesor
     */
    public Rendimiento obtenerEvaluacion(Rendimiento d) {
        PreparedStatement ps;
        Rendimiento evaluacion = new Rendimiento();
        try {
            ps = conexion.prepareStatement("SELECT total_estudiantes as t, "
                    + "nota_prom as np, nota1 as n1, nota2 as n2, "
                    + "nota3 as n3, nota4 as n4, nota5 as n5, "
                    + "retirados as r, ano, trimestre "
                    + "FROM rendimiento "
                    + "WHERE codigo_materia = ? "
                    + "AND usbid_profesor = ? "
                    + "AND trimestre = ?;");

            String codigo_materia = d.getCodigo_materia();
            ps.setString(1, codigo_materia);
            ps.setString(2, d.getUsbid_profesor());
            ps.setString(3, obtenerTrimestrePorNombre(d.getTrimestre()));

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

    /**
     * obtenerEvaluacion
     *
     * Obtener la evaluación de un profesor.
     *
     * @param usbid_profesor: identificador del profesor del cual que se quiere
     * obtener la evaluación
     * @return rendimiento del profesor
     */
    public Rendimiento obtenerEvaluacion(String usbid_profesor) {
        PreparedStatement ps;
        Rendimiento evaluacion = new Rendimiento();
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

    /**
     * obtenerEvaluacionPDF
     *
     * Obtener evaluación en función del PDF generado por el Sistema de
     * Evaluacion de Profesores
     *
     * @param usbid_profesor: identificador del profesor del cual se quiere
     * obtener la evaluación.
     * @return rendimiento del profesor
     */
    public Rendimiento obtenerEvaluacionPDF(String usbid_profesor) {
        PreparedStatement ps;
        Rendimiento evaluacion = new Rendimiento();
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

    /**
     * obtenerEvaluacion
     *
     * Obtener evaluación en función de la generación de estadísticas de
     * evaluación del sistema.
     *
     * @param codigo_materia código de la materia de la cual se quiere obtener
     * la evaluación
     * @param usbid_profesor: identificador del profesor del cual se quiere
     * obtener la evaluación.
     * @param ano: año de la evaluación
     * @param trimestre: trimestre de la evaluación
     * @return rendimiento del profesor
     */
    public Rendimiento obtenerEvaluacion(String codigo_materia,
            String usbid_profesor, int ano, String trimestre) {
        PreparedStatement ps;
        Rendimiento evaluacion = new Rendimiento();
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

    /**
     * obtenerEvaluaciones
     *
     * Obtener evaluación en función de la generación de estadísticas de
     * evaluación del sistema
     *
     * @param codigo_materia: código de la materia de la cual se quiere obtener
     * la evaluación
     * @param ano: año de la evaluación
     * @param trimestre: trimestre de la evaluación
     * @return rendimiento de todos los profesores en ese año y este trimestre
     */
    public Rendimiento obtenerEvaluaciones(String codigo_materia, int ano,
            String trimestre) {
        PreparedStatement ps;
        Rendimiento evaluacion = new Rendimiento();
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

    /**
     * agregarProfesor
     *
     * Agregar un profesor al sistema
     *
     * @param p: profesor a agregar al sistema
     * @param id_departamento: identificador del departamento que agrega un
     * profesor al sistema
     * @return booleano que determina si el profesor fue agregado.
     */
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

    /**
     * consultarMateriasSeleccionadas
     *
     * Consulta las materias seleccionadas por un departamento para asignarlas a
     * un profesor que las dicte
     *
     * @param materias_seleccionadas: lista de materias seleccionadas.
     * @return lista de materias.
     */
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

    /**
     * agregarDicta
     *
     * Agrega la interrelación "Dicta" a la base de datos.
     *
     * @param profesor: profesor que Dicta.
     * @param id_materia: materia que Dicta
     * @param periodo: período en el cual Dicta la materia
     * @return
     */
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

    /**
     * agregarEspecificacionesArchivo
     *
     * Agregar especificaciones a un archivo.
     *
     * @param usuario: usuario que carga el documento
     * @param trimestre: trimestre en el que se subió el archivo
     * @param ano: año en el que se cargo el archivo
     * @param nombre: nombre del archivo
     * @param descripcion: descripcion del archivo
     * @return booleano que determina si se cargó el archivo
     */
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

    /**
     * listarDirectoriosProfesor
     *
     * Listar los path de los directorios de un determinado profesor
     *
     * @param usbid: identificador del profesor del que se quieren obtener los
     * path
     * @return listado de paths
     */
    public ArrayList<String> listarDirectoriosProfesor(String usbid) {
        PreparedStatement ps;
        ArrayList<String> archivos = new ArrayList<String>(0);
        try {
            ps = conexion.prepareStatement("SELECT path "
                    + "FROM directorios "
                    + "WHERE usbid_profesor = ?;");
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

    /**
     * listarArchivosProfesor
     *
     * Listar los archivos subidos por un profesor
     *
     * @param archivo: objeto que contiene el nombre, año y trimestre en el que
     * se subió el archivo
     * @return listado de archivos
     */
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

    /**
     * listarAnosArchivos
     *
     * Obtiene el listado de los años en los cuales existen archivos subidos por
     * el profesor
     *
     * @param usbid_profesor: identificador del profesor del cual se quieren
     * obtener los archivos
     * @return listado de nombres de archivos
     */
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

    /**
     * consultarMateriasAsignadas
     *
     * Consultar las materias asignadas a determinado profesor
     *
     * @param usbid_profesor: profesor al cual se quieren consultar las materias
     * dictadas
     * @return listado de materias
     */
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

    /**
     * eliminarPeriodos
     *
     * Eliminar períodos en los que determinado profesor Dicta las materias.
     *
     * @param id_profesor: profesor al cual se eliminará la carga académica
     * @param codigo_materia: código de la materia a la cual se eliminará la
     * carga académica
     * @return booleano que determina si se elimino el período determinado
     */
    public boolean eliminarPeriodos(String id_profesor, String codigo_materia) {
        PreparedStatement ps1;
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

    /* ESTO SE DEBE ELIMINAR */
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

    /**
     * listarMateriasNoDictadas
     *
     * Obtiene el listado de materias que un determinado profesor no Dicta
     *
     * @param id_departamento: identificador del departamento
     * @param id_profesor: identificador del profesor sobre el cual se realizará
     * el listado
     * @return listado de materias.
     */
    public ArrayList<Materia> listarMateriasNoDictadas(String id_departamento,
            String id_profesor) {

        ArrayList<Materia> materias = new ArrayList<Materia>(0);
        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement("SELECT * "
                    + "FROM oferta as o, materia "
                    + "WHERE o.codigo_departamento = ? "
                    + "AND o.codigo_materia = codigo "
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

    /**
     * evaluarCoordinacion
     *
     * La coordinación evalúa a determinado profesor
     *
     * @param rendimiento: objeto que contiene la información para la evaluación
     * @param id_coordinacion: identificador de la coordinación que evalúa
     * @return booleano que determina que se evaluó
     */
    public boolean evaluarCoordinacion(Rendimiento rendimiento,
            String id_coordinacion) {

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

    /**
     * revisadoPorDecanato
     *
     * Determina si una evaluación fue revisada por el decanato
     *
     * @param rendimiento: objeto que contiene la información de la evaluación
     * @param id_departamento: identificador del decanato que se revisó.
     * @return booleano que determina si la revisión fue realizada por el
     * decanato
     */
    public boolean revisadoPorDecanato(Rendimiento rendimiento,
            String id_departamento) {

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

    /**
     * revisadoPorDepartamento
     *
     * Determina si una evaluación fue revisada por el departamento
     *
     * @param rendimiento: objeto que contiene la información de la evaluación
     * @param id_coordinacion: identificador de la coordinación que realizó la
     * evaluación
     * @return booleano que determina si la revisión fue realizada por el
     * departamento
     */
    public boolean revisadoPorDepartamento(Rendimiento rendimiento,
            String id_coordinacion) {

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

    /**
     * iterarEvaluarRendimientoDecanato
     *
     * Función que permite evaluar cada rendimiento por trimestre de determinado
     * profesor
     *
     * @param id_coordinacion: identificador de la coordinación que realizó la
     * evaluación
     * @param usbid_profesor: identificador del profesor a evaluar
     * @param ano: año de la evaluación
     * @param trimestre: trimestre de la evaluación
     * @return booleano que determina que se actualizaron todos los rendimientos
     */
    public boolean iterarEvaluarRendimientoDecanato(String id_coordinacion,
            String usbid_profesor, int ano, String trimestre) {
        PreparedStatement ps0;

        try {

            ps0 = conexion.prepareStatement("SELECT m.codigo_materia, "
                    + "e.usbid_profesor, ano, trimestre "
                    + "FROM evaluacion AS e, maneja as m, rendimiento as r "
                    + "WHERE e.codigo_coordinacion = ? "
                    + "AND e.usbid_profesor = ? "
                    + "AND e.usbid_profesor = r.usbid_profesor "
                    + "AND m.codigo_materia = r.codigo_materia "
                    + "AND evaluado = 'no';");
            ps0.setString(1, id_coordinacion);
            ps0.setString(2, usbid_profesor);

            ResultSet rs = ps0.executeQuery();

            while (rs.next()) {

                evaluarRendimiento(
                        rs.getString("usbid_profesor"),
                        rs.getString("codigo_materia"),
                        ano, trimestre);
            }

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * iterarEvaluarRendimientoDepartamento
     *
     * Función que permite evaluar cada rendimiento por trimestre de determinado
     * profesor
     *
     * @param id_coordinacion: identificador de la coordinación que realizó la
     * evaluación
     * @param usbid_profesor: identificador del profesor a evaluar
     * @param ano: año de la evaluación
     * @param trimestre: trimestre de la evaluación
     * @return booleano que determina que se actualizaron todos los rendimientos
     */
    public boolean iterarEvaluarRendimientoDepartamento(String id_departamento,
            String usbid_profesor, int ano, String trimestre) {
        PreparedStatement ps0;

        try {

            ps0 = conexion.prepareStatement("SELECT DISTINCT o.codigo_materia, "
                    + "e.usbid_profesor, ano, trimestre "
                    + "FROM evaluacion AS e, oferta as o, rendimiento as r, "
                    + "pertenece as p "
                    + "WHERE o.codigo_departamento = ? "
                    + "AND o.codigo_departamento = p.codigo_departamento "
                    + "AND e.usbid_profesor = ? "
                    + "AND e.usbid_profesor = r.usbid_profesor "
                    + "AND o.codigo_materia = r.codigo_materia "
                    + "AND r.evaluado = 'no';");
            ps0.setString(1, id_departamento);
            ps0.setString(2, usbid_profesor);

            ResultSet rs = ps0.executeQuery();

            while (rs.next()) {

                evaluarRendimiento(
                        rs.getString("usbid_profesor"),
                        rs.getString("codigo_materia"),
                        ano, trimestre);

            }

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * evaluarRendimiento
     *
     * Evaluar un rendimiento determinado
     *
     * @param usbid: identificador del profesor a evaluar
     * @param materia: materia sobre la cual se evaluará
     * @param ano: año en el cual se realiza la evaluación
     * @param trimestre: trimestre en el cual se realiza la evaluación
     * @return booleano que determina si se evaluó el rendimiento
     */
    public boolean evaluarRendimiento(String usbid, String materia, int ano,
            String trimestre) {

        PreparedStatement ps0, ps1;

        try {

            ps0 = conexion.prepareStatement("UPDATE rendimiento "
                    + "SET ano_evaluacion = ?, trimestre_evaluacion = ? "
                    + "WHERE evaluado = 'no' "
                    + "AND usbid_profesor = ? "
                    + "AND codigo_materia = ?;");
            ps0.setInt(1, ano);
            ps0.setString(2, trimestre);
            ps0.setString(3, usbid);
            ps0.setString(4, materia);

            Integer j = ps0.executeUpdate();

            ps1 = conexion.prepareStatement("UPDATE rendimiento "
                    + "SET evaluado = 'si' "
                    + "WHERE usbid_profesor = ? "
                    + "AND codigo_materia = ? "
                    + "AND trimestre_evaluacion = ? "
                    + "AND ano_evaluacion = ?;");
            ps1.setString(1, usbid);
            ps1.setString(2, materia);
            ps1.setString(3, trimestre);
            ps1.setInt(4, ano);

            Integer i = ps1.executeUpdate();

            return i > 0 && j > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * obtenerAnoYTrimestre
     *
     * Obtiene el año y el trimestre actual
     *
     * @return arreglo que contiene el año y el trimestre.
     */
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

    /**
     * obtenerTrimestre
     *
     * Obtiene el trimestre dado el número de mes en el que se encuentra
     *
     * @param fecha_mes: número del mes
     * @return String que contiene las siglas del trimestre.
     */
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

    /**
     * evaluar
     *
     * Procedimiento que finaliza la evaluación de profesores
     *
     * @param rendimiento: objeto que contiene la información correspondiente a
     * la evaluación
     * @param id_departamento: identificador del departamento que evalua
     * @param id_coordinacion: identificador de la coordinación sobre la cual el
     * departamento revisará la evaluación.
     */
    public void evaluar(Rendimiento rendimiento, String id_departamento,
            String id_coordinacion) {

        PreparedStatement ps0, ps1;

        try {

            String usbid = rendimiento.getUsbid_profesor();
            String materia = rendimiento.getCodigo_materia();

            int[] ano_y_trimestre = obtenerAnoYTrimestre();
            String trimestre = obtenerTrimestre(ano_y_trimestre[1]);

            if (id_coordinacion == null) {
                ps0 = conexion.prepareStatement("SELECT DISTINCT codigo_coordinacion, "
                        + "ano, recomendado_coordinacion, "
                        + "ev.observaciones_coordinacion "
                        + "FROM evaluacion AS ev, rendimiento AS r "
                        + "WHERE ev.usbid_profesor = ? "
                        + "AND ev.usbid_profesor = r.usbid_profesor "
                        + "AND r.evaluado = 'no';");
                ps0.setString(1, usbid);
            } else {
                ps0 = conexion.prepareStatement("SELECT DISTINCT codigo_coordinacion, "
                        + "ano, recomendado_coordinacion, "
                        + "ev.observaciones_coordinacion "
                        + "FROM evaluacion AS ev, rendimiento AS r "
                        + "WHERE ev.usbid_profesor = ? "
                        + "AND ev.usbid_profesor = r.usbid_profesor "
                        + "AND ev.codigo_coordinacion = ? "
                        + "AND r.evaluado = 'no';");
                ps0.setString(1, usbid);
                ps0.setString(2, id_coordinacion);
            }

            ResultSet rs = ps0.executeQuery();

            if (id_coordinacion == null) {
                iterarEvaluarRendimientoDepartamento(id_departamento, usbid,
                        ano_y_trimestre[0], trimestre);
            } else {
                iterarEvaluarRendimientoDecanato(id_coordinacion, usbid,
                        ano_y_trimestre[0], trimestre);
            }

            while (rs.next()) {
                ps1 = conexion.prepareStatement("INSERT into evaluado "
                        + "VALUES (?,?,?,?,?,?);");
                ps1.setString(1, rs.getString(1));
                ps1.setString(2, usbid);
                ps1.setInt(3, ano_y_trimestre[0]);
                ps1.setString(4, trimestre);
                ps1.setString(5, rs.getString(3));
                ps1.setString(6, rs.getString(4));

                ps1.executeUpdate();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * borrarEvaluadosDepartamento
     *
     * Borra aquellos profesores que han sido evaluados por la coordinación y
     * revisados por el decanato
     *
     * @param rendimiento: objeto que contiene la información sobre la
     * evaluación
     * @param id_departamento: identificador del departamento
     * @return booleano que determina se si borraron los evaluados por el
     * departamento
     */
    public boolean borrarEvaluadosDepartamento(Rendimiento rendimiento,
            String id_departamento) {


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

    /**
     * borrarEvaluadosDecanato
     *
     * Borra aquellos profesores que han sido evaluados por la coordinación y
     * revisados por el departamento
     *
     * @param rendimiento: objeto que contiene la información sobre la
     * evaluación
     * @param id_coordinacion: identificador de la coordinacion
     * @return booleano que determina se si borraron los evaluados por el
     * departamento
     */
    public boolean borrarEvaluadosDecanato(Rendimiento rendimiento,
            String id_coordinacion) {


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

    /**
     * revisadoDepartamento
     *
     * Marca como revisado la evaluación del profesor por parte del departamento
     *
     * @param rendimiento: objeto que contiene la información correspondiente a
     * la evaluación
     * @param id_departamento: identificador del departamento que revisa la
     * evaluación
     * @return booleano que determina si la evaluación fue revisada
     */
    public boolean revisadoDepartamento(Rendimiento rendimiento,
            String id_departamento) {

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

    /**
     * revisadoDecanato
     *
     * Marca como revisado la evaluación del profesor por parte del decanato
     *
     * @param rendimiento: objeto que contiene la información correspondiente a
     * la evaluación
     * @param id_coordinacion: identificador de la coordinación.
     * @return booleano que determina si la evaluación fue revisada
     */
    public boolean revisadoDecanato(Rendimiento rendimiento,
            String id_coordinacion) {

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

    /**
     * listarProfesoresEvaluadosCoordinacion
     *
     * Lista los profesores evaluados por una determinada coordinacion
     *
     * @param id_coordinacion: identificador de la coordinacion
     * @return listado de profesores evaluados
     */
    public ArrayList<Profesor> listarProfesoresEvaluadosCoordinacion(
            String id_coordinacion) {

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

    /**
     * listarProfesoresPorEvaluarCoordinacion
     *
     * Listado de profesores por evaluar por parte de la coordinacion
     *
     * @param id_coordinacion: identificación de la coordinacion
     * @return listado de profesores por evaluar
     */
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

    /**
     * listarProfesoresPorEvaluarDecanato
     *
     * Listado de profesores por evaluar por parte del decanato
     *
     * @param id_coordinacion: identificador de la coordinacion
     * @return listado de profesores por evaluar
     */
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

    /**
     * obtenerTrimestrePorNombre
     *
     * Obtener las siglas correspondientes al nombre del trimestre
     *
     * @param periodo
     * @return string con las siglas correspondientes al trimestre
     */
    public String obtenerTrimestrePorNombre(String periodo) {

        if (periodo.equals("Septiembre-Diciembre")) {
            return "SD";
        } else if (periodo.equals("Ener-Marzo")) {
            return "EM";
        } else if (periodo.equals("Abril-Julio")) {
            return "AJ";
        } else if (periodo.equals("Intensivo")) {
            return "V";
        }

        return null;
    }

    /**
     * obtenerTrimestrePorSiglas
     *
     * Obtener el nombre del trimestre por sus siglas
     *
     * @param periodo
     * @return string con el nombre del trimestre
     */
    public String obtenerTrimestrePorSiglas(String periodo) {

        if (periodo.equals("SD")) {
            return "Septiembre-Diciembre";
        } else if (periodo.equals("EM")) {
            return "Ener-Marzo";
        } else if (periodo.equals("AJ")) {
            return "Abril-Julio";
        } else if (periodo.equals("V")) {
            return "Intensivo";
        }

        return null;
    }

    /**
     * listarAnoEvaluacionesEnviadasCoordinacion
     *
     * Listar los años de las evaluaciones enviadas por parte de la coordinación
     *
     * @param id_coordinacion: identificador de la coordinacion
     * @param usbid_profesor: identificador del profesor cuyas evaluaciones
     * enviadas se van a consultar
     * @return listado de evaluaciones
     */
    public ArrayList<Rendimiento> listarAnoEvaluacionesEnviadasCoordinacion(
            String id_coordinacion, String usbid_profesor) {

        PreparedStatement ps;
        ArrayList<Rendimiento> rendimiento = new ArrayList(0);
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
                Rendimiento r = new Rendimiento();
                r.setAno(rs.getInt("ano"));
                String periodo = rs.getString("trimestre");

                r.setTrimestre(obtenerTrimestrePorSiglas(periodo));
                rendimiento.add(r);
            }

            return rendimiento;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * listarAnoEvaluacionesEnviadasDepartamento
     *
     * Listar los años de las evaluaciones enviadas por parte del departamento
     *
     * @param id_departamento: identificador del departamento que realiza la
     * consulta
     * @param usbid_profesor: identificador del profesor cuyas evaluaciones
     * enviadas se van a consultar.
     * @return listado de evaluaciones
     */
    public ArrayList<Rendimiento> listarAnoEvaluacionesEnviadasDepartamento(
            String id_departamento, String usbid_profesor) {

        PreparedStatement ps;
        ArrayList<Rendimiento> rendimiento = new ArrayList(0);
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
                Rendimiento r = new Rendimiento();
                r.setAno(rs.getInt("ano"));
                String periodo = rs.getString("trimestre");

                r.setTrimestre(obtenerTrimestrePorSiglas(periodo));
                rendimiento.add(r);
            }

            return rendimiento;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * resumenInformacionProfesor
     *
     * Se obtiene las informaciones de los profesores presentadas por las
     * coordinaciones
     *
     * @param usbid_profesor: identificador del profesor del cual se va a
     * obtener el resumen
     * @return recopilación de la información del profesor proporcionada por las
     * coordinaciones correspondientes
     */
    public InformacionProfesorCoord resumenInformacionProfesor(
            String usbid_profesor) {

        PreparedStatement ps;
        InformacionProfesorCoord informacion = new InformacionProfesorCoord();
        try {
            ps = conexion.prepareStatement("SELECT * "
                    + "FROM informacion_profesor_coordinacion "
                    + "WHERE usbid_profesor = ?;");
            ps.setString(1, usbid_profesor);
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

    /**
     * listarInformacionProfesorCoordinacion
     *
     * Obtiene la información del profesor proporcionada por la coordinación
     *
     * @param id_coordinacion: identificador de la coordinación que proporcionó
     * la infromación
     * @param usbid_profesor: identificador del profesor
     * @return información del profesor proporcionada por la coordinación.
     */
    public InformacionProfesorCoord listarInformacionProfesorCoordinacion(
            String id_coordinacion, String usbid_profesor) {

        PreparedStatement ps;
        InformacionProfesorCoord informacion = new InformacionProfesorCoord();
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

    /**
     * listarArchivosProfesor
     *
     * Lista los archivos subidos por un profesor en base a una lista de
     * archivos que contiene los anos y trimestres que deben ser consultados.
     *
     * @param usbid_profesor: identificador del profesor
     * @param archivos_considerados: archivos que se deben consultar
     * @return listado de archivos
     */
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

    /**
     * crearInformacionProfesorCoordinacion
     *
     * Crea la infromación que proporciona la coordinación en la evaluación del
     * profesor
     *
     * @param id_coordinacion: identificador de la coordinación
     * @param usbid_profesor: identificador del profesor
     * @param informacion: instancia de la informacion del profesor
     * @return booleano que determina si la informacion fue creada
     */
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

    /**
     * actualizarInformacionProfesorCoordinacion
     *
     * Actualiza la información de determinado profesor
     *
     * @param id_coordinacion: identificador de la coordinacion que actualiza la
     * información del profesor
     * @param usbid_profesor: identificador de profesor cuya información es
     * actualizada
     * @param informacion: instancia de la información del profesor
     * @return booleano que determina si la informacion fue actualizada
     */
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

    /**
     * listarEvaluadosPorCoordinacion
     *
     * Listado de los profesores evaluados pro la coordinacion por parte del
     * departamento
     *
     * @param id_departamento
     * @param id_profesor
     * @return
     */
    public ArrayList<Dicta> listarEvaluadosPorCoordinacion(
            String id_departamento, String id_profesor) {

        PreparedStatement ps, ps2;
        ArrayList<Dicta> dicta_materia = new ArrayList(0);
        String codigo_materia;
        String usbid_profesor;
        try {
            ps = conexion.prepareStatement("SELECT DISTINCT e.codigo_materia, "
                    + "e.usbid_profesor, m.nombre, r.trimestre "
                    + "FROM evaluar as e, materia as m, rendimiento as r "
                    + "WHERE e.codigo_departamento = ? "
                    + "AND m.codigo = e.codigo_materia "
                    + "AND e.codigo_materia = r.codigo_materia "
                    + "AND e.usbid_profesor = r.usbid_profesor "
                    + "AND r.evaluado = 'no' "
                    + "AND e.usbid_profesor NOT IN ("
                    + "SELECT ec.usbid_profesor "
                    + "FROM evaluacion as ec "
                    + "WHERE ec.revisado_departamento = 'si' "
                    + "AND ec.usbid_profesor = e.usbid_profesor "
                    + ")"
                    + "AND e.usbid_profesor = ? "
                    + "ORDER BY e.usbid_profesor;");
            ps.setString(1, id_departamento);
            ps.setString(2, id_profesor);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                usbid_profesor = rs.getString("usbid_profesor");

                int pendientes = contarEvaluacionesPendientesDepartamento(id_departamento, usbid_profesor);
                int total = contarEvaluacionesDepartamento(id_departamento, usbid_profesor);

                Dicta d = new Dicta();
                codigo_materia = rs.getString("codigo_materia");
                d.setCodigoMateria(codigo_materia);
                d.setOpcion(rs.getString("nombre"));
                d.setPeriodo(obtenerTrimestrePorSiglas(rs.getString("trimestre")));

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

    /**
     * listarEvaluadosPorCoordinacion
     *
     * Listado de los profesores evaluados pro la coordinacion por parte del
     * departamento
     *
     * @param id_departamento
     * @param id_profesor
     * @return
     */
    public ArrayList<Dicta> listarEvaluadosPorCoordinacion(String id_departamento) {

        PreparedStatement ps, ps2;
        ArrayList<Dicta> dicta_materia = new ArrayList(0);
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

                    Dicta d = new Dicta();
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

    /**
     * obtenerEvaluacionCoordinaciones
     *
     * Obtiene el resultado de las evaluaciones realizadas por las
     * coordinaciones
     *
     * @param id_departamento
     * @param usbid_profesor
     * @return
     */
    public ArrayList<Rendimiento> obtenerEvaluacionCoordinaciones(
            String id_departamento, String usbid_profesor) {

        PreparedStatement ps;
        ArrayList<Rendimiento> informacion = new ArrayList<Rendimiento>(0);
        try {

            ps = conexion.prepareStatement("SELECT codigo, nombre, "
                    + "observaciones_coordinacion, recomendado_coordinacion "
                    + "FROM evaluacion, coordinacion "
                    + "WHERE usbid_profesor = ? "
                    + "AND codigo = codigo_coordinacion;");
            ps.setString(1, usbid_profesor);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Rendimiento rendimiento = new Rendimiento();
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

    /**
     * obtenerEvaluacionCoordinaciones
     *
     * Obtiene el resultado de las evaluaciones realizadas por las
     * coordinaciones
     *
     * @param id_departamento
     * @param usbid_profesor
     * @return
     */
    public ArrayList<Rendimiento> obtenerEvaluacionCoordinaciones(
            String id_departamento, String usbid_profesor, String trimestre,
            int ano) {

        PreparedStatement ps;
        ArrayList<Rendimiento> informacion = new ArrayList<Rendimiento>(0);
        try {

            ps = conexion.prepareStatement("SELECT codigo, nombre, "
                    + "observaciones, recomendado "
                    + "FROM evaluado, coordinacion "
                    + "WHERE usbid_profesor = ? "
                    + "AND codigo = codigo_coordinacion "
                    + "AND ano = ? "
                    + "AND trimestre = ?;");
            ps.setString(1, usbid_profesor);
            ps.setInt(2, ano);
            ps.setString(3, trimestre);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Rendimiento rendimiento = new Rendimiento();
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