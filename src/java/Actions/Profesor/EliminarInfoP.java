/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions.Profesor;

import DBMS.DBMS;
import Clases.Profesor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author admin
 */
public class EliminarInfoP extends org.apache.struts.action.Action {

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
        
        Profesor u = (Profesor) form;
        HttpSession session = request.getSession(true);

        //elimino informacion del profesor actual del sistema
        boolean elimino = DBMS.getInstance().resetInfoProfesor(u);
        u = DBMS.getInstance().obtenerInfoProfesor(u.getUsbid());
        //retorno a pagina de exito
        request.setAttribute("usbid", u.getUsbid());
        request.setAttribute("profesor", u);
        request.setAttribute("eliminacion",SUCCESS);
        return mapping.findForward(SUCCESS);
    }
}
