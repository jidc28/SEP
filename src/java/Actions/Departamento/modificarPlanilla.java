package Actions.Departamento;

import Clases.*;
import DBMS.DBMS;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author jidc28
 */
public class modificarPlanilla extends org.apache.struts.action.Action {
    /* forward name="success" path="" */

    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";

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
        String id_departamento = (String) session.getAttribute("usbid");
        Profesor profesor = (Profesor) session.getAttribute("profesor");
        String id_profesor = profesor.getUsbid();

        rendimientoProf renMateria = (rendimientoProf) form;

        renMateria.setUsbid_profesor(id_profesor);

        /*Se obtienen los años correspondientes */
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String fecha = dateFormat.format(date).toString();
        String ano = fecha.substring(0, 4);

        int[] anos = new int[4];
        anos[3] = Integer.parseInt(ano);
        int i = 2;
        int tmp = anos[3];

        while (i > -1) {
            tmp--;
            anos[i] = tmp;
            i--;
        }

        int _1 = renMateria.getNota1();
        int _2 = renMateria.getNota2();
        int _3 = renMateria.getNota3();
        int _4 = renMateria.getNota4();
        int _5 = renMateria.getNota5();
        int _r = renMateria.getRetirados();
        int _total = renMateria.getTotal_estudiantes();

        /* En caso que se ingrese 0 estudiantes */
        if (_total == 0) {
            ArrayList<Materia> materias =
                    DBMS.getInstance().obtenerSolicitudEvaluacionesProfesor(id_profesor, id_departamento);

            request.setAttribute("materias", materias);
            request.setAttribute("rendimientoProf", renMateria);
            request.setAttribute("agregar_informacion", FAILURE);
            request.setAttribute("anos", anos);
            /* Se envía a la página de error */
            return mapping.findForward(FAILURE);
        }

        /* En el caso en el que el total de estudiantes ingresados sea menor 
         * que la suma de todas las notas y retirados */
        if (_total != _1 + _2 + _3 + _4 + _5 + _r) {
            ArrayList<Materia> materias =
                    DBMS.getInstance().obtenerSolicitudEvaluacionesProfesor(id_profesor, id_departamento);

            request.setAttribute("materias", materias);
            request.setAttribute("rendimientoProf", renMateria);
            request.setAttribute("error_num_estudiantes", renMateria);
            request.setAttribute("anos", anos);
            /* Se envía a la página de error */
            return mapping.findForward(FAILURE);
        }

        /* En caso que se ingresen valores negativos */
        if (_total < 0 || _1 < 0 || _2 < 0 || _3 < 0 || _4 < 0 || _5 < 0 || _r < 0) {
            ArrayList<Materia> materias =
                    DBMS.getInstance().obtenerSolicitudEvaluacionesProfesor(id_profesor, id_departamento);

            request.setAttribute("materias", materias);
            request.setAttribute("rendimientoProf", renMateria);
            request.setAttribute("numero_negativo", FAILURE);
            request.setAttribute("anos", anos);
            return mapping.findForward(FAILURE);
        }

        /* Se calcula el promedio de las notas */
        float promedio = (float) (_1 + (2 * _2) + (3 * _3) + (4 * _4) + (5 * _5)) / (_total - _r);
        renMateria.setNota_prom(promedio);

        /* Se modifica la planilla en la base de datos */
        boolean agregar = DBMS.getInstance().modificarRendimientoProfesor(renMateria, id_departamento);

        /* Se obtiene el listado de planillas llenas */
        ArrayList<rendimientoProf> rendimiento =
                DBMS.getInstance().obtenerPlanillasLlenas(id_profesor, id_departamento);

        ArrayList<Materia> materias =
                DBMS.getInstance().obtenerSolicitudEvaluacionesProfesor(id_profesor, id_departamento);

        request.setAttribute("materias", materias);
        request.setAttribute("rendimiento", rendimiento);

        /* En el caso en que la planilla se modifico exitosamente */
        if (agregar) {
            request.setAttribute("planilla_modificada", renMateria);
            return mapping.findForward(SUCCESS);
            /* En el caso contrario*/
        } else {
            request.setAttribute("planilla_no_modificada", renMateria);
            request.setAttribute("anos", anos);
            return mapping.findForward(FAILURE);
        }
    }
}