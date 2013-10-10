/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions.Decanato;

import DBMS.DBMS;
import Clases.Decanato;
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
 * @author admin
 */
public class CambiarStatusDecanatoA extends org.apache.struts.action.Action {

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

        Decanato u = (Decanato) form;
        HttpSession session = request.getSession(true);

        ActionErrors error = new ActionErrors();


        //elimina usuario del sistema 
        boolean actualizo = DBMS.getInstance().actualizarStatusDecanato(u);
        
        //obtengo una lista de usuarios registrados
        ArrayList<Decanato> decan = DBMS.getInstance().listarDecanatos();

        //si existen usuarios registrados

        //retorno a pagina de exito
        session.setAttribute("decanatos", decan);
        return mapping.findForward(SUCCESS);
    }
}