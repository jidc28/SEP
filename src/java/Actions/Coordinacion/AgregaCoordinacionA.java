/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions.Coordinacion;

import Clases.Decanato;
import Clases.Usuario;
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
public class AgregaCoordinacionA extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String FAILURE = "failure";
    private static final String SESION_EXPIRADA = "sesion_expirada";

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
        ArrayList<Decanato> decanatos = DBMS.getInstance().listarDecanatos();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        /* En caso de haber expirado la sesion se direcciona a la vista que
         * le indica al usuario que debe volver a iniciar sesion. */
        if (usuario == null) {
            return mapping.findForward(SESION_EXPIRADA);
        }
        
        String tipousuario = usuario.getTipousuario();

        /* Dependiendo del tipo de usuario se listan o no los decanatos
         * Administrador: debe asignar a un decanato la coordinacio que
         *                va a agregar al sistema
         * Decanato: se asigna a si mismo la coordinacion que va a agregar
         *           al sistema*/
        if (tipousuario.equals("administrador")) {
            request.setAttribute("decanatos", decanatos);
        }
        if (tipousuario.equals("administrador") || tipousuario.equals("decanato")) {
            return mapping.findForward(tipousuario);
        } else {
            /* En caso que el usuario no sea ni administrador ni decanato se
             * direcciona a la pagina que indica que no se esta autorizado
             * para realizar esta operacion */
            return mapping.findForward(FAILURE);
        }
    }
}
