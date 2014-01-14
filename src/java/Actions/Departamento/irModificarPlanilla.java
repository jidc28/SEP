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
public class irModificarPlanilla extends org.apache.struts.action.Action {
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
        Profesor profesor = (Profesor) session.getAttribute("profesor");
        
        rendimientoProf rendimiento = (rendimientoProf) form;
        rendimiento = DBMS.getInstance().obtenerPlanillaEvaluacionProfesor(profesor.getUsbid(),rendimiento.getCodigo_materia());
        
        rendimiento.setViejoAno(rendimiento.getAno());
        rendimiento.setViejoTrimestre(rendimiento.getTrimestre());
        
        session.setAttribute("profesor",profesor);
        request.setAttribute("rendimientoProf",rendimiento);
        return mapping.findForward(SUCCESS);
        
    }
}
