package Actions.Evaluaciones;

import Clases.*;
import DBMS.DBMS;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ListarEvaluacionesPendientesGeneral extends Action {

    private static final String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    @SuppressWarnings("empty-statement")
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String tipousuario = usuario.getTipousuario();
        String id = usuario.getUsbid();

        if (session.getAttribute("usuario") != null) {

            Dicta d = (Dicta) form;
            Archivo[] archivos_considerados = null;
            ArrayList<Dicta> evaluaciones_pendientes = null;

            if (d.getOpcion().equals("pendiente")) {
                archivos_considerados = obtenerArchivosConsiderados(-1, null, 1);
            } else {
                String trimestre = (String) session.getAttribute("trimestre");
                int ano = (Integer) session.getAttribute("ano");
                archivos_considerados = obtenerArchivosConsiderados(ano, trimestre, 2);
            }

            /* Se obtiene el rendimiento del profesor determinado asociado con
             * la materia que maneja la coordinación */
            Rendimiento evaluacion =
                    DBMS.getInstance().obtenerEvaluacion(d.getUsbidProfesor());

            /* Se obtiene toda la informacion del profesor */
            Profesor profesor =
                    DBMS.getInstance().obtenerInfoProfesor(d.getUsbidProfesor());

            /* Se obtienen todos los archivos subidos por el profesor */
            ArrayList<Archivo> archivos =
                    DBMS.getInstance().
                    listarArchivosProfesor(profesor.getUsbid(), archivos_considerados);

            /* Se calcula la cantidad de aplazados y la cantidad de aprobados */
            int total = evaluacion.getTotal_estudiantes();
            int aplazados = evaluacion.getNota1() + evaluacion.getNota2();
            int aprobados = evaluacion.getNota3() + evaluacion.getNota4()
                    + evaluacion.getNota5();

            int total_nota = (evaluacion.getNota1() * 1) + (evaluacion.getNota2() * 2)
                    + (evaluacion.getNota3() * 3) + (evaluacion.getNota4() * 4)
                    + (evaluacion.getNota5() * 5);
            float promedio = (float) total_nota / (total - evaluacion.getRetirados());
            evaluacion.setNota_prom(promedio);

            /* Se obtienen los porcentajes de cada rubro y se transforman a 
             * dos decimales */
            String porcentaje1 = String.format("%.2f", calcularPorcentaje(total, evaluacion.getNota1()));
            String porcentaje2 = String.format("%.2f", calcularPorcentaje(total, evaluacion.getNota2()));
            String porcentaje3 = String.format("%.2f", calcularPorcentaje(total, evaluacion.getNota3()));
            String porcentaje4 = String.format("%.2f", calcularPorcentaje(total, evaluacion.getNota4()));
            String porcentaje5 = String.format("%.2f", calcularPorcentaje(total, evaluacion.getNota5()));
            String porcentajeR = String.format("%.2f", calcularPorcentaje(total, evaluacion.getRetirados()));

            String porcentajeApr = String.format("%.2f", calcularPorcentaje(total, aprobados));
            String porcentajeApl = String.format("%.2f", calcularPorcentaje(total, aplazados));

            /* Si el usuario que accede a esta funcionalidad es el/la 
             * coordinador/coordinadora */
            if (tipousuario.equals("coordinacion")) {

                /* Si existe una informacion previa se envia a la vista, sino, se crea
                 * una instancia vacia de la informacion */
                InformacionProfesorCoord informacion =
                        DBMS.getInstance().
                        listarInformacionProfesorCoordinacion(id, profesor.getUsbid());

                if (informacion == null) {
                    boolean creada =
                            DBMS.getInstance().
                            crearInformacionProfesorCoordinacion(id, profesor.getUsbid(), new InformacionProfesorCoord());
                    informacion =
                            DBMS.getInstance().
                            listarInformacionProfesorCoordinacion(id, profesor.getUsbid());
                }

                session.setAttribute("informacion", informacion);

                request.setAttribute("evaluar_coordinacion", SUCCESS);
                request.setAttribute("evaluar", SUCCESS);

                evaluaciones_pendientes = DBMS.getInstance().listarEvaluacionesPendientes(id, profesor.getUsbid());

                /* Si el usuario que accede a esta funcionalidad es
                 * jefe de departamento */
            } else if (tipousuario.equals("departamento")) {

                ArrayList<Rendimiento> evaluacion_coordinaciones =
                        DBMS.getInstance().
                        obtenerEvaluacionCoordinaciones(id, profesor.getUsbid());

                request.setAttribute("evaluacion_departamento", evaluacion_coordinaciones);
                request.setAttribute("revisar", SUCCESS);

                evaluaciones_pendientes = DBMS.getInstance().listarEvaluadosPorCoordinacion(id, profesor.getUsbid());

                /* Si el usuario que accede a esta funcionalidad decano */
            } else if (tipousuario.equals("decanato")) {

                String id_coordinacion = (String) session.getAttribute("coordinacion");

                /* Si existe una informacion previa se envia a la vista, sino, se crea
                 * una instancia vacia de la informacion */
                InformacionProfesorCoord informacion =
                        DBMS.getInstance().
                        listarInformacionProfesorCoordinacion(id_coordinacion, d.getUsbidProfesor());

                session.setAttribute("informacion", informacion);

                String trimestre = evaluacion.getTrimestre();
                int ano = evaluacion.getAno();

                /* Se obtienen los datos de la evaluacion que realizo la
                 * coordinacion */
                Rendimiento evaluado =
                            DBMS.getInstance().listarEvaluacionesGeneralCoordinacion(
                            id_coordinacion, d.getUsbidProfesor());

                request.setAttribute("revisar", SUCCESS);

                request.setAttribute("evaluado_coordinacion", evaluado);

                Coordinacion coordinacion = new Coordinacion();
                coordinacion.setCodigo((String) session.getAttribute("coordinacion"));

                evaluaciones_pendientes = DBMS.getInstance().listarEvaluacionesPendientes(coordinacion, d.getUsbidProfesor());

            }

            /* Se envian a la vista los atributos correspondiente */
            request.setAttribute("general", SUCCESS);
            request.setAttribute("evaluaciones_pendientes", evaluaciones_pendientes);
            request.setAttribute("listar_archivos", SUCCESS);
            request.setAttribute("archivos", archivos);
            session.setAttribute("profesor", profesor);
            session.setAttribute("evaluacion", evaluacion);
            session.setAttribute("porcentaje1", porcentaje1);
            session.setAttribute("porcentaje2", porcentaje2);
            session.setAttribute("porcentaje3", porcentaje3);
            session.setAttribute("porcentaje4", porcentaje4);
            session.setAttribute("porcentaje5", porcentaje5);
            session.setAttribute("retirados", porcentajeR);
            session.setAttribute("aplazados", porcentajeApl);
            session.setAttribute("aprobados", porcentajeApr);
        }

        return mapping.findForward(SUCCESS);
    }

    /**
     * Calculo de porcentaje.
     *
     * @param total total de los estudiantes
     * @param parte parte de los estudiantes
     * @return entero con el valor del porcentaje
     */
    public Float calcularPorcentaje(int total, int parte) {
        Float resultado = (float) (parte * 100) / total;
        return resultado;
    }

    /**
     * Obtener archivos
     *
     * @param ano del cual se deben revisar los archivos
     * @param mes en el cual se deben revisar loa archivos
     * @param opcion 1 para pendiente.
     * @return Archivo [] listado de archivos subidos por el profesor.
     */
    public static Archivo[] obtenerArchivosConsiderados(int ano, String mes, int opcion) {

        int int_fecha_ano;
        int fecha_mes = -1;

        /* Se obtiene la fecha */
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        /* Se extrae el ano y el trimestre actual */
        String fecha = dateFormat.format(date).toString();
        String fecha_ano = fecha.substring(0, 4);


        /* En el caso en que la evaluación todavía no se haya realizado
         * (tenga el status "pendiente") */
        if (opcion == 1) {
            /* Se transforman a enteros para ser procesados mas facilmente. */
            int_fecha_ano = Integer.parseInt(fecha_ano);
            fecha_mes = Integer.parseInt(fecha.substring(5, 7));

            /* En el caso en que la evaluación ya se haya realizado */
        } else {
            int_fecha_ano = ano;

            if (mes.equals("EM")) {
                fecha_mes = 1;
            } else if (mes.equals("AJ")) {
                fecha_mes = 5;
            } else if (mes.equals("SD")) {
                fecha_mes = 10;
            } else if (mes.equals("V")) {
                fecha_mes = 8;
            }
        }

        /* Se inicializan archivos (los que se van a considerar) */
        Archivo a_1 = new Archivo();
        Archivo a_2 = new Archivo();
        Archivo a_3 = new Archivo();
        Archivo a_4 = new Archivo();

        /* Según el mes en que se esté realizando la evaluacion se revisarán 
         * los archivos de profesor agregados por el ano y el trimestre */
        if (1 <= fecha_mes && fecha_mes <= 3) {
            /* El trimestre es ENERO-MARZO */
            a_1.setAno(int_fecha_ano);
            a_1.setTrimestre("EM");
            a_2.setAno(int_fecha_ano - 1);
            a_2.setTrimestre("SD");
            a_3.setAno(int_fecha_ano - 1);
            a_3.setTrimestre("V");
            a_4.setAno(int_fecha_ano - 1);
            a_4.setTrimestre("AJ");

        } else if (4 <= fecha_mes && fecha_mes <= 7) {
            /* El trimestre es ABRIL-JULIO */
            a_1.setAno(int_fecha_ano);
            a_1.setTrimestre("AJ");
            a_2.setAno(int_fecha_ano);
            a_2.setTrimestre("EM");
            a_3.setAno(int_fecha_ano - 1);
            a_3.setTrimestre("SD");
            a_4.setAno(int_fecha_ano - 1);
            a_4.setTrimestre("V");

        } else if (9 <= fecha_mes && fecha_mes <= 12) {
            /* El trimestre es SEPTIEMBRE-DICIEMBRE */
            a_1.setAno(int_fecha_ano);
            a_1.setTrimestre("SD");
            a_2.setAno(int_fecha_ano);
            a_2.setTrimestre("V");
            a_3.setAno(int_fecha_ano);
            a_3.setTrimestre("AJ");
            a_4.setAno(int_fecha_ano);
            a_4.setTrimestre("EM");

        } else if (fecha_mes == 8) {
            /* Período INTENSIVO */
            a_1.setAno(int_fecha_ano);
            a_1.setTrimestre("V");
            a_2.setAno(int_fecha_ano);
            a_2.setTrimestre("AJ");
            a_3.setAno(int_fecha_ano);
            a_3.setTrimestre("EM");
            a_4.setAno(int_fecha_ano - 1);
            a_4.setTrimestre("SD");
        }

        /* Se agregan los archivos a un arreglo para ser procesados 
         * posteriormente */
        Archivo[] archivos_considerados = {a_1, a_2, a_3, a_4};
        return archivos_considerados;
    }
}