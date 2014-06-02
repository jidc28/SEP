package Actions.Evaluaciones;

import Clases.*;
import DBMS.DBMS;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author smaf
 */
public class ListarEvaluacionesEnviadas extends org.apache.struts.action.Action {

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
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String id = usuario.getUsbid();
        String tipousuario = usuario.getTipousuario();
        Profesor profesor = (Profesor) session.getAttribute("profesor");

        /* Se obtienen los datos requeridos por la consulta */
        Rendimiento rendimiento = (Rendimiento) form;
        int ano = rendimiento.getAno();
        String trimestre = rendimiento.getTrimestre();

        ArrayList<Rendimiento> evaluaciones_enviadas = null;

        Archivo[] archivos_considerados =
                ListarEvaluacionesPendientesGeneral.obtenerArchivosConsiderados(ano, trimestre, 2);

        /* Se obtienen todos los archivos subidos por el profesor */
        ArrayList<Archivo> archivos =
                DBMS.getInstance().
                listarArchivosProfesor(profesor.getUsbid(), archivos_considerados);

        /* Dependiendo del tipo de usuario se consultan las evaluaciones
         * enviadas */
        if (tipousuario.equals("coordinacion")) {

            Rendimiento[] evaluacion_coordinaciones = new Rendimiento[1];
            evaluacion_coordinaciones[0] =
                    DBMS.getInstance().listarEvaluacionesGeneralCoordinacion(
                    id, profesor.getUsbid(), ano, trimestre);

            Coordinacion coordinacion = new Coordinacion();
            coordinacion.setCodigo(id);
            coordinacion = DBMS.getInstance().obtenerNombreCoordinacion(coordinacion);
            evaluacion_coordinaciones[0].setObservaciones_d(coordinacion.getNombre());

            evaluaciones_enviadas =
                    DBMS.getInstance().listarEvaluacionesEnviadasCoordinacion(
                    id, ano, trimestre, profesor.getUsbid());

            request.setAttribute("evaluacion_departamento", evaluacion_coordinaciones);

        } else if (tipousuario.equals("departamento")) {

            ArrayList<Rendimiento> evaluacion_coordinaciones =
                    DBMS.getInstance().
                    obtenerEvaluacionCoordinaciones(id, profesor.getUsbid(), trimestre, ano);

            evaluaciones_enviadas =
                    DBMS.getInstance().listarEvaluacionesEnviadasDepartamento(
                    id, ano, trimestre, profesor.getUsbid());

            request.setAttribute("evaluacion_departamento", evaluacion_coordinaciones);

        } else if (tipousuario.equals("decanato")) {

            String id_coordinacion = (String) session.getAttribute("coordinacion");

            Rendimiento[] evaluacion_coordinaciones = new Rendimiento[1];
            evaluacion_coordinaciones[0] =
                    DBMS.getInstance().listarEvaluacionesGeneralCoordinacion(
                    id_coordinacion, profesor.getUsbid(), ano, trimestre);

            Coordinacion coordinacion = new Coordinacion();
            coordinacion.setCodigo(id_coordinacion);
            coordinacion = DBMS.getInstance().obtenerNombreCoordinacion(coordinacion);
            evaluacion_coordinaciones[0].setObservaciones_d(coordinacion.getNombre());

            evaluaciones_enviadas =
                    DBMS.getInstance().listarEvaluacionesEnviadasCoordinacion(
                    id_coordinacion, ano, trimestre, profesor.getUsbid());

            request.setAttribute("evaluacion_departamento", evaluacion_coordinaciones);
        }

        /* En caso en el que no existan evaluaciones enviadas */
        if (evaluaciones_enviadas.isEmpty()) {
            request.setAttribute("vacio", SUCCESS);
        }

        /* Se envian los atributos a la vista */
        request.setAttribute("evaluaciones_enviadas", evaluaciones_enviadas);
        request.setAttribute("listar_archivos", SUCCESS);
        session.setAttribute("trimestre", trimestre);
        request.setAttribute("archivos", archivos);
        request.setAttribute("enviadas", SUCCESS);
        request.setAttribute("general", SUCCESS);
        session.setAttribute("ano", ano);
        return mapping.findForward(SUCCESS);
    }
}