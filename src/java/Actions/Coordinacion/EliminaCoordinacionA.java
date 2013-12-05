/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions.Coordinacion;

import Clases.Coordinacion;
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
 * @author smaf
 */
public class EliminaCoordinacionA extends org.apache.struts.action.Action {
    
    private static final String FAILURE = "failure";
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

        Coordinacion u = (Coordinacion) form;
        HttpSession session = request.getSession(true);

            boolean eliminado = DBMS.getInstance().eliminarCoordinacion(u);

            if (!eliminado) {
                request.setAttribute("noEliminado",FAILURE);
                return mapping.findForward(SUCCESS);
            } else {
                request.setAttribute("eliminado",SUCCESS);

                ArrayList<Coordinacion> coords = DBMS.getInstance().listarCoordinaciones();

                //si existen carreras registradas

                //retorno a pagina de exito
                session.setAttribute("coordinaciones", coords);
                return mapping.findForward(SUCCESS);
            }
        // }
    }
}
