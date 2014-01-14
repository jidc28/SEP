/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions.Departamento;

import Clases.*;
import DBMS.DBMS;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
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

        if (renMateria.getTotal_estudiantes() == 0) {
            ArrayList<Materia> materias = DBMS.getInstance().obtenerSolicitudEvaluacionesProfesor(id_profesor, id_departamento);
            request.setAttribute("materias", materias);
            request.setAttribute("rendimientoProf", renMateria);
            request.setAttribute("agregar_informacion", FAILURE);
            return mapping.findForward(FAILURE);
        }

        if (renMateria.getTotal_estudiantes()
                != renMateria.getNota1() + renMateria.getNota2()
                + renMateria.getNota3() + renMateria.getNota4()
                + renMateria.getNota5() + renMateria.getRetirados()) {
            ArrayList<Materia> materias = DBMS.getInstance().obtenerSolicitudEvaluacionesProfesor(id_profesor, id_departamento);
            request.setAttribute("materias", materias);
            request.setAttribute("rendimientoProf", renMateria);
            request.setAttribute("error_num_estudiantes", renMateria);
            return mapping.findForward(FAILURE);
        }

        if (renMateria.getNota_prom() < 0.0 || renMateria.getTotal_estudiantes() < 0
                || renMateria.getNota1() < 0 || renMateria.getNota2() < 0
                || renMateria.getNota3() < 0 || renMateria.getNota4() < 0
                || renMateria.getNota5() < 0 || renMateria.getRetirados() < 0) {
            ArrayList<Materia> materias = DBMS.getInstance().obtenerSolicitudEvaluacionesProfesor(id_profesor, id_departamento);
            request.setAttribute("materias", materias);
            request.setAttribute("rendimientoProf", renMateria);
            request.setAttribute("numero_negativo", FAILURE);
            return mapping.findForward(FAILURE);
        }

        boolean agregar = DBMS.getInstance().agregarRendimientoProfesor(renMateria,id_departamento);

        ArrayList<rendimientoProf> rendimiento = DBMS.getInstance().obtenerRendimientoProfesor(id_profesor, id_departamento);
        ArrayList<Materia> materias = DBMS.getInstance().obtenerSolicitudEvaluacionesProfesor(id_profesor, id_departamento);

        request.setAttribute("materias", materias);
        request.setAttribute("rendimiento", rendimiento);
        if (agregar) {
            request.setAttribute("planilla_guardada", renMateria);
        } else {
            request.setAttribute("planilla_no_guardada", renMateria);
        }
        return mapping.findForward(SUCCESS);
    }
}
