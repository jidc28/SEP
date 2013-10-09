/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions.Profesor;

import DBMS.DBMS;
import Forms.EliminarUserForm;
import Clases.Usuario;
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
public class EliminarProfesorAction extends org.apache.struts.action.Action {

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

        EliminarUserForm u = (EliminarUserForm) form;
        HttpSession session = request.getSession(true);

        ActionErrors error = new ActionErrors();


            //verifica un usuario en el sistema CAS
            boolean elimino = DBMS.getInstance().eliminarUsuario(u);
            
            ArrayList<Usuario> users = DBMS.getInstance().listarProfesores();

            if (elimino) {
                //retorno a pagina de exito
                session.setAttribute("usuarios", users);
                //si elimino usuario entonces
                return mapping.findForward(SUCCESS);
                
            } else {
                return mapping.findForward(FAILURE);
            }

        
    }
}

