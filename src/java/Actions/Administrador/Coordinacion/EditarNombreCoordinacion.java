package Actions.Administrador.Coordinacion;

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
public class EditarNombreCoordinacion extends org.apache.struts.action.Action {

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

        Coordinacion u = (Coordinacion) form;
        HttpSession session = request.getSession(true);
        String codigoDecan = (String) session.getAttribute("codigoDecanatoActual");

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        /* En caso de haber expirado la sesion se direcciona a la vista que
         * le indica al usuario que debe volver a iniciar sesion. */
        if (usuario == null) {
            return mapping.findForward(SESION_EXPIRADA);
        }

        String tipousuario = usuario.getTipousuario();

        session.removeAttribute("coordinaciones");

        ActionErrors error = new ActionErrors();

        Coordinacion c = DBMS.getInstance().obtenerNombreCoordinacion(u);
        ArrayList<Decanato> decanatos = DBMS.getInstance().listarDecanatos();
        ArrayList<Coordinacion> coords = 
                DBMS.getInstance().listarCoordinacionesAdscritas(codigoDecan, null);

        /* Envio del listado de coordinaciones a la vista correspondiente */
        request.setAttribute("decanatos", decanatos);
        request.setAttribute("codigo", c.getCodigo());
        request.setAttribute("nombre", c.getNombre());
        request.setAttribute("coordinaciones", coords);
        
        /* Si el tipo del usuario es administrador o decanato se le autoriza
         * la realizacion de esta operacion, en caso contrario se direcciona
         * a la vista que le notifica que no esta autorizado para realizar
         * dicha operacion */
        if (tipousuario.equals("administrador") || tipousuario.equals("decanato")) {
            return mapping.findForward(tipousuario);
        } else {
            return mapping.findForward(FAILURE);
        }
    }
}