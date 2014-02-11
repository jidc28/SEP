/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions.Evaluaciones;

import Clases.Coordinacion;
import Clases.dicta;
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
public class ListarEvaluacionesPendientes extends org.apache.struts.action.Action {
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
        String id_coordinacion = (String) session.getAttribute("usbid");
        
        ArrayList<dicta> evaluaciones_pendientes;
        evaluaciones_pendientes = DBMS.getInstance().listarEvaluacionesPendientes(id_coordinacion);
        
        if (evaluaciones_pendientes.isEmpty()){
            request.setAttribute("vacio",SUCCESS);
        }
        
        //retorno a pagina de exito
        request.setAttribute("evaluaciones_pendientes", evaluaciones_pendientes);
        return mapping.findForward(SUCCESS);
    }
}