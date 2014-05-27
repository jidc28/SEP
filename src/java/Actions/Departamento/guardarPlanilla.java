/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class guardarPlanilla extends org.apache.struts.action.Action {
    /* forward name="success" path="" */

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
        String id_departamento = (String) session.getAttribute("usbid");
        Profesor profesor = (Profesor) session.getAttribute("profesor");

        String id_profesor = profesor.getUsbid();

        rendimientoProf renMateria = (rendimientoProf) form;

        renMateria.setUsbid_profesor(id_profesor);

        /* Se obtiene el ano */
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String fecha = dateFormat.format(date).toString();
        String ano = fecha.substring(0, 4);

        int[] anos = new int[2];
        anos[1] = Integer.parseInt(ano);
        anos[0] = anos[1] - 1;

        int _1 = renMateria.getNota1();
        int _2 = renMateria.getNota2();
        int _3 = renMateria.getNota3();
        int _4 = renMateria.getNota4();
        int _5 = renMateria.getNota5();
        int _r = renMateria.getRetirados();
        int _total = renMateria.getTotal_estudiantes();

        /* Caso en que el total de estudiantes es cero */
        if (_total == 0) {

            ArrayList<Materia> materias =
                    DBMS.getInstance().
                    obtenerSolicitudEvaluacionesProfesor(id_profesor, id_departamento);

            request.setAttribute("anos", anos);
            request.setAttribute("materias", materias);
            request.setAttribute("rendimientoProf", renMateria);
            request.setAttribute("agregar_informacion", renMateria);
            return mapping.findForward(SUCCESS);
        }

        /* Caso en que el total de estudiantes no coincide con la suma de la 
         * cantidad de retirados, sacaron 1, sacaron 2, sacaron 3, sacaron 4 
         * y sacaron 5 */
        if (_total != _1 + _2 + _3 + _4 + _5 + _r) {

            ArrayList<Materia> materias =
                    DBMS.getInstance().
                    obtenerSolicitudEvaluacionesProfesor(id_profesor, id_departamento);

            request.setAttribute("anos", anos);
            request.setAttribute("materias", materias);
            request.setAttribute("rendimientoProf", renMateria);
            request.setAttribute("error_num_estudiantes", renMateria);
            return mapping.findForward(SUCCESS);
        }

        /* Caso en que alguno de los campos es menor que cero */
        if (_total < 0 || _1 < 0 || _2 < 0 || _3 < 0 || _4 < 0 || _5 < 0 || _r < 0) {

            ArrayList<Materia> materias =
                    DBMS.getInstance().
                    obtenerSolicitudEvaluacionesProfesor(id_profesor, id_departamento);

            request.setAttribute("anos", anos);
            request.setAttribute("materias", materias);
            request.setAttribute("rendimientoProf", renMateria);
            request.setAttribute("numero_negativo", SUCCESS);
            return mapping.findForward(SUCCESS);
        }

        float promedio = (float) (_1 + (2 * _2) + (3 * _3) + (4 * _4) + (5 * _5)) / (_total - _r);

        renMateria.setNota_prom(promedio);

        String periodo = renMateria.getTrimestre();

        if (periodo.equals("Septiembre-Diciembre")) {
            renMateria.setTrimestre("SD");
        } else if (periodo.equals("Enero-Marzo")) {
            renMateria.setTrimestre("EM");
        } else if (periodo.equals("Abril-Julio")) {
            renMateria.setTrimestre("AJ");
        } else if (periodo.equals("Intensivo")) {
            renMateria.setTrimestre("V");
        }

        boolean agregar =
                DBMS.getInstance().agregarRendimientoProfesor(renMateria, id_departamento);

        ArrayList<rendimientoProf> rendimiento =
                DBMS.getInstance().obtenerRendimientoProfesor(id_profesor, id_departamento);

        ArrayList<Materia> materias =
                DBMS.getInstance().obtenerSolicitudEvaluacionesProfesor(id_profesor, id_departamento);

        request.setAttribute("materias", materias);
        request.setAttribute("rendimiento", rendimiento);

        if (agregar) {
            request.setAttribute("planilla_guardada", renMateria);
        } else {
            request.setAttribute("planilla_no_guardada", renMateria);
        }

        request.setAttribute("anos", anos);
        request.setAttribute("rendimientoProf", new rendimientoProf());
        return mapping.findForward(SUCCESS);
    }
}
