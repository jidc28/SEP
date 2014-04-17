/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions.Decanato;

import Clases.*;
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
 * @author admin
 */
public class EliminarDecanato extends org.apache.struts.action.Action {

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

        /* Se obtienen los datos del decanato a eliminar*/
        Decanato decanato = (Decanato) form;
        
        /* Se elimina el decanato correspondiente. */
        boolean eliminado = DBMS.getInstance().eliminarDecanato(decanato);
        
        /* Se obtiene el listado de decanatos registrados en el sistema */
        ArrayList<Decanato> decanatos = DBMS.getInstance().listarDecanatos();

        session.setAttribute("decanatos", decanatos);
        return mapping.findForward(SUCCESS);
    }
}
