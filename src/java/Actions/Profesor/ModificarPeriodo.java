/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions.Profesor;

import Clases.*;
import DBMS.DBMS;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Langtech
 */
public class ModificarPeriodo extends org.apache.struts.action.Action {

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

        Materia materia = (Materia) form;
        HttpSession session = request.getSession(true);
        Profesor profesor = (Profesor) session.getAttribute("profesor");

        boolean eliminados =
                DBMS.getInstance().eliminarPeriodos(profesor.getUsbid(), materia.getCodigo());

        if (materia.getPeriodoSD() != null) {
            DBMS.getInstance().agregarDicta(profesor, materia.getCodigo(), "SD");
        }
        if (materia.getPeriodoEM() != null) {
            DBMS.getInstance().agregarDicta(profesor, materia.getCodigo(), "EM");
        }
        if (materia.getPeriodoAJ() != null) {
            DBMS.getInstance().agregarDicta(profesor, materia.getCodigo(), "AJ");
        }
        if (materia.getPeriodoV() != null) {
            DBMS.getInstance().agregarDicta(profesor, materia.getCodigo(), "V");
        }

        ArrayList<Materia> materias =
                DBMS.getInstance().consultarMateriasAsignadas(profesor.getUsbid());
        
        request.setAttribute("materias", materias);
        return mapping.findForward(SUCCESS);
    }
}
