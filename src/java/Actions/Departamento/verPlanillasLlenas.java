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
public class verPlanillasLlenas extends org.apache.struts.action.Action {
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
        
        Profesor profesor = (Profesor) form;
        String id_profesor = profesor.getUsbid();
        profesor.setNombre(profesor.getApellido() + ", " + profesor.getNombre());
        
        ArrayList<rendimientoProf> rendimiento = DBMS.getInstance().obtenerRendimientoProfesor(profesor.getUsbid(),id_departamento);
        
        session.setAttribute("profesor", profesor);
        request.setAttribute("rendimiento", rendimiento);
        return mapping.findForward(SUCCESS);
    }
}
